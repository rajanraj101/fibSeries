package com.fib.beans;

import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

import org.bson.types.ObjectId; 

import com.fib.util.RangeComparator;

public class FibSeriesMaster {
	 
	private ObjectId id;
	private double diff; // note: Diff is in percent
	private Set<Range> rangeSet; 
	
	private static FibSeriesMaster singletonFibMater;
	
	private FibSeriesMaster() {
		rangeSet = new TreeSet<Range>(new RangeComparator());
	}
	
	public static FibSeriesMaster getInstance()
	{
		if(singletonFibMater != null)
			return singletonFibMater;
		else
			return new FibSeriesMaster();
	}

	public ObjectId getId() {
		return id;
	}

	public void setId(ObjectId id) {
		this.id = id;
	}

	public double getDiff() {
		return diff;
	}

	public void setDiff(double diff) {
		this.diff = diff;
	}

	public Set<Range> getRangeSet() {
		return rangeSet;
	}

	public void setRangeSet(Set<Range> rangeSet) {
		this.rangeSet = rangeSet;
	}

	public StringBuffer validateRange()
	{
		StringBuffer err = new StringBuffer();
		
		Iterator<Range> itr =rangeSet.iterator();
		
		while(itr.hasNext())
		{
			Range r = itr.next();
			
			if(r.getStart() > r.getEnd())
				err.append(", start range ").append(r.getStart()).append(" should less than end range ").append(r.getEnd()).append(" ");
		}
		
		return err;
	}

	@Override
	public String toString() {
		return "{\"id\":\"" + id + "\", \"diff\":" + diff + ", \"range\":" + rangeSet + "}";
	}
	
	
}
