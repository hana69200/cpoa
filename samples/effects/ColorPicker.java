/**
 * Copyright (c) 2017, MindFusion LLC - Bulgaria.
 */

package effects;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;

import javax.swing.*;


@SuppressWarnings("serial")
class ColorPicker extends JButton implements ActionListener
{
	public ColorPicker()
	{
		super();
		color = new Color(255, 255, 254);
		setPreferredSize(new Dimension(24, 24));
		addActionListener(this);
	}

	@Override()
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);

		Graphics2D g2 = (Graphics2D)g.create();

		final float padding = 4;
		Rectangle2D.Float bounds = new Rectangle2D.Float(
			padding, padding, getWidth() - 2 * padding - 1, getHeight() - 2 * padding - 1);

		g2.setPaint(color);
		g2.fill(bounds);

		g2.setPaint(Color.gray);
		g2.draw(bounds);

		g2.dispose();
	}

	public void actionPerformed(ActionEvent e)
	{
		Color newColor = JColorChooser.showDialog(this, "Select color", color);
		if (newColor != null)
			setColor(newColor);
	}


	public Color getColor()
	{
		return color;
	}

	public void setColor(Color value)
	{
		Object oldValue = color;
		color = value;
		firePropertyChange("color", oldValue, value);
		repaint();
	}

	private Color color;
}
