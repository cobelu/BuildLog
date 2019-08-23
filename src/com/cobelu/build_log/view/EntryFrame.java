package com.cobelu.build_log.view;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.cobelu.build_log.entity.Entry;

public class EntryFrame extends JFrame {

	private static final long serialVersionUID = 1L;

	/**
	 * A blank, unfilled entry editing.
	 */
	public EntryFrame() {
		setSize(new Dimension(300, 400));
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		
		JLabel lblImAnEntry = new JLabel("I'm an Entry Frame!");
		panel.add(lblImAnEntry);
		// TODO Auto-generated constructor stub

	}

	/**
	 * Calls blank constructor and then fills in the fields with the values from the
	 * entry.
	 * 
	 * @param entry
	 */
	public EntryFrame(Entry entry) {
		// TODO Auto-generated constructor stub
		super();
	}

}
