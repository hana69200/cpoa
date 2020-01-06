/**
 * Copyright (c) 2017, MindFusion LLC - Bulgaria.
 */

package milestoneItems;

import java.awt.Color;
import java.util.*;

import com.mindfusion.common.*;
import com.mindfusion.drawing.*;
import com.mindfusion.scheduling.*;
import com.mindfusion.scheduling.model.*;


public class MainWindow extends BaseWindow
{
	public MainWindow()
	{
		setSize(648, 651);
		setTitle("MindFusion.Scheduling Sample: Milestone Items");
		setInfo("This sample demonstrates the Milestone item mode in the List view. " +
			"The milestone items use custom item viewer in order to draw as rhombuses.");

		// Calendar initialization start
		calendar.beginInit();
		calendar.setCurrentView(CalendarView.List);
		calendar.setTheme(ThemeType.Silver);
		calendar.setShowToolTips(true);

		calendar.getListViewSettings().setEnableMilestoneMode(true);
		calendar.getListViewSettings().setFreeDrag(true);
		calendar.getListViewSettings().setOrientation(Orientation.Horizontal);
		calendar.getListViewSettings().setRotateHeaderTexts(false);
		calendar.getListViewSettings().getCellSettings().setHeaderPosition(Position.Top);
		calendar.getItemSettings().setShadowStyle(ShadowStyle.None);
		calendar.getItemSettings().setSize(24);

		calendar.setInteractiveItemType(MilestoneItem.class);
		calendar.endInit();
		// Calendar initialization end

		// Create some milestone items
		DateTime baseDate = calendar.getFirstDate();

		Color[] colors = new Color[]
		{
			Colors.Red,
			Colors.Blue,
			Colors.Magenta,
			Colors.DarkViolet
		};

		calendar.beginInit();
		Random random = new Random(DateTime.now().getMillisecond());
		for (int i = 0; i < 50; i++)
		{
			MilestoneItem item = new MilestoneItem();

			item.setStartTime(baseDate.add(Duration.fromDays(random.nextInt() % 7)));
			item.setEndTime(item.getStartTime());
			item.setHeaderText(String.valueOf(i));
			item.setColor(colors[random.nextInt(Integer.MAX_VALUE) % colors.length]);

			calendar.getSchedule().getItems().add(item);

			calendar.setItemListLane(item, Math.abs(random.nextInt()) % 15);
		}
		calendar.endInit();

		content.add(calendar);
	}


	private static final long serialVersionUID = 1L;
}
