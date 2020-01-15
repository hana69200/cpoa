/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vue;

import com.mindfusion.common.DateTime;
import com.mindfusion.scheduling.Calendar;
import javax.swing.JComponent;

/**
 *
 * @author MarionM
 */
public class Planning extends JComponent
{
	private Calendar calendar;

	public Planning()
	{
		calendar = new Calendar();
		calendar.beginInit();
		calendar.getTimetableSettings().getDates().clear();
		for (int i = 0; i < 5; i++)
			calendar.getTimetableSettings().getDates().add(DateTime.today().addDays(i));
		calendar.endInit();
	}

	public Calendar getCalendar()
	{
		return calendar;
	}

	private static final long serialVersionUID = 1L;

}
