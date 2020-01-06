/**
 * Copyright (c) 2017, MindFusion LLC - Bulgaria.
 */

package tutorial3;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import com.mindfusion.common.DateTime;
import com.mindfusion.scheduling.Calendar;
import com.mindfusion.scheduling.CalendarAdapter;
import com.mindfusion.scheduling.CalendarView;
import com.mindfusion.scheduling.ItemMouseEvent;
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
		setTitle("Tutorial 3");

		calendar = new Calendar();
		getContentPane().add(calendar, BorderLayout.CENTER);

		calendar.beginInit();
		calendar.setCurrentView(CalendarView.WeekRange);
		calendar.setDate(new DateTime(2015, 1, 1));
		calendar.setEndDate(new DateTime(2015, 3, 1));
		calendar.setInteractiveItemType(MyApp.class);
		calendar.endInit();

		Schedule.registerItemClass(MyApp.class, "myapp", 1);

		calendar.addCalendarListener(new CalendarAdapter()
		{
			@Override
			public void itemClick(ItemMouseEvent e) {
				if (e.getItem() instanceof MyApp)
				{
				    calendar.resetDrag();
				    JOptionPane.showMessageDialog(MainWindow.this, "This is our item.");
				}
			}
		});
	}


	private static final long serialVersionUID = 1L;

	private Calendar calendar;
}