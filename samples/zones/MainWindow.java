/**
 * Copyright (c) 2017, MindFusion LLC - Bulgaria.
 */

package zones;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.event.*;
import java.util.*;

import javax.swing.*;

import com.mindfusion.common.*;
import com.mindfusion.drawing.*;
import com.mindfusion.scheduling.*;
import com.mindfusion.scheduling.model.*;
import com.mindfusion.scheduling.model.ItemEvent;


public class MainWindow extends BaseWindow
{
	public MainWindow()
	{
		setSize(648, 651);
		setTitle("MindFusion.Scheduling Sample: Zones");
		setInfo("This sample demonstrates timetable custom drawing, user interaction control " +
			"and custom events, all combined to achieve particular application behavior.<ul>" +
			"<li>To define new zone select a range of cells and click either of the two " +
			"'Zone...' buttons below.</li><li>To delete an existing zone, select any part " +
			"of it and click the 'Delete' button.</li><li>To create a new zoned item, " +
			"select cells belonging to only one zone and type in the item's text.</li><li>To " +
			"move items between zones, drag the item.</li></ul>Note that when an item is " +
			"created it is aligned with the type of the zone it was created in and it can no " +
			"longer be placed in a zone from the opposing type.");
		
		createUI();

		// Calendar initialization start
		calendar.beginInit();
		calendar.setCurrentView(CalendarView.Timetable);
		calendar.setTheme(ThemeType.Vista);
		calendar.setCustomDraw(EnumSet.of(CustomDrawElements.TimetableCell));
		
		calendar.getSelection().getSelectedElementsStyle().setBorderBottomColor(Colors.Transparent);
		calendar.getSelection().getSelectedElementsStyle().setBorderBottomWidth(-1);
		calendar.getSelection().getSelectedElementsStyle().setBorderLeftColor(Colors.Transparent);
		calendar.getSelection().getSelectedElementsStyle().setBorderLeftWidth(-1);
		calendar.getSelection().getSelectedElementsStyle().setBorderRightColor(Colors.Transparent);
		calendar.getSelection().getSelectedElementsStyle().setBorderRightWidth(-1);
		calendar.getSelection().getSelectedElementsStyle().setBorderTopColor(Colors.Transparent);
		calendar.getSelection().getSelectedElementsStyle().setBorderTopWidth(-1);
		calendar.getSelection().getSelectedElementsStyle().setFillColor(Colors.Transparent);
		calendar.getSelection().getSelectedElementsStyle().setBrush(Brushes.Transparent);
		calendar.getSelection().getSelectedElementsStyle().setHeaderBorderBottomColor(new Color(0, 0, 0, 0));
		
		calendar.getTimetableSettings().getCellStyle().setBorderBottomColor(new Color(169, 169, 169));
		calendar.getTimetableSettings().getCellStyle().setBorderBottomWidth(1);
		calendar.getTimetableSettings().getCellStyle().setBorderLeftColor(new Color(169, 169, 169));
		calendar.getTimetableSettings().getCellStyle().setBorderLeftWidth(1);
		calendar.getTimetableSettings().getCellStyle().setBorderRightColor(new Color(169, 169, 169));
		calendar.getTimetableSettings().getCellStyle().setBorderRightWidth(1);
		calendar.getTimetableSettings().getCellStyle().setBorderTopColor(new Color(169, 169, 169));
		calendar.getTimetableSettings().getCellStyle().setBorderTopWidth(1);
		calendar.getTimetableSettings().getCellStyle().setHeaderTextShadowOffset(0);
		calendar.getTimetableSettings().getCellStyle().setHeaderTextShadowStyle(ShadowStyle.None);
		calendar.getTimetableSettings().getDates().clear();
		calendar.getTimetableSettings().getDates().add(DateTime.today());
		calendar.getTimetableSettings().getDates().add(DateTime.today().addDays(1));
		calendar.getTimetableSettings().setItemOffset(30);
		calendar.getTimetableSettings().setShowItemSpans(false);
		calendar.getTimetableSettings().setSnapInterval(Duration.fromMinutes(1));
		calendar.getTimetableSettings().setVisibleColumns(2);
		
		calendar.setInteractiveItemType(ZoneEvent.class);
		calendar.endInit();
		// Calendar initialization end
		
		calendar.addCalendarListener(new CalendarAdapter() {
			public void draw(CalendarDrawEvent e) {
				onCalendarDraw(e);
			}
			public void itemCreating(ItemConfirmEvent e) {
				onCalendarItemCreating(e);
			}
			public void itemModifying(ItemModifyConfirmEvent e) {
				onItemModifying(e);
			}
			public void itemCreated(ItemEvent e) {
				onItemCreated(e);
			}
			public void itemClick(ItemMouseEvent e) {
				onItemClick(e);
			}
		});
	}

	private void createUI()
	{
		JPanel pnlBottom = new JPanel();
		pnlBottom.setLayout(null);
		
		SpringLayout layout = new SpringLayout();
		layout.putConstraint(SpringLayout.EAST, calendar, 0, SpringLayout.EAST, content);
		layout.putConstraint(SpringLayout.NORTH, calendar, 0, SpringLayout.NORTH, content);
		layout.putConstraint(SpringLayout.WEST, calendar, 0, SpringLayout.WEST, content);
		layout.putConstraint(SpringLayout.SOUTH, calendar, -35, SpringLayout.SOUTH, content);
		
		layout.putConstraint(SpringLayout.EAST, pnlBottom, 0, SpringLayout.EAST, content);
		layout.putConstraint(SpringLayout.NORTH, pnlBottom, 0, SpringLayout.SOUTH, calendar);
		layout.putConstraint(SpringLayout.WEST, pnlBottom, 0, SpringLayout.WEST, content);
		layout.putConstraint(SpringLayout.SOUTH, pnlBottom, 0, SpringLayout.SOUTH, content);
		
		content.setLayout(layout);
		content.add(calendar);
		content.add(pnlBottom);
		
		JButton btnZoneA = new JButton("Zone A");
		btnZoneA.setBounds(5, 5, 110, 25);
		btnZoneA.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				btnZoneAClick();
			}
		});
		pnlBottom.add(btnZoneA);
		
		JButton btnZoneB = new JButton("Zone B");
		btnZoneB.setBounds(120, 5, 110, 25);
		btnZoneB.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				btnZoneBClick();
			}
		});
		pnlBottom.add(btnZoneB);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.setBounds(235, 5, 110, 25);
		btnDelete.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				btnDeleteClick();
			}
		});
		pnlBottom.add(btnDelete);
		
	}

	protected void btnZoneBClick()
	{
		addZone(false);
	}

	protected void btnZoneAClick()
	{
		addZone(true);
	}

	protected void btnDeleteClick()
	{
		for (int j = 0; j < zones.size(); )
		{
			Zone z = zones.get(j);

			// Check if zone is selected
			boolean found = false;
			for (int i = 0; i < calendar.getSelection().getRanges().size(); i += 2)
			{
				DateTime start = calendar.getSelection().getRanges().get(i);
				DateTime end = calendar.getSelection().getRanges().get(i + 1);
				if (intersect(z.getStart(), z.getEnd(), start, end))
				{
					zones.remove(j);
					found = true;

					// Remove all items from the zone
					for (int k = 0; k < calendar.getSchedule().getItems().size(); )
					{
						Item item = calendar.getSchedule().getItems().get(k);

						if (intersect(z.getStart(), z.getEnd(), item.getStartTime(), item.getEndTime()))
						{
							calendar.getSchedule().getItems().remove(k);
							continue;
						}

						k++;
					}

					break;
				}
			}

			if (!found)
				j++;
		}

		calendar.getSelection().reset();
	}
	
	protected void onCalendarDraw(CalendarDrawEvent e)
	{
		if (e.getElement() == CustomDrawElements.TimetableCell)
		{
			DateTime cellStart = DateTime.op_Addition(e.getDate(), e.getStartTime());
			DateTime cellEnd = DateTime.op_Addition(cellStart, calendar.getTimetableSettings().getCellTime());
			boolean zone = false;
			boolean zoneStart = false;
			boolean zoneEnd = false;
			boolean type = false;

			for (Zone z : zones)
			{
				if (DateTime.op_Equality(z.getStart(), cellStart))
				{
					zone = true;
					type = z.getType();
					zoneStart = true;
				}

				if (DateTime.op_Equality(z.getEnd(), cellEnd))
				{
					zone = true;
					type = z.getType();
					zoneEnd = true;
				}

				if (DateTime.op_LessThan(z.getStart(), cellStart) && DateTime.op_LessThan(cellStart, z.getEnd()) ||
						DateTime.op_LessThan(z.getStart(), cellEnd) && DateTime.op_LessThan(cellEnd, z.getEnd()))
				{
					zone = true;
					type = z.getType();
				}
			}

			boolean selected = false;
			if (calendar.getSelection().contains(cellStart))
				selected = true;

			Rectangle b = new Rectangle(e.getBounds());
			AwtGraphics g = new AwtGraphics(e.getGraphics());
			Pen p = new Pen(e.getStyle().getLineColor(), 1);
			if (zone)
			{
				Brush b2 = new SolidBrush(new Color(255, 255, 255, 20));
				Brush b4 = null;
				if (type)
					b4 = new SolidBrush(new Color(Colors.Goldenrod.getRed(),
						Colors.Goldenrod.getGreen(), Colors.Goldenrod.getBlue(), 75));
				else
					b4 = new SolidBrush(new Color(Colors.LightSteelBlue.getRed(),
						Colors.LightSteelBlue.getGreen(), Colors.LightSteelBlue.getBlue(), 75));

				g.fillRectangle(b2, b);
				g.fillRectangle(b4, b.getMinX() + 1, b.getMinY(), 30, b.getHeight());

				g.drawLine(p, b.getMinX() + 30, b.getMinY(), b.getMinX() + 30, b.getMaxY() - 1);

				g.drawLine(p, b.getMinX(), b.getMinY(), b.getMinX(), b.getMaxY() - 1);
				g.drawLine(p, b.getMaxX() - 1, b.getMinY(), b.getMaxX() - 1, b.getMaxY() - 1);

				if (zoneStart)
					g.drawLine(p, b.getMinX(), b.getMinY(), b.getMaxX() - 1, b.getMinY());

				if (zoneEnd)
					g.drawLine(p, b.getMinX(), b.getMaxY() - 1, b.getMaxX() - 1, b.getMaxY() - 1);
			}

			Brush b1 = new SolidBrush(new Color(0, 0, 0, 40));
			if (selected)
			{
				boolean start = false;
				boolean end = false;
				for (int i = 0; i < calendar.getSelection().getRanges().size(); i += 2)
				{
					DateTime istart = calendar.getSelection().getRanges().get(i);
					DateTime iend = calendar.getSelection().getRanges().get(i + 1);

					if (DateTime.op_Equality(cellStart, istart))
						start = true;
					if (DateTime.op_Equality(cellEnd, iend))
						end = true;
				}

				g.fillRectangle(b1, b);

				g.drawLine(p, b.getMinX(), b.getMinY(), b.getMinX(), b.getMaxY() - 1);
				g.drawLine(p, b.getMaxX() - 1, b.getMinY(), b.getMaxX() - 1, b.getMaxY() - 1);

				if (start)
					g.drawLine(p, b.getMinX(), b.getMinY(), b.getMaxX() - 1, b.getMinY());

				if (end)
					g.drawLine(p, b.getMinX(), b.getMaxY() - 1, b.getMaxX() - 1, b.getMaxY() - 1);
			}
		}
	}

	protected void onCalendarItemCreating(ItemConfirmEvent e)
	{
		DateTime start = e.getItem().getStartTime();
		DateTime end = e.getItem().getEndTime();

		boolean inZone = false;
		for (Zone z : zones)
		{
			if (DateTime.op_LessThanOrEqual(z.getStart(), start) && DateTime.op_LessThanOrEqual(end, z.getEnd()))
			{
				inZone = true;
				break;
			}
		}

		if (!inZone)
		{
			JOptionPane.showMessageDialog(this, "Events cannot be created outside zones.");
			e.setConfirm(false);
		}
	}

	protected void onItemModifying(ItemModifyConfirmEvent e)
	{
		ZoneEvent item = (ZoneEvent)e.getItem();
		DateTime start = e.getNewStartTime();
		DateTime end = e.getNewEndTime();

		boolean inZone = false;
		for (Zone z : zones)
		{
			if (DateTime.op_LessThanOrEqual(z.getStart(), start) && DateTime.op_LessThanOrEqual(end, z.getEnd()))
			{
				if (z.getType() == item.getZoneType())
				{
					inZone = true;
					break;
				}
			}
		}

		if (!inZone)
		{
			e.setConfirm(false);
		}
	}

	protected void onItemCreated(ItemEvent e)
	{
		ZoneEvent item = (ZoneEvent)e.getItem();

		for (Zone z : zones)
		{
			if (intersect(z.getStart(), z.getEnd(), item.getStartTime(), item.getEndTime()))
			{
				item.setZoneType(z.getType());
				break;
			}
		}
	}

	protected void onItemClick(ItemMouseEvent e)
	{
		if (e.getButton() == MouseEvent.BUTTON1 && e.getClicks() == 2)
		{
			calendar.resetDrag();

			JOptionPane.showMessageDialog(this, "This item is aligned to zone of type: " +
					(((ZoneEvent)e.getItem()).getZoneType() ? "'A'" : "'B'") + ".");
		}
	}
	
	private void addZone(boolean type)
	{
		if (calendar.getSelection().getRanges().size() < 2)
			return;

		DateTime start = (DateTime)calendar.getSelection().getRanges().get(0);
		DateTime end = (DateTime)calendar.getSelection().getRanges().get(1);

		// Check for zone intersection
		boolean inter = false;
		for (Zone z : zones)
		{
			if (intersect(z.getStart(), z.getEnd(), start, end))
			{
				inter = true;
				break;
			}
		}

		if (inter)
		{
			JOptionPane.showMessageDialog(this, "Cannot create intersecting zones.");
		}
		else
		{
			// Define a new zone
			zones.add(new Zone(start, end, "hey", type));

			calendar.getSelection().reset();
		}
	}
	
	/**
	 * Checks whether the specified time intervals intersect. 
	 */
	private static boolean intersect(
		DateTime start1, DateTime end1,
		DateTime start2, DateTime end2)
	{
		if (DateTime.op_LessThanOrEqual(end2, start1) || DateTime.op_LessThanOrEqual(end1, start2))
			return false;

		return true;
	}

	/**
	 * A list with all defined zones.
	 */
	private ArrayList<Zone> zones = new ArrayList<Zone>();
	
	private static final long serialVersionUID = 1L;
}
