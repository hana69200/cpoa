/**
 * Copyright (c) 2017, MindFusion LLC - Bulgaria.
 */

package resources;

import java.awt.Font;
import java.util.*;

import com.mindfusion.common.*;
import com.mindfusion.scheduling.*;
import com.mindfusion.scheduling.model.*;


public class MainWindow extends BaseWindow
{
	public MainWindow()
	{
		setSize(648, 651);
		setTitle("MindFusion.Scheduling Sample: Resources");
		setInfo("This sample demonstrates the Resource view. Several random appointments " +
			"are created for each resource at the application start.<ul><li>Try collapsing " +
			"and expanding individual resources by clicking on the expand/collapse button " +
			"in the header area of the corresponding resource.</li></ul>");

		// Calendar initialization start
		calendar.beginInit();
		calendar.setTheme(ThemeType.Lila);
		calendar.setCurrentView(CalendarView.ResourceView);
		calendar.setGroupType(GroupType.GroupByContacts);
		
		calendar.getItemSettings().getSelectedItemStyle().setHeaderFont(new Font("Arial", Font.BOLD | Font.ITALIC, 12));
		calendar.getItemSettings().getSelectedItemStyle().setHeaderTextShadowStyle(ShadowStyle.None);
		calendar.getItemSettings().getSelectedItemStyle().setTextShadowStyle(ShadowStyle.None);
		calendar.getItemSettings().getStyle().setHeaderFont(new Font("Arial", Font.BOLD | Font.ITALIC, 12));
		calendar.getItemSettings().getStyle().setHeaderTextShadowStyle(ShadowStyle.None);
		calendar.getItemSettings().getStyle().setTextShadowStyle(ShadowStyle.None);
		
		calendar.getResourceViewSettings().setGroupRowHeader(false);
		calendar.getResourceViewSettings().getMiddleTimelineSettings().setFormat("d MMMM");
		calendar.getResourceViewSettings().getMiddleTimelineSettings().setUnit(TimeUnit.Week);
		calendar.getResourceViewSettings().setSnapUnit(TimeUnit.Hour);
		calendar.getResourceViewSettings().getTopTimelineSettings().setFormat("MMMM yyyy");
		calendar.getResourceViewSettings().getTopTimelineSettings().setUnit(TimeUnit.Month);
		calendar.getResourceViewSettings().setViewStyle(ResourceViewStyle.Lanes);
		
		// Create some contacts
		Contact contact = new Contact();
		contact.setFirstName("Douglas");
		contact.setLastName("Clark");
		calendar.getContacts().add(contact);

		contact = new Contact();
		contact.setFirstName("Jayna");
		contact.setLastName("Silversteed");
		calendar.getContacts().add(contact);

		contact = new Contact();
		contact.setFirstName("Joe");
		contact.setLastName("Timberwood");
		calendar.getContacts().add(contact);

		Appointment app = null;
		Random r = new Random();

		for (int i = 0; i < 20; i++)
		{
			app = new Appointment();
			app.setStartTime(DateTime.op_Addition(calendar.getDate(), Duration.fromDays(r.nextInt(10) + 3)));
			app.setEndTime(DateTime.op_Addition(app.getStartTime(), Duration.fromHours(24 + r.nextInt(24))));
			app.setHeaderText("Resource " + i);
			app.getContacts().add(calendar.getContacts().get(r.nextInt(3)));
			calendar.getSchedule().getItems().add(app);
		}
		calendar.endInit();
		// Calendar initialization end

		content.add(calendar);
	}


	private static final long serialVersionUID = 1L;
}
