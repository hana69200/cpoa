/**
 * Copyright (c) 2017, MindFusion LLC - Bulgaria.
 */

package customEvents;

import java.awt.Font;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.geom.Dimension2D;
import java.util.EnumSet;

import javax.swing.*;

import com.mindfusion.common.*;
import com.mindfusion.drawing.*;
import com.mindfusion.scheduling.*;
import com.mindfusion.scheduling.model.*;


public class MainWindow extends BaseWindow
{
	public MainWindow()
	{
		setSize(468, 650);
		setTitle("MindFusion.Scheduling Sample: Custom Events");
		setInfo("This sample demonstrates how to derive from the Appointment class in order " +
			"to implement custom schedule events. The sample also shows how to specify that " +
			"custom objects must be instantiated when a user creates events interactively " +
			"(by typing) and how to perform additional drawing over the custom events.");
		
		// To be able to serialize custom events,
		// we have to register their types in the
		// class table of the underlying schedule
		Schedule.registerItemClass(MyEvent.class, "customEvent", 1);

		// Calendar initialization start
		calendar.beginInit();
		calendar.setCurrentView(CalendarView.Timetable);
		calendar.getWeekRangeSettings().setHeaderStyle(EnumSet.of(WeekRangeHeaderStyle.Title));

		// Make the control create events of our type
		// when the user types directly in the control
		calendar.setInteractiveItemType(MyEvent.class);

		// Set custom drawing for items
		calendar.setCustomDraw(EnumSet.of(CustomDrawElements.TimetableItem));

		// Set the displayed date to today
		DateTime today = DateTime.today();
		calendar.getTimetableSettings().getDates().clear();
		calendar.getTimetableSettings().getDates().add(today);

		// Create one event initially and add it to the schedule
		MyEvent myEvent = new MyEvent();
		myEvent.setHeaderText("My Event");
		myEvent.setDescriptionText("This is the event's description.");
		myEvent.setCustomField("This is my event.\nCool, isn't it?");
		myEvent.setStartTime(DateTime.op_Addition(today, new Duration(180 * Duration.TicksPerMinute)));
		myEvent.setEndTime(DateTime.op_Addition(today, new Duration(240 * Duration.TicksPerMinute)));
		calendar.getSchedule().getItems().add(myEvent);
		calendar.setTheme(ThemeType.Light);
		calendar.endInit();
		// Calendar initialization end
		
		calendar.addCalendarListener(new CalendarAdapter(){
			public void draw(CalendarDrawEvent e) {
				calendarDraw(e);
			}
			public void itemClick(ItemMouseEvent e) {
				calendarItemClicked(e);
			}
		});

		content.add(calendar);
	}

	protected void calendarItemClicked(ItemMouseEvent e)
	{
		if (e.getButton() == MouseEvent.BUTTON1 && e.getClicks() > 1)
		{
			calendar.resetDrag();

			// Display the custom field of the clicked event
			JOptionPane.showMessageDialog(this, ((MyEvent)e.getItem()).getCustomField(), "Custom Event", JOptionPane.INFORMATION_MESSAGE);
		}
	}

	protected void calendarDraw(CalendarDrawEvent e)
	{
		if (e.getElement() == CustomDrawElements.TimetableItem)
		{
			AwtGraphics g = new AwtGraphics(e.getGraphics());

			MyEvent myEvent = (MyEvent)calendar.getSchedule().getItems().get(e.getIndex());
			String customField = myEvent.getCustomField();

			Font font = e.getStyle().getFont();
			if (font == null)
				font = calendar.getItemSettings().getStyle().getFont();
			if (font == null)
				font = calendar.getTimetableSettings().getStyle().getFont();

			Dimension2D size = g.measureString(customField, font);

			int width = (int)size.getWidth();
			int height = (int)size.getHeight();
			if (width > e.getBounds().width - 4)
				width = e.getBounds().width - 4;
			if (height > e.getBounds().height - 4)
				height = e.getBounds().height - 4;

			// Draw the custom field in the lower right corner of the item
			Rectangle bounds = new Rectangle(
				(int)e.getBounds().getMaxX() - width - 3,
				(int)e.getBounds().getMaxY() - height - 3,
				width, height);

			g.fillRectangle(Brushes.Yellow, bounds);
			g.drawRectangle(Pens.Black, bounds);
			g.drawString(customField, font, Brushes.Black, new Point(bounds.x, bounds.y));
		}
	}

	private static final long serialVersionUID = 1L;
}