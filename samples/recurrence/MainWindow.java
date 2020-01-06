/**
 * Copyright (c) 2017, MindFusion LLC - Bulgaria.
 */

package recurrence;

import java.awt.geom.Rectangle2D;
import java.util.EnumSet;

import com.mindfusion.common.*;
import com.mindfusion.drawing.*;
import com.mindfusion.scheduling.*;
import com.mindfusion.scheduling.model.*;


public class MainWindow extends BaseWindow
{
	public MainWindow()
	{
		setSize(648, 651);
		setTitle("MindFusion.Scheduling Sample: Resources");
		setInfo("This sample demonstrates how to use MindFusion.Scheduling Recurrence objects " +
			"for purposes other than recurring events. Particularly, the sample custom-draws " +
			"all days that match a specific recurrence pattern - every third Friday of a month.");
		
		// Calendar initialization start
		calendar.beginInit();
		DateTime today = DateTime.today();
		calendar.setDate(today);
		calendar.setCurrentView(CalendarView.MonthRange);
		calendar.setCustomDraw(EnumSet.of(CustomDrawElements.CellHeader));
		calendar.getMonthSettings().getDaySettings().setHeaderSize(0);
		calendar.endInit();
		// Calendar initialization end

		// Create the recurrence pattern (The third Friday each month)
		recurrence = new Recurrence();
		recurrence.setPattern(RecurrencePattern.Monthly);
		recurrence.setMonthlyRecurrence(MonthlyRecurrence.ByDayType);
		recurrence.setOccurrence(Occurrence.Third);
		recurrence.setDay(DayOfWeekType.Friday);
		recurrence.setStartDate(new DateTime(today.getYear(), today.getMonth(), 1));
		recurrence.setRecurrenceEnd(RecurrenceEnd.Never);

		// We have to associate the recurrence with an Item-derived
		// object to tell it what kind of objects to create in
		// generateItems method. For this purpose we use the
		// worker object below
		worker = new RecurringDay();
		worker.setRecurrence(recurrence);
		
		calendar.addCalendarListener(new CalendarAdapter()
		{
			@Override()
			public void draw(CalendarDrawEvent e) {
				onDraw(e);
			}
		});

		content.add(calendar);
	}

	private void onDraw(CalendarDrawEvent e)
	{
		if (e.getElement() == CustomDrawElements.CellHeader)
		{
			DateTime date = e.getDate();

			// Check if this day falls within our recurrence pattern
			ItemList items = recurrence.generateItems(date, date);

			boolean isOccurrence = false;
			for (Item item: items)
			{
				if (DateTime.op_Equality(item.getStartTime().getDate(), date))
				{
					isOccurrence = true;
					break;
				}
			}

			if (isOccurrence)
			{
				// Do the custom drawing
				Rectangle2D bounds = new Rectangle2D.Double(
					e.getBounds().getX(), e.getBounds().getY(),
					e.getBounds().getWidth() - 1, e.getBounds().getHeight() - 1);
				new AwtGraphics(e.getGraphics()).drawRectangle(Pens.Red, bounds);
			}
		}
	}


	private static final long serialVersionUID = 1L;

	private Recurrence recurrence;
	private RecurringDay worker;
}
