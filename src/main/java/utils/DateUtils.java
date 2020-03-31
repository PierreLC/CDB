package utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.mysql.cj.util.StringUtils;

public class DateUtils {
	
	public static LocalDateTime convertToLDT(String date) {
		if (StringUtils.isNullOrEmpty(date)) {
			return null;
		}
		date = date + " 00:00:00";
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		LocalDateTime datetime = LocalDateTime.parse(date, formatter);
		return datetime;
	}
}
