/**
 * Copyright (c) 2017, MindFusion LLC - Bulgaria.
 */

package dragAndDrop;

import java.awt.Component;
import java.awt.Font;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.geom.Point2D;
import java.io.IOException;
import java.util.EnumSet;

import javax.swing.*;
import javax.swing.border.*;

import com.mindfusion.common.DateTime;
import com.mindfusion.common.Duration;
import com.mindfusion.drawing.AwtGraphics;
import com.mindfusion.drawing.Brushes;
import com.mindfusion.drawing.Pens;
import com.mindfusion.scheduling.CalendarAdapter;
import com.mindfusion.scheduling.CalendarDrawEvent;
import com.mindfusion.scheduling.CalendarView;
import com.mindfusion.scheduling.CustomDrawElements;
import com.mindfusion.scheduling.ThemeType;
import com.mindfusion.scheduling.TimetableSettings;
import com.mindfusion.scheduling.Calendar;


public class MainWindow extends BaseWindow
{
	public MainWindow()
	{
		setSize(504, 650);
		setTitle("MindFusion.Scheduling Sample: Drag & Drop");
		setInfo("Drag and drop functionality is not built into the Calendar control but " +
			"can be easily implemented. This sample demonstrates how.<ul><li>Drag any of " +
			"the icons below over the control area in order to create new events of the " +
			"respective type.</li></ul>");
		
		createUI();
	}

	private void createUI()
	{
		JPanel pnlRight = new JPanel();
		pnlRight.setLayout(null);
		
		SpringLayout layout = new SpringLayout();
		layout.putConstraint(SpringLayout.WEST, calendar, 0, SpringLayout.WEST, content);
		layout.putConstraint(SpringLayout.NORTH, calendar, 0, SpringLayout.NORTH, content);
		layout.putConstraint(SpringLayout.EAST, calendar, -120, SpringLayout.EAST, content);
		layout.putConstraint(SpringLayout.SOUTH, calendar, 0, SpringLayout.SOUTH, content);
		
		layout.putConstraint(SpringLayout.WEST, pnlRight, 0, SpringLayout.EAST, calendar);
		layout.putConstraint(SpringLayout.NORTH, pnlRight, 0, SpringLayout.NORTH, content);
		layout.putConstraint(SpringLayout.EAST, pnlRight, 0, SpringLayout.EAST, content);
		layout.putConstraint(SpringLayout.SOUTH, pnlRight, 0, SpringLayout.SOUTH, content);
		
		content.setLayout(layout);
		content.add(calendar);
		content.add(pnlRight);
		
		calendar.setCurrentView(CalendarView.Timetable);
		calendar.setTheme(ThemeType.Windows2003);
		
		transferHandler = new ItemTransferHandler(this);
		calendar.setTransferHandler(transferHandler);
		calendar.setCustomDraw(EnumSet.of(CustomDrawElements.TimetableCell));
		calendar.addCalendarListener(new CalendarAdapter()
		{
			@Override
			public void draw(CalendarDrawEvent e)
			{
				if (e.getElement() == CustomDrawElements.TimetableCell)
				{
					Point dropLocation = MainWindow.this.transferHandler.getDropLocation();
					if (dropLocation == null)
						return;
					
					Calendar calendar = MainWindow.this.calendar;
					AwtGraphics g = new AwtGraphics(e.getGraphics());
					
					if (MainWindow.this.btnMethod1.isSelected())
					{
						DateTime pointedDate = calendar.getDateAt(dropLocation.x, dropLocation.y);
						if (!DateTime.MinValue.equals(pointedDate))
						{
							Duration segment = calendar.getTimetableSettings().getCellTime();

							DateTime cellStart = e.getDate().add(e.getStartTime());
							DateTime cellEnd = e.getDate().add(e.getEndTime());

							DateTime prevCellStart = cellStart.subtract(segment);
							DateTime prevCellEnd = cellEnd.subtract(segment);

							Rectangle bounds = e.getBounds();

							// Fill the cell
							if (DateTime.op_LessThanOrEqual(cellStart, pointedDate) &&
								DateTime.op_LessThan(pointedDate, cellEnd))
							{
								// It is the first cell
								g.fillRectangle(Brushes.LightSteelBlue, bounds);

								// Draw lines on the left, top and right
								g.drawLine(Pens.SteelBlue,
									(int)bounds.getMinX(), (int)bounds.getMaxY(), (int)bounds.getMinX(), (int)bounds.getMinY());
								g.drawLine(Pens.SteelBlue,
									(int)bounds.getMinX(), (int)bounds.getMinY(), (int)bounds.getMaxX(), (int)bounds.getMinY());
								g.drawLine(Pens.SteelBlue,
									(int)bounds.getMaxX(), (int)bounds.getMinY(), (int)bounds.getMaxX(), (int)bounds.getMaxY());

								// Draw the time interval
								String interval = cellStart.toString("hh:mm a");
								g.drawString(interval, new Font(calendar.getFont().getName(), Font.PLAIN, calendar.getFont().getSize()),
									Brushes.Black, new Point2D.Double(bounds.getMinX() + 2, bounds.getMinY() + 2));
							}
							if (DateTime.op_LessThanOrEqual(prevCellStart, pointedDate) &&
								DateTime.op_LessThan(pointedDate, prevCellEnd))
							{
								// It is the second cell
								g.fillRectangle(Brushes.LightSteelBlue, bounds);

								// Draw lines on the left, bottom and right
								g.drawLine(Pens.SteelBlue,
									(int)bounds.getMinX(), (int)bounds.getMinY(), (int)bounds.getMinX(), (int)bounds.getMaxY());
								g.drawLine(Pens.SteelBlue,
									(int)bounds.getMinX(), (int)bounds.getMaxY(), (int)bounds.getMaxX(), (int)bounds.getMaxY());
								g.drawLine(Pens.SteelBlue,
									(int)bounds.getMaxX(), (int)bounds.getMaxY(), (int)bounds.getMaxX(), (int)bounds.getMinY());
							}
						}
					}
					else if (MainWindow.this.btnMethod2.isSelected())
					{
						DateTime pointedDate = calendar.getExactDateAt(dropLocation.x, dropLocation.y);
						if (DateTime.op_Equality(pointedDate.getDate(), e.getDate()))
						{
							if (Duration.op_LessThanOrEqual(e.getStartTime(), pointedDate.getTimeOfDay()) &&
								Duration.op_LessThanOrEqual(pointedDate.getTimeOfDay(), e.getEndTime()))
							{
								// Draw a line, indicating the item's start time
								TimetableSettings settings = calendar.getTimetableSettings();
								long off = pointedDate.getTimeOfDay().getTicks() - e.getStartTime().getTicks();
								int offPixels = (int)(((double)off / settings.getCellTime().getTicks()) * settings.getCellSize());

								g.drawLine(Pens.Black,
									(int)e.getBounds().getMinX() + 1, e.getBounds().y + offPixels,
									(int)e.getBounds().getMaxX() - 1, e.getBounds().y + offPixels);
							}
						}
					}
				}
				super.draw(e);
			}
		});
		
		// Setup the dates displayed in the Schedule view
		DateTime today = DateTime.today();

		calendar.getTimetableSettings().getDates().clear();
		for (int i = 0; i < 5; i++)
			calendar.getTimetableSettings().getDates().add(today.addDays(i));

		// Set the visible columns to 3
		calendar.getTimetableSettings().setVisibleColumns(3);
		
		JPanel pnlRTop = new JPanel();
		pnlRTop.setBounds(0, 0, 120, 80);
		pnlRTop.setBorder(new TitledBorder("Drag Method"));
		pnlRTop.setLayout(null);
		
		ButtonGroup group = new ButtonGroup();
		
		btnMethod1 = new JRadioButton("Method #1");
		btnMethod1.setBounds(10, 20, 100, 25);
		btnMethod1.setSelected(true);
		btnMethod2 = new JRadioButton("Method #2");
		btnMethod2.setBounds(10, 45, 100, 25);
		
		pnlRTop.add(btnMethod1);
		pnlRTop.add(btnMethod2);
		group.add(btnMethod1);
		group.add(btnMethod2);
		pnlRTop.setBounds(0, 0, 120, 80);
		
		JPanel pnlBtm = new JPanel();
		pnlBtm.setLayout(null);
		pnlBtm.setBounds(5, 80, 110, 360);
		
		pnlRight.add(pnlRTop);
		pnlRight.add(pnlBtm);

		TaskItem[] items = new TaskItem[]
			{
				new TaskItem("Appointment", createImageIcon("Resources/appointment.gif", "Appointment")),
				new TaskItem("Task", createImageIcon("Resources/task.gif", "Task")),
				new TaskItem("Event", createImageIcon("Resources/event.gif", "Event")),
				new TaskItem("Free", createImageIcon("Resources/free.gif", "Free"))
			};
		
		JList list = new JList(items);
		list.setBounds(0, 0, 110, 360);
		list.setDragEnabled(true);
		list.setTransferHandler(transferHandler);
		list.setCellRenderer(new MyCellRenderer());

		pnlBtm.add(list);
	}

	/** Returns an ImageIcon, or null if the path was invalid. */
	protected ImageIcon createImageIcon(String path, String description)
	{
	    java.net.URL imgURL = getClass().getResource(path);
	    if (imgURL != null)
	    {
	        return new ImageIcon(imgURL, description);
	    }
	    else
	    {
	        System.err.println("Couldn't find file: " + path);
	        return null;
	    }
	}
	
	public boolean getMethod1()
	{
		return btnMethod1.isSelected();
	}
		
	private static final long serialVersionUID = 1L;
	
	private ItemTransferHandler transferHandler;
	JRadioButton btnMethod1;
	JRadioButton btnMethod2;
}


class TaskItem
{
	public TaskItem(String text, Icon icon)
	{
		this.text = text;
		this.icon = icon;
	}
	
	public Icon getIcon()
	{
		return icon;
	}
	
	public void setIcon(Icon value)
	{
		icon = value;
	}
	
	public String getText()
	{
		return text;
	}
	
	public void setText(String value)
	{
		text = value;
	}
	
	
	private Icon icon;
	private String text;
}

class TaskItemTransferable implements Transferable
{
    public TaskItemTransferable(TaskItem item) 
    {
        this.item = item;
    }

    @Override
    public DataFlavor[] getTransferDataFlavors()
    {
        return new DataFlavor[] { TASK_ITEM_DATA_FLAVOR };
    }

    @Override
    public boolean isDataFlavorSupported(DataFlavor flavor)
    {
        return flavor.equals(TASK_ITEM_DATA_FLAVOR);
    }

    @Override
    public Object getTransferData(DataFlavor flavor) throws UnsupportedFlavorException, IOException
    {
        return item;
    }

    public static final DataFlavor TASK_ITEM_DATA_FLAVOR = new DataFlavor(TaskItem.class, "java/TaskItem");

    private TaskItem item;
}

class MyCellRenderer extends JLabel implements ListCellRenderer
{
	public Component getListCellRendererComponent(
      JList list, Object value, int index, boolean isSelected, boolean cellHasFocus)
    {
    	if (value instanceof TaskItem)
    	{
    		TaskItem item = (TaskItem)value;
    		setText(item.getText());
    		setIcon(item.getIcon());
    	}
    	else if (value != null)
    	{
    		setText(value.toString());
    		setIcon(null);
    	}
    	else
    	{
    		setText("");
    		setIcon(null);
    	}
    	
        if (isSelected)
        {
            setBackground(list.getSelectionBackground());
            setForeground(list.getSelectionForeground());
        }
        else
        {
            setBackground(list.getBackground());
            setForeground(list.getForeground());
        }
        setEnabled(list.isEnabled());
        setFont(list.getFont());
        setOpaque(true);
        return this;
    }


	private static final long serialVersionUID = 1L;
}
