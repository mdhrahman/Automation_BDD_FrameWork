package com.ui.qa.commons;

import io.restassured.response.Response;
import org.apache.log4j.Logger;
import org.testng.Assert;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class CommonMethods {

	public static Logger Log = Logger.getLogger(Logger.class.getName());

	public static void validateResponseSchema(Response response, String schemaLoc) {

		try {

			response.then().assertThat().body(matchesJsonSchemaInClasspath(schemaLoc));

		} catch (Exception e) {

			e.printStackTrace();
			Log.fatal("Exception in verifying schema of the response", e);
			Assert.assertNull(e);

		}
	}

	public static String getEpochSeconds() {
		Date date = new Date();
		String secs = String.valueOf(date.getTime() / 1000);
		return secs;
	}

	public static String getEpochMilliSeconds() {
		Date date = new Date();
		String millis = String.valueOf(date.getTime());
		return millis;
	}

	public static String getDigitsFromEpochSeconds(int n) {
		Date date = new Date();
		String secs = String.valueOf(date.getTime() / 1000);
		return secs.substring(secs.length() - n);
	}

	public static String getDigitsFromEpochMilliseconds(int n) {
		Date date = new Date();
		String millis = String.valueOf(date.getTime());
		return millis.substring(millis.length() - n);
	}

	public static String getCurrentTimeMySQL() {

		Date dt = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(dt);

	}

	public static String getTimeMidnightYesterday() {

		LocalDate tomorrow = LocalDate.now().minusDays(1);
		Date dt = Date.from(tomorrow.atStartOfDay(ZoneId.systemDefault()).toInstant());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(dt);

	}

	public static String getTimeMidnightinPast(int days) {

		LocalDate tomorrow = LocalDate.now().minusDays(days);
		Date dt = Date.from(tomorrow.atStartOfDay(ZoneId.systemDefault()).toInstant());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(dt);

	}

	public static String getTimeMidnight() {

		LocalDate tomorrow = LocalDate.now();
		Date dt = Date.from(tomorrow.atStartOfDay(ZoneId.systemDefault()).toInstant());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(dt);

	}

	public static String getCurrentDateOnly() {

		Date dt = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(dt);

	}

	public static String getDateInFormat(String format) {

		Date dt = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(dt);

	}

	public static String getFutureDateInFormat(String format, int days) {


		LocalDate tomorrow = LocalDate.now().plusDays(days);
		Date dt = Date.from(tomorrow.atStartOfDay(ZoneId.systemDefault()).toInstant());
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(dt);

	}

	public static String addDaysTo(String oldDate, String format, int days){

		String[] old = oldDate.split("/", 5);

		LocalDate tomorrow = LocalDate.of(Integer.parseInt(old[2]), Integer.parseInt(old[0]),Integer.parseInt(old[1])).plusDays(days);

		Date dt = Date.from(tomorrow.atStartOfDay(ZoneId.systemDefault()).toInstant());
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(dt);

	}

	public static String addMonthsTo(String oldDate, String format, int months){

		String[] old = oldDate.split("/", 5);

		LocalDate tomorrow = LocalDate.of(Integer.parseInt(old[2]), Integer.parseInt(old[0]),Integer.parseInt(old[1])).plusMonths(months);

		Date dt = Date.from(tomorrow.atStartOfDay(ZoneId.systemDefault()).toInstant());
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(dt);

	}

	public static String addYearsTo(String oldDate, String format, int years){

		String[] old = oldDate.split("/", 5);

		LocalDate tomorrow = LocalDate.of(Integer.parseInt(old[2]), Integer.parseInt(old[0]),Integer.parseInt(old[1])).plusYears(years);

		Date dt = Date.from(tomorrow.atStartOfDay(ZoneId.systemDefault()).toInstant());
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(dt);

	}

	public static String getSameDateInFutureYears(String format, int years) {

		LocalDate tom = LocalDate.now().plusYears(years);
		Date dt = Date.from(tom.atStartOfDay(ZoneId.systemDefault()).toInstant());
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(dt);

	}

	public static int getCurrentYear(){

		return Calendar.getInstance().get(Calendar.YEAR);
	}

	public static String getDateOnlyFor(Date dt) {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(dt);

	}

	public static String getCurrentTimeEST() {

		Date dt = new Date();

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		sdf.setTimeZone(TimeZone.getTimeZone("GMT-4:00"));

		return sdf.format(dt);

	}

	public static void CompareTwoString(String expectedValue, String actualValue, boolean testCase)  {
		if (testCase) {
			if (expectedValue.contains(actualValue)) {
				System.out.println("Actual Text '" + expectedValue + "' matches with Expected Text '" + actualValue + "'");

			} else {
				System.out.println("Actual Text '" + expectedValue + "' Doesn't matches with Expected Text '" + actualValue + "'");

			}
		}

	}


}