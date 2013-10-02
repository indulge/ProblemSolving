package com.mhhe.clrs2e.tests;
/************************************************************************
 *
 * 1. This software is for the purpose of demonstrating one of many
 * ways to implement the algorithms in Introduction to Algorithms,
 * Second edition, by Thomas H. Cormen, Charles E. Leiserson, Ronald
 * L. Rivest, and Clifford Stein.  This software has been tested on a
 * limited set of test cases, but it has not been exhaustively tested.
 * It should not be used for mission-critical applications without
 * further testing.
 *
 * 2. McGraw-Hill licenses and authorizes you to use this software
 * only on a microcomputer located within your own facilities.
 *
 * 3. You will abide by the Copyright Law of the United States.
 *
 * 4. You may prepare a derivative version of this software provided
 * that your source code indicates that it is based on this software and
 * also that you have made changes to it.
 *
 * 5. If you believe that you have found an error in this software,
 * please send email to clrs-java-bugs@mhhe.com.  If you have a
 * suggestion for an improvement, please send email to
 * clrs-java-suggestions@mhhe.com.
 *
 ***********************************************************************/

// DataStructuresGUI.java
// Demonstrates, via a GUI, the stack and queue data structures from
// Chapter 10 of Introduction to Algorithms, Second edition.

import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import com.mhhe.clrs2e.*;

public class DataStructuresGUI extends JFrame
{
    // Set up the GUI and display it.
    public DataStructuresGUI(String title)
    {
	super(title);
	setContentPane(makeContentPane());
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	pack();
	setVisible(true);
    }

    // Create the GUI.
    protected JPanel makeContentPane()
    {
	JPanel panel = new JPanel();
	JTabbedPane tabbed = new JTabbedPane();

	panel.add(tabbed);

	JPanel stackPanel = new StackPanel();
	tabbed.addTab("Stack", stackPanel);

	JPanel queuePanel = new QueuePanel();
	tabbed.addTab("Queue", queuePanel);
	
	return panel;
    }

    protected class StackPanel extends JPanel
    {
	protected JTextArea area;
	protected JTextField text;
	protected Stack stack;

	// Create a panel to test Stack.
	public StackPanel()
	{
	    stack = new StackArray(100);

	    GridBagLayout gb = new GridBagLayout();
	    GridBagConstraints gc = new GridBagConstraints();
	    
	    setLayout(gb);
	    gc.weightx = 0.5;
	    gc.weighty = 0.5;
	    gc.fill = GridBagConstraints.BOTH;

	    text = new JTextField(10);
	    text.addActionListener(new PushListener());
	    gc.gridx = 0; gc.gridy = 0; gc.gridwidth = 2;
	    gb.setConstraints(text, gc);
	    add(text);

	    JButton pushButton = new JButton("Push");
	    pushButton.addActionListener(new PushListener());
	    gc.gridx = 2; gc.gridy = 0; gc.gridwidth = 1;
	    gb.setConstraints(pushButton, gc);
	    add(pushButton);

	    JButton popButton = new JButton("pop");
	    popButton.addActionListener(new PopListener());
	    gc.gridx = 0; gc.gridy = 1; gc.gridwidth = 1;
	    gb.setConstraints(popButton, gc);
	    add(popButton);

	    JButton emptyButton = new JButton("Is Empty?");
	    emptyButton.addActionListener(new EmptyListener());
	    gc.gridx = 1; gc.gridy = 1; gc.gridwidth = 1;
	    gb.setConstraints(emptyButton, gc);
	    add(emptyButton);

	    JButton clearButton = new JButton("New stack");
	    clearButton.addActionListener(new ClearListener());
	    gc.gridx = 2; gc.gridy = 1; gc.gridwidth = 1;
	    gb.setConstraints(clearButton, gc);
	    add(clearButton);

	    area = new JTextArea("", 20, 40);
	    JScrollPane scroll = new JScrollPane(area);
	    scroll.setVerticalScrollBarPolicy
		(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
	    area.setEditable(false);
	    gc.gridx = 0; gc.gridy = 2; gc.gridwidth = 3;
	    gb.setConstraints(scroll, gc);
	    add(scroll);
	}

	// Push the contents of text onto the stack and report.
	protected class PushListener implements ActionListener
	{
	    public void actionPerformed(ActionEvent ev)
	    {
		stack.push(text.getText());
		area.append("Pushed " + text.getText() + "\n");
		text.selectAll();
	    }
	}

	// Pop the top of the stack and display the contents.
	protected class PopListener implements ActionListener
	{
	    public void actionPerformed(ActionEvent ev)
	    {
		try {
		    area.append("Popped " + stack.pop().toString() + "\n");
		} catch (StackUnderflowException ex) {
		    area.append("Stack Underflow occurred\n");
		}
	    }
	}

	// Query if the stack is empty. 
	protected class EmptyListener implements ActionListener
	{
	    public void actionPerformed(ActionEvent ev)
	    {
		if (stack.isEmpty())
		    area.append("Stack is empty\n");
		else
		    area.append("Stack is not empty\n");
	    }
	}

	// Create a new stack.
	protected class ClearListener implements ActionListener
	{
	    public void actionPerformed(ActionEvent ev)
	    {
		stack = new StackArray(100);
		area.append("New stack created\n");
	    }
	}
    }

    protected class QueuePanel extends JPanel
    {
	protected JTextArea area;
	protected JTextField text;
	protected Queue queue;

	// Set up the panel for testing Queue.
	public QueuePanel()
	{
	    queue = new QueueArray(100);

	    GridBagLayout gb = new GridBagLayout();
	    GridBagConstraints gc = new GridBagConstraints();
	    
	    setLayout(gb);
	    gc.weightx = 0.5;
	    gc.weighty = 0.5;
	    gc.fill = GridBagConstraints.BOTH;

	    text = new JTextField(10);
	    text.addActionListener(new EnqueueListener());
	    gc.gridx = 0; gc.gridy = 0; gc.gridwidth = 2;
	    gb.setConstraints(text, gc);
	    add(text);

	    JButton enqueueButton = new JButton("Enqueue");
	    enqueueButton.addActionListener(new EnqueueListener());
	    gc.gridx = 2; gc.gridy = 0; gc.gridwidth = 1;
	    gb.setConstraints(enqueueButton, gc);
	    add(enqueueButton);

	    JButton dequeueButton = new JButton("dequeue");
	    dequeueButton.addActionListener(new DequeueListener());
	    gc.gridx = 0; gc.gridy = 1; gc.gridwidth = 1;
	    gb.setConstraints(dequeueButton, gc);
	    add(dequeueButton);

	    JButton emptyButton = new JButton("Is Empty?");
	    emptyButton.addActionListener(new EmptyListener());
	    gc.gridx = 1; gc.gridy = 1; gc.gridwidth = 1;
	    gb.setConstraints(emptyButton, gc);
	    add(emptyButton);

	    JButton clearButton = new JButton("New queue");
	    clearButton.addActionListener(new ClearListener());
	    gc.gridx = 2; gc.gridy = 1; gc.gridwidth = 1;
	    gb.setConstraints(clearButton, gc);
	    add(clearButton);

	    area = new JTextArea("", 20, 40);
	    JScrollPane scroll = new JScrollPane(area);
	    scroll.setVerticalScrollBarPolicy
		(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
	    area.setEditable(false);
	    gc.gridx = 0; gc.gridy = 2; gc.gridwidth = 3;
	    gb.setConstraints(scroll, gc);
	    add(scroll);
	}

	// Enqueue the contents of text and display.
	protected class EnqueueListener implements ActionListener
	{
	    public void actionPerformed(ActionEvent ev)
	    {
		queue.enqueue(text.getText());
		area.append("Enqueued " + text.getText() + "\n");
		text.selectAll();
	    }
	}

	// Dequeue the head of the queue and display it.
	protected class DequeueListener implements ActionListener
	{
	    public void actionPerformed(ActionEvent ev)
	    {
		try {
		    area.append("Dequeued " + 
				queue.dequeue().toString() + "\n");
		} catch (Exception ex) {
		    area.append("Caught an exception: " + ex + "\n");
		}
	    }
	}

	// Query if the queue is empty.
	protected class EmptyListener implements ActionListener
	{
	    public void actionPerformed(ActionEvent ev)
	    {
		if (queue.isEmpty())
		    area.append("Queue is empty\n");
		else
		    area.append("Queue is not empty\n");
	    }
	}

	// Make a new Queue.
	protected class ClearListener implements ActionListener
	{
	    public void actionPerformed(ActionEvent ev)
	    {
		queue = new QueueArray(100);
		area.append("New queue created\n");
	    }
	}
    }

    public static void main(String[] args)
    {
	DataStructuresGUI dsg = 
	    new DataStructuresGUI("Data Structures Demo");
    }
}
