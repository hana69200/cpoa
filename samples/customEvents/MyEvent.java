/**
 * Copyright (c) 2017, MindFusion LLC - Bulgaria.
 */

package customEvents;

import org.w3c.dom.*;

import com.mindfusion.common.*;
import com.mindfusion.scheduling.model.*;


/**
 * This is our custom event. It has a single custom field.
 *
 */
public class MyEvent extends Appointment
{
	/**
	 * Custom events are required to provide parameterless constructor.
	 */
	public MyEvent()
	{
		// Set the custom field to contain the
		// time when the event was created
		_customField = DateTime.now().toString("HH:mm a");
	}


	/**
	 * A SaveTo override that provides custom serialization
	 * to XML documents for our event.
	 */
	@Override()
	public void saveTo(Element element, XmlSerializationContext context)
	{
		context.writeString(_customField, "customField", element);
		super.saveTo(element, context);
	}

	/**
	 * A LoadFrom override that provides custom deserialization
	 * from XML document for our event.
	 */
	@Override()
	public void loadFrom(Element element, XmlSerializationContext context)
	{
		_customField = context.readString("customField", element);
		super.loadFrom(element, context);
	}

	/**
	 * A Clone override that enables interactive item cloning.
	 */
	public Object Clone()
	{
		MyEvent clone = new MyEvent();

		// The following code replicates the code used in
		// the Appointment's Clone method
		clone.setAllDayEvent(this.getAllDayEvent());
		clone.setDescriptionText(this.getDescriptionText());
		clone.setEndTime(this.getEndTime());
		clone.setHeaderText(this.getHeaderText());
		clone.setLocation(this.getLocation());
		clone.setLocked(this.getLocked());
		clone.setPriority(this.getPriority());
		clone.setReminder(this.getReminder());
		// TODO: implement
		//clone.setSelectedStyle(new Style(this.getSelectedStyle(), this.getSelectedStyle().));
		clone.setStartTime(this.getStartTime());
		clone.setStyle(this.getStyle().cloneShallow());
		clone.setTag(this.getTag());
		clone.setTask(this.getTask());
		clone.setVisible(this.getVisible());

		for (Resource resource : this.getResources())
			clone.getResources().add(resource);

		for (Contact contact : this.getContacts())
			clone.getContacts().add(contact);

		// Now copy the custom fields
		clone.setCustomField(this.getCustomField());

		return clone;
	}


	/**
	 * Gets the custom field.
	 */
	public String getCustomField()
	{
		return _customField;
	}
	
	/**
	 * Sets the custom field.
	 */
	public void setCustomField(String value)
	{
		_customField = value;

		// Mark this event as an exception if it is a recurrence instance
		if (getRecurrence() != null)
			getRecurrence().markException(this, false);
	}


	private String _customField;
}