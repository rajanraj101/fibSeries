package com.fib.db;

import com.fib.beans.FibConstants;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import java.io.IOException;

import org.bson.Document;

public class DBConnect {
	
	private static MongoClient mongoClient;
	private static MongoDatabase  mongoDatabase;

	public static MongoClient getMongoClient() {
		
		if( mongoClient == null)
			mongoClient = new MongoClient( FibConstants.host , FibConstants.port );
		
		return mongoClient;
	}

	public static MongoDatabase getDatabase() throws IOException
	{
		if( mongoClient == null)
			mongoClient = getMongoClient();
		
		if(mongoDatabase == null)
			mongoDatabase =  mongoClient.getDatabase(FibConstants.databaseName);
			
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
}