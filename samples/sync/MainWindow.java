/**
 * Copyright (c) 2017, MindFusion LLC - Bulgaria.
 */

package sync;

import java.util.*;

import javax.swing.*;

import com.mindfusion.common.*;
import com.mindfusion.drawing.*;
import com.mindfusion.scheduling.*;
import com.mindfusion.scheduling.Calendar;
import com.mindfusion.scheduling.model.*;


public class MainWindow extends BaseWindow
{
	public MainWindow()
	{
		setSize(728, 571);
		setTitle("MindFusion.Scheduling Sample: Synchronization");
		setInfo("This sample demonstrates how to display and manipulate a single document " +
			"(Schedule) object through many views (Calendar controls).<ul><li>Scroll the " +
			"contents on the left view to see how the right view is automatically updated." +
			"</li><li>Double-click on a date in the right view to make it visible in the " +
			"left view.</li></ul>");

		// Calendar initialization start
		calendar.beginInit();
		calendar.setCurrentView(CalendarView.WeekRange);
		calendar.setTheme(ThemeType.Windows2003);
		calendar.getTimetableSettings().setMinuteFormat(MinuteFormat.LeadingZero);
		calendar.getWeekRangeSettings().setHeaderShadowStyle(ShadowStyle.Fading);

		DateTime start = DateTime.today();
		DateTime end = DateTime.op_Addition(start, new Duration(365 * Duration.TicksPerDay));

		// Set the date displayed in the calendar
		calendar.setDate(start);
		calendar.setEndDate(end);
		calendar.setAllowInplaceEdit(false);
		calendar.endInit();
		// Calendar initialization end

		year = new Calendar();
		year.beginInit();
		year.setCurrentView(CalendarView.MonthRange);
		year.setTheme(ThemeType.Windows2003);
		year.getMonthRangeSettings().setHeaderStyle(EnumSet.noneOf(MonthRangeHeaderStyles.class));
		year.getMonthSettings().setHeaderStyle(EnumSet.of(MonthHeaderStyles.Title));
		year.getMonthSettings().setMainHeaderHeight(20);
		year.getTimetableSettings().setMinuteFormat(MinuteFormat.LeadingZero);
		year.getWeekRangeSettings().setVisibleRows(8);

		yearFormat = new DateStyle();
		yearFormat.setStyle(new Style());
		yearFormat.getStyle().setBrush(new SolidBrush(Colors.LightSlateGray));
		yearFormat.getStyle().setHeaderTextColor(Colors.Yellow);
		year.getDayStyles().add(yearFormat);
		year.endInit();
		
		JSplitPane sp = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, calendar, year);
		sp.setDividerLocation(440);
		content.add(sp);
		
		// Listen for selection changes in the Calendar
		//calendar.getSelection().Changed += new EventHandler(Selection_Changed);
		calendar.getSelection().addChangeListener(new ChangeListener(){
			@Override public void changed(EventObject e) {
				onSelectionChanged(e);
			}
		});
		
		calendar.addCalendarListener(new CalendarAdapter(){
			public void scroll(EventObject e) {
				onCalendarScroll(e);
			}
		});
		
		year.addCalendarListener(new CalendarAdapter(){
			public void dateClick(ResourceDateEvent e) {
				onYearDateClick(e);
			}
		});

		updateYearView();
	}

	protected void onYearDateClick(ResourceDateEvent e) {
		if (e.getClicks() > 1)
		{
			calendar.ensureVisible(e.getDate());
			calendar.invalidate();
			updateYearView();
		}
	}

	protected void onCalendarScroll(EventObject e) {
		// When the contents of the calendar view are scrolled, update the
		// year view
		updateYearView();
	}

	protected void onSelectionChanged(EventObject e) {
		if (calendar.getSelection().getIsEmpty())
			return;

		year.getSelection().set(
			(DateTime)calendar.getSelection().getRanges().get(0),
			(DateTime)calendar.getSelection().getRanges().get(1));
	}

	private void updateYearView() {
		DateTime first = calendar.getFirstVisibleDate();
		DateTime last = calendar.getLastVisibleDate();

		yearFormat.setFrom(first);
		yearFormat.setTo(last);
		year.setDate(first);
	}

	private DateStyle yearFormat;
	private Calendar year;
	private static final long serialVersionUID = 1L;
}
