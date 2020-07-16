package com.mag.frontsense.models;

import com.mongodb.MongoClientURI;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.BsonTimestamp;
import org.bson.Document;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class MongoSensor {

    private final String DBUser         = "root";
    private final String DBPassword     = "jXi5B86rnboc2SQC";
    private final String DBName         = "fs-sensor";
    private final String DBCollection   = "sensor";

    private MongoClient connectDB() {
        MongoClientURI uri = new MongoClientURI("mongodb+srv://"+ DBUser +":"+ DBPassword +"@cluster0-9m5aj.mongodb.net/"+ DBName +"?retryWrites=true&w=majority");

        return new MongoClient(uri);
    }

    public List<Sensor> allReadings() {
        MongoClient client = connectDB();
        MongoDatabase db = client.getDatabase(DBName);
        MongoCollection<Document> sensorCollection = db.getCollection(DBCollection);

        List<Sensor> result = new ArrayList<>();

        for(Document curr: sensorCollection.find()) {
            Sensor currSensor = new Sensor(curr.getString("sensorType"),
                                    curr.getInteger("userId"),
                                    curr.getString("readings"),
                                    curr.getDouble("lng"),
                                    curr.getDouble("lat"),
                                    new Date(((org.bson.BsonTimestamp)curr.get("timestamp")).getValue()),
                                    curr.getInteger("taskId")
            );
            //BSONTimestamp ---> Date: new Date(((org.bson.BsonTimestamp)curr.get("timestamp")).getValue())
            result.add(currSensor);
        }

        return result;
    }

    public JSONObject insertReadings(JSONObject sensorReadings) {
        MongoClient client = connectDB();
        MongoDatabase db = client.getDatabase(DBName);
        MongoCollection<Document> sensorCollection = db.getCollection(DBCollection);

        JSONObject response = new JSONObject();

        Document newReadings = Document.parse(sensorReadings.toString());

        for(Document row : newReadings.getList("sensorReadings", Document.class)) {
            Document readings = new Document();

            readings.put("sensorType", newReadings.getString("sensorType"));
            readings.put("userId", newReadings.getInteger("userId"));
            readings.put("taskId", newReadings.getInteger("taskId"));

            readings.put("readings", row.getString("readings"));
            readings.put("lat", row.getDouble("lat"));
            readings.put("lng", row.getDouble("lng"));

            long timestamp = (Long) row.get("timestamp");
            readings.put("timestamp", new BsonTimestamp(timestamp));

            try {
                sensorCollection.insertOne(readings);
            } catch (Exception e) {
                response.put("error", "Error inserting readings.");
                return response;
            }
        }

        response.put("success", "Readings inserted successfully.");
        return response;
    }
}
