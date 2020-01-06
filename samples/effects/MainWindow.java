/**
 * Copyright (c) 2017, MindFusion LLC - Bulgaria.
 */

package effects;

import java.awt.*;
import java.awt.event.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.beans.*;
import java.text.*;
import java.util.Vector;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.event.*;
import javax.swing.event.ChangeListener;

import com.mindfusion.common.*;
import com.mindfusion.drawing.*;
import com.mindfusion.scheduling.*;
import com.mindfusion.scheduling.model.*;


public class MainWindow extends BaseWindow
	implements ChangeListener, ListSelectionListener, ItemListener, PropertyChangeListener, ActionListener
{
	public MainWindow()
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationByPlatform(true);
		setSize(1024, 768);
		setTitle("MindFusion.Scheduling Sample: Effects");
		setInfo("This sample demonstrates the built-in item effects in MindFusion.Scheduling.<p>" +
			"The Aero and Glass effects can be applied and customized through the controls at the " +
			"right side of the window. There are also many available presets in the list above them.");

		// Create effects
		glassEffect = new GlassEffect();
		aeroEffect = new AeroEffect();

		initializeComponent();

		// Defaults
		calendar.beginInit();
		calendar.setDate(null);
		calendar.setEndDate(null);
		calendar.setCurrentView(CalendarView.Timetable);
		calendar.setTheme(ThemeType.Windows2003);
		calendar.getTimetableSettings().setShowPadding(false);
		calendar.getItemSettings().setShadowStyle(ShadowStyle.None);
		calendar.getItemSettings().setSize(24);
		calendar.getTimetableSettings().getDates().clear();
		calendar.getTimetableSettings().getDates().add(DateTime.today());
		calendar.getTimetableSettings().getDates().add(DateTime.today().addDays(1));
		calendar.getTimetableSettings().setShowDayHeader(true);
		calendar.endInit();

		// Create some preview items
		Appointment app;

		app = new Appointment();
		app.setStartTime(DateTime.today());
		app.setEndTime(DateTime.today());
		app.setAllDayEvent(true);
		app.setHeaderText("All-day appointment");
		calendar.getSchedule().getItems().add(app);

		app = new Appointment();
		app.setStartTime(DateTime.today().addHours(2));
		app.setEndTime(DateTime.today().addHours(4));
		app.setHeaderText("Event #1");
		app.setDescriptionText("Description for Event #1.");
		calendar.getSchedule().getItems().add(app);

		app = new Appointment();
		app.setStartTime(DateTime.today().addHours(3));
		app.setEndTime(DateTime.today().addHours(4.5));
		app.setHeaderText("Event #2");
		app.setDescriptionText("Description for Event #2.");
		calendar.getSchedule().getItems().add(app);

		app = new Appointment();
		app.setStartTime(DateTime.today().addHours(5));
		app.setEndTime(DateTime.today().addHours(6));
		app.setHeaderText("Event #3");
		app.setDescriptionText("Description for Event #3.");
		calendar.getSchedule().getItems().add(app);

		// Presets
		defaultPreset = new Preset();
		defaultPreset.Name = "(Default)";
		defaultPreset.UseGlassEffect = false;
		defaultPreset.GlassEffectType = GlassEffectType.Type1;
		defaultPreset.UsePenAsGlow = false;
		defaultPreset.GlowColor = Colors.White;
		defaultPreset.ReflectionColor = Colors.White;
		defaultPreset.UseAeroEffect = false;
		defaultPreset.Opacity = 40;
		defaultPreset.InnerOutlineColor = Colors.White;
		defaultPreset.ShadeColor = Colors.Black;
		defaultPreset.OverridesThemeSettings = true;
		defaultPreset.CalendarBackground = Colors.White;
		defaultPreset.CalendarBorder = Colors.DarkSlateGray;
		defaultPreset.ItemBorder = Colors.DarkSlateGray;
		defaultPreset.ItemBackground1 = Colors.LightSteelBlue;
		defaultPreset.UseItemBackground2 = false;
		defaultPreset.ItemBackground2 = Colors.White;
		defaultPreset.Theme = ThemeType.Standard;
		applyPreset(defaultPreset);

		Vector<Object> list = new Vector<>();

		Preset preset = new Preset();
		preset.Name = "Glass & Aero (Windows2003 Theme)";
		preset.UseGlassEffect = true;
		preset.GlassEffectType = GlassEffectType.Type1;
		preset.UsePenAsGlow = false;
		preset.GlowColor = Colors.White;
		preset.ReflectionColor = Colors.White;
		preset.UseAeroEffect = true;
		preset.Opacity = 40;
		preset.InnerOutlineColor = Colors.White;
		preset.ShadeColor = Colors.Black;
		preset.OverridesThemeSettings = false;
		preset.CalendarBackground = Colors.White;
		preset.CalendarBorder = Colors.DarkSlateGray;
		preset.ItemBorder = Colors.DarkSlateGray;
		preset.ItemBackground1 = Colors.LightSteelBlue;
		preset.UseItemBackground2 = false;
		preset.ItemBackground2 = Colors.White;
		preset.Theme = ThemeType.Windows2003;
		list.add(preset);

		preset = new Preset();
		preset.Name = "Glass & Aero (Lila Theme)";
		preset.UseGlassEffect = true;
		preset.GlassEffectType = GlassEffectType.Type1;
		preset.UsePenAsGlow = false;
		preset.GlowColor = Colors.White;
		preset.ReflectionColor = Colors.White;
		preset.UseAeroEffect = true;
		preset.Opacity = 40;
		preset.InnerOutlineColor = Colors.White;
		preset.ShadeColor = Colors.Black;
		preset.OverridesThemeSettings = false;
		preset.CalendarBackground = Colors.White;
		preset.CalendarBorder = Colors.DarkSlateGray;
		preset.ItemBorder = Colors.DarkSlateGray;
		preset.ItemBackground1 = Colors.LightSteelBlue;
		preset.UseItemBackground2 = false;
		preset.ItemBackground2 = Colors.White;
		preset.Theme = ThemeType.Lila;
		list.add(preset);

		preset = new Preset();
		preset.Name = "Glass & Aero (Silver Theme)";
		preset.UseGlassEffect = true;
		preset.GlassEffectType = GlassEffectType.Type1;
		preset.UsePenAsGlow = false;
		preset.GlowColor = Colors.White;
		preset.ReflectionColor = Colors.White;
		preset.UseAeroEffect = true;
		preset.Opacity = 40;
		preset.InnerOutlineColor = Colors.White;
		preset.ShadeColor = Colors.Black;
		preset.OverridesThemeSettings = false;
		preset.CalendarBackground = Colors.White;
		preset.CalendarBorder = Colors.DarkSlateGray;
		preset.ItemBorder = Colors.DarkSlateGray;
		preset.ItemBackground1 = Colors.LightSteelBlue;
		preset.UseItemBackground2 = false;
		preset.ItemBackground2 = Colors.White;
		preset.Theme = ThemeType.Silver;
		list.add(preset);

		preset = new Preset();
		preset.Name = "Glass & Aero (Vista Theme)";
		preset.UseGlassEffect = true;
		preset.GlassEffectType = GlassEffectType.Type1;
		preset.UsePenAsGlow = false;
		preset.GlowColor = Colors.White;
		preset.ReflectionColor = Colors.White;
		preset.UseAeroEffect = true;
		preset.Opacity = 40;
		preset.InnerOutlineColor = Colors.White;
		preset.ShadeColor = Colors.Black;
		preset.OverridesThemeSettings = false;
		preset.CalendarBackground = Colors.White;
		preset.CalendarBorder = Colors.DarkSlateGray;
		preset.ItemBorder = Colors.DarkSlateGray;
		preset.ItemBackground1 = Colors.LightSteelBlue;
		preset.UseItemBackground2 = false;
		preset.ItemBackground2 = Colors.White;
		preset.Theme = ThemeType.Vista;
		list.add(preset);

		preset = new Preset();
		preset.Name = "Glass (Default Colors)";
		preset.UseGlassEffect = true;
		preset.GlassEffectType = GlassEffectType.Type1;
		preset.UsePenAsGlow = false;
		preset.GlowColor = Colors.White;
		preset.ReflectionColor = Colors.White;
		preset.UseAeroEffect = false;
		preset.Opacity = 40;
		preset.InnerOutlineColor = Colors.White;
		preset.ShadeColor = Colors.Black;
		preset.OverridesThemeSettings = true;
		preset.CalendarBackground = Colors.White;
		preset.CalendarBorder = Colors.DarkSlateGray;
		preset.ItemBorder = Colors.DarkSlateGray;
		preset.ItemBackground1 = Colors.LightSteelBlue;
		preset.UseItemBackground2 = false;
		preset.ItemBackground2 = Colors.White;
		preset.Theme = ThemeType.Standard;
		list.add(preset);

		preset = new Preset();
		preset.Name = "Glass & Aero (Orange Background)";
		preset.UseGlassEffect = true;
		preset.GlassEffectType = GlassEffectType.Type2;
		preset.UsePenAsGlow = false;
		preset.GlowColor = Colors.White;
		preset.ReflectionColor = Colors.White;
		preset.UseAeroEffect = true;
		preset.Opacity = 20;
		preset.InnerOutlineColor = Colors.White;
		preset.ShadeColor = Colors.Black;
		preset.OverridesThemeSettings = true;
		preset.CalendarBackground = Colors.Orange;
		preset.CalendarBorder = Colors.DarkGoldenrod;
		preset.ItemBorder = Colors.DarkSlateGray;
		preset.ItemBackground1 = Colors.LightSteelBlue;
		preset.UseItemBackground2 = false;
		preset.ItemBackground2 = Colors.White;
		preset.Theme = ThemeType.Standard;
		list.add(preset);

		preset = new Preset();
		preset.Name = "Metalic";
		preset.UseGlassEffect = true;
		preset.GlassEffectType = GlassEffectType.Type2;
		preset.UsePenAsGlow = true;
		preset.GlowColor = Colors.White;
		preset.ReflectionColor = Colors.White;
		preset.UseAeroEffect = true;
		preset.Opacity = 50;
		preset.InnerOutlineColor = Colors.White;
		preset.ShadeColor = Colors.Black;
		preset.OverridesThemeSettings = true;
		preset.CalendarBackground = new Color(0xC0, 0xC0, 0xC0);
		preset.CalendarBorder = Colors.DarkSlateGray;
		preset.ItemBorder = Colors.DarkSlateGray;
		preset.ItemBackground1 = Colors.LightSteelBlue;
		preset.UseItemBackground2 = false;
		preset.ItemBackground2 = Colors.White;
		preset.Theme = ThemeType.Standard;
		list.add(preset);

		preset = new Preset();
		preset.Name = "Neon Glow";
		preset.UseGlassEffect = true;
		preset.GlassEffectType = GlassEffectType.Type2;
		preset.UsePenAsGlow = true;
		preset.GlowColor = Colors.White;
		preset.ReflectionColor = Colors.White;
		preset.UseAeroEffect = true;
		preset.Opacity = 0;
		preset.InnerOutlineColor = Colors.Black;
		preset.ShadeColor = new Color(0x80, 0xFF, 0xFF);
		preset.OverridesThemeSettings = true;
		preset.CalendarBackground = Colors.Black;
		preset.CalendarBorder = new Color(0x80, 0xFF, 0xFF);
		preset.ItemBorder = new Color(0x80, 0xFF, 0xFF);
		preset.ItemBackground1 = Colors.Black;
		preset.UseItemBackground2 = false;
		preset.ItemBackground2 = Colors.White;
		preset.Theme = ThemeType.Standard;
		list.add(preset);

		preset = new Preset();
		preset.Name = "Gray glass";
		preset.UseGlassEffect = true;
		preset.GlassEffectType = GlassEffectType.Type3;
		preset.UsePenAsGlow = false;
		preset.GlowColor = Colors.White;
		preset.ReflectionColor = Colors.White;
		preset.UseAeroEffect = true;
		preset.Opacity = 40;
		preset.InnerOutlineColor = Colors.White;
		preset.ShadeColor = Colors.Black;
		preset.OverridesThemeSettings = true;
		preset.CalendarBackground = Colors.Gray;
		preset.CalendarBorder = Colors.Black;
		preset.ItemBorder = Colors.Black;
		preset.ItemBackground1 = Colors.LightCyan;
		preset.UseItemBackground2 = true;
		preset.ItemBackground2 = new Color(0xA8, 0xBF, 0xBF);
		preset.Theme = ThemeType.Standard;
		list.add(preset);

		preset = new Preset();
		preset.Name = "Brown";
		preset.UseGlassEffect = true;
		preset.GlassEffectType = GlassEffectType.Type1;
		preset.UsePenAsGlow = false;
		preset.GlowColor = Colors.White;
		preset.ReflectionColor = Colors.White;
		preset.UseAeroEffect = false;
		preset.Opacity = 40;
		preset.InnerOutlineColor = Colors.White;
		preset.ShadeColor = Colors.Black;
		preset.OverridesThemeSettings = true;
		preset.CalendarBackground = Colors.PaleGoldenrod;
		preset.CalendarBorder = Colors.Black;
		preset.ItemBorder = Colors.Black;
		preset.ItemBackground1 = new Color(0x80, 0x90, 0x30, 0x20);
		preset.UseItemBackground2 = false;
		preset.ItemBackground2 = Colors.White;
		preset.Theme = ThemeType.Standard;
		list.add(preset);

		preset = new Preset();
		preset.Name = "Bright Red";
		preset.UseGlassEffect = true;
		preset.GlassEffectType = GlassEffectType.Type4;
		preset.UsePenAsGlow = false;
		preset.GlowColor = Colors.Black;
		preset.ReflectionColor = Colors.White;
		preset.UseAeroEffect = false;
		preset.Opacity = 40;
		preset.InnerOutlineColor = Colors.White;
		preset.ShadeColor = Colors.Black;
		preset.OverridesThemeSettings = true;
		preset.CalendarBackground = Colors.White;
		preset.CalendarBorder = new Color(0x40, 0x0, 0x0);
		preset.ItemBorder = new Color(0x60, 0x0, 0x0);
		preset.ItemBackground1 = Colors.Red;
		preset.UseItemBackground2 = false;
		preset.ItemBackground2 = Colors.White;
		preset.Theme = ThemeType.Standard;
		list.add(preset);

		presetsList.setListData(list);
	}

	private void initializeComponent()
	{
		content.setLayout(new BorderLayout());

		panel = new JPanel(new VerticalFlowLayout(VerticalFlowLayout.LEFT, VerticalFlowLayout.TOP, 5, 0));
		leftPanel = new JPanel();
		presetsList = new JList();
		panel2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		checkBox1 = new JCheckBox("Glass Effect");
		glassEffectPanel = new JPanel(new SpringLayout());
		label1 = new JLabel("Type:");
		comboBox1 = new JComboBox();
		checkBox2 = new JCheckBox("Use Pen as Glow");
		label2 = new JLabel("Glow color:");
		colorPicker1 = new ColorPicker();
		label3 = new JLabel("Reflection color:");
		colorPicker2 = new ColorPicker();
		panel3 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		checkBox3 = new JCheckBox("Aero Effect");
		aeroEffectPanel = new JPanel(new SpringLayout());
		label7 = new JLabel("Opacity (%):");
		numeric1 = new JFormattedTextField(NumberFormat.getInstance());
		label6 = new JLabel("Inner outline color:");
		colorPicker4 = new ColorPicker();
		label5 = new JLabel("Shade color:");
		colorPicker3 = new ColorPicker();
		bottomPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		panel6 = new JPanel(new SpringLayout());
		label4 = new JLabel("Calendar background:");
		colorPicker5 = new ColorPicker();
		label9 = new JLabel("Items border & text:");
		colorPicker7 = new ColorPicker();
		label8 = new JLabel("Items background:");
		panel7 = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 2));
		colorPicker6 = new ColorPicker();
		checkBox6 = new JCheckBox();
		colorPicker8 = new ColorPicker();
		checkBox4 = new JCheckBox("Override theme settings");
		label10 = new JLabel("Calendar borders:");
		colorPicker9 = new ColorPicker();
		panel5 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		panel4 = new JPanel(new VerticalFlowLayout(VerticalFlowLayout.LEFT, VerticalFlowLayout.TOP, 5, 0));
		label11 = new JLabel("Theme");
		panel8 = new JPanel(new VerticalFlowLayout(VerticalFlowLayout.LEFT, VerticalFlowLayout.TOP, 5, 0));
		panel9 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		panel10 = new JPanel(new SpringLayout());
		radioButton1 = new JRadioButton("Standard", true);
		radioButton2 = new JRadioButton("Windows2003");
		radioButton3 = new JRadioButton("Lila");
		radioButton4 = new JRadioButton("Silver");
		radioButton5 = new JRadioButton("Vista");
		radioButton6 = new JRadioButton("Light");
		group = new ButtonGroup();
		// panel
		panel.setPreferredSize(new Dimension(250, 250));
		panel.add(Box.createVerticalStrut(10));
		panel.add(new JLabel("Presets:"));
		panel.add(Box.createVerticalStrut(5));
		panel.add(presetsList, Integer.valueOf(VerticalFlowLayout.STRETCH));
		panel.add(Box.createVerticalStrut(5));
		panel.add(panel2, Integer.valueOf(VerticalFlowLayout.STRETCH));
		panel.add(glassEffectPanel, Integer.valueOf(VerticalFlowLayout.STRETCH));
		panel.add(Box.createVerticalStrut(5));
		panel.add(panel3, Integer.valueOf(VerticalFlowLayout.STRETCH));
		panel.add(aeroEffectPanel, Integer.valueOf(VerticalFlowLayout.STRETCH));
		panel.add(Box.createVerticalStrut(5));
		// leftPanel
		leftPanel.setLayout(new BorderLayout());
		leftPanel.add(calendar, BorderLayout.CENTER);
		leftPanel.add(bottomPanel, BorderLayout.SOUTH);
		// presetsList
		presetsList.setPreferredSize(new Dimension(230, 203));
		presetsList.setBorder(new LineBorder(AwtColors.Gray, 1));
		presetsList.addListSelectionListener(this);
		// checkBox1
		checkBox1.setOpaque(false);
		checkBox1.setAlignmentX(0);
		checkBox1.addItemListener(this);
		// panel2
		panel2.add(checkBox1);
		panel2.setBackground(new java.awt.Color(0xB9, 0xD1, 0xEA));
		// glassEffectPanel
		glassEffectPanel.setBackground(new java.awt.Color(0xD7, 0xE4, 0xF2));
		glassEffectPanel.setPreferredSize(new Dimension(231, 122));
		glassEffectPanel.add(label1);
		glassEffectPanel.add(comboBox1);
		glassEffectPanel.add(new JLabel());
		glassEffectPanel.add(checkBox2);
		glassEffectPanel.add(label2);
		glassEffectPanel.add(colorPicker1);
		glassEffectPanel.add(label3);
		glassEffectPanel.add(colorPicker2);
		makeCompactGrid(glassEffectPanel, 4, 2, 10, 10, 5, 5);
		// label1
		label1.setOpaque(false);
		label1.setLabelFor(comboBox1);
		// comboBox1
		comboBox1.addItem("Type 1");
		comboBox1.addItem("Type 2");
		comboBox1.addItem("Type 3");
		comboBox1.addItem("Type 4");
		comboBox1.addItemListener(this);
		// checkBox2
		checkBox2.setOpaque(false);
		checkBox2.addItemListener(this);
		// label2
		label2.setOpaque(false);
		// colorPicker1
		colorPicker1.addPropertyChangeListener(this);
		// label2
		label2.setLabelFor(colorPicker1);
		// label3
		label3.setOpaque(false);
		label3.setLabelFor(colorPicker2);
		// colorPicker2
		colorPicker2.addPropertyChangeListener(this);
		// panel3
		panel3.setBackground(new java.awt.Color(0xB9, 0xD1, 0xEA));
		panel3.add(checkBox3);
		// checkBox3
		checkBox3.setOpaque(false);
		checkBox3.setAlignmentX(0);
		checkBox3.addItemListener(this);
		// aeroEffectPanel
		aeroEffectPanel.setBackground(new java.awt.Color(0xD7, 0xE4, 0xF2));
		aeroEffectPanel.setPreferredSize(new Dimension(231, 93));
		aeroEffectPanel.add(label7);
		aeroEffectPanel.add(numeric1);
		aeroEffectPanel.add(label2);
		aeroEffectPanel.add(colorPicker4);
		aeroEffectPanel.add(label5);
		aeroEffectPanel.add(colorPicker3);
		makeCompactGrid(aeroEffectPanel, 3, 2, 10, 10, 5, 5);
		// label7
		label7.setOpaque(false);
		label7.setLabelFor(numeric1);
		// numeric1
		numeric1.addPropertyChangeListener(this);
		// label6
		label6.setOpaque(false);
		label6.setLabelFor(colorPicker4);
		// colorPicker4
		colorPicker4.addPropertyChangeListener(this);
		// label5
		label5.setOpaque(false);
		label5.setLabelFor(colorPicker3);
		// colorPicker3
		colorPicker3.setColor(Colors.Black);
		colorPicker3.addPropertyChangeListener(this);
		// bottomPanel
		bottomPanel.add(panel4);
		bottomPanel.add(panel8);
		// panel4
		panel4.add(panel5, Integer.valueOf(VerticalFlowLayout.STRETCH));
		panel4.add(panel6);
		// panel8
		panel8.add(panel9, Integer.valueOf(VerticalFlowLayout.STRETCH));
		panel8.add(panel10);
		// panel6
		panel6.setBackground(new java.awt.Color(0xD7, 0xE4, 0xF2));
		panel6.add(label4);
		panel6.add(colorPicker5);
		panel6.add(label10);
		panel6.add(colorPicker9);
		panel6.add(label9);
		panel6.add(colorPicker7);
		panel6.add(label8);
		panel6.add(panel7);
		makeCompactGrid(panel6, 4, 2, 10, 10, 5, 5);
		// label4
		label4.setOpaque(false);
		label4.setLabelFor(colorPicker5);
		// colorPicker5
		colorPicker5.addPropertyChangeListener(this);
		// label9
		label9.setOpaque(false);
		label9.setLabelFor(colorPicker7);
		// colorPicker7
		colorPicker7.addPropertyChangeListener(this);
		// label8
		label8.setOpaque(false);
		label8.setLabelFor(colorPicker6);
		// colorPicker6
		colorPicker6.addPropertyChangeListener(this);
		// panel7
		panel7.add(colorPicker6);
		panel7.add(checkBox6);
		panel7.add(colorPicker8);
		panel7.setOpaque(false);
		// checkBox6
		checkBox6.setOpaque(false);
		checkBox6.addItemListener(this);
		// colorPicker8
		colorPicker8.addPropertyChangeListener(this);
		// checkBox4
		checkBox4.setOpaque(false);
		checkBox4.setAlignmentX(0);
		checkBox4.addItemListener(this);
		// colorPicker9
		colorPicker9.addPropertyChangeListener(this);
		// panel5
		panel5.add(checkBox4);
		panel5.setBackground(new java.awt.Color(0xB9, 0xD1, 0xEA));
		// panel9
		panel9.add(label11);
		panel9.setBackground(new java.awt.Color(0xB9, 0xD1, 0xEA));
		panel9.setPreferredSize(new Dimension(0, 32));
		// panel10
		panel10.setBackground(new java.awt.Color(0xD7, 0xE4, 0xF2));
		panel10.add(radioButton1);
		panel10.add(radioButton2);
		panel10.add(radioButton3);
		panel10.add(radioButton4);
		panel10.add(radioButton5);
		panel10.add(radioButton6);
		makeCompactGrid(panel10, 6, 1, 5, 5, 5, 0);
		// radioButton1
		radioButton1.addItemListener(this);
		radioButton1.setOpaque(false);
		// radioButton2
		radioButton2.addItemListener(this);
		radioButton2.setOpaque(false);
		// radioButton3
		radioButton3.addItemListener(this);
		radioButton3.setOpaque(false);
		// radioButton4
		radioButton4.addItemListener(this);
		radioButton4.setOpaque(false);
		// radioButton5
		radioButton5.addItemListener(this);
		radioButton5.setOpaque(false);
		// radioButton6
		radioButton6.addItemListener(this);
		radioButton6.setOpaque(false);
		// group
		group.add(radioButton1);
		group.add(radioButton2);
		group.add(radioButton3);
		group.add(radioButton4);
		group.add(radioButton5);
		group.add(radioButton6);

		// Structure
		content.add(leftPanel, BorderLayout.CENTER);
		content.add(panel, BorderLayout.EAST);
	}

	/**
	 * {@link ChangeListener#stateChanged} implementation.
	 */
	public void stateChanged(ChangeEvent e)
	{
	}

	/**
	 * {@link ListSelectionListener#valueChanged} implementation.
	 */
	public void valueChanged(ListSelectionEvent e)
	{
		if (e.getSource() == presetsList)
		{
			// Apply the selected preset
			applyPreset((Preset)presetsList.getSelectedValue());
		}
	}

	/**
	 * {@link ItemListener#itemStateChanged} implementation.
	 */
	public void itemStateChanged(ItemEvent e)
	{
		Object source = e.getSource();
		if (source == radioButton1)
		{
			calendar.setTheme(ThemeType.Standard);
		}
		else if (source == radioButton2)
		{
			calendar.setTheme(ThemeType.Windows2003);
		}
		else if (source == radioButton3)
		{
			calendar.setTheme(ThemeType.Lila);
		}
		else if (source == radioButton4)
		{
			calendar.setTheme(ThemeType.Silver);
		}
		else if (source == radioButton5)
		{
			calendar.setTheme(ThemeType.Vista);
		}
		else if (source == radioButton6)
		{
			calendar.setTheme(ThemeType.Light);
		}
		else if (source == comboBox1)
		{
			glassEffect.setType(GlassEffectType.values()[comboBox1.getSelectedIndex()]);
		}
		else if (source == checkBox1)
		{
			if (checkBox1.isSelected())
				calendar.getItemEffects().add(glassEffect);
			else
				calendar.getItemEffects().remove(glassEffect);
		}
		else if (source == checkBox2)
		{
			glassEffect.setUsePenAsGlow(checkBox2.isSelected());
		}
		else if (source == checkBox3)
		{
			if (checkBox3.isSelected())
				calendar.getItemEffects().add(aeroEffect);
			else
				calendar.getItemEffects().remove(aeroEffect);
		}
		else if (source == checkBox4)
		{
			if (checkBox4.isSelected())
			{
				setStyleBrushes(calendar.getTimetableSettings().getCellStyle(),
					new SolidBrush(colorPicker5.getColor()));
				setStyleBrushes(calendar.getTimetableSettings().getWorkTimeCellStyle(),
					new SolidBrush(colorPicker5.getColor()));
				calendar.getTimetableSettings().setDayHeaderBrush(
					new SolidBrush(colorPicker5.getColor()));
				setStyleBorders(calendar.getTimetableSettings().getCellStyle(), colorPicker9.getColor());
				setStyleBorders(calendar.getTimetableSettings().getWorkTimeCellStyle(), colorPicker9.getColor());
				setStyleBorders(calendar.getItemSettings().getStyle(), colorPicker7.getColor());
				setStyleBorders(calendar.getItemSettings().getSelectedItemStyle(), colorPicker7.getColor());
				setStyleBorders(calendar.getItemSettings().getPointedItemStyle(), colorPicker7.getColor());
				setStyleBorders(calendar.getItemSettings().getPointedSelectedItemStyle(), colorPicker7.getColor());
				calendar.getItemSettings().getStyle().setLineColor(colorPicker7.getColor());
				calendar.getItemSettings().getSelectedItemStyle().setLineColor(colorPicker7.getColor());
				calendar.getItemSettings().getPointedItemStyle().setLineColor(colorPicker7.getColor());
				calendar.getItemSettings().getPointedSelectedItemStyle().setLineColor(colorPicker7.getColor());
				calendar.getItemSettings().getStyle().setTextColor(colorPicker7.getColor());
				calendar.getItemSettings().getSelectedItemStyle().setTextColor(colorPicker7.getColor());
				calendar.getItemSettings().getPointedItemStyle().setTextColor(colorPicker7.getColor());
				calendar.getItemSettings().getPointedSelectedItemStyle().setTextColor(colorPicker7.getColor());
				calendar.getItemSettings().getStyle().setHeaderTextColor(colorPicker7.getColor());
				calendar.getItemSettings().getSelectedItemStyle().setHeaderTextColor(colorPicker7.getColor());
				calendar.getItemSettings().getPointedItemStyle().setHeaderTextColor(colorPicker7.getColor());
				calendar.getItemSettings().getPointedSelectedItemStyle().setHeaderTextColor(colorPicker7.getColor());
				updateItemBrush();
			}
			else
			{
				resetStyleBrushes(calendar.getTimetableSettings().getCellStyle());
				resetStyleBrushes(calendar.getTimetableSettings().getWorkTimeCellStyle());
				calendar.getTimetableSettings().setDayHeaderBrush(null);
				resetStyleBorders(calendar.getTimetableSettings().getCellStyle());
				resetStyleBorders(calendar.getTimetableSettings().getWorkTimeCellStyle());

				resetStyleBorders(calendar.getItemSettings().getStyle());
				resetStyleBorders(calendar.getItemSettings().getSelectedItemStyle());
				resetStyleBorders(calendar.getItemSettings().getPointedItemStyle());
				resetStyleBorders(calendar.getItemSettings().getPointedSelectedItemStyle());
				calendar.getItemSettings().getStyle().setLineColor(null);
				calendar.getItemSettings().getSelectedItemStyle().setLineColor(null);
				calendar.getItemSettings().getPointedItemStyle().setLineColor(null);
				calendar.getItemSettings().getPointedSelectedItemStyle().setLineColor(null);
				calendar.getItemSettings().getStyle().setTextColor(null);
				calendar.getItemSettings().getSelectedItemStyle().setTextColor(null);
				calendar.getItemSettings().getPointedItemStyle().setTextColor(null);
				calendar.getItemSettings().getPointedSelectedItemStyle().setTextColor(null);
				calendar.getItemSettings().getStyle().setHeaderTextColor(null);
				calendar.getItemSettings().getSelectedItemStyle().setHeaderTextColor(null);
				calendar.getItemSettings().getPointedItemStyle().setHeaderTextColor(null);
				calendar.getItemSettings().getPointedSelectedItemStyle().setHeaderTextColor(null);

				resetStyleBrushes(calendar.getItemSettings().getStyle());
				resetStyleBrushes(calendar.getItemSettings().getSelectedItemStyle());
				resetStyleBrushes(calendar.getItemSettings().getPointedItemStyle());
				resetStyleBrushes(calendar.getItemSettings().getPointedSelectedItemStyle());
				calendar.getItemSettings().getStyle().setFillColor(null);
				calendar.getItemSettings().getSelectedItemStyle().setFillColor(null);
				calendar.getItemSettings().getPointedItemStyle().setFillColor(null);
				calendar.getItemSettings().getPointedSelectedItemStyle().setFillColor(null);
			}
		}
		else if (source == checkBox6)
		{
			updateItemBrush();
			colorPicker8.setEnabled(checkBox6.isSelected());
		}
	}

	/**
	 * {@link PropertyChangeListener#propertyChange} implementation.
	 */
	public void propertyChange(PropertyChangeEvent evt)
	{
		Object source = evt.getSource();
		if ("color".equals(evt.getPropertyName()))
		{
			if (source == colorPicker1)
			{
				glassEffect.setGlowColor(colorPicker1.getColor());
			}
			else if (source == colorPicker2)
			{
				glassEffect.setReflectionColor(colorPicker2.getColor());
			}
			else if (source == colorPicker3)
			{
				aeroEffect.setShadeColor(colorPicker3.getColor());
			}
			else if (source == colorPicker4)
			{
				aeroEffect.setInnerOutlineColor(colorPicker4.getColor());
			}
			else if (source == colorPicker5)
			{
				if (!checkBox4.isSelected())
					return;

				setStyleBrushes(calendar.getTimetableSettings().getCellStyle(),
					new SolidBrush(colorPicker5.getColor()));
				setStyleBrushes(calendar.getTimetableSettings().getWorkTimeCellStyle(),
					new SolidBrush(colorPicker5.getColor()));
				calendar.getTimetableSettings().setDayHeaderBrush(
					new SolidBrush(colorPicker5.getColor()));
			}
			else if (source == colorPicker7)
			{
				if (!checkBox4.isSelected())
					return;

				setStyleBorders(calendar.getItemSettings().getStyle(), colorPicker7.getColor());
				setStyleBorders(calendar.getItemSettings().getSelectedItemStyle(), colorPicker7.getColor());
				setStyleBorders(calendar.getItemSettings().getPointedItemStyle(), colorPicker7.getColor());
				setStyleBorders(calendar.getItemSettings().getPointedSelectedItemStyle(), colorPicker7.getColor());

				calendar.getItemSettings().getStyle().setLineColor(colorPicker7.getColor());
				calendar.getItemSettings().getSelectedItemStyle().setLineColor(colorPicker7.getColor());
				calendar.getItemSettings().getPointedItemStyle().setLineColor(colorPicker7.getColor());
				calendar.getItemSettings().getPointedSelectedItemStyle().setLineColor(colorPicker7.getColor());

				calendar.getItemSettings().getStyle().setTextColor(colorPicker7.getColor());
				calendar.getItemSettings().getSelectedItemStyle().setTextColor(colorPicker7.getColor());
				calendar.getItemSettings().getPointedItemStyle().setTextColor(colorPicker7.getColor());
				calendar.getItemSettings().getPointedSelectedItemStyle().setTextColor(colorPicker7.getColor());

				calendar.getItemSettings().getStyle().setHeaderTextColor(colorPicker7.getColor());
				calendar.getItemSettings().getSelectedItemStyle().setHeaderTextColor(colorPicker7.getColor());
				calendar.getItemSettings().getPointedItemStyle().setHeaderTextColor(colorPicker7.getColor());
				calendar.getItemSettings().getPointedSelectedItemStyle().setHeaderTextColor(colorPicker7.getColor());
			}
			else if (source == colorPicker9)
			{
				if (!checkBox4.isSelected())
					return;

				setStyleBorders(calendar.getTimetableSettings().getCellStyle(), colorPicker9.getColor());
				setStyleBorders(calendar.getTimetableSettings().getWorkTimeCellStyle(), colorPicker9.getColor());
			}
			else if (source == colorPicker6 || evt.getSource() == colorPicker8)
			{
				updateItemBrush();
			}
		}
		if ("value".equals(evt.getPropertyName()))
		{
			if (source == numeric1)
			{
				try
				{
					aeroEffect.setOpacity(Float.parseFloat(String.valueOf(numeric1.getValue())) / 100);
				}
				catch (Exception ex)
				{
				}
			}
		}
	}

	/**
	 * {@link ActionListener#actionPerformed} implementation.
	 */
	public void actionPerformed(ActionEvent e)
	{
	}

	/**
	 * Aligns the first <code>rows</code> * <code>cols</code>
	 * components of <code>parent</code> in
	 * a grid. Each component in a column is as wide as the maximum
	 * preferred width of the components in that column;
	 * height is similarly determined for each row.
	 * The parent is made just big enough to fit them all.
	 *
	 * @param rows number of rows
	 * @param cols number of columns
	 * @param initialX x location to start the grid at
	 * @param initialY y location to start the grid at
	 * @param xPad x padding between cells
	 * @param yPad y padding between cells
	 */
	private static void makeCompactGrid(Container parent,
		int rows, int cols, int initialX, int initialY, int xPad, int yPad)
	{
		SpringLayout layout;
		try
		{
			layout = (SpringLayout)parent.getLayout();
		}
		catch (ClassCastException exc)
		{
			System.err.println("The first argument to makeCompactGrid must use SpringLayout.");
				return;
		}

		// Align all cells in each column and make them the same width.
		Spring x = Spring.constant(initialX);
		for (int c = 0; c < cols; c++)
		{
			Spring width = Spring.constant(0);
			for (int r = 0; r < rows; r++)
			{
				width = Spring.max(width,
					getConstraintsForCell(r, c, parent, cols).getWidth());
			}
			for (int r = 0; r < rows; r++)
			{
				SpringLayout.Constraints constraints =
					getConstraintsForCell(r, c, parent, cols);
				constraints.setX(x);
				constraints.setWidth(width);
			}
			x = Spring.sum(x, Spring.sum(width, Spring.constant(xPad)));
		}

		// Align all cells in each row and make them the same height.
		Spring y = Spring.constant(initialY);
		for (int r = 0; r < rows; r++)
		{
			Spring height = Spring.constant(0);
			for (int c = 0; c < cols; c++)
			{
				height = Spring.max(height,
					getConstraintsForCell(r, c, parent, cols).getHeight());
			}
			for (int c = 0; c < cols; c++)
			{
				SpringLayout.Constraints constraints =
					getConstraintsForCell(r, c, parent, cols);
				constraints.setY(y);
				constraints.setHeight(height);
			}
			y = Spring.sum(y, Spring.sum(height, Spring.constant(yPad)));
		}

		//Set the parent's size
		SpringLayout.Constraints pCons = layout.getConstraints(parent);
		pCons.setConstraint(SpringLayout.SOUTH, y);
		pCons.setConstraint(SpringLayout.EAST, x);
	}

	private static SpringLayout.Constraints getConstraintsForCell(
		int row, int col, Container parent, int cols)
	{
		SpringLayout layout = (SpringLayout)parent.getLayout();
		Component c = parent.getComponent(row * cols + col);
		return layout.getConstraints(c);
	}

	private void updateItemBrush()
	{
		Brush brush = null;
		if (!checkBox6.isSelected())
			brush = new SolidBrush(colorPicker6.getColor());
		else
			brush = new GradientBrush(colorPicker6.getColor(), colorPicker8.getColor(), 90);

		setStyleBrushes(calendar.getItemSettings().getStyle(), brush);
		setStyleBrushes(calendar.getItemSettings().getSelectedItemStyle(), brush);
		setStyleBrushes(calendar.getItemSettings().getPointedItemStyle(), brush);
		setStyleBrushes(calendar.getItemSettings().getPointedSelectedItemStyle(), brush);

		calendar.getItemSettings().getStyle().setFillColor(colorPicker6.getColor());
		calendar.getItemSettings().getSelectedItemStyle().setFillColor(colorPicker6.getColor());
		calendar.getItemSettings().getPointedItemStyle().setFillColor(colorPicker6.getColor());
		calendar.getItemSettings().getPointedSelectedItemStyle().setFillColor(colorPicker6.getColor());
	}

	private void setStyleBorders(Style style, Color color)
	{
		style.setBorderLeftColor(color);
		style.setBorderTopColor(color);
		style.setBorderRightColor(color);
		style.setBorderBottomColor(color);
		style.setHeaderBorderLeftColor(color);
		style.setHeaderBorderTopColor(color);
		style.setHeaderBorderRightColor(color);
		style.setHeaderBorderBottomColor(color);
	}

	private void setStyleBrushes(Style style, Brush brush)
	{
		style.setBrush(brush);
		style.setHeaderBrush(brush);
	}

	private void resetStyleBorders(Style style)
	{
		style.setBorderLeftColor(null);
		style.setBorderTopColor(null);
		style.setBorderRightColor(null);
		style.setBorderBottomColor(null);
		style.setHeaderBorderLeftColor(null);
		style.setHeaderBorderTopColor(null);
		style.setHeaderBorderRightColor(null);
		style.setHeaderBorderBottomColor(null);
	}

	private void resetStyleBrushes(Style style)
	{
		style.setBrush(null);
		style.setHeaderBrush(null);
	}

	private void applyPreset(Preset preset)
	{
		if (preset == null)
			return;

		checkBox1.setSelected(preset.UseGlassEffect);
		comboBox1.setSelectedIndex(preset.GlassEffectType.ordinal());
		checkBox2.setSelected(preset.UsePenAsGlow);
		colorPicker1.setColor(preset.GlowColor);
		colorPicker2.setColor(preset.ReflectionColor);

		checkBox3.setSelected(preset.UseAeroEffect);
		numeric1.setText(Integer.toString(preset.Opacity));
		colorPicker3.setColor(preset.ShadeColor);
		colorPicker4.setColor(preset.InnerOutlineColor);

		checkBox4.setSelected(preset.OverridesThemeSettings);
		colorPicker5.setColor(preset.CalendarBackground);
		colorPicker9.setColor(preset.CalendarBorder);
		colorPicker7.setColor(preset.ItemBorder);
		colorPicker6.setColor(preset.ItemBackground1);
		checkBox6.setSelected(preset.UseItemBackground2);
		colorPicker8.setColor(preset.ItemBackground2);

		if (preset.Theme != null)
		{
			switch (preset.Theme)
			{

				case Standard:
					radioButton1.setSelected(true);
					break;

				case Windows2003:
					radioButton2.setSelected(true);
					break;

				case Lila:
					radioButton3.setSelected(true);
					break;

				case Silver:
					radioButton4.setSelected(true);
					break;

				case Vista:
					radioButton5.setSelected(true);
					break;
					
				case Light:
					radioButton6.setSelected(true);
					break;

			}
		}
	}


	private static final long serialVersionUID = 1L;

	private JPanel panel;
	private JPanel leftPanel;
	private JList presetsList;
	private JPanel panel2;
	private JCheckBox checkBox1;
	private JPanel glassEffectPanel;
	private JLabel label1;
	private JComboBox comboBox1;
	private JCheckBox checkBox2;
	private JLabel label2;
	private ColorPicker colorPicker1;
	private JLabel label3;
	private ColorPicker colorPicker2;
	private JPanel panel3;
	private JCheckBox checkBox3;
	private JPanel aeroEffectPanel;
	private JLabel label7;
	private JFormattedTextField numeric1;
	private JLabel label6;
	private ColorPicker colorPicker4;
	private JLabel label5;
	private ColorPicker colorPicker3;
	private JPanel bottomPanel;
	private JPanel panel6;
	private JLabel label4;
	private ColorPicker colorPicker5;
	private JLabel label9;
	private ColorPicker colorPicker7;
	private JLabel label8;
	private JPanel panel7;
	private ColorPicker colorPicker6;
	private JCheckBox checkBox6;
	private ColorPicker colorPicker8;
	private JCheckBox checkBox4;
	private JLabel label10;
	private ColorPicker colorPicker9;
	private JPanel panel5;
	private JPanel panel4;
	private JLabel label11;
	private JPanel panel8;
	private JPanel panel9;
	private JPanel panel10;
	private JRadioButton radioButton1;
	private JRadioButton radioButton2;
	private JRadioButton radioButton3;
	private JRadioButton radioButton4;
	private JRadioButton radioButton5;
	private JRadioButton radioButton6;
	private ButtonGroup group;

	private GlassEffect glassEffect;
	private AeroEffect aeroEffect;
	private Preset defaultPreset;
}

final class AwtColors
{
	public static final java.awt.Color White = new java.awt.Color(255, 255, 255);
	public static final java.awt.Color Black = new java.awt.Color(0, 0, 0);
	public static final java.awt.Color DarkSlateGray = new java.awt.Color(0x2F, 0x4F, 0x4F);
	public static final java.awt.Color LightSteelBlue = new java.awt.Color(0xB0, 0xC4, 0xDE);
	public static final java.awt.Color Orange = new java.awt.Color(0xFF, 0xA5, 0x00);
	public static final java.awt.Color Red = new java.awt.Color(0xFF, 0x00, 0x00);
	public static final java.awt.Color LightCyan = new java.awt.Color(0xE0, 0xFF, 0xFF);
	public static final java.awt.Color Gray = new java.awt.Color(0x80, 0x80, 0x80);
	public static final java.awt.Color PaleGoldenrod = new java.awt.Color(0xEE, 0xE8, 0xAA);
}
