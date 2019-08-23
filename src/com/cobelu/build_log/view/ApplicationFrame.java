package com.cobelu.build_log.view;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import com.cobelu.build_log.model.Model;

public class ApplicationFrame extends JFrame {

	private static final long serialVersionUID = 1L;
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

		JMenuItem mntmNewEntry = new JMenuItem("New Entry");
		mntmNewEntry.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				onNewEntryPress();
			}
		});
		mnFile.add(mntmNewEntry);

		mnFile.addSeparator();

		JMenuItem mntmNewProject = new JMenuItem("New Project");
		mntmNewProject.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				onNewProjectPress();
			}
		});
		mnFile.add(mntmNewProject);

		JMenuItem mntmOpenProject = new JMenuItem("Open Project");
		mntmOpenProject.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				onOpenProjectPress();
			}
		});
		mnFile.add(mntmOpenProject);

		mnFile.addSeparator();

		JMenuItem mntmExit = new JMenuItem("Exit");
		mntmExit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				onExitPress();
			}
		});
		mnFile.add(mntmExit);

		JMenu mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);

		JMenuItem mntmAbout = new JMenuItem("About");
		mntmAbout.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				onAboutPress();
			}
		});
		mnHelp.add(mntmAbout);

		entryPanel = new EntryPanel(model.getEntryModel());
		getContentPane().add(entryPanel);

	}
	
	/**
	 * Called when the new entry button is pressed.
	 */
	private void onNewEntryPress() {
		
	}
	
	/**
	 * Called when the new project button is pressed.
	 */
	private void onNewProjectPress() {
		System.exit(0);
	}
	
	/**
	 * Called when the open project button is pressed.
	 */
	private void onOpenProjectPress() {
		System.exit(0);
	}
	
	/**
	 * Called when the exit button is pressed.
	 */
	private void onExitPress() {
		System.exit(0);
	}
	
	/**
	 * Called when the exit button is pressed.
	 */
	private void onAboutPress() {
		JOptionPane.showMessageDialog(null, "Build Log");	
	}

	public Model getModel() {
		return model;
	}

}
