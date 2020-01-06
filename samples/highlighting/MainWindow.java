/**
 * Copyright (c) 2017, MindFusion LLC - Bulgaria.
 */

package highlighting;

import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.*;
import java.util.EnumSet;

import javax.swing.*;
import javax.swing.event.*;

import com.mindfusion.common.*;
import com.mindfusion.drawing.*;
import com.mindfusion.scheduling.*;
import com.mindfusion.scheduling.model.*;


public class MainWindow extends BaseWindow
{
	public MainWindow()
	{
		setSize(648, 651);
		setTitle("MindFusion.Scheduling Sample: Highlighting");
		setInfo("<ul><li>Right-click on any cell in the view below to highlight this cell and " +
			"to bring a context menu, aligned with the border of the clicked cell.</li>" +
			"<li>Change the view and the relative position of the context menu from the 'View' " +
			"and 'Position' menus respectively.</li></ul>");

		// Calendar initialization start
		calendar.beginInit();
		calendar.getSelection().setEnabled(false);
		calendar.setCustomDraw(EnumSet.of(CustomDrawElements.TimetableCell, CustomDrawElements.ResourceViewCell));
		calendar.setGroupType(GroupType.GroupByContacts);
		calendar.setCurrentView(CalendarView.SingleMonth);
		
		calendar.getResourceViewSettings().setViewStyle(ResourceViewStyle.Lanes);

		Contact c1 = new Contact();
		c1.setFirstName("John");
		c1.setLastName("Withers");
		
		Contact c2 = new Contact();
		c2.setFirstName("Aby");
		c2.setMiddleName("S");
		c2.setLastName("S");
		
		calendar.getContacts().add(c1);
		calendar.getContacts().add(c2);
		calendar.endInit();
		// Calendar initialization end

		calendar.addCalendarListener(new CalendarAdapter(){
			public void dateClick(ResourceDateEvent e) {
				onCalendarDateClick(e);
			}
			public void draw(CalendarDrawEvent e) {
				onCalendarDraw(e);
			}
		});

		content.add(calendar);

		contextMenu = new JPopupMenu();
		contextMenu.add(new JMenuItem("Action 1"));
		contextMenu.add(new JMenuItem("Action 2"));
		contextMenu.addSeparator();
		contextMenu.add(new JMenuItem("Action 3"));
		contextMenu.addComponentListener(new ComponentAdapter(){
			public void componentHidden(java.awt.event.ComponentEvent arg0){
				onContextMenuHidden();
			}
		});
		contextMenu.addPopupMenuListener(new PopupMenuListener() {
			public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
			}
			
			public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
				onContextMenuHidden();
			}
			
			public void popupMenuCanceled(PopupMenuEvent e) {
			}
		});
		
		calendar.setComponentPopupMenu(contextMenu);
		
		ButtonGroup group1 = new ButtonGroup();
		ButtonGroup group2 = new ButtonGroup();
		JMenu mPosition = new JMenu("Position");
		JMenu mView = new JMenu("View");
		
		rdBtnMnIAtCurrsor = new JRadioButtonMenuItem("At Currsor");
		rdBtnMnIAtCurrsor.setSelected(true);
		JRadioButtonMenuItem rdBtnMnIAtCellBorder = new JRadioButtonMenuItem("At Cell Border");
		group1.add(rdBtnMnIAtCellBorder);
		group1.add(rdBtnMnIAtCurrsor);
		mPosition.add(rdBtnMnIAtCurrsor);
		mPosition.add(rdBtnMnIAtCellBorder);
		
		JRadioButtonMenuItem rdBtnMnISingleMonth = new JRadioButtonMenuItem("Single Month");
		rdBtnMnISingleMonth.setSelected(true);
		rdBtnMnISingleMonth.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				calendar.setCurrentView(CalendarView.SingleMonth);
			}
		});
		
		JRadioButtonMenuItem rdBtnMnITimetable = new JRadioButtonMenuItem("Timetable");
		rdBtnMnITimetable.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				calendar.setCurrentView(CalendarView.Timetable);
			}
		});
		
		JRadioButtonMenuItem rdBtnMnIResource = new JRadioButtonMenuItem("Resource");
		rdBtnMnIResource.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				calendar.setCurrentView(CalendarView.ResourceView);
			}
		});
		
		group2.add(rdBtnMnISingleMonth);
		group2.add(rdBtnMnITimetable);
		group2.add(rdBtnMnIResource);
		mView.add(rdBtnMnISingleMonth);
		mView.add(rdBtnMnITimetable);
		mView.add(rdBtnMnIResource);
		
		getJMenuBar().add(mView);
		getJMenuBar().add(mPosition);
	}
	
	protected void onContextMenuHidden() {
		calendar.getDayStyles().clear();

		_clickedResource = null;
		_clickedDate = DateTime.MinValue;

		calendar.repaint();
	}

	protected void onCalendarDraw(CalendarDrawEvent e) {
		if (DateTime.op_Inequality(_clickedDate, DateTime.MinValue) && _clickedResource != null)
		{
			if (e.getResource() == _clickedResource)
			{
				AwtGraphics g = new AwtGraphics(e.getGraphics());
				if (e.getElement() == CustomDrawElements.ResourceViewCell)
				{
					DateTime from = DateTime.op_Addition(e.getDate(), e.getStartTime());
					DateTime to = DateTime.op_Addition(e.getDate(), e.getEndTime());

					if (DateTime.op_LessThanOrEqual(from, _clickedDate) && 
							DateTime.op_LessThanOrEqual(_clickedDate, to))
					{
						Rectangle bounds = new Rectangle(e.getBounds());
						bounds.x += 1;
						bounds.y += 1;
						bounds.width -= 2;
						bounds.height -= 2;
						g.fillRectangle(Brushes.Yellow, bounds);
					}
				}
				else if (e.getElement() == CustomDrawElements.TimetableCell)
				{
					DateTime from = DateTime.op_Addition(e.getDate(), e.getStartTime());
					DateTime to = DateTime.op_Addition(e.getDate(), e.getEndTime());

					if (DateTime.op_LessThanOrEqual(from, _clickedDate) &&
							DateTime.op_LessThan(_clickedDate, to))
					{
						Rectangle bounds = new Rectangle(e.getBounds());
						bounds.x += 1;
						bounds.y += 1;
						bounds.width -= 2;
						bounds.height -= 2;
						g.fillRectangle(Brushes.Yellow, bounds);
					}
				}
			}
		}
	}

	protected void onCalendarDateClick(ResourceDateEvent e)
	{
		if (e.getClicks() == 1 && e.getButton() == MouseEvent.BUTTON3)
		{
			DateStyle style = new DateStyle();
			style.setFrom(e.getDate());
			style.setTo(e.getDate());
			style.setStyle(new Style());
			style.getStyle().setBrush(new SolidBrush(Colors.Yellow));

			calendar.getDayStyles().add(style);

			_clickedDate = e.getDate();
			_clickedResource = e.getResource();

			calendar.repaint();

			if (rdBtnMnIAtCurrsor.isSelected())
			{
				contextMenu.setLocation(MouseInfo.getPointerInfo().getLocation());
				contextMenu.setVisible(true);
			}
			else
			{
				Point p = new Point((int)e.getBounds().getMinX(), (int)e.getBounds().getMaxY());
				javax.swing.SwingUtilities.convertPointToScreen(p , calendar);
				contextMenu.setLocation(p);
				contextMenu.setVisible(true);
			}
		}
	}

	private DateTime _clickedDate = DateTime.MinValue;
	private Resource _clickedResource = null;
	private JPopupMenu contextMenu;
	
	private JRadioButtonMenuItem rdBtnMnIAtCurrsor;
	
	private static final long serialVersionUID = 1L;
}
