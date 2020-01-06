/**
 * Copyright (c) 2017, MindFusion LLC - Bulgaria.
 */

package dualView;

import java.awt.Font;
import java.util.*;

import javax.swing.*;

import com.mindfusion.common.*;
import com.mindfusion.scheduling.*;
import com.mindfusion.scheduling.Calendar;
import com.mindfusion.scheduling.model.*;


public class MainWindow extends BaseWindow 
{
	public MainWindow()
	{
		setSize(648, 651);
		setTitle("MindFusion.Scheduling Sample: Dual View");
		setInfo("<ul><li>Create items in the Timetable schedule to the right to see how " +
			"the corresponding days in the Month calendar to the left become bold.</li>" +
			"<li>Select dates in the Month calendar to the left to edit the appointments " +
			"in these days in the Timetable schedule to the right.</li></ul>");

		// Calendar initialization start
		calendar.beginInit();
		calendar.setCurrentView(CalendarView.MonthRange);
		calendar.setTheme(ThemeType.Silver);
		calendar.getMonthRangeSettings().setMonthsPerRow(1);
		calendar.getMonthRangeSettings().setNumberOfMonths(2);
		calendar.getMonthRangeSettings().setScrollInterval(1);
		calendar.getSelection().setAllowMultiple(false);
		calendar.getMonthSettings().getDaySettings().setHeaderSize(0);
		calendar.endInit();
		// Calendar initialization end
		
		_schedule = new Calendar();
		_schedule.setCurrentView(CalendarView.Timetable);
		
		// Synchronize both views
		_schedule.beginInit();
		_schedule.setSchedule(calendar.getSchedule());
		_schedule.setTheme(ThemeType.Silver);
		_schedule.setDate(calendar.getDate());
		_schedule.setEndDate(calendar.getEndDate());
		_schedule.endInit();
		
		// Select the current date
		calendar.getSelection().set(DateTime.today());
		
		// Listen for selection changes
		calendar.getSelection().addChangeListener(new ChangeListener(){
			@Override
			public void changed(EventObject e) {
				onSelectionChanged();
			}
		});
		
		// Listen for item creation/deletion/modification
		_schedule.addCalendarListener(new CalendarAdapter(){
			public void itemCreated(ItemEvent e) {
				onItemCreated(e);
			}
			public void itemDeleted(ItemEvent e) {
				onItemDeleted(e);
			}
		});
					
		JSplitPane pane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, calendar, _schedule);
		pane.setDividerLocation(250);
		content.add(pane);
	}

	protected void onItemDeleted(ItemEvent e) {
		// Check if there are more items on this date.
		// If no - remove the custom style
		DateTime start = e.getItem().getStartTime().getDate();
		DateTime end = e.getItem().getEndTime();
		end = new DateTime(end.getYear(), end.getMonth(), end.getDay(), 23, 59, 59);
		if (_schedule.getSchedule().getAllItems(start, end).size() == 0)
		{
			for (DateStyle s : calendar.getDayStyles())
			{
				if (DateTime.op_Equality(s.getFrom().getDate(), e.getItem().getStartTime().getDate()))
				{
					calendar.getDayStyles().remove(s);
					break;
				}
			}
		}
	}

	protected void onItemCreated(ItemEvent e) {
		// Check if the item date already has custom style
		for (DateStyle s : calendar.getDayStyles())
		{
			if (DateTime.op_Equality(s.getFrom().getDate(), e.getItem().getStartTime().getDate()))
				return;
		}

		// No custom style for this date -> add one
		DateStyle style = new DateStyle();
		style.setFrom(e.getItem().getStartTime().getDate());
		style.setTo(e.getItem().getStartTime().getDate());
		style.getStyle().setHeaderFont(new Font("Tahoma", Font.BOLD, 10));
		calendar.getDayStyles().add(style);
		calendar.invalidate();
	}

	protected void onSelectionChanged() {
		if (calendar.getSelection().getIsEmpty())
		{
			_schedule.getTimetableSettings().getDates().clear();
			return;
		}

		_schedule.beginInit();
		_schedule.getTimetableSettings().getDates().clear();
		_schedule.getTimetableSettings().getDates().add((DateTime)calendar.getSelection().getRanges().get(0));
		_schedule.endInit();
	}

	private Calendar _schedule;
	private static final long serialVersionUID = 1L;
}
