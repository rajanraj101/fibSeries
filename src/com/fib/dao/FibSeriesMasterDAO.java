package com.fib.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;

import org.bson.BsonDocument;
import org.bson.BsonDouble;
import org.bson.Document;
import org.bson.types.ObjectId;

import com.fib.beans.FibConstants;
import com.fib.beans.FibSeriesMaster;
import com.fib.beans.Range;
import com.fib.db.DBConnect;
import com.fib.util.RangeComparator;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;

public class FibSeriesMasterDAO {

	public FibSeriesMaster parseFromDocument(Document doc)
	{
		ArrayList<Document> docRanges = (ArrayList<Document>) doc.get("range");
		TreeSet<Range> rangeSet = new  TreeSet<Range>(new RangeComparator());
		
		for(Document d : docRanges) 
		{
			Range r = new Range();
			r.setStart( d.getDouble("start"));
			r.setEnd( d.getDouble("end"));
			
			rangeSet.add(r);
		}
		
		FibSeriesMaster fibSeries = FibSeriesMaster.getInstance();
		fibSeries.setId(doc.getObjectId("_id"));
		fibSeries.setDiff( doc.getDouble("diff"));
		fibSeries.setRangeSet(rangeSet);
		
		return fibSeries;
	}
	
	public Document parseToDocument(FibSeriesMaster fibSeries)
	{
		List<BsonDocument> arrList= new ArrayList<BsonDocument>();
		Iterator<Range> itr = fibSeries.getRangeSet().iterator();
		while(itr.hasNext())
		{
			Range r = itr.next();
			BsonDocument rangeDoc = new BsonDocument();
			rangeDoc.put("start", new BsonDouble( r.getStart() ));
			rangeDoc.put("end",  new BsonDouble(r.getEnd()));
			
			arrList.add(rangeDoc);
		}
		
		Document docFibSeries = new Document();
		docFibSeries.put("_id", new ObjectId());
		docFibSeries.put("diff", fibSeries.getDiff()); 
		docFibSeries.put("range", arrList); 
		
		System.out.println("@#@ docFibSeries : "+ docFibSeries);
		return docFibSeries;
	}
	
	public FibSeriesMaster getFibSeriesMaster() throws Exception
	{
		FibSeriesMaster fibSeries = null;
		try
		{
			MongoCollection<Document> colFibSeries = DBConnect.getDocumentCollection(FibConstants.documentFibSeriesMaster);	
			if(colFibSeries!=null)
			{
				FindIterable<Document> docItr = colFibSeries.find();
				Document doc = docItr.first();
				if(doc != null)
				{
					fibSeries = parseFromDocument(doc);
				}
			}
			//System.out.println("**** fibSeries : "+ fibSeries);
			
		}
		catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
		finally {
			// DBConnect.closeDatabase();
		}
		
		return fibSeries;
	}
	
	public void insertFibSeriesMaster(FibSeriesMaster fibSeries) throws Exception
	{
		try
		{
			// 1. Validation
			StringBuffer strErr = fibSeries.validateRange(); 
			if(strErr.length()>0)
			{
				throw new Exception(strErr.toString());
			}
			
			// 2. Get Collection: if exist then drop
			MongoCollection<Document> colFibSeries = DBConnect.getDocumentCollection(FibConstants.documentFibSeriesMaster);		
			if(colFibSeries != null)
				colFibSeries.drop();
			
			// 3. pojo to documents
			Document docFibSeries = parseToDocument(fibSeries);
						
			// 4. Insert 
			colFibSeries.insertOne(docFibSeries);
			
			
		}
		catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
		finally {
			// DBConnect.closeDatabase();
		}
	}
	
	public static void main(String args[]) throws Exception	{
	
		FibSeriesMasterDAO dao = new FibSeriesMasterDAO();
		FibSeriesMaster masObj = dao.getFibSeriesMaster();
		System.out.println(masObj);
	}
}
