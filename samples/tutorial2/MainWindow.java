/**
 * Copyright (c) 2017, MindFusion LLC - Bulgaria.
 */

package tutorial2;

import java.awt.BorderLayout;
import java.util.EnumSet;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import com.mindfusion.common.DateTime;
import com.mindfusion.common.DayOfWeek;
import com.mindfusion.scheduling.Calendar;
import com.mindfusion.scheduling.CalendarView;
import com.mindfusion.scheduling.model.*;


public class MainWindow extends JFrame
{
	public static void main(String[] args)
	{
		SwingUtilities.invokeLater(new Runnable()
		{
			public void run()
			{
				try
				{
					new MainWindow().setVisible(true);
				}
				catch (Exception exp)
				{
				}
			}
		});
	}

	protected MainWindow()
	{
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(400, 400);
		setTitle("Tutorial 2");

		calendar = new Calendar();
		getContentPane().add(calendar, BorderLayout.CENTER);

		calendar.beginInit();
		calendar.setCurrentView(CalendarView.WeekRange);
		calendar.setDate(new DateTime(2015, 1, 1));
		calendar.setEndDate(new DateTime(2015, 3, 1));

		Appointment app = new Appointment();
		app.setHeaderText("Meet George");
		app.setDescriptionText("This is a sample appointment");
		app.setStartTime(new DateTime(2015, 1, 10, 14, 0, 0));
		app.setEndTime(new DateTime(2015, 1, 10, 16, 30, 0));
		calendar.getSchedule().getItems().add(app);

		Recurrence rec = new Recurrence();
		rec.setPattern(RecurrencePattern.Weekly);
		rec.setDaysOfWeek(EnumSet.of(DayOfWeek.Monday, DayOfWeek.Wednesday));
		rec.setWeeks(2);
		rec.setStartDate(new DateTime(2006, 1, 10));
		rec.setRecurrenceEnd(RecurrenceEnd.Never);
		app.setRecurrence(rec);

		calendar.endInit();
	}


	private static final long serialVersionUID = 1L;

	private Calendar calendar;
}