/**
 * Copyright (c) 2017, MindFusion LLC - Bulgaria.
 */

package holidays;

import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;
import java.util.EnumSet;

import com.mindfusion.common.*;
import com.mindfusion.drawing.*;
import com.mindfusion.scheduling.*;


public class MainWindow extends BaseWindow 
{
	public MainWindow()
	{
		setSize(808, 611);
		setTitle("MindFusion.Scheduling Sample: Holidays");
		setInfo("This sample demonstrates how to mark special dates across the calendar using " +
			"custom drawing.");

		// Calendar initialization start
		calendar.beginInit();
		calendar.setDate(DateTime.today());

		calendar.getMonthSettings().getDaySettings().setShowToday(false);
		calendar.getMonthSettings().getDaySettings().setHeaderSize(0);
		calendar.getMonthSettings().getDaySettings().getStyle().setHeaderFont(new Font("Verdana", Font.BOLD, 16));
		calendar.getMonthSettings().setHeaderSpacing(2);
		calendar.getMonthSettings().setMainHeaderHeight(48);
		calendar.getMonthSettings().getStyle().setFont(new Font("Verdana", Font.PLAIN, 16));
		calendar.getMonthSettings().getStyle().setHeaderFont(new Font("Verdana", Font.BOLD, 24));
		calendar.getMonthSettings().setSubHeaderHeight(32);

		calendar.setShowToolTips(true);
		calendar.setTheme(ThemeType.Silver);
		calendar.setCustomDraw(EnumSet.of(CustomDrawElements.CellHeader));

		DayOfWeekStyle style = new DayOfWeekStyle();
		style.setDayOfWeek(DayOfWeek.Saturday);
		style.getStyle().setHeaderTextColor(new Color(192, 0, 0));
		calendar.getDayOfWeekStyles().add(style);
		style = new DayOfWeekStyle();
		style.setDayOfWeek(DayOfWeek.Sunday);
		style.getStyle().setHeaderTextColor(new Color(192, 0, 0));
		calendar.getDayOfWeekStyles().add(style);
		calendar.endInit();
		// Calendar initialization end

		calendar.addCalendarListener(new CalendarAdapter()
		{
			@Override()
			public void tooltipDisplaying(TooltipEvent e) {
				onTooltipDisplaying(e);
			}

			@Override()
			public void draw(CalendarDrawEvent e) {
				onDraw(e);
			}

			@Override()
			public void visibleDateChanged(DateChangedEvent e) {
				onVisibleDateChanged(e);
			}
		});

		content.add(calendar);

		updateHolidays();
	}

	public void onTooltipDisplaying(TooltipEvent e)
	{
		StringBuilder tooltip = new StringBuilder();
		if (holidays != null && holidays.length > 0)
		{
			for (Holiday holiday: holidays)
			{
				if (DateTime.op_LessThanOrEqual(holiday.getDate(), e.getTime().getDate()) &&
					DateTime.op_LessThanOrEqual(e.getTime().getDate(), holiday.getEndDate()))
				{
					if (tooltip.length() > 0)
						tooltip.append("; ");
					tooltip.append(holiday.getTitle());
				}
			}
		}

		e.setTooltip(tooltip.toString());
	}

	public void onDraw(CalendarDrawEvent e)
	{
		AwtGraphics g = new AwtGraphics(e.getGraphics());

		if (e.getElement() == CustomDrawElements.CellHeader)
		{
			Rectangle bounds = e.getBounds();
			if (DateTime.op_Equality(e.getDate().getDate(), DateTime.today()))
			{
				Brush brush = new SolidBrush(new Color(255, 255, 255, 100));
				g.fillRectangle(brush, bounds);

				Pen pen = new Pen(Colors.Red, 3);
				g.drawRectangle(pen, bounds);
			}
			else
			{
				if (holidays != null && holidays.length > 0)
				{
					boolean isHoliday = false;
					for (Holiday holiday: holidays)
					{
						if (DateTime.op_LessThanOrEqual(holiday.getDate(), e.getDate().getDate()) &&
							DateTime.op_LessThanOrEqual(e.getDate().getDate(), holiday.getEndDate()))
						{
							isHoliday = true;
							break;
						}
					}

					if (isHoliday)
					{
						Brush brush = new SolidBrush(new Color(Colors.LightSteelBlue.getRed(),
							Colors.LightSteelBlue.getGreen(), Colors.LightSteelBlue.getBlue(), 128));
						g.fillRectangle(brush, bounds);

						Pen pen = new Pen(new Color(Colors.SlateGray.getRed(),
							Colors.SlateGray.getGreen(), Colors.SlateGray.getBlue(), 192));
						Rectangle2D.Double borderBounds = new Rectangle2D.Double(
							bounds.getX(), bounds.getY(), bounds.getWidth() - 1, bounds.getHeight() - 1);
						g.drawRectangle(pen, borderBounds);

						TextFormat format = new TextFormat();
						format.setHorizontalAlign(Align.Center);
						format.setVerticalAlign(Align.Center);

						Brush brush2 = new SolidBrush(new Color(192, 0, 0));
						g.drawString(e.getText(), e.getStyle().getHeaderFont(), brush2, bounds, format);
					}
				}
			}
		}
	}

	public void onVisibleDateChanged(DateChangedEvent e)
	{
		updateHolidays();
	}
	

	private void updateHolidays()
	{
		DateTime date = calendar.getDate();
		holidays = new USHolidayProvider().getHolidays(
			new DateTime(date.getYear(), date.getMonth(), 1),
			new DateTime(date.getYear(), date.getMonth(), DateTime.daysInMonth(date.getYear(), date.getMonth())));
	}


	private Holiday[] holidays;
	private static final long serialVersionUID = 1L;
}
