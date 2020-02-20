package exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DaoException extends RuntimeException{
	
	public static final Logger logCons = LoggerFactory.getLogger(Logger.class);
	public static final Logger logFile = LoggerFactory.getLogger(Logger.class);
	
	public static void displayError (String perso) {
		PropertyConfigurator.configure(Logging.class.getClassLoader().getResource(log4jConsole));
		logCons.error(perso);
		PropertyConfigurator.configure(Logging.class.getClassLoader().getResource(log4JFile));
		logFile.error(perso);
	}
	
	public static void displayInfo (String perso) {
		PropertyConfigurator.configure(Logging.class.getClassLoader().getResource(log4jConsole));
		logCons.error(perso);
		PropertyConfigurator.configure(Logging.class.getClassLoader().getResource(log4JFile));
		logFile.error(perso);
	}

	public DaoException(String message) {
		super(message);
	}
	
	private static final long serialVersionUID = 9184234891416097610L;
	

}
