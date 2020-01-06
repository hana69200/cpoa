/**
 * Copyright (c) 2017, MindFusion LLC - Bulgaria.
 */

package zones;

import com.mindfusion.scheduling.model.*;


/**
 * Our custom 'zoned' appointment.
 */
public class ZoneEvent extends Appointment
{
	/**
	 * Always define parameterless constructor for custom items.
	 */
	public ZoneEvent()
	{
		_zoneType = false;
	}

	/**
	 * <code>Appointment.Clone</code> override.
	 */
	public ZoneEvent clone()
	{
		ZoneEvent clone = new ZoneEvent();

		clone.setAllDayEvent(this.getAllDayEvent());
		clone.setAllowChangeEnd(this.getAllowChangeEnd());
		clone.setAllowChangeStart(this.getAllowChangeStart());
		clone.setAllowMove(this.getAllowMove());
		clone.setDescriptionText(this.getDescriptionText());
		clone.setEndTime(this.getEndTime());
		clone.setHeaderText(this.getHeaderText());
		clone.setLocation(this.getLocation());
		clone.setLocked(this.getLocked());
		clone.setPointedSelectedStyle(this.getPointedSelectedStyle().cloneShallow());
		clone.setPointedStyle(this.getPointedStyle().cloneShallow());
		clone.setPriority(this.getPriority());
		clone.setReminder(this.getReminder());
		clone.setSelectedStyle(this.getSelectedStyle().cloneShallow());
		clone.setStartTime(this.getStartTime());
		clone.setStyle(this.getStyle().cloneShallow());
		clone.setTag(this.getTag());
		clone.setTask(this.getTask());
		clone.setVisible(this.getVisible());

		for (Resource resource : this.getResources())
			clone.getResources().add(resource);

		for (Contact contact : this.getContacts())
			clone.getContacts().add(contact);

		clone.setZoneType(this._zoneType);

		return clone;
	}


	/**
	 * Gets the zone this item is aligned to.
	 */
	public boolean getZoneType()
	{
		return _zoneType;
	}
	
	/**
	 * Sets the zone this item is aligned to.
	 */
	public void setZoneType(boolean value)
	{ 
		_zoneType = value; 
	}
	

	private boolean _zoneType;
}