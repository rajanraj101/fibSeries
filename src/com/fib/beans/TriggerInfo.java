package com.fib.beans;

public class TriggerInfo {

	String type;
	double low;
	double high;
	
	public TriggerInfo() {}
	
	public TriggerInfo(String type, double low, double high) {
		super();
		this.type = type;
		this.low = low;
		this.high = high;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public double getLow() {
		return low;
	}
	public void setLow(double low) {
		this.low = low;
	}
	public double getHigh() {
		return high;
	}
	public void setHigh(double high) {
		this.high = high;
	}

	@Override
	public String toString() {
		return "{\"type\":\"" + type + "\", \"low\":" + low + ", \"high\":" + high + "}";
	}
	
	

}

