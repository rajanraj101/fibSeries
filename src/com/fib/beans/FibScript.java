package com.fib.beans;

import java.util.List;


public class FibScript {
	
	String script_name;
	List<TriggerInfo> arrTiggerInfo; 
	
	public String getScript_name() {
		return script_name;
	}
	public void setScript_name(String script_name) {
		this.script_name = script_name;
	}
	public List<TriggerInfo>  getArrTiggerInfo() {
		return arrTiggerInfo;
	}
	public void setArrTiggerInfo(List<TriggerInfo>  arrTiggerInfo) {
		this.arrTiggerInfo = arrTiggerInfo;
	}
	@Override
	public String toString() {
		return "{\"script_name\":\"" + script_name + "\", \"tigger_info\":" + arrTiggerInfo + "}";
	} 
	
	
	
}
