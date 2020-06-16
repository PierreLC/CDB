package com.excilys.exceptions;

public enum LogSQL {

	DB_ACCESS_LOG("Connection to the database failed"),
	SQL_EXCEPTION_LOG("Error of syntax with the SQL request"),
	CONNECTION_LOG("Connection failed to start"),
	NULL_OBJECT_LOG("You've tried to manipulated a null object");
	
	private String log;
	
	LogSQL(String log){
		this.log = log;
	}
	
	public String getLog() {
		return this.log;
	}
}
