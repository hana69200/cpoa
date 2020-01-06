/**
 * Copyright (c) 2017, MindFusion LLC - Bulgaria.
 */

package resourceTimeline;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.geom.Rectangle2D;
import java.util.*;

import com.mindfusion.common.*;
import com.mindfusion.drawing.Brush;
import com.mindfusion.drawing.Pen;
import com.mindfusion.drawing.SolidBrush;
import com.mindfusion.scheduling.*;
import com.mindfusion.scheduling.model.*;


public class MainWindow extends BaseWindow
{
	private RangeSelector timeline;
	
	public MainWindow()
	{
		super();
		initializeComponent();
		this.addComponentListener(new ComponentAdapter() {
		    public void componentResized(ComponentEvent e) {
		        updateTimeline();      
		    }
		});
		setSize(648, 651);
		setTitle("MindFusion.Scheduling Sample: Resource Timeline");
		setInfo("This sample demonstrates how to integrate the Calendar control with the RangeSelector control.");

		timeline = new RangeSelector();
		timeline.setButtonSize(12);
		timeline.setLocation(0, 430);
		timeline.setMinimum(0D);
		timeline.setName("timeline");
		timeline.setSize(628, 65);
		timeline.setPreferredSize(new Dimension(628, 65));
		//timeline.setMaximumSize(new Dimension(628, 65));
		//this.timeline.TabIndex = 1;
		
		// Calendar initialization start
		calendar.beginInit();
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
		
		timeline.addRangeSelectorListener(new RangeSelectorListener(){

			@Override
			public void valueChanged(EventObject e)
			{
				calendar.setViewport(new DateTime((long)timeline.getSelectionStart()),
						new DateTime((long)timeline.getSelectionEnd()), true);
				timeline.repaint();
			}

			@Override
			public void drawBackground(DrawEvent e)
			{
				Rectangle2D bounds = e.getBounds();
				Graphics2D g = (Graphics2D)e.getGraphics();
				// Draw days
				DateTime date = calendar.getDate();
				while (date.isLessThan(calendar.getEndDate()))
				{
					double prc = (double)(date.getTicks() - calendar.getDate().getTicks()) / (calendar.getEndDate().getTicks() - calendar.getDate().getTicks());

					int x = (int)(bounds.getX() + (int)(prc * bounds.getWidth()));
					Pen pen = new Pen(calendar.getResourceViewSettings().getStyle().getBorderLeftColor());
					pen.applyTo(g);
					g.drawLine(x, (int)bounds.getY(), x, (int)(bounds.getY()+bounds.getHeight()));
					Brush brush = new SolidBrush(calendar.getResourceViewSettings().getStyle().getHeaderTextColor());
					Font f = calendar.getResourceViewSettings().getStyle().getHeaderFont();
					g.setFont(f);
					brush.applyTo(g, bounds);
					g.drawString(date.toString("MMM"), x+2, (int)(bounds.getY()+bounds.getHeight()-2));
					
					date = date.addDays(DateTime.daysInMonth(date.getYear(), date.getMonth()) - date.getDay() + 1);
				}
			}
			
		});
		
		// Calendar initialization end
		initTimeline();
		updateTimeline();
		
		calendar.addCalendarListener(new CalendarAdapter(){			
			@Override
			public void hScroll(ScrollEvent e)
			{
				if (e.getType() == 5)//ScrollEventType.EndScroll)
				{
					updateTimeline();
				}
			}			
		});		
		
		calendar.setViewport(new DateTime((long)timeline.getSelectionStart()),
				new DateTime((long)timeline.getSelectionEnd()), true);
		calendar.invalidate();
			
		
		content.add(calendar, BorderLayout.CENTER);
		content.add(timeline, BorderLayout.SOUTH);
	}
	
	void initTimeline()
	{
		DateTime start = calendar.getDate();
		DateTime end = calendar.getEndDate();
		
		timeline.setMinimum(start.getTicks());
		timeline.setMaximum(end.getTicks());

		timeline.setMinimumSelectionLength((timeline.getMaximum() - timeline.getMinimum()) / 30);
		
		timeline.setStep(Duration.TicksPerDay);//TimeSpan.TicksPerDay;
		timeline.setSelectionStart(timeline.getMinimum());
		timeline.setSelectionEnd(timeline.getSelectionStart() + timeline.getMinimumSelectionLength());
	}

	
	void updateTimeline()
	{
		timeline.setSelectionStart(calendar.getFirstVisibleDate().getTicks());
		timeline.setSelectionEnd(calendar.getLastVisibleDate().getTicks());
	}

	private void initializeComponent()
	{
		/*System.ComponentModel.ComponentResourceManager resources = new System.ComponentModel.ComponentResourceManager(typeof(MainForm));
		this._pnlInfo = new System.Windows.Forms.Panel();
		this._lbInfo = new System.Windows.Forms.WebBrowser();
		this._pnlMain = new System.Windows.Forms.Panel();
		calendar = new MindFusion.Scheduling.WinForms.Calendar();
		this.timeline = new MindFusion.UI.WinForms.RangeSelector();
		this._statusBar = new System.Windows.Forms.StatusStrip();
		this._pnlSeparator = new System.Windows.Forms.Panel();
		this._mainMenu = new System.Windows.Forms.MenuStrip();
		this._menuFile = new System.Windows.Forms.ToolStripMenuItem();
		this._menuExit = new System.Windows.Forms.ToolStripMenuItem();
		this._menuHelp = new System.Windows.Forms.ToolStripMenuItem();
		this._menuAbout = new System.Windows.Forms.ToolStripMenuItem();
		this._pnlInfo.SuspendLayout();
		this._pnlMain.SuspendLayout();
		((System.ComponentModel.ISupportInitialize)(calendar)).BeginInit();
		this._mainMenu.SuspendLayout();
		this.SuspendLayout();
		// 
		// _pnlInfo
		// 
		this._pnlInfo.BackColor = System.Drawing.Color.Goldenrod;
		this._pnlInfo.Controls.Add(this._lbInfo);
		this._pnlInfo.Dock = System.Windows.Forms.DockStyle.Top;
		this._pnlInfo.Location = new System.Drawing.Point(0, 24);
		this._pnlInfo.Name = "_pnlInfo";
		this._pnlInfo.Padding = new System.Windows.Forms.Padding(1);
		this._pnlInfo.Size = new System.Drawing.Size(632, 64);
		this._pnlInfo.TabIndex = 2;
		// 
		// _lbInfo
		// 
		this._lbInfo.AllowNavigation = false;
		this._lbInfo.Dock = System.Windows.Forms.DockStyle.Fill;
		this._lbInfo.IsWebBrowserContextMenuEnabled = false;
		this._lbInfo.Location = new System.Drawing.Point(1, 1);
		this._lbInfo.MinimumSize = new System.Drawing.Size(20, 20);
		this._lbInfo.Name = "_lbInfo";
		this._lbInfo.ScriptErrorsSuppressed = true;
		this._lbInfo.Size = new System.Drawing.Size(630, 62);
		this._lbInfo.TabIndex = 1;
		// 
		// _pnlMain
		// 
		this._pnlMain.BorderStyle = System.Windows.Forms.BorderStyle.Fixed3D;
		this._pnlMain.Controls.Add(calendar);
		this._pnlMain.Controls.Add(this.timeline);
		this._pnlMain.Dock = System.Windows.Forms.DockStyle.Fill;
		this._pnlMain.Location = new System.Drawing.Point(0, 92);
		this._pnlMain.Name = "_pnlMain";
		this._pnlMain.Size = new System.Drawing.Size(632, 499);
		this._pnlMain.TabIndex = 7;*/
		// 
		// _calendar
		// 
		calendar.setCurrentTime(new DateTime(2008, 3, 4, 14, 43, 26, 803));
		calendar.setCurrentView(CalendarView.ResourceView);
		calendar.setDate(new DateTime(2006, 2, 1, 0, 0, 0, 0));
		//calendar.setDateTimeFormat((System.Globalization.DateTimeFormatInfo)(resources.GetObject("_calendar.DateTimeFormat")));
		//calendar.Dock = System.Windows.Forms.DockStyle.Fill;
		calendar.setEndDate(new DateTime(2006, 4, 30, 0, 0, 0, 0));
		calendar.setGroupType(GroupType.GroupByResources);
		//calendar.getItemSettings().setSelectedItemStyle = ((MindFusion.Scheduling.Style)(resources.GetObject("_calendar.ItemSettings.SelectedItemStyle")));
		//calendar.ItemSettings.Style = ((MindFusion.Scheduling.Style)(resources.GetObject("_calendar.ItemSettings.Style")));
		//calendar.setLocation(new Point(0, 0));
		calendar.setName("calendar");
		calendar.getResourceViewSettings().setGroupRowHeader(false);// = MindFusion.Scheduling.WinForms.State.Disabled;
		calendar.getResourceViewSettings().getMiddleTimelineSettings().setFormat("d MMMM");
		calendar.getResourceViewSettings().getMiddleTimelineSettings().setUnit(TimeUnit.Week);
		calendar.getResourceViewSettings().setSnapUnit(TimeUnit.Hour);
		calendar.getResourceViewSettings().setTimelines(3);
		calendar.getResourceViewSettings().getTopTimelineSettings().setFormat("MMMM yyyy");
		calendar.getResourceViewSettings().getTopTimelineSettings().setUnit(TimeUnit.Month);
		calendar.getResourceViewSettings().setViewStyle(ResourceViewStyle.Lanes);
		//calendar.setSize(new Dimension(628, 430));
		calendar.setBounds(new Rectangle(0,0,628, 430));
		//calendar.setSystemCalendar(null);
		//calendar.setTabIndex = 0;
		calendar.setTheme(ThemeType.Light);
		// 
		// timeline
		// 
		/*
		this.timeline.ButtonSize = 12;
		this.timeline.Dock = System.Windows.Forms.DockStyle.Bottom;
		this.timeline.Location = new System.Drawing.Point(0, 430);
		this.timeline.Minimum = 0D;
		this.timeline.Name = "timeline";
		this.timeline.Size = new System.Drawing.Size(628, 65);
		this.timeline.TabIndex = 1;
		// 
		// _statusBar
		// 
		this._statusBar.Location = new System.Drawing.Point(0, 591);
		this._statusBar.Name = "_statusBar";
		this._statusBar.Size = new System.Drawing.Size(632, 22);
		this._statusBar.TabIndex = 11;
		// 
		// _pnlSeparator
		// 
		this._pnlSeparator.Dock = System.Windows.Forms.DockStyle.Top;
		this._pnlSeparator.Location = new System.Drawing.Point(0, 88);
		this._pnlSeparator.Name = "_pnlSeparator";
		this._pnlSeparator.Size = new System.Drawing.Size(632, 4);
		this._pnlSeparator.TabIndex = 8;
		// 
		// _mainMenu
		// 
		this._mainMenu.Items.AddRange(new System.Windows.Forms.ToolStripItem[] {
        this._menuFile,
        this._menuHelp});
		this._mainMenu.Location = new System.Drawing.Point(0, 0);
		this._mainMenu.Name = "_mainMenu";
		this._mainMenu.Size = new System.Drawing.Size(632, 24);
		this._mainMenu.TabIndex = 14;
		// 
		// _menuFile
		// 
		this._menuFile.DropDownItems.AddRange(new System.Windows.Forms.ToolStripItem[] {
        this._menuExit});
		this._menuFile.Name = "_menuFile";
		this._menuFile.Size = new System.Drawing.Size(37, 20);
		this._menuFile.Text = "&File";
		// 
		// _menuExit
		// 
		this._menuExit.Name = "_menuExit";
		this._menuExit.Size = new System.Drawing.Size(92, 22);
		this._menuExit.Text = "E&xit";
		this._menuExit.Click += new System.EventHandler(this.OnExit);
		// 
		// _menuHelp
		// 
		this._menuHelp.DropDownItems.AddRange(new System.Windows.Forms.ToolStripItem[] {
        this._menuAbout});
		this._menuHelp.Name = "_menuHelp";
		this._menuHelp.Size = new System.Drawing.Size(44, 20);
		this._menuHelp.Text = "&Help";
		// 
		// _menuAbout
		// 
		this._menuAbout.Name = "_menuAbout";
		this._menuAbout.Size = new System.Drawing.Size(116, 22);
		this._menuAbout.Text = "&About...";
		this._menuAbout.Click += new System.EventHandler(this.OnAbout);
		// 
		// MainForm
		// 
		this.ClientSize = new System.Drawing.Size(632, 613);
		this.Controls.Add(this._pnlMain);
		this.Controls.Add(this._pnlSeparator);
		this.Controls.Add(this._pnlInfo);
		this.Controls.Add(this._mainMenu);
		this.Controls.Add(this._statusBar);
		this.Icon = ((System.Drawing.Icon)(resources.GetObject("$this.Icon")));
		this.Name = "MainForm";
		this.Text = "MindFusion.Scheduling Sample: Resource Timeline";
		this._pnlInfo.ResumeLayout(false);
		this._pnlMain.ResumeLayout(false);
		((System.ComponentModel.ISupportInitialize)(calendar)).EndInit();
		this._mainMenu.ResumeLayout(false);
		this._mainMenu.PerformLayout();
		this.ResumeLayout(false);
		this.PerformLayout();
		*/

	}

	private static final long serialVersionUID = 1L;
}
