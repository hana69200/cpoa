/**
 * Copyright (c) 2017, MindFusion LLC - Bulgaria.
 */

package linearMonthView;

import java.util.EnumSet;

import com.mindfusion.drawing.*;
import com.mindfusion.scheduling.*;
import com.mindfusion.scheduling.model.*;


public class MainWindow extends BaseWindow
{
	public MainWindow()
	{
		setSize(960, 507);
		setTitle("MindFusion.Scheduling Sample: Linear Month View");
		setInfo("This view demonstrates the new Linear cell layout in the SingleMonth and MonthRange views.");

		// Calendar initialization start
		calendar.beginInit();
		calendar.setCurrentView(CalendarView.MonthRange);
		calendar.setTheme(ThemeType.Lila);
		
		calendar.getMonthRangeSettings().setVisibleRows(3);
		calendar.getMonthRangeSettings().setMonthsPerRow(1);
		
		calendar.getMonthSettings().setCellLayout(MonthCellLayout.Linear);
		calendar.getMonthSettings().getDaySettings().setHeaderSize(18);
		calendar.getMonthSettings().getDaySettings().getStyle().setBorderBottomColor(Colors.Purple);
		calendar.getMonthSettings().getDaySettings().getStyle().setBorderBottomWidth(1);
		calendar.getMonthSettings().getDaySettings().getStyle().setBorderLeftColor(Colors.Violet);
		calendar.getMonthSettings().getDaySettings().getStyle().setBorderLeftWidth(1);
		calendar.getMonthSettings().getDaySettings().getStyle().setBorderRightColor(Colors.Purple);
		calendar.getMonthSettings().getDaySettings().getStyle().setBorderRightWidth(1);
		calendar.getMonthSettings().getDaySettings().getStyle().setBorderTopColor(Colors.Violet);
		calendar.getMonthSettings().getDaySettings().getStyle().setBorderTopWidth(1);
		
		calendar.getMonthSettings().getDaySettings().getStyle().setHeaderBorderBottomColor(Colors.Purple);
		calendar.getMonthSettings().getDaySettings().getStyle().setHeaderBorderBottomWidth(0);
		calendar.getMonthSettings().getDaySettings().getStyle().setHeaderBorderBottomColor(Colors.Purple);
		calendar.getMonthSettings().getDaySettings().getStyle().setHeaderBorderBottomWidth(0);
		calendar.getMonthSettings().getDaySettings().getStyle().setHeaderBorderBottomColor(Colors.Purple);
		calendar.getMonthSettings().getDaySettings().getStyle().setHeaderBorderBottomWidth(0);
		calendar.getMonthSettings().getDaySettings().getStyle().setHeaderBorderBottomColor(Colors.Purple);
		calendar.getMonthSettings().getDaySettings().getStyle().setHeaderBorderBottomWidth(0);
		
		GradientBrush headerBrush = new GradientBrush(Colors.Violet, Colors.White, 270);
		calendar.getMonthSettings().getDaySettings().getStyle().setHeaderBrush(headerBrush);
		calendar.getMonthSettings().getDaySettings().getStyle().setTextShadowStyle(ShadowStyle.None);
		calendar.getMonthSettings().setHeaderStyle(EnumSet.of(MonthHeaderStyles.Title, MonthHeaderStyles.SubheaderPerDay));
		calendar.getMonthSettings().setMainHeaderHeight(100);
		calendar.endInit();
		// Calendar initialization end

		content.add(calendar);
	}


	private static final long serialVersionUID = 1L;
}
