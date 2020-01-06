/**
 * Copyright (c) 2017, MindFusion LLC - Bulgaria.
 */

package recurrence;

import com.mindfusion.common.*;
import com.mindfusion.scheduling.model.*;


/**
 * An item subclass used to generate occurrences upon.
 */
public class RecurringDay extends Item
{
	public RecurringDay()
	{
		_startTime = DateTime.MinValue;
		_endTime = DateTime.MinValue;
	}

	@Override()
	public Item clone()
	{
		return null;
	}

	@Override()
	public boolean getAllDayEvent()
	{
		return false;
	}

	@Override()
	public void setAllDayEvent(boolean value)
	{
	}

	@Override()
	public ResourceList<Contact> getContacts()
	{
		return EmptyContacts;
	}

	@Override()
	public String getDescriptionText()
	{
		return "";
	}

	@Override()
	public void setDescriptionText(String value)
	{
	}

	@Override()
	public String getHeaderText()
	{
		return "";
	}

	@Override()
	public void setHeaderText(String value)
	{
	}

	@Override()
	public DateTime getStartTime()
	{
		return _startTime;
	}

	@Override()
	public void setStartTime(DateTime value)
	{
		_startTime = value;
	}

	@Override()
	public DateTime getEndTime()
	{
		return _endTime;
	}

	@Override()
	public void setEndTime(DateTime value)
	{
		_endTime = value;
	}

	@Override()
	public String getId()
	{
		return "";
	}

	@Override()
	public void setId(String value)
	{
	}

	@Override()
	public Location getLocation()
	{
		return null;
	}

	@Override()
	public void setLocation(Location value)
	{
	}

	@Override()
	public Task getTask()
	{
		return null;
	}

	@Override()
	public void setTask(Task value)
	{
	}

	@Override()
	public Reminder getReminder()
	{
		return null;
	}

	@Override()
	public void setReminder(Reminder value)
	{
	}

	@Override()
	public boolean getLocked()
	{
		return false;
	}

	@Override()
	public void setLocked(boolean value)
	{
	}

	@Override()
	public Style getStyle()
	{
		return null;
	}

	@Override()
	public void setStyle(Style value)
	{
	}

	@Override()
	public boolean getVisible()
	{
		return false;
	}

	@Override()
	public void setVisible(boolean value)
	{
	}

	@Override()
	public Object getTag()
	{
		return null;
	}

	@Override()
	public void setTag(Object value)
	{
	}

	@Override()
	public Style getSelectedStyle()
	{
		return null;
	}

	@Override()
	public void setSelectedStyle(Style value)
	{
	}

	@Override()
	public ResourceList<Resource> getResources()
	{
		return EmptyResources;
	}

	@Override()
	public boolean getAllowChangeStart()
	{
		return true;
	}

	@Override()
	public void setAllowChangeStart(boolean value)
	{
	}

	@Override()
	public boolean getAllowChangeEnd()
	{
		return true;
	}

	@Override()
	public void setAllowChangeEnd(boolean value)
	{
	}

	@Override()
	public boolean getAllowMove()
	{
		return true;
	}

	@Override()
	public void setAllowMove(boolean value)
	{
	}

	@Override()
	public Style getPointedStyle()
	{
		return null;
	}

	@Override()
	public void setPointedStyle(Style value)
	{
	}

	@Override()
	public Style getPointedSelectedStyle()
	{
		return null;
	}

	@Override()
	public void setPointedSelectedStyle(Style value)
	{
	}


	private static ResourceList<Contact> EmptyContacts = new ResourceList<Contact>();
	private static ResourceList<Resource> EmptyResources = new ResourceList<Resource>();

	private DateTime _startTime;
	private DateTime _endTime;
}
