package mapper;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Component;

import com.mysql.cj.util.StringUtils;

@Component
public class DateMapper {

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
