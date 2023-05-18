package com.java4.popcorn.data.MongoMember;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Projections;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.InsertOneResult;
import com.mongodb.client.result.UpdateResult;
import org.bson.Document;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MongoMemberDAO {
    String url = "mongodb://localhost:27017";
    String dbName = "popcorn";
    public MongoMemberVO selectOne(String id) {
        try(MongoClient mongoClient = MongoClients.create(url)) {
            MongoDatabase db = mongoClient.getDatabase(dbName);
            MongoCollection<Document> collection = db.getCollection("member");
            Document doc = collection.find(Filters.eq("id", id))
                    .projection(Projections.excludeId())
                    .first();
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(doc.toJson(), MongoMemberVO.class);
        }catch (Exception e){
            System.out.println("No such member");
            return null;
        }
    }

    public InsertOneResult insertOne(String line_id, List<String> movie_list, List<String> theater_list) {
        try(MongoClient mongoClient = MongoClients.create(url)) {
            MongoDatabase db = mongoClient.getDatabase(dbName);
            MongoCollection<Document> collection = db.getCollection("member");

            Document document = new Document("line_id", line_id)
                    .append("movie_favorites", movie_list)
                    .append("theater_favorites", theater_list);

            return collection.insertOne(document);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public InsertOneResult insertOne(String line_id) {
        return insertOne(line_id, new ArrayList<>(), new ArrayList<>());
    }

    public DeleteResult deleteOne(String userId) {
        Document filter = new Document("line_id", userId);
        try(MongoClient mongoClient = MongoClients.create(url)) {
            MongoDatabase db = mongoClient.getDatabase(dbName);
            MongoCollection<Document> collection = db.getCollection("member");
            return collection.deleteOne(filter);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /*
    나중에 movie_id로 수정해야합니다!!!
     */
    @Deprecated
    public UpdateResult pushMovie(String userId, String movie_title) {
        Document filter = new Document("line_id", userId);
        Document update = new Document("$push", new Document("movie_favorites", movie_title));
        return getUpdateResult(filter, update);
    }

    @Deprecated
    public UpdateResult pullMovie(String userId, String movie_title) {
        Document filter = new Document("line_id", userId);
        Document update = new Document("$pull", new Document("movie_favorites", movie_title));
        return getUpdateResult(filter, update);
    }

    @Deprecated
    public UpdateResult pushTheater(String userId, String theater_id) {
        Document filter = new Document("line_id", userId);
        Document update = new Document("$push", new Document("theater_favorites", theater_id));
        return getUpdateResult(filter, update);
    }

    @Deprecated
    public UpdateResult pullTheater(String userId, String theater_id) {
        Document filter = new Document("line_id", userId);
        Document update = new Document("$pull", new Document("theater_favorites", theater_id));
        return getUpdateResult(filter, update);
    }

    private UpdateResult getUpdateResult(Document filter, Document update) {
        try(MongoClient mongoClient = MongoClients.create(url)) {
            MongoDatabase db = mongoClient.getDatabase(dbName);
            MongoCollection<Document> collection = db.getCollection("member");
            return collection.updateOne(filter, update);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
