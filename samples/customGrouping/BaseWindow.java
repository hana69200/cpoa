/**
 * Copyright (c) 2017, MindFusion LLC - Bulgaria.
 */

package customGrouping;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.text.html.*;

import com.mindfusion.common.*;
import com.mindfusion.scheduling.*;


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


	private static final long serialVersionUID = 1L;

	protected Calendar calendar;
	protected JPanel content;
	private JTextPane label;
	private JScrollPane labelPane;
}
