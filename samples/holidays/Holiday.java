/**
 * Copyright (c) 2017, MindFusion LLC - Bulgaria.
 */

package holidays;

import com.mindfusion.common.*;


/**
 * Represents a holiday.
 *
 */
public class Holiday
{
	/**
	 * Initializes a new instance of the {@link Holiday} class.
	 */
	public Holiday()
	{
	}

	/**
	 * Initializes a new instance of the {@link Holiday} class with the
	 * specified start and end date and title.
	 * @param start The start date of the holiday.
	 * @param end The end date of the holiday.
	 * @param title The title of the holiday.
	 */
	public Holiday(DateTime start, DateTime end, String title)
	{
		this.date = start;
		this.endDate = end;
		this.title = title;
	}

	/**
	 * Initializes a new instance of the {@link Holiday} class with the
	 * specified start and end date and title.
	 * @param start The start date of the holiday.
	 * @param end The end date of the holiday.
	 * @param title The title of the holiday.
	 * @param isDayOff A value indicating whether the holiday is a rest day.
	 */
	public Holiday(DateTime start, DateTime end, String title, boolean isDayOff)
	{
		this.date = start;
		this.endDate = end;
		this.title = title;
		this.isDayOff = isDayOff;
	}

	/**
	 * Gets the start date of the holiday.
	 */
	public DateTime getDate()
	{
		return date;
	}

	/**
	 * Sets the start date of the holiday.
	 */
	public void setDate(DateTime value)
	{
		date = value;
	}

	/**
	 * Gets the end date of the holiday.
	 */
	public DateTime getEndDate()
	{
		return endDate;
	}

	/**
	 * Sets the end date of the holiday.
	 */
	public void setEndDate(DateTime value)
	{
		endDate = value;
	}

	/**
	 * Gets the title of the holiday.
	 */
	public String getTitle()
	{
		return title;
	}

	/**
	 * Sets the title of the holiday.
	 */
	public void setTitle(String value)
	{
		title = value;
	}

	/**
	 * Gets a value indicating whether the holiday is a rest day.
	 */
	public boolean getIsDayOff()
	{
		return isDayOff;
	}

	/**
	 * Sets a value indicating whether the holiday is a rest day.
	 */
	public void setIsDayOff(boolean value)
	{
		isDayOff = value;
	}



	private DateTime date;
	private DateTime endDate;
	private String title;
	private boolean isDayOff;
}