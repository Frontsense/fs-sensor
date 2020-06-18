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

    public List<Sensor> testReadings() {
        MongoClient client = connectDB();
        MongoDatabase db = client.getDatabase(DBName);
        MongoCollection<Document> sensorCollection = db.getCollection(DBCollection);

        List<Sensor> result = new ArrayList<>();

        for(Document curr: sensorCollection.find()) {
            Sensor currSensor = new Sensor(curr.getString("sensorType"),
                                    curr.getInteger("userId"),
                                    curr.getString("readings"),
                                    curr.getDouble("lang"),
                                    curr.getDouble("lat"),
                                    new Date(((org.bson.BsonTimestamp)curr.get("timestamp")).getValue()),
                                    curr.getInteger("taskId")
            );
            //BSONTimestamp ---> Date: new Date(((org.bson.BsonTimestamp)curr.get("timestamp")).getValue())
            result.add(currSensor);
        }

        return result;
    }

    public void insertReadings(JSONObject sensorReadings) {
        MongoClient client = connectDB();
        MongoDatabase db = client.getDatabase(DBName);
        MongoCollection<Document> sensorCollection = db.getCollection(DBCollection);

        Document newReadings = Document.parse(sensorReadings.toString());
        long timestamp = (Long) newReadings.get("timestamp");
        newReadings.remove("timestamp");
        newReadings.append("timestamp", new BsonTimestamp(timestamp));
        sensorCollection.insertOne(newReadings);
    }
}
