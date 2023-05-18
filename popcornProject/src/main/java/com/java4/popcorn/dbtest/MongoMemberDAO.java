package com.java4.popcorn.dbtest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Projections;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

@Component
public class MongoMemberDAO {
    String url = "mongodb://localhost:27017";
    String dbName = "member";
    public static void main(String[] args) {
        MongoMemberDAO dao = new MongoMemberDAO();
        Member member = dao.selectOne("mrsGwanggyo");
        System.out.println(member);
        //member.getMovie_favorites().forEach(System.out::println);
    }
    public Member selectOne(String id) {
        try(MongoClient mongoClient = MongoClients.create(url)) {
            MongoDatabase db = mongoClient.getDatabase(dbName);
            MongoCollection<Document> collection = db.getCollection("member");
            Document doc = collection.find(Filters.eq("id", id))
                    .projection(Projections.excludeId())
                    .first();
            //System.out.println(doc.toJson());
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(doc.toJson(), Member.class);
        }catch (Exception e){
            System.out.println("No such member");
            return null;
        }
    }

}
