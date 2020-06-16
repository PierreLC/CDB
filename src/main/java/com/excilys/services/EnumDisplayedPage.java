package com.excilys.services;

public enum EnumDisplayedPage {

	COMPUTER_NAME, INTRODUCED, DISCONTINUED, COMPANY, DEFAULT;
	
	public static EnumDisplayedPage displayedPage(String columnName) {
		switch (columnName) {
		
		case "computerName":
			return COMPUTER_NAME;
		case "introduced":
			return INTRODUCED;
		case "discontinued":
			return DISCONTINUED;
		case "company":
			return COMPANY;
		default : 
			return DEFAULT;
		}
	}
}
