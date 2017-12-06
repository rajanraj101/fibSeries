package com.fib.dao;

import org.bson.BsonDocument;
import org.bson.BsonDouble;
import org.bson.BsonString;
import org.bson.Document;

import com.fib.beans.FibConstants;
import com.fib.beans.FibScript;
import com.fib.beans.TriggerInfo;
import com.fib.db.DBConnect;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.result.DeleteResult;

import static com.mongodb.client.model.Filters.eq;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class FibScriptDAO {
	
	MongoCollection<Document> colFibScript = null;
	
	public Document parseToDocument(FibScript fibScript)
	{
		
		List<BsonDocument> arrList= new ArrayList<BsonDocument>();
		Iterator<TriggerInfo> itr = fibScript.getArrTiggerInfo().iterator();
		
		while(itr.hasNext())
		{
			TriggerInfo t = itr.next();
			BsonDocument tdoc = new BsonDocument();
			tdoc.put("type", new BsonString( t.getType() ));
			tdoc.put("low", new BsonDouble( t.getLow() ));
			tdoc.put("high",  new BsonDouble(t.getHigh()));
			
			arrList.add(tdoc);
		}
		
		Document doc = new Document();
		doc.put("script_name", fibScript.getScript_name()); 
		doc.put("tigger_info", arrList);
		return doc;
	}
	
	public FibScript parseFromDocument(Document doc)
	{  
		ArrayList<Document> docArrTrrigerInfo = (ArrayList<Document>) doc.get("tigger_info");
		List<TriggerInfo> arrTiggerInfo = new ArrayList<TriggerInfo>();
		for(Document d : docArrTrrigerInfo )
		{
			TriggerInfo t = new TriggerInfo();
			t.setType(d.getString("type"));
			t.setLow(d.getDouble("low"));
			t.setHigh(d.getDouble("high"));
			arrTiggerInfo.add(t);
		}
		
		FibScript fibScript = new FibScript();
		fibScript.setScript_name(doc.getString("script_name"));
		fibScript.setArrTiggerInfo(arrTiggerInfo);
		
		return fibScript;
	}
	
	
	public FibScript findByName(String name) throws Exception
	{	
		FibScript fibScript = null;
		try
		{
			colFibScript = DBConnect.getDocumentCollection(FibConstants.documentFibScript);
			Document doc = colFibScript.find(eq("script_name", name)).first();
			if(doc != null)
			{
				if(doc.getString("script_name") != null 
						&&  !doc.getString("script_name").equals("null"))
				{
					fibScript = parseFromDocument(doc);
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			throw e;
		}
		finally {
			// DBConnect.closeDatabase();
		}
		return fibScript;
	}
	
	public List<FibScript> findAll()  throws Exception
	{
		List<FibScript> fibScriptList = new ArrayList<FibScript>();
		
		try {
		
			colFibScript = DBConnect.getDocumentCollection(FibConstants.documentFibScript);
			
			FindIterable<Document> docItr = colFibScript.find();
			
			for(Document doc : docItr)
			{
				if(doc != null)
				{
					if(doc.getString("script_name") != null 
							&&  !doc.getString("script_name").equals("null"))
					{
						fibScriptList.add(parseFromDocument(doc));
					}
				}
			}
			//System.out.println(fibScriptList);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			throw e;
		}
		finally {
			// DBConnect.closeDatabase();
		}	
			
		return fibScriptList;
	}
	
	public void delete(String script_name)throws Exception
	{	
		try
		{
			colFibScript = DBConnect.getDocumentCollection(FibConstants.documentFibScript);
			DeleteResult res = colFibScript.deleteOne(new BsonDocument("script_name", new BsonString(script_name)));
			System.out.println("delete count : "+ res.getDeletedCount());
		}
		catch(Exception e)
		{
			e.printStackTrace();
			throw e;
		}
		finally {
			// DBConnect.closeDatabase();
		}
	}
	
	public void insertFibScript(FibScript fibScript) throws Exception
	{	
		try
		{
			colFibScript = DBConnect.getDocumentCollection(FibConstants.documentFibScript);
			Document doc =  parseToDocument(fibScript);
			System.out.println(" fibScript : "+ fibScript);
			colFibScript.insertOne(doc);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			throw e;
		}
		finally {
			// DBConnect.closeDatabase();
		}
	}
}
