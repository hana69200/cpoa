/**
 * Copyright (c) 2017, MindFusion LLC - Bulgaria.
 */

package clipboard;

import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.datatransfer.*;
import java.awt.event.*;
import java.io.*;
import java.util.UUID;

import javax.swing.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;

import org.w3c.dom.*;
import org.xml.sax.InputSource;

import com.mindfusion.common.*;
import com.mindfusion.scheduling.*;
import com.mindfusion.scheduling.model.*;


public class MainWindow extends BaseWindow implements ClipboardOwner
{
	public MainWindow()
	{
		setSize(424, 574);
		setTitle("MindFusion.Scheduling Sample: Clipboard");
		setInfo("This sample imlements clipboard operations on the Calendar control.<br>" +
			"<ul><li>Select a time cell and start typing to create a schedule item.</li>" +
			"<li>Right-click on an item to copy it.</li>" +
			"<li>Right-click on a day cell to paste.</li></ul>");

		// Calendar initialization start
		calendar.beginInit();
		calendar.setTheme(ThemeType.Vista);
		calendar.setCurrentView(CalendarView.Timetable);

		// Setup the dates displayed in the Schedule view
		DateTime today = DateTime.today();
		calendar.getTimetableSettings().getDates().clear();
		calendar.getTimetableSettings().getDates().add(today);
		calendar.endInit();
		// Calendar initialization end

		calendar.addCalendarListener(new CalendarAdapter() {
			@Override
			public void itemClick(ItemMouseEvent e) {
				onItemClicked(e);
			}

			@Override
			public void dateClick(ResourceDateEvent e) {
				onDateClicked(e);
			}
		});

		content.add(calendar);

		contextMenu = new JPopupMenu();
		contextMenu.setEnabled(true);
		
		mICopy = new JMenuItem("Copy");
		mICopy.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				onCopy();
			}
		});
		
		mIPaste = new JMenuItem("Paste");
		mIPaste.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				onPaste();
			}
		});
		
		contextMenu.add(mICopy);
		contextMenu.add(mIPaste);
	}
	
	public void lostOwnership(Clipboard clipboard, Transferable contents)
	{
	}

	protected void onCopy()
	{
		if (_clickedItem == null)
			return;

		// Copy the clicked item
		String value = serializeItem(_clickedItem);
		if (value != null)
		{
			java.awt.Toolkit.getDefaultToolkit().getSystemClipboard().setContents(
				new StringSelection(value), this);
		}
	}

	protected void onPaste()
	{
		Clipboard clipboard = java.awt.Toolkit.getDefaultToolkit().getSystemClipboard();
		if (!clipboard.getContents(this).isDataFlavorSupported(DataFlavor.stringFlavor))
			return;

		String value;
		try
		{
			value = (String)clipboard.getData(DataFlavor.stringFlavor);
		}
		catch (Exception ex)
		{
			return;
		}

		Item newItem = deserializeItem(value);
		if (newItem != null)
		{
			// Set the appropriate start and end time for the
			// newly created item
			Duration length = newItem.getEndTime().subtract(newItem.getStartTime());
			newItem.setStartTime(_clickedDate);
			newItem.setEndTime(_clickedDate.add(length));

			// Ensure the item has unique id
			newItem.setId(UUID.randomUUID().toString());

			// Add the item to the schedule
			calendar.getSchedule().getItems().add(newItem);
		}
	}

	private void onItemClicked(ItemMouseEvent e)
	{
		if (e.getButton() == MouseEvent.BUTTON3)
		{
			// Select the right-clicked item
			calendar.getItemSelection().clear();
			calendar.getItemSelection().add(e.getItem());

			// Remember the item
			_clickedItem = e.getItem();

			mICopy.setEnabled(true);
			mIPaste.setEnabled(false);
			
			// Pop-up the context menu
			Point where = MouseInfo.getPointerInfo().getLocation();
			where.x -= calendar.getLocationOnScreen().x;
			where.y -= calendar.getLocationOnScreen().y;
			contextMenu.show(calendar, where.x, where.y);
		}
	}

	private void onDateClicked(ResourceDateEvent e)
	{
		if (e.getButton() == MouseEvent.BUTTON3)
		{
			// Select the right-clicked date
			calendar.getSelection().set(e.getDate(), DateTime.op_Addition(e.getDate(), calendar.getTimetableSettings().getCellTime()));

			// Remember the date
			_clickedDate = e.getDate();

			mICopy.setEnabled(false);
			mIPaste.setEnabled(false);

			// Update the menu
			Clipboard clipboard = java.awt.Toolkit.getDefaultToolkit().getSystemClipboard();
			try
			{
				Transferable dataObj = clipboard.getContents(this);
				if (dataObj.isDataFlavorSupported(DataFlavor.stringFlavor))
				{
					String value = (String)clipboard.getData(DataFlavor.stringFlavor);
					if (value != null)
						mIPaste.setEnabled(deserializeItem(value) != null);
				}
			}
			catch (Exception exp)
			{
			}
			
			// Pop-up the context menu
			Point where = MouseInfo.getPointerInfo().getLocation();
			where.x -= calendar.getLocationOnScreen().x;
			where.y -= calendar.getLocationOnScreen().y;
			contextMenu.show(calendar, where.x, where.y);
		}
	}

	private String serializeItem(Item item)
	{
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        Document document;
		try
		{
			document = factory.newDocumentBuilder().newDocument();
		}
		catch (ParserConfigurationException e)
		{
			return null;
		}
        Element element = document.createElement("MyItem");
        document.appendChild(element);
		XmlSerializationContext context = new XmlSerializationContext(calendar.getSchedule(), document);
		item.saveTo(element, context);

		StringWriter writer = new StringWriter();
		StreamResult streamResult = new StreamResult(writer);

		DOMSource source = new DOMSource(document);
		try
		{
			Transformer transformer = TransformerFactory.newInstance().newTransformer();
	        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
	        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
	        transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-16");
	        transformer.transform(source, streamResult);
	
	        writer.flush();

	        return writer.toString();
		}
		catch (Exception ex)
		{
		}

		return null;
	}

	private Item deserializeItem(String value)
	{
		StringReader reader = new StringReader(value);
		
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        Document document;
		try
		{
			document = factory.newDocumentBuilder().parse(new InputSource(reader));
		}
		catch (Exception e)
		{
			return null;
		}

		XmlSerializationContext context = new XmlSerializationContext(calendar.getSchedule(), document);
        Element element = (Element)document.getFirstChild();

        Item item = new Appointment();
		item.loadFrom(element, context);

		return item;
	}


	private Item _clickedItem;
	private DateTime _clickedDate;
	private JPopupMenu contextMenu;
	private JMenuItem mIPaste;
	private JMenuItem mICopy;

	private static final long serialVersionUID = 1L;
}
