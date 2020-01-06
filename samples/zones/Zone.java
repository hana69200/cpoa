/**
 * Copyright (c) 2017, MindFusion LLC - Bulgaria.
 */

package zones;

import com.mindfusion.common.*;


/**
 * A definition of a zone.
 */
public class Zone
{
	public Zone(DateTime start, DateTime end, String name, boolean type)
	{
		_start = start;
		_end = end;
		_name = name;
		_type = type;
	}

	public DateTime getStart()
	{
		return _start;
	}

	public DateTime getEnd()
	{
		return _end;
	}

	public String getNameString()
	{
		return _name;
	}

	public boolean getType()
	{
		return _type;
	}


	private DateTime _start;
	private DateTime _end;
	private String _name;
	private boolean _type;
}