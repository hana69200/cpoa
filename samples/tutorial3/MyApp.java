/**
 * Copyright (c) 2017, MindFusion LLC - Bulgaria.
 */

package tutorial3;

import org.w3c.dom.Element;

import com.mindfusion.scheduling.model.*;


public class MyApp extends Appointment
{
    public MyApp()
    {
        _kept = true;
    }

    @Override
    public MyApp clone()
    {
        MyApp clone = new MyApp();

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
        clone.setSelectedStyle(this.getSelectedStyle().cloneShallow());
        clone.setStartTime(this.getStartTime());
        clone.setStyle(this.getStyle().cloneShallow());
        clone.setTag(this.getTag());
        clone.setTask(this.getTask());
        clone.setVisible(this.getVisible());

        for (Resource resource: this.getResources())
            clone.getResources().add(resource);

        for (Contact contact: this.getContacts())
            clone.getContacts().add(contact);

        // Now copy the custom fields
        clone.setKept(this.getKept());

        return clone;
    }

    @Override
    public void saveTo(Element element, XmlSerializationContext context)
    {
        super.saveTo(element, context);
        context.writeBool(_kept, "kept", element);
    }

    @Override
    public void loadFrom(Element element, XmlSerializationContext context)
    {
        super.saveTo(element, context);
        _kept = context.readBool("kept", element);
    }

    @Override
    protected void copyOccurrence(Item master)
    {
        super.copyOccurrence(master);
        _kept = ((MyApp)master).getKept();
    }

    @Override
    protected ItemState createState()
    {
        return new MyAppState();
    }

    @Override
    protected ItemState saveState()
    {
        MyAppState state = (MyAppState)super.saveState();
        state.setKept(this.getKept());
        return state;
    }

    @Override
    protected void restoreState(ItemState state)
    {
        MyAppState myState = (MyAppState)state;
        this.setKept(myState.getKept());
        super.restoreState(state);
    }

    public boolean getKept()
    {
        return _kept;
    }

    public void setKept(boolean value)
    {
        _kept = value;
        if (getRecurrence() != null)
            getRecurrence().markException(this, false);
    }


    private boolean _kept;
}

class MyAppState extends ItemState
{
	@Override
	public boolean equals(Object obj)
	{
		if (obj == null || !(obj instanceof MyAppState))
			return false;

		MyAppState other = (MyAppState)obj;

        if (kept != other.kept)
            return false;

        return super.equals(other);
    }

	public boolean getKept()
	{
		return kept;
	}

	public void setKept(boolean value)
	{
		kept = value;
	}


    private boolean kept;
}
