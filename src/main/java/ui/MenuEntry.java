package ui;

public enum MenuEntry {

	DISPLAYCOMPUTERS, DELETECOMPUTER, UPDATECOMPUTER, ADDCOMPUTER, DISPLAYCOMPANIES, DISPLAYDETAILS, EXIT;

	public static MenuEntry entry(int choice) {
		switch (choice) {

		case 1:
			return DISPLAYCOMPUTERS;
		case 2:
			return DELETECOMPUTER;
		case 3:
			return UPDATECOMPUTER;
		case 4:
			return ADDCOMPUTER;
		case 5:
			return DISPLAYCOMPANIES;
		case 6:
			return DISPLAYDETAILS;
		default:
			return EXIT;

		}

	}

}
