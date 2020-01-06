/**
 * Copyright (c) 2017, MindFusion LLC - Bulgaria.
 */

package listView;

import java.awt.Color;
import java.awt.event.*;
import java.util.EnumSet;

import javax.swing.*;

import com.mindfusion.common.*;
import com.mindfusion.scheduling.*;
import com.mindfusion.scheduling.model.*;


public class MainWindow extends BaseWindow
{
	public MainWindow()
	{
		setSize(648, 651);
		setTitle("MindFusion.Scheduling Sample: List View");
		setInfo("<ul><li>Enable Free dragging from the 'Tools' menu. Free dragging allows " +
			"users to drag items anywhere within a List view. Once dropped the item stays " +
			"at its drop location.</li></ul>");

		// Calendar initialization start
		calendar.beginInit();

		calendar.setAllowInplaceEdit(false);
		calendar.setTheme(ThemeType.Silver);
		calendar.setCurrentView(CalendarView.List);
		calendar.getListViewSettings().setCellUnits(TimeUnit.Week);
		calendar.getListViewSettings().setOrientation(Orientation.Horizontal);
		calendar.getListViewSettings().setRotateHeaderTexts(false);
		calendar.getListViewSettings().setHeaderShadowColor(new Color(80, 0, 0, 0));
		calendar.getListViewSettings().setHeaderShadowStyle(ShadowStyle.Fading);
		calendar.getListViewSettings().setHeaderSize(20);
		calendar.getListViewSettings().setNumberOfCells(52);
		calendar.getListViewSettings().setTitleFormat("EEE MM/dd/yyyy");
		calendar.getListViewSettings().setVisibleCells(4);
		calendar.getListViewSettings().setVisibleColumns(3);
		
		calendar.getListViewSettings().getCellSettings().setHeaderPosition(Position.None);
		
		calendar.setCurrentTime(new DateTime(2012, 3, 4));
		calendar.setDate(new DateTime(2012, 10, 27));
		calendar.setEndDate(new DateTime(2012, 11, 27));
		calendar.getItemSettings().setSize(32);
		calendar.endInit();

		calendar.beginInit();
		DateTime firstDate = calendar.getFirstVisibleDate();
		DateTime oneWeek = firstDate.addDays(7);
		DateTime twoWeeks = oneWeek.addDays(7);
		
		// Initialize appointments
		Appointment app;

		app = new Appointment();
		app.setStartTime(firstDate);
		app.setEndTime(oneWeek);
		app.setHeaderText("Naruto #35\nM 10/27 - F 11/10");
		app.setPriority(0);
		calendar.getSchedule().getItems().add(app);

		app = new Appointment();
		app.setStartTime(firstDate);
		app.setEndTime(oneWeek);
		app.setHeaderText("Dragon Ball #10\nM 10/27 - F 11/10");
		app.setPriority(-1);
		calendar.getSchedule().getItems().add(app);

		app = new Appointment();
		app.setStartTime(firstDate);
		app.setEndTime(oneWeek);
		app.setHeaderText("Hikaru No Go #38\nM 10/27 - F 11/10");
		app.setPriority(-2);
		calendar.getSchedule().getItems().add(app);

		app = new Appointment();
		app.setStartTime(firstDate);
		app.setEndTime(oneWeek);
		app.setHeaderText("MAR #5\nM 10/27 - F 11/10");
		app.setPriority(-3);
		calendar.getSchedule().getItems().add(app);

		app = new Appointment();
		app.setStartTime(firstDate);
		app.setEndTime(oneWeek);
		app.setHeaderText("Pokemnon: Johto...\nM 10/27 - F 11/10");
		app.setPriority(-4);
		calendar.getSchedule().getItems().add(app);

		app = new Appointment();
		app.setStartTime(firstDate);
		app.setEndTime(oneWeek);
		app.setHeaderText("One Piece\nM 10/27 - F 11/10");
		app.setPriority(-5);
		calendar.getSchedule().getItems().add(app);

		app = new Appointment();
		app.setStartTime(oneWeek);
		app.setEndTime(twoWeeks);
		app.setHeaderText("Naruto #36\nM 11/6 - F 11/17");
		app.setPriority(0);
		calendar.getSchedule().getItems().add(app);

		app = new Appointment();
		app.setStartTime(oneWeek);
		app.setEndTime(twoWeeks);
		app.setHeaderText("Dragon Ball #11\nM 11/6 - F 11/17");
		app.setPriority(-1);
		calendar.getSchedule().getItems().add(app);

		app = new Appointment();
		app.setStartTime(oneWeek);
		app.setEndTime(twoWeeks);
		app.setHeaderText("Hikaru No Go\nM 11/6 - F 11/17");
		app.setPriority(-2);
		calendar.getSchedule().getItems().add(app);

		app = new Appointment();
		app.setStartTime(oneWeek);
		app.setEndTime(twoWeeks);
		app.setHeaderText("MAR #39\nM 11/6 - F 11/17");
		app.setPriority(-3);
		calendar.getSchedule().getItems().add(app);

		app = new Appointment();
		app.setStartTime(oneWeek);
		app.setEndTime(twoWeeks);
		app.setHeaderText("Pokemon: Johto...\nM 11/6 - F 11/17");
		app.setPriority(-4);
		calendar.getSchedule().getItems().add(app);

		app = new Appointment();
		app.setStartTime(oneWeek);
		app.setEndTime(twoWeeks);
		app.setHeaderText("One Piece\nM 11/6 - F 11/17");
		app.setPriority(-5);
		calendar.getSchedule().getItems().add(app);

		app = new Appointment();
		app.setStartTime(oneWeek);
		app.setEndTime(twoWeeks);
		app.setHeaderText("Dragon Ball\nM 11/6 - F 11/17");
		app.setPriority(-6);
		calendar.getSchedule().getItems().add(app);

		app = new Appointment();
		app.setStartTime(oneWeek);
		app.setEndTime(twoWeeks);
		app.setHeaderText("Hikaru No Go\nM 11/6 - F 11/17");
		app.setPriority(-7);
		calendar.getSchedule().getItems().add(app);

		app = new Appointment();
		app.setStartTime(oneWeek);
		app.setEndTime(twoWeeks);
		app.setHeaderText("MAR\nM 11/6 - F 11/17");
		app.setPriority(-8);
		calendar.getSchedule().getItems().add(app);

		app = new Appointment();
		app.setStartTime(oneWeek);
		app.setEndTime(twoWeeks);
		app.setHeaderText("Pokemon: Johto...\nM 11/6 - F 11/17");
		app.setPriority(-9);
		calendar.getSchedule().getItems().add(app);

		app = new Appointment();
		app.setStartTime(oneWeek);
		app.setEndTime(twoWeeks);
		app.setHeaderText("One Piece\nM 11/6 - F 11/17");
		app.setPriority(-10);
		calendar.getSchedule().getItems().add(app);

		calendar.endInit();
		// Calendar initialization end
		
		content.add(calendar);

		initMenuItems();
	}
	
	private void initMenuItems()
	{
		JMenu menu = new JMenu("Tools");
		getJMenuBar().add(menu);
		
		ButtonGroup bg = new ButtonGroup();
		
		JRadioButtonMenuItem item = new JRadioButtonMenuItem("Toggle Free Dragging");
		item.setSelected(calendar.getListViewSettings().getFreeDrag());
		item.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				onToggleFreeDragging();
			}
		});
		menu.add(item);
		bg.add(item);
		
		item = new JRadioButtonMenuItem("Toggle Sub-Headers");
		item.setSelected(calendar.getListViewSettings().getHeaderStyle().contains(ListViewHeaderStyles.Subheader));
		item.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				onToggleSubHeaders();
			}
		});
		menu.add(item);
		bg.add(item);
		menu.addSeparator();
		
		bg = new ButtonGroup();
		item = new JRadioButtonMenuItem("Snap to Cell");
		item.setSelected(!calendar.getListViewSettings().getEnableSnapping());
		item.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				onSnapToCell();
			}
		});
		menu.add(item);
		bg.add(item);
		
		item = new JRadioButtonMenuItem("Snap to Second");
		item.setSelected(calendar.getListViewSettings().getEnableSnapping() &&
				calendar.getListViewSettings().getSnapUnit() == TimeUnit.Second);
		item.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				onSnapToSeconds();
			}
		});
		menu.add(item);
		bg.add(item);
		
		item = new JRadioButtonMenuItem("Snap to Hour");
		item.setSelected(calendar.getListViewSettings().getEnableSnapping() &&
				calendar.getListViewSettings().getSnapUnit() == TimeUnit.Hour);
		item.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				onSnapToHour();
			}
		});
		menu.add(item);
		bg.add(item);
		
		item = new JRadioButtonMenuItem("Snap to Day");
		item.setSelected(calendar.getListViewSettings().getEnableSnapping() &&
				calendar.getListViewSettings().getSnapUnit() == TimeUnit.Day);
		item.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				onSnapToDay();
			}
		});
		menu.add(item);
		bg.add(item);
	}

	protected void onToggleFreeDragging()
	{
		if (calendar.getListViewSettings().getFreeDrag())
			calendar.getListViewSettings().setFreeDrag(false);
		else
			calendar.getListViewSettings().setFreeDrag(true);
	}

	protected void onToggleSubHeaders()
	{
		EnumSet<ListViewHeaderStyles> style = calendar.getListViewSettings().getHeaderStyle();
		if (!style.contains(ListViewHeaderStyles.Subheader))
			style.add(ListViewHeaderStyles.Subheader);
		else
			style.remove(ListViewHeaderStyles.Subheader);
		calendar.getListViewSettings().setHeaderStyle(style);
	}

	protected void onSnapToCell()
	{
		// Effectively disable custom snapping
		calendar.getListViewSettings().setEnableSnapping(false);
	}

	protected void onSnapToSeconds()
	{
		// Enable custom snapping
		calendar.getListViewSettings().setEnableSnapping(true);
		calendar.getListViewSettings().setSnapUnit(TimeUnit.Second);
	}

	protected void onSnapToHour()
	{
		// Enable custom snapping
		calendar.getListViewSettings().setEnableSnapping(true);
		calendar.getListViewSettings().setSnapUnit(TimeUnit.Hour);
	}

	protected void onSnapToDay()
	{
		// Enable custom snapping
		calendar.getListViewSettings().setEnableSnapping(true);
		calendar.getListViewSettings().setSnapUnit(TimeUnit.Day);
	}

	private static final long serialVersionUID = 1L;
}
