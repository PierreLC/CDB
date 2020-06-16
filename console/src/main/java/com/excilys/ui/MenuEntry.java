package com.excilys.ui;

public enum MenuEntry {

	LISTCOMPUTERS, PAGECOMPUTERS, DELETECOMPUTER, UPDATECOMPUTER, ADDCOMPUTER, DISPLAYCOMPANIES, DISPLAYDETAILS, EXIT;

	public static MenuEntry entry(int choice) {
		switch (choice) {

		case 1:
			return LISTCOMPUTERS;
		case 2:
			return PAGECOMPUTERS;
		case 3:
			return DELETECOMPUTER;
		case 4:
			return UPDATECOMPUTER;
		case 5:
			return ADDCOMPUTER;
		case 6:
			return DISPLAYCOMPANIES;
		case 7:
			return DISPLAYDETAILS;
		case 8: 
			return EXIT;
		default:
			return EXIT;

		}

	}

}
