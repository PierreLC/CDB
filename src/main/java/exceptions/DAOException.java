package exceptions;

import org.apache.log4j.PropertyConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DAOException extends RuntimeException{
	
	public static final Logger logConsole = LoggerFactory.getLogger(Logger.class);
	public static final Logger logFile = LoggerFactory.getLogger(Logger.class);
	
	public static void displayError (String perso) {
//		PropertyConfigurator.configure(DAOException.class.getClassLoader().getResource("log4jConsole.properties"));
//		logConsole.error(perso);
//		PropertyConfigurator.configure(DAOException.class.getClassLoader().getResource("log4jFile"));
//		logFile.error(perso);
	}
	
	public static void displayInfo (String perso) {
//		PropertyConfigurator.configure(DAOException.class.getClassLoader().getResource("log4jConsole.properties"));
//		logConsole.error(perso);
//		PropertyConfigurator.configure(DAOException.class.getClassLoader().getResource("log4jFile"));
//		logFile.error(perso);
	}
	
	private static final long serialVersionUID = 9184234891416097610L;
	

}
