/**
 * Copyright (c) 2017, MindFusion LLC - Bulgaria.
 */

package minApp;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import com.mindfusion.scheduling.*;
import com.mindfusion.scheduling.model.*;


public class MainWindow extends JFrame
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

	public MainWindow()
	{
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(368, 362);
		setTitle("MindFusion.Scheduling Sample: Minimal Application");

		calendar = new Calendar();
		calendar.setTheme(ThemeType.Light);

		JToolBar toolBar = new JToolBar();
		toolBar.setFloatable(false);
		
		nextButtonInToolbar(toolBar, imageFileNames[0]).addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				calendar.setCurrentView(CalendarView.SingleMonth);
			}
		});
		
		nextButtonInToolbar(toolBar, imageFileNames[1]).addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				calendar.setCurrentView(CalendarView.MonthRange);
			}
		});
		
		nextButtonInToolbar(toolBar, imageFileNames[2]).addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				calendar.setCurrentView(CalendarView.List);
			}
		});
		
		nextButtonInToolbar(toolBar, imageFileNames[3]).addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				calendar.setCurrentView(CalendarView.WeekRange);
			}
		});
		
		nextButtonInToolbar(toolBar, imageFileNames[4]).addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				calendar.setCurrentView(CalendarView.Timetable);
			}
		});
		
		nextButtonInToolbar(toolBar, imageFileNames[5]).addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				exit();
				WindowEvent windowClosing = new WindowEvent(MainWindow.this, WindowEvent.WINDOW_CLOSING);
				MainWindow.this.dispatchEvent(windowClosing);
			}
		});
		
		Container cp = getContentPane();
		cp.setLayout(new BorderLayout());
		cp.add(toolBar, BorderLayout.PAGE_START);
        cp.add(calendar, BorderLayout.CENTER);
        
        addWindowListener(new WindowAdapter(){
        	public void windowClosing(WindowEvent e){
        		exit();
        	}
        	public void windowOpened(WindowEvent e){
        		onWindowOpened();
        	}
        });
        
        // Initialize the date file
        _dataFile = new java.io.File("Schedule.dat").getAbsolutePath();
	}
	
	private void onWindowOpened() {
		if (new java.io.File(_dataFile).exists())
			calendar.getSchedule().loadFrom(_dataFile, ContentType.Xml);
	}

	private void exit() {
		calendar.getSchedule().saveTo(_dataFile, ContentType.Xml);
	}

	private JButton nextButtonInToolbar(JToolBar bar, String imageName)
	{
		JButton button = new JButton(new ThumbnailAction(imagedir, imageName));
		button.setBorderPainted(false);
		button.setMargin(new Insets(5, 5, 5, 5));
		button.setSize(35, 35);
		
		bar.add(button);
		
		return button;
	}

	/**
     * List of all the image files to load.
     */
    private String[] imageFileNames = { "0.png", "1.png", "2.png", "3.png", "4.png", "5.png"};
 
    private String _dataFile;
	private Calendar calendar;
	private String imagedir = "Resources/";
	private static final long serialVersionUID = 1L;
}

class ThumbnailAction extends AbstractAction
{
	public ThumbnailAction(String path, String imagePath)
	{
		// The LARGE_ICON_KEY is the key for setting the
		// icon when an Action is applied to a button.
		ImageIcon icon = createImageIcon(path, imagePath);
		putValue(LARGE_ICON_KEY, icon);
	}
	
	private ImageIcon createImageIcon(String path, String fileName) {
        java.net.URL imgURL = getClass().getResource(path + fileName);
        if (imgURL == null)
        	imgURL = getClass().getResource("/" + path + fileName);

        if (imgURL != null) {
            return new ImageIcon(imgURL, fileName);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }
	
	@Override
	public void actionPerformed(ActionEvent e) {
	}
	
	private static final long serialVersionUID = 1L;
}