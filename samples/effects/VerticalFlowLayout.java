/**
 * Copyright (c) 2017, MindFusion LLC - Bulgaria.
 */

package effects;

import java.awt.*;
import java.io.*;
import java.util.*;


/**
 * A version of {@link FlowLayout} which arranges its
 * components vertically.
 */
class VerticalFlowLayout implements LayoutManager2, Serializable
{
	/**
	 * Initializes a new instance of the {@link VerticalFlowLayout} class with
	 * a centered alignment and a default 5-unit horizontal and vertical gap.
	 */
	public VerticalFlowLayout()
	{
		this(CENTER, 5, 5);
	}

	/**
	 * Initializes a new instance of the {@link VerticalFlowLayout} class with
	 * the specified alignment and a default 5-unit horizontal and vertical gap.
	 */
	public VerticalFlowLayout(int align)
	{
		this(align, 5, 5);
	}

	/**
	 * Initializes a new instance of the {@link VerticalFlowLayout} class with
	 * the indicated alignment and the indicated horizontal and vertical gaps.
	 */
	public VerticalFlowLayout(int align, int hgap, int vgap)
	{
		this(LEFT, align, hgap, vgap);
	}

	/**
	 * Initializes a new instance of the {@link VerticalFlowLayout} class.
	 */
	public VerticalFlowLayout(int halign, int align, int hgap, int vgap)
	{
		this.constraints = new HashMap<Component, Object>();

		this.halign = halign;
		this.align = align;
		this.hgap = hgap;
		this.vgap = vgap;
	}

	/**
	 * Gets the alignment for this layout.
	 */
	public int getAlignment()
	{
		return align;
	}

	/**
	 * Sets the alignment for this layout.
	 */
	public void setAlignment(int value)
	{
		align = value;
	}

	/**
	 * Gets the horizontal alignment of layout components.
	 */
	public int getHAlignment()
	{
		return halign;
	}

	/**
	 * Sets the horizontal alignment of layout components.
	 */
	public void setHAlignment(int value)
	{
		halign = value;
	}

	/**
	 * Gets the horizontal gap between components
	 * and between the components and the borders
	 * of the <code>Container</code>.
	 */
	public int getHgap()
	{
		return hgap;
	}

	/**
	 * Sets the horizontal gap between components and
	 * between the components and the borders of the
	 * <code>Container</code>.
	 */
	public void setHgap(int hgap)
	{
		this.hgap = hgap;
	}

	/**
	 * Gets the vertical gap between components and
	 * between the components and the borders of the
	 * <code>Container</code>.
	 */
	public int getVgap()
	{
		return vgap;
	}

	/**
	 * Sets the vertical gap between components and between
	 * the components and the borders of the <code>Container</code>.
	 */
	public void setVgap(int vgap)
	{
		this.vgap = vgap;
	}

	public void addLayoutComponent(String name, Component comp)
	{
	}

	public void addLayoutComponent(Component comp, Object constraints)
	{
		this.constraints.put(comp, constraints);
	}

	public void removeLayoutComponent(Component comp)
	{
		this.constraints.remove(comp);
	}

	public float getLayoutAlignmentX(Container target)
	{
		return 0;
	}

	public float getLayoutAlignmentY(Container target)
	{
		return 0;
	}

	public void invalidateLayout(Container target)
	{
	}

	public Dimension maximumLayoutSize(Container target)
	{
		return new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE);
	}

	/**
	 * Returns the preferred dimensions for this layout given the
	 * <i>visible</i> components in the specified target container.
	 */
	public Dimension preferredLayoutSize(Container target)
	{
		synchronized (target.getTreeLock())
		{
			Dimension dim = new Dimension(0, 0);
			int nmembers = target.getComponentCount();
			boolean firstVisibleComponent = true;
	
			for (int i = 0 ; i < nmembers ; i++)
			{
				Component m = target.getComponent(i);
	
				if (m.isVisible())
				{
					Dimension d = m.getPreferredSize();
					dim.width = Math.max(dim.width, d.width);
	
					if (firstVisibleComponent)
					{
						firstVisibleComponent = false;
					}
					else
					{
						dim.height += vgap;
					}
	
					dim.height += d.height;
				}
			}
	
			Insets insets = target.getInsets();
			dim.width += insets.left + insets.right + hgap*2;
			dim.height += insets.top + insets.bottom + vgap*2;
			return dim;
		}
	}

	/**
	 * Returns the minimum dimensions needed to layout the <i>visible</i>
	 * components contained in the specified target container.
	 */
	public Dimension minimumLayoutSize(Container target)
	{
		synchronized (target.getTreeLock())
		{
			Dimension dim = new Dimension(0, 0);
			int nmembers = target.getComponentCount();
			boolean firstVisibleComponent = true;
	
			for (int i = 0 ; i < nmembers ; i++)
			{
				Component m = target.getComponent(i);
				if (m.isVisible())
				{
					Dimension d = m.getMinimumSize();
					dim.width = Math.max(dim.width, d.width);
	
					if (firstVisibleComponent)
					{
						firstVisibleComponent = false;
					}
					else
					{
						dim.height += vgap;
					}
	
					dim.height += d.height;
				}
			}
	
	
			Insets insets = target.getInsets();
			dim.width += insets.left + insets.right + hgap*2;
			dim.height += insets.top + insets.bottom + vgap*2;
			return dim;
		}
	}

	/**
	 * Lays out the container. This method lets each
	 * <i>visible</i> component take
	 * its preferred size by reshaping the components in the
	 * target container in order to satisfy the alignment of
	 * this <code>VerticalFlowLayout</code> object.
	 */
	public void layoutContainer(Container target)
	{
		synchronized (target.getTreeLock())
		{
			Insets insets = target.getInsets();
			int maxWidth = target.getSize().width - (insets.left + insets.right + hgap*2);
			int maxHeight = target.getSize().height - (insets.top + insets.bottom + vgap*2);
			int nmembers = target.getComponentCount();
			int x = insets.left + hgap;
			int y = 0;
			int columnWidth = 0;
			int start = 0;
	
			boolean ttb = target.getComponentOrientation().isLeftToRight();
	
			for (int i = 0 ; i < nmembers ; i++)
			{
				Component m = target.getComponent(i);
	
				if (m.isVisible())
				{
					Dimension d = m.getPreferredSize();

					int halign = this.halign;
					Object constraint = constraints.get(m);
					if (constraint != null && constraint instanceof Integer)
						halign = ((Integer)constraint).intValue();

					if (halign == VerticalFlowLayout.STRETCH)
						m.setSize(maxWidth, d.height);
					else
						m.setSize(d.width, d.height);
	
					if (y > 0)
						y += vgap;

					y += d.height;
					columnWidth = Math.max(columnWidth, d.width);
				}
			}
	
			moveComponents(target, x, insets.top + vgap, columnWidth, maxHeight - y, start, nmembers, ttb);
		}
	}

	/**
	 * Centers the elements in the specified row, if there is any slack.
	 */
	private void moveComponents(Container target, int x, int y, int width, int height,
		int columnStart, int columnEnd, boolean ttb)
	{
		switch (align)
		{
			case TOP:
				y += ttb ? 0 : height;
				break;
			case CENTER:
				y += height / 2;
				break;
			case BOTTOM:
				y += ttb ? height : 0;
				break;
		}

		for (int i = columnStart ; i < columnEnd ; i++)
		{
			Component m = target.getComponent(i);

			int halign = this.halign;
			Object constraint = constraints.get(m);
			if (constraint != null && constraint instanceof Integer)
				halign = ((Integer)constraint).intValue();

			if (m.isVisible())
			{
				int cx = 0;

				switch (halign)
				{

				case LEFT:
				case STRETCH:
					cx = x;
					break;

				case MIDDLE:
					cx = x + (width - m.getSize().width) / 2;
					break;

				case RIGHT:
					cx = x + width - m.getSize().width;
					break;

				}

				if (ttb)
				{
					m.setLocation(cx, y);
				}
				else
				{
					m.setLocation(cx, target.getSize().height - y - m.getSize().height);
				}

				y += m.getSize().height + vgap;
			}
		}
	}

	/**
	 * Returns a string representation of this <code>VerticalFlowLayout</code>
	 * object and its values.
	 */
	public String toString()
	{
		String str = "";

		switch (align)
		{
			case TOP:    str = ",align=top"; break;
			case CENTER: str = ",align=center"; break;
			case BOTTOM: str = ",align=bottom"; break;
		}

		return getClass().getName() + "[hgap=" + hgap + ",vgap=" + vgap + str + "]";
	}


	private static final long serialVersionUID = 1L;

	/**
	 * This value indicates that each row of components
	 * should be top-justified.
	 */
	public static final int TOP = 0;

	/**
	 * This value indicates that each row of components
	 * should be centered.
	 */
	public static final int CENTER = 1;

	/**
	 * This value indicates that each row of components
	 * should be bottom-justified.
	 */
	public static final int BOTTOM = 2;

	/**
	 * This value indicates that the components
	 * should be left-aligned.
	 */
	public static final int LEFT = 0;

	/**
	 * This value indicates that the components
	 * should be centered horizontally.
	 */
	public static final int MIDDLE = 1;

	/**
	 * This value indicates that the components
	 * should be right-aligned.
	 */
	public static final int RIGHT = 2;

	/**
	 * This value indicates that the components
	 * should be stretched horizontally.
	 */
	public static final int STRETCH = 3;

	/**
	 * <code>align</code> is the property that determines
	 * how each row distributes empty space.
	 * It can be one of the following three values:
	 * <ul>
	 * <code>TOP</code>
	 * <code>BOTTOM</code>
	 * <code>CENTER</code>
	 * </ul>
	 */
	private int align;

	/**
	 * The horizontal alignment of components.
	 * It can be one of the following three values:
	 * <ul>
	 * <code>LEFT</code>
	 * <code>RIGHT</code>
	 * <code>MIDDLE</code>
	 * </ul>
	 */
	private int halign;

	/**
	 * The flow layout manager allows a separation of
	 * components with gaps.  The horizontal gap will
	 * specify the space between components and between
	 * the components and the borders of the
	 * <code>Container</code>.
	 */
	private int hgap;

	/**
	 * The flow layout manager allows a separation of
	 * components with gaps.  The vertical gap will
	 * specify the space between rows and between the
	 * the rows and the borders of the <code>Container</code>.
	 */
	private int vgap;

	private HashMap<Component, Object> constraints;
}
