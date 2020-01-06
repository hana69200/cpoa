/**
 * Copyright (c) 2017, MindFusion LLC - Bulgaria.
 */

package customGrouping;

import java.awt.Rectangle;
import java.util.*;

import com.mindfusion.drawing.*;
import com.mindfusion.scheduling.*;
import com.mindfusion.scheduling.model.*;


public class MainWindow extends BaseWindow
{
	public MainWindow()
	{
		setSize(648, 651);
		setTitle("MindFusion.Scheduling Sample: Custom Grouping");
		setInfo("This sample illustrates how to use custom grouping in a Resource view. " +
			"Custom grouping represents the ability to specify the secondary resources " +
			"individually for each primary resource.<p>" +
			"In this particular case the view displays a list of machines with different " +
			"workers assigned to each machine. The same worker can be assigned to more than " +
			"one machine. In addition, if a worker has more than one assigned task at a time, " +
			"the conflicting tasks will be marked off with red.");

		calendar.addCalendarListener(new CalendarAdapter()
		{
			@Override
			public void customizeGrouping(CustomizeGroupingEvent e)
			{
				Machine machine = (Machine)e.getParents()[0];
				e.getGroupByResources().clear();
				e.getGroupByResources().addAll(machine.getWorkers());
			}

			@Override
			public void draw(CalendarDrawEvent e)
			{
				if (e.getElement() == CustomDrawElements.ItemMoveBand)
				{
					// Find the worker associated with the item
					Worker worker = null;
					for (Resource resource : e.getItem().getResources())
					{
						if (resource instanceof Worker)
							worker = (Worker)resource;
						if (worker != null)
							break;
					}

					// Find all items associated with this worker
					ItemList items = calendar.getSchedule().getAllItems(
						e.getItem().getStartTime(), e.getItem().getEndTime(), worker);
					if (items.size() > 1)
					{
						Rectangle bounds = e.getBounds();
						bounds = new Rectangle(bounds.x + 1, bounds.y + 1,
							bounds.width - 1, bounds.height - 1);
						Brushes.Red.applyTo(e.getGraphics(), bounds);
						e.getGraphics().fill(bounds);
					}
				}
			}
		});

		Worker worker1 = new Worker();
		worker1.setName("Jim");
		Worker worker2 = new Worker();
		worker2.setName("Paul");
		Worker worker3 = new Worker();
		worker3.setName("Kim");
		Worker worker4 = new Worker();
		worker4.setName("Kate");
		Worker worker5 = new Worker();
		worker5.setName("Chris");
		Worker worker6 = new Worker();
		worker6.setName("Oliver");

		Machine machine1 = new Machine();
		machine1.setName("Machine #1");
		machine1.getWorkers().add(worker1);
		machine1.getWorkers().add(worker3);
		Machine machine2 = new Machine();
		machine2.setName("Machine #2");
		machine2.getWorkers().add(worker2);
		machine2.getWorkers().add(worker5);
		machine2.getWorkers().add(worker6);
		Machine machine3 = new Machine();
		machine3.setName("Machine #3");
		machine3.getWorkers().add(worker3);
		machine3.getWorkers().add(worker4);
		machine3.getWorkers().add(worker5);

		calendar.beginInit();
		calendar.getResources().add(worker1);
		calendar.getResources().add(worker2);
		calendar.getResources().add(worker3);
		calendar.getResources().add(worker4);
		calendar.getResources().add(worker5);
		calendar.getResources().add(worker6);
		calendar.getResources().add(machine1);
		calendar.getResources().add(machine2);
		calendar.getResources().add(machine3);

		calendar.setCurrentView(CalendarView.ResourceView);
		calendar.getResourceViewSettings().setAllowResizeRowHeaders(true);
		calendar.getResourceViewSettings().setRowHeaderSize(200);
		calendar.getResourceViewSettings().setSnapUnit(TimeUnit.Minute);
		calendar.getResourceViewSettings().setSnapUnitCount(1);
		calendar.getResourceViewSettings().setViewStyle(ResourceViewStyle.Lanes);
		calendar.setTheme(ThemeType.Silver);

		calendar.setGroupType(GroupType.GroupByResources);
		calendar.setSecondaryGroupType(GroupType.GroupByResources);
		calendar.setCustomGroupType(Machine.class);
		calendar.setCustomSecondaryGroupType(Worker.class);

		calendar.setCustomDraw(EnumSet.of(CustomDrawElements.ItemMoveBand));
		calendar.endInit();

		content.add(calendar);
	}


	private static final long serialVersionUID = 1L;
}

class Worker extends Resource
{
	public Worker()
	{
	}
}

class Machine extends Resource
{
	public Machine()
	{
		workers = new ArrayList<Worker>();
	}

	public List<Worker> getWorkers()
	{
		return workers;
	}


	private List<Worker> workers;
}
