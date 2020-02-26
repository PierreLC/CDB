package utils;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.junit.Assert;
import org.junit.Test;

public class DateUtilsTest {

	@Test
	public void convertToLDT_with_null_parameter() {
		Assert.assertNull(DateUtils.convertToLDT(null));
	}
	
	@Test
	public void convertToLDT_with_today_return_today_with_hours_and_minutes() {
		Assert.assertEquals(LocalDateTime.now()
				.withHour(0).withMinute(0).withSecond(0).withNano(0),
				DateUtils.convertToLDT(LocalDate.now().toString()));
	}
}
