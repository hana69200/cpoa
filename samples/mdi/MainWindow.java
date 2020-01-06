/**
 * Copyright (c) 2017, MindFusion LLC - Bulgaria.
 */

package mdi;

import javax.swing.*;

import com.mindfusion.common.*;
import com.mindfusion.scheduling.*;


public class MainWindow extends BaseWindow
{
	public MainWindow()
	{
		setSize(648, 651);
		setTitle("MindFusion.Scheduling Sample: MDI");
		setInfo("This sample demonstrates how to display and manipulate a single " +
			"document (Schedule) object through many views (Calendar controls).<ul>" +
			"<li>Create items in any of the views below by selecting one or more time " +
			"cells and typing.</li></ul>");

		// Calendar initialization start
		calendar.beginInit();
		calendar.setTheme(ThemeType.Lila);
		calendar.setCurrentView(CalendarView.WeekRange);
		calendar.getWeekRangeSettings().setVisibleRows(8);

		// Set the date displayed in the calendar
		DateTime start = DateTime.today();
		DateTime end = start.addDays(60);
		calendar.setDate(start);
		calendar.setEndDate(end);
		calendar.endInit();
		// Calendar initialization end
		
		schedule = new Calendar();
		
		// Synchronize both views
		schedule.beginInit();
		schedule.setSchedule(calendar.getSchedule());
		schedule.setCurrentView(CalendarView.Timetable);
		schedule.setTheme(ThemeType.Lila);
		schedule.setCurrentTime(calendar.getCurrentTime());
		schedule.setDate(calendar.getDate());
		schedule.setEndDate(calendar.getEndDate());

		// Set more dates to be displayed in the timetable
		schedule.getTimetableSettings().getDates().clear();
		for (int i = 0; i < 5; i++)
			schedule.getTimetableSettings().getDates().add(start.addDays(i));
		schedule.getTimetableSettings().setVisibleColumns(5);
		schedule.endInit();

		calendar.addCalendarListener(new CalendarAdapter()
		{
			public void itemModified(ItemModifiedEvent e) {
				schedule.repaint();
			}
		});

		schedule.addCalendarListener(new CalendarAdapter()
		{
			public void itemModified(ItemModifiedEvent e) {
				calendar.repaint();
			}
		});

		JSplitPane pane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, schedule, calendar);
		pane.setDividerLocation(300);
		content.add(pane);
	}

	private Calendar schedule;
	private static final long serialVersionUID = 1L;
}