/**
 * Copyright (c) 2017, MindFusion LLC - Bulgaria.
 */

package customDrawElements;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.event.*;
import java.lang.reflect.*;
import java.util.EnumSet;

import javax.swing.*; 
import javax.swing.border.*;

import com.mindfusion.common.*;
import com.mindfusion.drawing.*;
import com.mindfusion.scheduling.*;
import com.mindfusion.scheduling.model.*;


public class MainWindow extends BaseWindow
{
	public MainWindow()
	{
		setSize(808, 611);
		setTitle("MindFusion.Scheduling Sample: CustomDraw Elements");
		setInfo("This sample demonstrates the calendar elements corresponding to the " +
			"values in the CustomDrawElements enumeration.");

		// Calendar initialization start
		calendar.setTheme(ThemeType.Vista);
		// Calendar initialization end

		JPanel pnlLeft = new JPanel();
		pnlLeft.setLayout(new BoxLayout(pnlLeft, BoxLayout.Y_AXIS));

		JScrollPane scrLeft = new JScrollPane(pnlLeft);
		scrLeft.setBorder(new TitledBorder("CustomDrawEvents"));

		JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, scrLeft, calendar);
		splitPane.setDividerLocation(250);
		content.add(splitPane);

		initList(pnlLeft);
	}
	
	private void initList(JPanel parent)
	{
		ButtonGroup group = new ButtonGroup();
		
		Class<?> cls = CustomDrawElements.class;
		for (Field fld : cls.getFields())
		{
			if (fld.getName().toLowerCase().equals("none") ||
				fld.getName().toLowerCase().equals("all"))
				continue;
			
			JRadioButton box = new JRadioButton(fld.getName());
			box.setHorizontalAlignment(SwingConstants.LEFT);
			box.setName(fld.getName());
			box.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					propertySelected(e.getSource());
				}
			});
			
			group.add(box);
			parent.add(box);
		}
		
		processing = false;
		
		calendar.getMonthSettings().setWeekHeaderStyle(MonthWeekHeaderStyle.Left);
		calendar.getListViewSettings().setCellUnits(TimeUnit.Week);
		EnumSet<ListViewHeaderStyles> style = calendar.getListViewSettings().getHeaderStyle();
		style.add(ListViewHeaderStyles.Subheader);
		calendar.getResourceViewSettings().setViewStyle(ResourceViewStyle.Lanes);
		calendar.getResourceViewSettings().getBottomTimelineSettings().setUnit(TimeUnit.Hour);
		calendar.getResourceViewSettings().getBottomTimelineSettings().setFormat("HH:mm");
		calendar.getTimetableSettings().setShowInfoHeader(true);
		calendar.getTimetableSettings().setVisibleColumns(3);
		calendar.addCalendarListener(new CalendarAdapter()
		{
			public void draw(CalendarDrawEvent e)
			{
				calendar_Draw(e);
			}
		});

		// Add some resources
		Resource r1 = new Resource();
		r1.setName("Resource #1");
		calendar.getResources().add(r1);

		Resource r2 = new Resource();
		r2.setName("Resource #2");
		calendar.getResources().add(r2);

		calendar.setGroupType(GroupType.GroupByResources);

		// Add some items
		Appointment a;

		a = new Appointment();
		a.setStartTime(new DateTime(2012, 6, 8));
		a.setEndTime(new DateTime(2012, 6, 11));
		a.setHeaderText("Item #1");
		calendar.getSchedule().getItems().add(a);

		a = new Appointment();
		a.setStartTime(new DateTime(2012, 6, 10));
		a.setEndTime(new DateTime(2012, 6, 12));
		a.setHeaderText("Item #2");
		calendar.getSchedule().getItems().add(a);

		// Add some items associated with resources
		a = new Appointment();
		a.setStartTime(new DateTime(2012, 6, 8, 4, 0, 0));
		a.setEndTime(new DateTime(2012, 6, 8, 6, 30, 0));
		a.setHeaderText("Item #3");
		a.getResources().add(r1);
		calendar.getSchedule().getItems().add(a);

		a = new Appointment();
		a.setStartTime(new DateTime(2012, 6, 8, 6, 0, 0));
		a.setEndTime(new DateTime(2012, 6, 8, 10, 0, 0));
		a.setHeaderText("Item 43");
		a.getResources().add(r1);
		calendar.getSchedule().getItems().add(a);

		a = new Appointment();
		a.setStartTime(new DateTime(2012, 6, 8));
		a.setEndTime(new DateTime(2012, 6, 9));
		a.setHeaderText("Item #5");
		a.getResources().add(r2);
		calendar.getSchedule().getItems().add(a);
	}

	protected void calendar_Draw(CalendarDrawEvent e)
	{
		Pen pen = new Pen(new Color(218, 165, 32));
		Brush brush = new SolidBrush(new Color(218, 165, 32, 75));

		AwtGraphics g = new AwtGraphics(e.getGraphics());
		Rectangle bounds = new Rectangle(e.getBounds().x, e.getBounds().y,
			e.getBounds().width - 1, e.getBounds().height - 1);

		g.fillRectangle(brush, bounds);
		g.drawRectangle(pen, bounds);
	}

	protected void propertySelected(Object source)
	{
		if (processing)
			return;
		processing = true;

		
		JRadioButton box = (JRadioButton)source;
		
		Class<?> cls = CustomDrawElements.class;
		CustomDrawElements value = null;
		try 
		{
			Field fld = cls.getField(box.getName());
			value = (CustomDrawElements)fld.get(CustomDrawElements.class);
		} catch (SecurityException e) {
		} catch (NoSuchFieldException e) {
		} catch (IllegalArgumentException e) {
		} catch (IllegalAccessException e) {
		}

		if (value != null)
		{
			switch (value)
			{
			case CalendarItem:
			case CellContents:
			case CellHeader:
				// The current view does not change
				break;

			case ListViewHeader:
			case ListViewHeaderCell:
			case ListViewSubHeader:
			case ListViewSubHeaderCell:
				calendar.setCurrentView(CalendarView.List);
				break;

			case MonthContents:
			case MonthMainHeader:
			case MonthRangeHeader:
			case MonthSubHeader:
			case MonthSubHeaderCell:
			case MonthWeekNumbersHeader:
			case MonthWeekNumbersHeaderCell:
				calendar.setCurrentView(CalendarView.MonthRange);
				break;

			case ResourceViewCell:
			case ResourceViewCellComplete:
			case ResourceViewItem:
			case ResourceViewRowHeader:
			case ResourceViewTimeline:
			case ResourceViewTimelineCell:
				calendar.setCurrentView(CalendarView.ResourceView);
				break;

			case TimetableItem:
			case TimetableCell:
			case TimetableColumnHeader:
			case TimetableGroupHeader:
			case TimetableGroupHeaderCell:
			case TimetableInfoHeader:
			case TimetableTimeline:
			case TimetableTimelineCell:
			case TimetableTimelineHourCell:
			case TimetableWholeDayHeader:
				calendar.setCurrentView(CalendarView.Timetable);
				break;
	
			case WeekRangeHeader:
			case WeekRangeResourceHeader:
			case WeekRangeSubHeader:
			case WeekRangeSubHeaderCell:
				calendar.setCurrentView(CalendarView.WeekRange);
				break;

			default:
				break;
			}
			
			calendar.setCustomDraw(EnumSet.of(value));
		}
		
		processing = false;
	}
	
	private boolean processing;
	private static final long serialVersionUID = 1L;
}