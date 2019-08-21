package com.cobelu.build_log.view;

import javax.swing.JFrame;
import java.awt.Dimension;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import com.cobelu.build_log.model.Model;

public class ApplicationFrame extends JFrame {
	
	private Model model;
	private EntryPanel entryPanel;
	
	public ApplicationFrame(Model model) {
		this.model = model;
		
		setSize(new Dimension(600, 400));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("New menu item");
		mnFile.add(mntmNewMenuItem);
		
		JMenu mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);
		
		JMenuItem mntmAbout = new JMenuItem("About");
		mnHelp.add(mntmAbout);
		
		entryPanel = new EntryPanel(model.getEntryModel());
		getContentPane().add(entryPanel);
		
	}

}
