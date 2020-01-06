/**
 * Copyright (c) 2017, MindFusion LLC - Bulgaria.
 */

package dragAndDrop;

import java.awt.Color;
import java.awt.Component;
import java.awt.Point;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.DnDConstants;
import java.io.IOException;

import javax.swing.JComponent;
import javax.swing.JList;
import javax.swing.TransferHandler;

import com.mindfusion.common.DateTime;
import com.mindfusion.drawing.Colors;
import com.mindfusion.drawing.GradientBrush;
import com.mindfusion.scheduling.Calendar;
import com.mindfusion.scheduling.model.Appointment;
import com.mindfusion.scheduling.model.Style;


public class ItemTransferHandler extends TransferHandler
{
	public ItemTransferHandler(MainWindow window)
	{
		this.window = window;
	}
	
	@Override
	public int getSourceActions(JComponent c) 
	{
	    return DnDConstants.ACTION_COPY_OR_MOVE;
	}
	
	@Override
	protected Transferable createTransferable(JComponent c)
	{
	    if (c instanceof JList)
	    {
	        JList list = (JList)c;
	        Object value = list.getSelectedValue();
	        if (value instanceof TaskItem)
	            return new TaskItemTransferable((TaskItem)value);
	    }
	    
	    return super.createTransferable(c);
	}
    
	@Override
	public boolean importData(TransferSupport support)
	{
		if (!canImport(support))
			return false;

		Calendar calendar = (Calendar)support.getComponent();
		Point location = support.getDropLocation().getDropPoint();
	    DateTime date;
	    if (window.getMethod1())
	    	date = calendar.getDateAt(location.x, location.y);
	    else
	    	date = calendar.getExactDateAt(location.x, location.y);

		try
		{
			TaskItem item = (TaskItem)support.getTransferable().getTransferData(TaskItemTransferable.TASK_ITEM_DATA_FLAVOR);
			Appointment app = new Appointment();
			app.setHeaderText(item.getText());
			app.setStartTime(date);
			app.setEndTime(date.addHours(1));
			Style style = app.getStyle();
			if ("Task".equals(item.getText()))
			{
				style.setBorderTopColor(Colors.DarkOrchid);
				style.setBorderLeftColor(Colors.DarkOrchid);
				style.setBorderBottomColor(Colors.DarkOrchid);
				style.setBorderRightColor(Colors.DarkOrchid);
				style.setLineColor(Colors.DarkOrchid);
				style.setFillColor(Colors.MediumOrchid);
				style.setBrush(new GradientBrush(Colors.White, Colors.Plum, 90));
				style.setHeaderTextColor(Colors.DarkViolet);
			}
			else if ("Event".equals(item.getText()))
			{
				style.setTextColor(new Color(0xFF, 0x80, 0x80, 0x80));
				style.setBorderTopColor(Colors.Goldenrod);
				style.setBorderLeftColor(Colors.Goldenrod);
				style.setBorderBottomColor(Colors.Goldenrod);
				style.setBorderRightColor(Colors.Goldenrod);
				style.setLineColor(Colors.Goldenrod);
				style.setFillColor(new Color(250, 200, 100));
				style.setBrush(new GradientBrush(Colors.White, Colors.PaleGoldenrod, 90));
				style.setHeaderTextColor(Colors.DarkGoldenrod);
			}
			else if ("Free".equals(item.getText()))
			{
				style.setTextColor(new Color(0xFF, 0x80, 0x80, 0x80));
				style.setBorderTopColor(Colors.LimeGreen);
				style.setBorderLeftColor(Colors.LimeGreen);
				style.setBorderBottomColor(Colors.LimeGreen);
				style.setBorderRightColor(Colors.LimeGreen);
				style.setLineColor(Colors.LimeGreen);
				style.setFillColor(Colors.PaleGreen);
				app.getStyle().setBrush(new GradientBrush(Colors.White, Colors.LightGreen, 90));
				style.setHeaderTextColor(Colors.DarkGreen);
			}
			calendar.getSchedule().getItems().add(app);
		}
		catch (UnsupportedFlavorException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}

		return super.importData(support);
	}
	
	@Override
	public boolean canImport(TransferSupport support)
	{
		dropLocation = null;
		
		Component c = support.getComponent();
		if (!(c instanceof Calendar))
			return false;

		Calendar calendar = (Calendar)c;

		Point location = support.getDropLocation().getDropPoint();
		DateTime date;
		if (window.getMethod1())
	    	date = calendar.getDateAt(location.x, location.y);
	    else
	    	date = calendar.getExactDateAt(location.x, location.y);
		
	    if (DateTime.MinValue.equals(date))
	    	return false;

		if (support.isDataFlavorSupported(TaskItemTransferable.TASK_ITEM_DATA_FLAVOR))
		{
			calendar.repaint();
			dropLocation = location;
			return true;
		}

		return false;
	}
	
	public Point getDropLocation()
	{
		return dropLocation;
	}


	private static final long serialVersionUID = 1L;
	
	private Point dropLocation;
	private MainWindow window;
}
