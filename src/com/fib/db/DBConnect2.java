package com.fib.db;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;

import java.io.IOException;

import org.bson.Document;

public class DBConnect2 {
	
	private static MongoClient mongoClient;
	private static MongoDatabase  mongoDatabase;

	public static MongoClient getMongoClient() {
		
		if( mongoClient == null)
			mongoClient = new MongoClient( "localhost" , 27017 );
		
		return mongoClient;
	}

	public static MongoDatabase getDatabase() throws IOException
	{
		if( mongoClient == null)
			mongoClient = getMongoClient();
		
		if(mongoDatabase == null)
			mongoDatabase =  mongoClient.getDatabase("EliteDemo");
			
		return mongoDatabase;
	}
	
	public static void closeDatabase()
	{	
		if(mongoClient !=null)
			mongoClient.close();
	}
	
	public static MongoCollection<Document> getDocumentCollection(String name) 
		throws Exception
	{	
		if(mongoDatabase == null)
			mongoDatabase = getDatabase();
		
		return mongoDatabase.getCollection(name);
	}	
	
	public static void main(String args[]) throws Exception {
		
		/*MongoCollection<Document> salesOrder =  (MongoCollection<Document>) getDocumentCollection("SalesOrder");
		System.out.println("salesOrder count :"+ salesOrder.count());
		*/
		
		mongoDatabase = getDatabase();
		
		MongoIterable<String> colls = mongoDatabase.listCollectionNames();

		for (String cName : colls) {
			MongoCollection<Document> col =  (MongoCollection<Document>) getDocumentCollection(cName);
			System.out.println(cName + "\t"+ col.count());
			
		}
		
	}
}