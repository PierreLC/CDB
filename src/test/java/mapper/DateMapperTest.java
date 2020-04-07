package mapper;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.junit.Assert;
import org.junit.Test;

import mapper.DateMapper;

public class DateMapperTest {

	@Test
	public void convertToLDT_with_null_parameter() {
		Assert.assertNull(DateMapper.convertToLDT(null));
	}
	
	@Test
	public void convertToLDT_with_today_return_today_with_hours_and_minutes() {
		Assert.assertEquals(LocalDateTime.now()
				.withHour(0).withMinute(0).withSecond(0).withNano(0),
				DateMapper.convertToLDT(LocalDate.now().toString()));
	}
}
