package com.db.test;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

import org.bson.types.ObjectId;

import com.fib.beans.FibScript;
import com.fib.beans.FibSeriesMaster;
import com.fib.beans.Range;
import com.fib.beans.TriggerInfo;
import com.fib.dao.FibScriptDAO;
import com.fib.dao.FibSeriesMasterDAO;
import com.fib.db.DBConnect;
import com.fib.util.RangeComparator;

public class DBTest {
	
	public static void main(String[] args) throws Exception {
		
		testInsertFibScript();
		//deleteFibSript();
		//testFindAllFibScript();
		
		/*FibSeriesMasterDAO fibSeriesDoa =new FibSeriesMasterDAO();
		fibSeriesDoa.getFibSeriesMaster();
		*/
	
	}
	
	public static void testFindFibScript() throws Exception {
		FibScriptDAO fibScriptDAO = new FibScriptDAO();
		FibScript fibScript =  fibScriptDAO.findByName("acc");
		
		System.out.println("$$$ fibScript : " + fibScript);
	}

	public static void deleteFibSript() throws Exception {
		
		FibScriptDAO fibScriptDAO = new FibScriptDAO();
		fibScriptDAO.delete("selcom");
	}
	
	public static void testFindAllFibScript() throws Exception {
		FibScriptDAO fibScriptDAO = new FibScriptDAO();
		List<FibScript> list =  fibScriptDAO.findAll();
		
		System.out.println("$$$ list : " + list);
	}

	public static void testInsertFibScript() throws Exception {
		
		ArrayList<TriggerInfo> arrTriggerInfo = new ArrayList<TriggerInfo>();
		arrTriggerInfo.add(new TriggerInfo( "monthly",1774.65,365.0) );
		arrTriggerInfo.add(new TriggerInfo( "weekly",1776.40,1174.0) );
		
		
		FibScript fibScript = new FibScript();
		fibScript.setScript_name("rcom");
		fibScript.setArrTiggerInfo(arrTriggerInfo);

		FibScriptDAO fibScriptDAO = new FibScriptDAO();
		fibScriptDAO.insertFibScript(fibScript);
		
	}
	
	
	public static void testInsertFibSeriesMaster() throws Exception {

		DBConnect.getDatabase();
		
		FibSeriesMasterDAO fibSeriesDoa =new FibSeriesMasterDAO();
		
		TreeSet<Range> range = new TreeSet<Range>(new RangeComparator());
		range.add(new Range(0.9,23.5));
		range.add(new Range(23.6,38.1));
		range.add(new Range(38.2,50.1));
		range.add(new Range(50.0,61.7));
		range.add(new Range(61.8,100.0));
		range.add(new Range(100.1,138.1));
		range.add(new Range(138.2,168.1));
		range.add(new Range(168.2,200));

		FibSeriesMaster fibSerMas = FibSeriesMaster.getInstance();
		fibSerMas.setId(new ObjectId());
		fibSerMas.setDiff(2);
		fibSerMas.setRangeSet(range);
		
		fibSeriesDoa.insertFibSeriesMaster(fibSerMas);
		
		
		fibSeriesDoa.getFibSeriesMaster();
		
		DBConnect.closeDatabase();
	}

}
