/**
 * Copyright (c) 2017, MindFusion LLC - Bulgaria.
 */

package googleCalendar;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.text.html.*;

import com.mindfusion.common.*;
import com.mindfusion.scheduling.*;
import com.mindfusion.scheduling.model.*;


public abstract class BaseWindow extends JFrame
{
	public static void main(String[] args)
	{
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				MainWindow window = null;
				try {
					window = new MainWindow();
					window.setVisible(true);
				}
				catch (Exception exp) {
				}
			}
		});
	}

	protected BaseWindow()
	{
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		fileChooser = new JFileChooser();
		calendar = new Calendar();
		calendar.setCurrentTime(DateTime.now());
		calendar.setDate(new DateTime(2012, 6, 8));
		calendar.setEndDate(new DateTime(2012, 7, 7));
		
		label = new JTextPane();
		label.setBackground(new Color(255, 255, 225));
		label.setBorder(new LineBorder(Color.orange, 1));
		label.setEditable(false);
		label.setEditorKit(new HTMLEditorKit());
		labelPane = new JScrollPane(label);
		
		content = new JPanel();
		content.setBackground(new Color(242, 242, 242));
		content.setLayout(new GridLayout(1, 1));
		Container cp = getContentPane();
		
		JMenuBar menuBar = new JMenuBar();
		JMenu mFile = new JMenu("File");
		JMenuItem mIFOpen = new JMenuItem("Open");
		mIFOpen.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				openFileChooserClicked();
			}
		});
		
		JMenuItem mIFSave = new JMenuItem("Save");
		mIFSave.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				saveFileChooserClicked();
			}
		});
		
		menuBar.add(mFile);
		mFile.add(mIFOpen);
		mFile.add(mIFSave);
		
		setJMenuBar(menuBar);
		
		SpringLayout layout = new SpringLayout();
		layout.putConstraint(SpringLayout.WEST, labelPane, 0, SpringLayout.WEST, cp);
		layout.putConstraint(SpringLayout.NORTH, labelPane, 0, SpringLayout.NORTH, cp);
		layout.putConstraint(SpringLayout.EAST, labelPane, 0, SpringLayout.EAST, cp);
		layout.putConstraint(SpringLayout.SOUTH, labelPane, 70, SpringLayout.NORTH, cp);

		layout.putConstraint(SpringLayout.WEST, content, 0, SpringLayout.WEST, cp);
		layout.putConstraint(SpringLayout.NORTH, content, 70, SpringLayout.NORTH, cp);
		layout.putConstraint(SpringLayout.EAST, content, 0, SpringLayout.EAST, cp);
		layout.putConstraint(SpringLayout.SOUTH, content, 0, SpringLayout.SOUTH, cp);
		
		cp.setLayout(layout);
		cp.add(labelPane);
		cp.add(content);
	}
	
	public void setInfo(String value)
	{
		label.setText("<div style=\"padding: 2pt; font-family: Verdana; font-size: 11pt;\">" + value + "</div>");
		label.setCaretPosition(0);
	}
	
	protected void saveFileChooserClicked() {
		if (fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION)
		{
			calendar.getSchedule().saveTo(fileChooser.getSelectedFile().getAbsolutePath(), ContentType.Xml);
		}
	}

	protected void openFileChooserClicked() {
		if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION)
		{
			calendar.getSchedule().loadFrom(fileChooser.getSelectedFile().getAbsolutePath(), ContentType.Xml);
			if (calendar.getGroupType() != GroupType.None)
			{
				calendar.beginInit();
				calendar.getContacts().clear();
				calendar.getResources().clear();
				calendar.getLocations().clear();
				calendar.getTasks().clear();
				calendar.getContacts().addAll(calendar.getSchedule().getContacts());
				calendar.getResources().addAll(calendar.getSchedule().getResources());
				calendar.getLocations().addAll(calendar.getSchedule().getLocations());
				calendar.getTasks().addAll(calendar.getSchedule().getTasks());
				calendar.endInit();
			}
		}
	}


	private static final long serialVersionUID = 1L;

	protected Calendar calendar;
	private JFileChooser fileChooser;
	protected JPanel content;
	private JTextPane label;
	private JScrollPane labelPane;
}
