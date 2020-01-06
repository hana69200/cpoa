/**
 * Copyright (c) 2017, MindFusion LLC - Bulgaria.
 */

package multipleGrouping;

import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.*;

import com.mindfusion.scheduling.*;
import com.mindfusion.scheduling.model.*;


public class MainWindow extends BaseWindow
{
	public MainWindow()
	{
		setSize(648, 651);
		setTitle("MindFusion.Scheduling Sample: Multiple Grouping");
		setInfo("This sample illustrates how to use multiple grouping in a Resource view.");

		createUI();

		// Calendar initialization start
		calendar.beginInit();
		calendar.setCurrentView(CalendarView.ResourceView);
		calendar.getResourceViewSettings().setAllowResizeRowHeaders(true);
		calendar.getResourceViewSettings().setRowHeaderSize(180);
		calendar.getResourceViewSettings().setViewStyle(ResourceViewStyle.Lanes);
		calendar.setTheme(ThemeType.Light);

		calendar.getResources().add(createResource(new Resource(),"Resource #1"));
		calendar.getResources().add(createResource(new Resource(),"Resource #2"));
		calendar.getResources().add(createResource(new Resource(),"Resource #3"));
		calendar.getResources().add(createResource(new Resource(),"Resource #4"));

		calendar.getContacts().add(createContact(new Contact(), "Contact #1", "Contact #1"));
		calendar.getContacts().add(createContact(new Contact(), "Contact #2", "Contact #2"));
		calendar.getContacts().add(createContact(new Contact(), "Contact #3", "Contact #3"));
		calendar.getContacts().add(createContact(new Contact(), "Contact #4", "Contact #4"));

		calendar.getLocations().add(createResource(new Location(), "Location #1"));
		calendar.getLocations().add(createResource(new Location(), "Location #2"));
		calendar.getLocations().add(createResource(new Location(), "Location #3"));
		calendar.getLocations().add(createResource(new Location(), "Location #4"));

		calendar.getTasks().add(createTask(new Task(), "Task #1"));
		calendar.getTasks().add(createTask(new Task(), "Task #2"));
		calendar.getTasks().add(createTask(new Task(), "Task #3"));
		calendar.getTasks().add(createTask(new Task(), "Task #4"));
		calendar.endInit();
		// Calendar initialization end
	}

	private void createUI()
	{
		JPanel container = new JPanel();
		content.add(container);
		
		JPanel tools = new JPanel();
		tools.setLayout(null);
		
		SpringLayout layout = new SpringLayout();
		layout.putConstraint(SpringLayout.EAST, calendar, 0, SpringLayout.EAST, container);
		layout.putConstraint(SpringLayout.NORTH, calendar, 0, SpringLayout.NORTH, container);
		layout.putConstraint(SpringLayout.WEST, calendar, 0, SpringLayout.WEST, container);
		layout.putConstraint(SpringLayout.SOUTH, calendar, -180, SpringLayout.SOUTH, container);
		
		layout.putConstraint(SpringLayout.EAST, tools, 0, SpringLayout.EAST, container);
		layout.putConstraint(SpringLayout.NORTH, tools, 0, SpringLayout.SOUTH, calendar);
		layout.putConstraint(SpringLayout.WEST, tools, 0, SpringLayout.WEST, container);
		layout.putConstraint(SpringLayout.SOUTH, tools, 0, SpringLayout.SOUTH, container);
		
		container.setLayout(layout);
		container.add(calendar);
		container.add(tools);
		
		JPanel pnlLeft = new JPanel();
		pnlLeft.setBounds(15, 15, 100, 150);
		pnlLeft.setBorder(new TitledBorder("Group by"));
		
		BoxLayout boxLayout = new BoxLayout(pnlLeft, BoxLayout.PAGE_AXIS);
		pnlLeft.setLayout(boxLayout);
		
		ButtonGroup bt = new ButtonGroup();
		addRadioButton(pnlLeft, true, "None", bt, new ActionImpl(GroupType.None));
		addRadioButton(pnlLeft, false, "Contacts", bt, new ActionImpl(GroupType.GroupByContacts));
		addRadioButton(pnlLeft, false, "Locations", bt, new ActionImpl(GroupType.GroupByLocations));
		addRadioButton(pnlLeft, false, "Tasks", bt, new ActionImpl(GroupType.GroupByTasks));
		addRadioButton(pnlLeft, false, "Resources", bt, new ActionImpl(GroupType.GroupByResources));
		
		JPanel pnlRight = new JPanel();
		pnlRight.setBounds(130, 15, 100, 150);
		pnlRight.setBorder(new TitledBorder("Then group by"));
		
		boxLayout = new BoxLayout(pnlRight, BoxLayout.PAGE_AXIS);
		pnlRight.setLayout(boxLayout);
		
		bt = new ButtonGroup();
		addRadioButton(pnlRight, true, "None", bt, new ActionImpl(GroupType.None, true));
		addRadioButton(pnlRight, false, "Contacts", bt, new ActionImpl(GroupType.GroupByContacts, true));
		addRadioButton(pnlRight, false, "Locations", bt, new ActionImpl(GroupType.GroupByLocations, true));
		addRadioButton(pnlRight, false, "Tasks", bt, new ActionImpl(GroupType.GroupByTasks, true));
		addRadioButton(pnlRight, false, "Resources", bt, new ActionImpl(GroupType.GroupByResources, true));
		
		tools.add(pnlLeft);
		tools.add(pnlRight);
	}
	
	private void addRadioButton(JPanel parent, boolean isChecked, String title, ButtonGroup group, ActionImpl action)
	{
		JRadioButton btn = new JRadioButton(title, isChecked);
		btn.addActionListener(action);
		parent.add(btn);
		group.add(btn);
	}
	
	private <T extends Resource> T createResource(T t, String name)
	{
		t.setName(name);
		return t;
	}

	private Contact createContact(Contact c, String name, String firstName)
	{
		createResource(c, name);
		c.setFirstName(firstName);
		return c;
	}

	private Task createTask(Task t, String name)
	{
		createResource(t, name);
		t.setSubject(name);
		return t;
	}
	
	class ActionImpl implements ActionListener
	{
		public ActionImpl(GroupType groupBy)
		{
			this(groupBy, false);
		}
		
		public ActionImpl(GroupType groupType, boolean thenGroupBy)
		{
			this.groupType = groupType;
			this.thenGroupBy = thenGroupBy;
		}

		@Override
		public void actionPerformed(ActionEvent e)
		{
			if (thenGroupBy)
				calendar.setSecondaryGroupType(groupType);
			else
				calendar.setGroupType(groupType);
			
		}
		
		GroupType groupType;
		boolean thenGroupBy;
	}
	
	private static final long serialVersionUID = 1L;
}
