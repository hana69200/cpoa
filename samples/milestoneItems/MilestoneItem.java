/**
 * Copyright (c) 2017, MindFusion LLC - Bulgaria.
 */

package milestoneItems;

import java.awt.Color;

import com.mindfusion.drawing.*;
import com.mindfusion.scheduling.ItemViewerAttribute;
import com.mindfusion.scheduling.model.*;


@ItemViewerAttribute(viewerType = MilestoneItemViewer.class)
public class MilestoneItem extends Appointment
{
	public MilestoneItem()
	{
		color = Colors.Black;
	}

	public boolean getAllowChangeStart()
	{
		return false;
	}

	public boolean getAllowChangeEnd()
	{
		return false;
	}

	public Color getColor()
	{
		return color;
	}
	
	public void setColor(Color value) 
	{ 
		color = value;
	}

	private Color color;
}