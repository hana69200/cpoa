/**
 * Copyright (c) 2017, MindFusion LLC - Bulgaria.
 */

package resourceTable;

import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.SystemColor;
import java.util.EnumSet;

import com.mindfusion.common.*;
import com.mindfusion.drawing.*;
import com.mindfusion.scheduling.*;
import com.mindfusion.scheduling.model.*;


public class MainWindow extends BaseWindow
{
	public MainWindow()
	{
		setSize(920, 682);
		setTitle("MindFusion.Scheduling Sample: Resource Table");
		setInfo("This sample demonstrates a static Resource view, which shows the " +
			"distribution of resources over a period of time.");

		// Calendar initialization start
		calendar.beginInit();
		calendar.setCurrentView(CalendarView.ResourceView);

		calendar.getItemSettings().getSelectedItemStyle().setHeaderFont(new Font("Verdana", Font.PLAIN, 9));
		calendar.getItemSettings().getSelectedItemStyle().setHeaderTextAlignment(EnumSet.of(TextAlignment.MiddleLeft));
		calendar.getItemSettings().getSelectedItemStyle().setHeaderTextShadowStyle(ShadowStyle.None);
		calendar.getItemSettings().getSelectedItemStyle().setHeaderTextShadowOffset(0);
		calendar.getItemSettings().getSelectedItemStyle().setHeaderTextColor(Colors.Teal);
		calendar.getItemSettings().getSelectedItemStyle().setHeaderBrush(Brushes.Transparent);
		calendar.getItemSettings().getSelectedItemStyle().setTextShadowStyle(ShadowStyle.None);
		calendar.getItemSettings().getSelectedItemStyle().setHeaderBorderLeftWidth(0);
		calendar.getItemSettings().getSelectedItemStyle().setHeaderBorderTopWidth(0);
		calendar.getItemSettings().getSelectedItemStyle().setHeaderBorderRightWidth(0);
		calendar.getItemSettings().getSelectedItemStyle().setHeaderBorderBottomWidth(0);
		
		calendar.getItemSettings().setShowMoreItemsCue(false);
		calendar.getItemSettings().setSpacing(0);
		calendar.getItemSettings().getStyle().setHeaderFont(new Font("Verdana", Font.PLAIN, 9));
		calendar.getItemSettings().getStyle().setHeaderTextAlignment(EnumSet.of(TextAlignment.MiddleLeft));
		calendar.getItemSettings().getStyle().setHeaderTextShadowStyle(ShadowStyle.None);
		calendar.getItemSettings().getStyle().setHeaderTextShadowOffset(0);
		calendar.getItemSettings().getStyle().setHeaderTextColor(new Color(22, 88, 92));
		calendar.getItemSettings().getStyle().setHeaderBrush(Brushes.Transparent);
		calendar.getItemSettings().getStyle().setTextShadowStyle(ShadowStyle.None);
		calendar.getItemSettings().getStyle().setHeaderBorderLeftWidth(0);
		calendar.getItemSettings().getStyle().setHeaderBorderTopWidth(0);
		calendar.getItemSettings().getStyle().setHeaderBorderRightWidth(0);
		calendar.getItemSettings().getStyle().setHeaderBorderBottomWidth(0);
		calendar.getItemSettings().setMoveBandSize(1);

		calendar.getResourceViewSettings().getBottomTimelineSettings().setFormat("EEE (MM/a)");
		calendar.getResourceViewSettings().getBottomTimelineSettings().setSize(15);
		calendar.getResourceViewSettings().getBottomTimelineSettings().getStyle().setHeaderBrush(Brushes.White);
		calendar.getResourceViewSettings().getBottomTimelineSettings().getStyle().setHeaderFont(new Font("Verdana", Font.BOLD, 10));
		calendar.getResourceViewSettings().getBottomTimelineSettings().getStyle().setHeaderTextAlignment(EnumSet.of(TextAlignment.MiddleCenter));
		calendar.getResourceViewSettings().getBottomTimelineSettings().getStyle().setHeaderTextColor(SystemColor.controlText);
		calendar.getResourceViewSettings().getBottomTimelineSettings().getStyle().setHeaderTextShadowOffset(0);
		calendar.getResourceViewSettings().getBottomTimelineSettings().getStyle().setHeaderTextShadowStyle(ShadowStyle.None);
		calendar.getResourceViewSettings().getBottomTimelineSettings().getStyle().setTextShadowStyle(ShadowStyle.None);
		calendar.getResourceViewSettings().getBottomTimelineSettings().getStyle().setLineColor(SystemColor.controlHighlight);
		calendar.getResourceViewSettings().getBottomTimelineSettings().getStyle().setTextShadowOffset(0);
		calendar.getResourceViewSettings().getBottomTimelineSettings().getStyle().setTextShadowStyle(ShadowStyle.None);
		calendar.getResourceViewSettings().getBottomTimelineSettings().setTickSize(15);
		calendar.getResourceViewSettings().setExpandableRows(false);
		calendar.getResourceViewSettings().setGridStyle(LineStyle.Solid);
		calendar.getResourceViewSettings().setInnerGridStyle(LineStyle.Invisible);
		calendar.getResourceViewSettings().setLaneSize(18);
		calendar.getResourceViewSettings().setScrollStep(Duration.fromDays(1));
		calendar.getResourceViewSettings().setShowPaddingDates(false);
		calendar.getResourceViewSettings().setShowResourceDuration(false);
		calendar.getResourceViewSettings().setTimelines(1);
		calendar.getResourceViewSettings().setTimelineScale(190);
		calendar.getResourceViewSettings().getStyle().setBorderBottomColor(SystemColor.controlHighlight);
		calendar.getResourceViewSettings().getStyle().setBorderRightColor(SystemColor.controlHighlight);
		calendar.getResourceViewSettings().getStyle().setHeaderBorderBottomColor(SystemColor.controlHighlight);
		calendar.getResourceViewSettings().getStyle().setHeaderBorderBottomWidth(1);
		calendar.getResourceViewSettings().getStyle().setHeaderBorderLeftColor(Colors.Transparent);
		calendar.getResourceViewSettings().getStyle().setHeaderBorderLeftWidth(1);
		calendar.getResourceViewSettings().getStyle().setHeaderBorderRightColor(SystemColor.controlHighlight);
		calendar.getResourceViewSettings().getStyle().setHeaderBorderRightWidth(1);
		calendar.getResourceViewSettings().getStyle().setHeaderBorderTopColor(Colors.Transparent);
		calendar.getResourceViewSettings().getStyle().setHeaderBorderTopWidth(1);
		calendar.getResourceViewSettings().getStyle().setHeaderBrush(new SolidBrush(Colors.White));
		calendar.getResourceViewSettings().getStyle().setHeaderFont(new Font("Verdana", Font.BOLD, 10));
		calendar.getResourceViewSettings().getStyle().setHeaderTextShadowOffset(0);
		calendar.getResourceViewSettings().getStyle().setHeaderTextShadowStyle(ShadowStyle.None);
		calendar.getResourceViewSettings().getStyle().setHeaderTextColor(SystemColor.controlText);
		calendar.getResourceViewSettings().getStyle().setLineColor(SystemColor.controlHighlight);
		calendar.getResourceViewSettings().getStyle().setTextShadowOffset(0);
		calendar.getResourceViewSettings().getStyle().setTextShadowStyle(ShadowStyle.None);
		calendar.getResourceViewSettings().setViewStyle(ResourceViewStyle.Lanes);

		calendar.setDate(new DateTime(2006, 3, 27, 0, 0, 0, 0));
		calendar.setEndDate(new DateTime(2006, 4, 1, 0, 0, 0, 0));
		calendar.setGroupType(GroupType.GroupByContacts);

		calendar.setAllowInplaceEdit(false);
		calendar.setEnabled(false);

		calendar.addCalendarListener(new CalendarAdapter() {
			public void draw(CalendarDrawEvent e) {
				onCalendarDraw(e);
			}
		});

		initContacts();
		initItems();
		calendar.endInit();
		// Calendar initialization end

		content.add(calendar);
	}

	private void initContacts() {
		Contact contact = new Contact();
		contact.setFirstName("Mike");
		contact.setId("IdMike");
		contact.setName("Mike");
		calendar.getContacts().add(contact);

		contact = new Contact();
		contact.setFirstName("Greg");
		contact.setId("IdGreg");
		contact.setName("Greg");
		calendar.getContacts().add(contact);

		contact = new Contact();
		contact.setFirstName("Tom");
		contact.setId("IdTom");
		contact.setName("Tom");
		calendar.getContacts().add(contact);

		contact = new Contact();
		contact.setFirstName("Mando");
		contact.setId("IdMando");
		contact.setName("Mando");
		calendar.getContacts().add(contact);

		contact = new Contact();
		contact.setFirstName("Alfredo");
		contact.setId("IdAlfredo");
		contact.setName("Alfredo");
		calendar.getContacts().add(contact);

		contact = new Contact();
		contact.setFirstName("Chuck");
		contact.setId("IdChuck");
		contact.setName("Chuck");
		calendar.getContacts().add(contact);

		contact = new Contact();
		contact.setFirstName("JJ");
		contact.setId("IdJJ");
		contact.setName("JJ");
		calendar.getContacts().add(contact);

		contact = new Contact();
		contact.setFirstName("Luis");
		contact.setId("IdLuis");
		contact.setName("Luis");
		calendar.getContacts().add(contact);

		contact = new Contact();
		contact.setFirstName("Scott");
		contact.setId("IdScott");
		contact.setName("Scott");
		calendar.getContacts().add(contact);

		contact = new Contact();
		contact.setFirstName("Ramon");
		contact.setId("IdRamon");
		contact.setName("Ramon");
		calendar.getContacts().add(contact);
	}

	private void initItems() {
		// Create the appointments
		Appointment app;

		app = new Appointment();
		app.setStartTime(new DateTime(2006, 3, 27));
		app.setEndTime(new DateTime(2006, 3, 28));
		app.getContacts().add(calendar.getSchedule().getContacts().get("IdMike"));
		app.setHeaderText("21965 Carbon Mesa Rd (1)");
		app.setPriority(0);
		calendar.getSchedule().getItems().add(app);

		app = new Appointment();
		app.setStartTime(new DateTime(2006, 3, 27));
		app.setEndTime(new DateTime(2006, 3, 28));
		app.getContacts().add(calendar.getSchedule().getContacts().get("IdMike"));
		app.setHeaderText("777 Ocampo Dr (1)");
		app.setPriority(-1);
		calendar.getSchedule().getItems().add(app);

		app = new Appointment();
		app.setStartTime(new DateTime(2006, 3, 27));
		app.setEndTime(new DateTime(2006, 3, 28));
		app.getContacts().add(calendar.getSchedule().getContacts().get("IdMike"));
		app.setHeaderText("401 Almar Ave (1)");
		app.setPriority(-2);
		calendar.getSchedule().getItems().add(app);

		app = new Appointment();
		app.setStartTime(new DateTime(2006, 3, 27));
		app.setEndTime(new DateTime(2006, 3, 28));
		app.getContacts().add(calendar.getSchedule().getContacts().get("IdMike"));
		app.setHeaderText("16680 Charmel Ln (1)");
		app.setPriority(-3);
		calendar.getSchedule().getItems().add(app);

		app = new Appointment();
		app.setStartTime(new DateTime(2006, 3, 27));
		app.setEndTime(new DateTime(2006, 3, 28));
		app.getContacts().add(calendar.getSchedule().getContacts().get("IdMike"));
		app.setHeaderText("10785 Queensland St (1)");
		app.setPriority(-4);
		calendar.getSchedule().getItems().add(app);

		app = new Appointment();
		app.setStartTime(new DateTime(2006, 3, 27));
		app.setEndTime(new DateTime(2006, 3, 28));
		app.getContacts().add(calendar.getSchedule().getContacts().get("IdMike"));
		app.setHeaderText("1237 25th St (1)");
		app.setPriority(-5);
		calendar.getSchedule().getItems().add(app);

		app = new Appointment();
		app.setStartTime(new DateTime(2006, 3, 27));
		app.setEndTime(new DateTime(2006, 3, 28));
		app.getContacts().add(calendar.getSchedule().getContacts().get("IdMike"));
		app.setHeaderText("3569 Veteran Ave (1)");
		app.setPriority(-6);
		calendar.getSchedule().getItems().add(app);

		app = new Appointment();
		app.setStartTime(new DateTime(2006, 3, 28));
		app.setEndTime(new DateTime(2006, 3, 29));
		app.getContacts().add(calendar.getSchedule().getContacts().get("IdMike"));
		app.setHeaderText("242 Aderno Way (1)");
		app.setPriority(0);
		calendar.getSchedule().getItems().add(app);

		app = new Appointment();
		app.setStartTime(new DateTime(2006, 3, 27));
		app.setEndTime(new DateTime(2006, 3, 28));
		app.getContacts().add(calendar.getSchedule().getContacts().get("IdGreg"));
		app.getContacts().add(calendar.getSchedule().getContacts().get("IdScott"));
		app.setHeaderText("8359 Hollywood Blvd (17)");
		app.setPriority(0);
		calendar.getSchedule().getItems().add(app);

		app = new Appointment();
		app.setStartTime(new DateTime(2006, 3, 27));
		app.setEndTime(new DateTime(2006, 3, 28));
		app.getContacts().add(calendar.getSchedule().getContacts().get("IdGreg"));
		app.setHeaderText("2788 Monte Mar Ter (14)");
		app.setPriority(-1);
		calendar.getSchedule().getItems().add(app);

		// Dummy item to expand the rows of certain resources
		app = new Appointment();
		app.setStartTime(new DateTime(2006, 3, 27));
		app.setEndTime(new DateTime(2006, 3, 28));
		app.getContacts().add(calendar.getSchedule().getContacts().get("IdGreg"));
		app.getContacts().add(calendar.getSchedule().getContacts().get("IdTom"));
		app.getContacts().add(calendar.getSchedule().getContacts().get("IdMando"));
		app.getContacts().add(calendar.getSchedule().getContacts().get("IdAlfredo"));
		app.getContacts().add(calendar.getSchedule().getContacts().get("IdChuck"));
		app.getContacts().add(calendar.getSchedule().getContacts().get("IdJJ"));
		app.getContacts().add(calendar.getSchedule().getContacts().get("IdLuis"));
		app.getContacts().add(calendar.getSchedule().getContacts().get("IdScott"));
		app.getContacts().add(calendar.getSchedule().getContacts().get("IdRamon"));
		app.setHeaderText("");
		app.setPriority(-100);
		calendar.getSchedule().getItems().add(app);

		// Dummy item #2
		app = new Appointment();
		app.setStartTime(new DateTime(2006, 3, 27));
		app.setEndTime(new DateTime(2006, 3, 28));
		app.getContacts().add(calendar.getSchedule().getContacts().get("IdTom"));
		app.getContacts().add(calendar.getSchedule().getContacts().get("IdAlfredo"));
		app.getContacts().add(calendar.getSchedule().getContacts().get("IdChuck"));
		app.getContacts().add(calendar.getSchedule().getContacts().get("IdJJ"));
		app.getContacts().add(calendar.getSchedule().getContacts().get("IdLuis"));
		app.getContacts().add(calendar.getSchedule().getContacts().get("IdRamon"));
		app.setHeaderText("");
		app.setPriority(-100);
		calendar.getSchedule().getItems().add(app);

		app = new Appointment();
		app.setStartTime(new DateTime(2006, 3, 29));
		app.setEndTime(new DateTime(2006, 3, 30));
		app.getContacts().add(calendar.getSchedule().getContacts().get("IdGreg"));
		app.getContacts().add(calendar.getSchedule().getContacts().get("IdChuck"));
		app.setHeaderText("11301 Wilshire Blvd. (43)");
		app.setPriority(0);
		calendar.getSchedule().getItems().add(app);

		app = new Appointment();
		app.setStartTime(new DateTime(2006, 3, 27));
		app.setEndTime(new DateTime(2006, 3, 28));
		app.getContacts().add(calendar.getSchedule().getContacts().get("IdTom"));
		app.getContacts().add(calendar.getSchedule().getContacts().get("IdScott"));
		app.setHeaderText("6200 San Fernando Rd (?)");
		app.setPriority(-1);
		calendar.getSchedule().getItems().add(app);

		app = new Appointment();
		app.setStartTime(new DateTime(2006, 3, 29));
		app.setEndTime(new DateTime(2006, 3, 30));
		app.getContacts().add(calendar.getSchedule().getContacts().get("IdTom"));
		app.getContacts().add(calendar.getSchedule().getContacts().get("IdJJ"));
		app.setHeaderText("247 W San Bernardino Rd (17)");
		app.setPriority(0);
		calendar.getSchedule().getItems().add(app);

		app = new Appointment();
		app.setStartTime(new DateTime(2006, 3, 27));
		app.setEndTime(new DateTime(2006, 3, 28));
		app.getContacts().add(calendar.getSchedule().getContacts().get("IdMando"));
		app.getContacts().add(calendar.getSchedule().getContacts().get("IdJJ"));
		app.setHeaderText("1156 Napoli Dr (6)");
		app.setPriority(0);
		calendar.getSchedule().getItems().add(app);

		app = new Appointment();
		app.setStartTime(new DateTime(2006, 3, 27));
		app.setEndTime(new DateTime(2006, 3, 28));
		app.getContacts().add(calendar.getSchedule().getContacts().get("IdMando"));
		app.getContacts().add(calendar.getSchedule().getContacts().get("IdLuis"));
		app.setHeaderText("1801 San Vicente Blvd (16)");
		app.setPriority(-1);
		calendar.getSchedule().getItems().add(app);

		app = new Appointment();
		app.setStartTime(new DateTime(2006, 3, 29));
		app.setEndTime(new DateTime(2006, 3, 30));
		app.getContacts().add(calendar.getSchedule().getContacts().get("IdMando"));
		app.getContacts().add(calendar.getSchedule().getContacts().get("IdScott"));
		app.setHeaderText("245 Maple Dr (7)");
		app.setPriority(0);
		calendar.getSchedule().getItems().add(app);

		app = new Appointment();
		app.setStartTime(new DateTime(2006, 3, 30));
		app.setEndTime(new DateTime(2006, 3, 31));
		app.getContacts().add(calendar.getSchedule().getContacts().get("IdMando"));
		app.getContacts().add(calendar.getSchedule().getContacts().get("IdJJ"));
		app.setHeaderText("162 Acari Dr (16)");
		app.setPriority(0);
		calendar.getSchedule().getItems().add(app);

		app = new Appointment();
		app.setStartTime(new DateTime(2006, 3, 27));
		app.setEndTime(new DateTime(2006, 3, 28));
		app.getContacts().add(calendar.getSchedule().getContacts().get("IdAlfredo"));
		app.getContacts().add(calendar.getSchedule().getContacts().get("IdChuck"));
		app.setHeaderText("427 Mesa Lila Rd (21)");
		app.setPriority(0);
		calendar.getSchedule().getItems().add(app);

		app = new Appointment();
		app.setStartTime(new DateTime(2006, 3, 30));
		app.setEndTime(new DateTime(2006, 3, 31));
		app.getContacts().add(calendar.getSchedule().getContacts().get("IdChuck"));
		app.getContacts().add(calendar.getSchedule().getContacts().get("IdJJ"));
		app.setHeaderText("2201 Beverly Dr (41)");
		app.setPriority(-1);
		calendar.getSchedule().getItems().add(app);
	}

	protected void onCalendarDraw(CalendarDrawEvent e)
	{
		AwtGraphics g = new AwtGraphics(e.getGraphics());
		if (e.getElement() == CustomDrawElements.ResourceViewCellComplete)
		{
			Rectangle bounds = new Rectangle(e.getBounds());
			bounds.x += 1;
			bounds.width -= 1;

			TextFormat f = new TextFormat(); 
			
			if (e.getResource() != null)
			{
				if (DateTime.op_Equality(e.getDate().getDate(), new DateTime(2006, 3, 27)))
				{
					if (e.getResource().getId().equals("IdRamon"))
					{
						g.fillRectangle(_brush1, bounds);
						g.drawString("Office", _font, _textBrush, bounds, f);
					}
				}

				if (DateTime.op_Equality(e.getDate().getDate(), new DateTime(2006, 3, 28)))
				{
					if (e.getResource().getId().equals("IdGreg") ||
						e.getResource().getId().equals("IdTom") ||
						e.getResource().getId().equals("IdMando") ||
						e.getResource().getId().equals("IdAlfredo") ||
						e.getResource().getId().equals("IdJJ") ||
						e.getResource().getId().equals("IdLuis") ||
						e.getResource().getId().equals("IdScott") ||
						e.getResource().getId().equals("IdRamon"))
					{
						g.fillRectangle(_brush1, bounds);
						g.drawString("Office", _font, _textBrush, bounds, f);
					}

					if (e.getResource().getId().equals("IdChuck"))
					{
						g.fillRectangle(_brush2, bounds);
						g.drawString("Research", _font, _textBrush, bounds, f);
					}
				}

				if (DateTime.op_Equality(e.getDate().getDate(), new DateTime(2006, 3, 29)))
				{
					if (e.getResource().getId().equals("IdMike") ||
						e.getResource().getId().equals("IdAlfredo") ||
						e.getResource().getId().equals("IdLuis") ||
						e.getResource().getId().equals("IdRamon"))
					{
						g.fillRectangle(_brush1, bounds);
						g.drawString("Office", _font, _textBrush, bounds, f);
					}
				}

				if (DateTime.op_Equality(e.getDate().getDate(), new DateTime(2006, 3, 30)))
				{
					if (e.getResource().getId().equals("IdMike") ||
						e.getResource().getId().equals("IdGreg") ||
						e.getResource().getId().equals("IdTom") ||
						e.getResource().getId().equals("IdAlfredo") ||
						e.getResource().getId().equals("IdLuis") ||
						e.getResource().getId().equals("IdScott") ||
						e.getResource().getId().equals("IdRamon"))
					{
						g.fillRectangle(_brush1, bounds);
						g.drawString("Office", _font, _textBrush, bounds, f);
					}
				}

				if (DateTime.op_Equality(e.getDate().getDate(), new DateTime(2006, 3, 31)))
				{
					g.fillRectangle(_brush1, bounds);
					g.drawString("Office", _font, _textBrush, bounds, f);
				}
			}
		}

		else if (e.getElement() == CustomDrawElements.ResourceViewRowHeader)
		{
			g.drawLine(Pens.White,
				e.getBounds().getMaxX() - 1,
				e.getBounds().getMinY(),
				e.getBounds().getMaxX() - 1,
				e.getBounds().getMaxY() - 2);
		}
	}

	private Brush _brush1 = new SolidBrush(new Color(254, 249, 207));
	private Brush _brush2 = new SolidBrush(new Color(254, 192, 203));
	private Font _font = new Font("Verdana", Font.PLAIN, 10);
	private Brush _textBrush = new SolidBrush(new Color(22, 88, 92));
	
	private static final long serialVersionUID = 1L;
}