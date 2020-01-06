/**
 * Copyright (c) 2017, MindFusion LLC - Bulgaria.
 */

package milestoneItems;

import java.awt.Rectangle;
import java.awt.geom.Point2D;

import com.mindfusion.drawing.*;
import com.mindfusion.scheduling.*;


public class MilestoneItemViewer implements ItemViewer
{
	public void draw(ItemDrawContext context)
	{
		MilestoneItem item = (MilestoneItem)context.getItem();

		if (context.getIsMilestone())
		{
			Rectangle r = context.getBounds();
			Rectangle b = new Rectangle(r.x, r.y, r.width - 1, r.height - 1);
			
			Point2D[] pts = new Point2D[]
				{
					new	Point2D.Double(b.getCenterX(), b.getMinY()),
					new	Point2D.Double(b.getMaxX(), b.getCenterY()),
					new	Point2D.Double(b.getCenterX(), b.getMaxY()),
					new	Point2D.Double(b.getMinX(), b.getCenterY())
				};

			//PathGradientBrush brush = new PathGradientBrush(pts);
			GradientBrush brush = new GradientBrush(Colors.White, item.getColor(), 45);

			AwtGraphics g = new AwtGraphics(context.getGraphics());
			g.fillPolygon(brush, pts);
			g.drawPolygon(new Pen(context.getStyle().getLineColor()), pts);

			context.drawHeaderText(b, true, false);
		}
		else
		{
			context.drawDefault();
		}
	}
}
