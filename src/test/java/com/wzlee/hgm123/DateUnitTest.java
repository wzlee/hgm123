package com.wzlee.hgm123;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

public class DateUnitTest {

	@Test
	public void test() {
		fail("Not yet implemented");
	}
	
	@Test
	public void DateTest() throws ParseException{
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat( "M月/d日/H点-m");
		Date date2 = sdf.parse("4月/22日/22点-12");
		System.out.println(sdf.format(date2)+(date2.getMinutes() <= 30?"开放":"半开放"));
	}
}
