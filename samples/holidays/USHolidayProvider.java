/**
 * Copyright (c) 2017, MindFusion LLC - Bulgaria.
 */

package holidays;

import java.util.*;

import com.mindfusion.common.*;


/**
 * Provides a list with the most common holidays in United States.
 */
public class USHolidayProvider
{
	/**
	 * Returns all holidays in the specified time interval.
	 * @param from The start of the time interval.
	 * @param to The end of the time interval.
	 * @return An array of {@link Holiday} objects representing the holidays
	 * in the interval of interest.
	 */
	public Holiday[] getHolidays(DateTime from, DateTime to)
	{
		ArrayList<Holiday> output = new ArrayList<Holiday>();

		// Halloween 
		addHoliday(output, from, to, 10, 31, "Halloween", 0, false);

		// Election Day
		addHoliday(output, from, to, 11, -1, "Election Day", 0, false, DayOfWeek.Monday, 1, false);

		// Veterans Day 			
		addHoliday(output, from, to, 11, 11, "Veterans Day", 0, true);

		// Thanksgiving Day 
		addHoliday(output, from, to, 11, -1, "Thanksgiving Day", 0, true, DayOfWeek.Thursday, 4, false);

		// Pearl Harbor Remembrance Day
		addHoliday(output, from, to, 12, 7, "Pearl Harbor Remembrance Day", 0, false);

		// Chanukah/Hanukkah
		addHoliday(output, from, to, 12, 2, "Chanukah/Hanukkah", 7, false);

		// Christmas Day
		addHoliday(output, from, to, 12, 25, "Christmas Day", 0, true);

		// Kwanzaa
		addHoliday(output, from, to, 12, 26, "Kwanzaa", 6, false);

		// New Year's Eve
		addHoliday(output, from, to, 12, 31, "New Year's Eve", 0, false);

		// New Year's Day
		addHoliday(output, from, to, 1, 1, "New Year's Day", 0, true);

		// College Football Championship Game
		addHoliday(output, from, to, 1, 10, "College Football Championship Game", 0, false);

		// Martin Luther King Day
		addHoliday(output, from, to, 1, -1, "Martin Luther King Day", 0, true, DayOfWeek.Monday, 2, false);

		// Belly Laugh Day
		addHoliday(output, from, to, 1, 24, "Belly Laugh Day", 0, false);

		// Groundhog Day
		addHoliday(output, from, to, 2, 2, "Groundhog Day", 0, false);

		// Super Bowl
		addHoliday(output, from, to, 2, 6, "Super Bowl", 0, false);

		// Lincoln's Birthday
		addHoliday(output, from, to, 2, 12, "Lincoln's Birthday", 0, false);

		// St. Valentines Day
		addHoliday(output, from, to, 2, 14, "St. Valentines Day", 0, false);

		// Presidents Day
		addHoliday(output, from, to, 2, -1, "Presidents Day", 0, true, DayOfWeek.Monday, 2, false);

		// Shrove Tuesday
		addHoliday(output, from, to, 3, -1, "Shrove Tuesday", 0, false, DayOfWeek.Tuesday, 1, false);

		// Mardi Gras Carnival in New Orleans
		addHoliday(output, from, to, 3, 8, "Mardi Gras Carnival in New Orleans", 0, false);

		// St. Patrick's Day
		addHoliday(output, from, to, 3, 17, "St. Patrick's Day", 0, false);

		// April Fool's Day
		addHoliday(output, from, to, 4, 1, "April Fool's Day", 0, false);

		// Tax Day
		addHoliday(output, from, to, 4, 15, "Tax Day", 0, false);

		// Good Friday
		for (int year = from.getYear(); year <= to.getYear(); year++)
		{
			DateTime goodFriday = easterSundayOf(year).addDays(-3);
			if (DateTime.op_GreaterThanOrEqual(goodFriday, from) &&
				DateTime.op_LessThanOrEqual(goodFriday, to))
				output.add(new Holiday(goodFriday, goodFriday, "Good Friday", false));
		}

		// Easter Sunday
		for (int year = from.getYear(); year <= to.getYear(); year++)
		{
			DateTime easter = easterSundayOf(year);
			if (DateTime.op_GreaterThanOrEqual(easter, from) &&
				DateTime.op_LessThanOrEqual(easter, to))
				output.add(new Holiday(easter, easter, "Easter", false));
		}

		// Administrative Professionals Day
		addHoliday(output, from, to, 4, 21, "Administrative Professionals Day", 0, false);

		// Earth Day
		addHoliday(output, from, to, 4, 22, "Earth Day", 0, false);

		// Cinco de Mayo
		addHoliday(output, from, to, 5, 5, "Cinco de Mayo", 0, false);

		// Mother's Day
		addHoliday(output, from, to, 5, -1, "Mother's Day", 0, false, DayOfWeek.Sunday, 1, false);

		// Armed Forces Day
		addHoliday(output, from, to, 5, 21, "Armed Forces Day", 0, false);

		// Memorial Day
		addHoliday(output, from, to, 5, -1, "Memorial Day", 0, true, DayOfWeek.Monday, 1, true);

		// Flag Day
		addHoliday(output, from, to, 6, 14, "Flag Day", 0, false);

		// Father's Day
		addHoliday(output, from, to, 6, -1, "Father's Day", 0, false, DayOfWeek.Sunday, 2, false);

		// Independence Day
		addHoliday(output, from, to, 7, 4, "Independence Day", 0, true);

		// Parents' Day
		addHoliday(output, from, to, 7, -1, "Parents' Day", 0, false, DayOfWeek.Sunday, 3, false);

		// Woodward Dream Cruise
		addHoliday(output, from, to, 8, 20, "Woodward Dream Cruise", 0, false);

		// Labor Day
		addHoliday(output, from, to, 9, -1, "Labor Day", 0, true, DayOfWeek.Monday, 0, false);

		// Patriot Day
		addHoliday(output, from, to, 9, 11, "Patriot Day", 0, false);

		// Grandparents' Day
		for (int i = from.getYear(); i <= to.getYear(); i++)
		{
			int coef = 0;
			for (int j = 0; j < 30; j++)
			{
				DateTime d = new DateTime(i, 9, j + 1);
				if (coef < 1)
				{
					if (d.getDayOfWeek() == DayOfWeek.Monday)
						coef += 1;
				}
				else if (coef == 1)
				{
					if (d.getDayOfWeek() == DayOfWeek.Sunday)
					{
						if (DateTime.op_GreaterThanOrEqual(d, from) &&
							DateTime.op_LessThanOrEqual(d, to))
						output.add(new Holiday(d, d, "Grandparents' Day", false));
					}
						break;
				}
			}
		}

		// Constitution Day
		addHoliday(output, from, to, 9, 16, "Constitution Day", 0, false);

		// Columbus Day
		addHoliday(output, from, to, 10, -1, "Columbus Day", 0, true, DayOfWeek.Monday, 1, false);

		// Boss's Day 
		addHoliday(output, from, to, 10, 16, "Boss's Day", 0, false);

		// Sweetest Day
		addHoliday(output, from, to, 10, -1, "Sweetest Day", 0, false, DayOfWeek.Saturday, 2, false);

		Holiday[] result = new Holiday[output.size()];
		return output.toArray(result);
	}

	/**
	 * Returns all holidays in the specified year.
	 * @param year The year.
	 * @return An array of {@link Holiday} objects representing the holidays in the year of interest.
	 */
	public Holiday[] getHolidays(int year)
	{
		year = Math.min(9998, Math.max(1, year));
		return getHolidays(new DateTime(year, 1, 1), new DateTime(year + 1, 1, 1));
	}


	private void addHoliday(List<Holiday> output, DateTime from, DateTime to,
		int month, int day, String holidayName, int range, boolean isDayOff)
	{
		addHoliday(output, from, to, month, day, holidayName, range,
			isDayOff, DayOfWeek.Monday, 0, false);
	}

	private void addHoliday(List<Holiday> output, DateTime from, DateTime to,
		int month, int day, String holidayName, int range, boolean isDayOff,
		DayOfWeek searchDay, int searchDayNumber, boolean reverse)
	{
		for (int i = from.getYear(); i <= to.getYear(); i++)
		{
			if (day == -1)
			{
				int coef = 1;
				int daysInMonth = DateTime.daysInMonth(i, month);
				for (int j = 1; j <= daysInMonth; j++)
				{
					DateTime d = new DateTime(i, month, j);
					if (reverse)
						d = new DateTime(i, month, (daysInMonth + 1) - j);
					if (coef < searchDayNumber)
					{
						if (d.getDayOfWeek() == searchDay)
							coef += 1;
					}
					else if (coef == searchDayNumber)
					{
						if (d.getDayOfWeek() == searchDay)							
						{
							if (DateTime.op_GreaterThanOrEqual(d, from) &&
								DateTime.op_LessThanOrEqual(d, to))
							output.add(new Holiday(d, d.addDays(range), holidayName, isDayOff));
							break;
						}
					}
				}
			}
			else
			{
				DateTime d = new DateTime(i, month, day);
				if (DateTime.op_GreaterThanOrEqual(d, from) &&
					DateTime.op_LessThanOrEqual(d, to))
					output.add(new Holiday(d, d.addDays(range), holidayName, isDayOff));
			}
		}
	}

	/**
	 * Finds the Easter Sunday of the specified year.
	 */
	private static DateTime easterSundayOf(int year)
	{
		int Y = year;
		int a = Y % 19;
		int b = Y / 100;
		int c = Y % 100;
		int d = b / 4;
		int e = b % 4;
		int f = (b + 8) / 25;
		int g = (b - f + 1) / 3;
		int h = (19 * a + b - d - g + 15) % 30;
		int i = c / 4;
		int k = c % 4;
		int L = (32 + 2 * e + 2 * i - h - k) % 7;
		int m = (a + 11 * h + 22 * L) / 451;
		int Month = (h + L - 7 * m + 114) / 31;
		int Day = ((h + L - 7 * m + 114) % 31) + 1;
		return new DateTime(year, Month, Day);
	}
}