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

// SortGUI.java
// Tests the sorting algorithms by means of a GUI.

import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.text.*;
import javax.swing.border.*;
import java.util.Random;
import com.mhhe.clrs2e.*;

public class SortGUI extends JFrame
{
    protected JCheckBox integersOnly;
    protected JTextField randText;
    protected ListPanel listPanel;
    protected Algorithm algorithm = Algorithm.INSERT;
    
    // Typesafe enum for sorting algorithms.
    protected static class Algorithm
    {
	final public String name;
	final public Class factory;
	
	private Algorithm(String name, Class factory)
	{
	    this.name = name;
	    this.factory = factory;
	}

	public String toString()
	{
	    return name;
	}

	public static final Algorithm INSERT = 
	    new Algorithm("InsertionSort", InsertionSort.class);
	public static final Algorithm MERGE = 
	    new Algorithm("MergeSort", MergeSort.class);
	public static final Algorithm HEAP = 
	    new Algorithm("HeapSort", Heapsort.class);
	public static final Algorithm QUICK = 
	    new Algorithm("Quicksort", Quicksort.class);
	public static final Algorithm COUNT = 
	    new Algorithm("CountingSort", CountingSort.class);
	public static final Algorithm RADIX = 
	    new Algorithm("RadixSort", RadixSort.class);
	public static final Algorithm BUCKET = 
	    new Algorithm("BucketSort", BucketSort.class);
	public static final Algorithm[] SORTERS = {INSERT, MERGE, HEAP, 
						  QUICK, COUNT, RADIX, 
						  BUCKET};
    }

    // Constructor.  Sets frame title and creates content pane. 
    public SortGUI(String title)
    {
	super(title);
	makeContentPane();
    }

    // Creates and lays out the internal components of the Sort Demo.
    protected void makeContentPane()
    {
	// Create the four main panels.
	JPanel algPanel = makeAlgorithmPanel();
	JPanel randPanel = makeRandomPanel();
	JPanel csPanel = makeClearSortPanel();
	listPanel = new ListPanel(NumberFormat.getInstance());

	// Layout the panels.
	Container contentPane = getContentPane();
	GridBagLayout gb = new GridBagLayout();
	GridBagConstraints gc = new GridBagConstraints();
	contentPane.setLayout(gb);

	gc.weightx = 0.5; gc.weighty = 0.5;
	gc.fill = GridBagConstraints.BOTH;

	gc.gridx = 0; gc.gridy = 0; gc.gridwidth = 2;
	gb.setConstraints(algPanel, gc);
	contentPane.add(algPanel);

	gc.gridwidth = 1; gc.gridx = 2; gc.gridheight = 2;
	gb.setConstraints(listPanel, gc);
	contentPane.add(listPanel);

	gc.gridx = 0; gc.gridy = 1; gc.gridheight = 1;
	gb.setConstraints(randPanel, gc);
	contentPane.add(randPanel);
	
	gc.gridx = 1; gc.gridy = 1;
	gb.setConstraints(csPanel, gc);
	contentPane.add(csPanel);	
    }

    // Algorithm selection panel.  Has radio buttons for choosing the
    // sorting algorithm and a checkbox for restricting the input to
    // integers.
    protected JPanel makeAlgorithmPanel()
    {
	JRadioButton[] algButtons;
	ButtonGroup group;

	JPanel panel = new JPanel();
	ActionListener algListener = new AlgorithmListener();
	panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

	group = new ButtonGroup(); 
	algButtons = new JRadioButton[Algorithm.SORTERS.length];

	// Create a button for each sorting algorithm.
	for (int i = 0; i < Algorithm.SORTERS.length; i++) {
	    algButtons[i] = new JRadioButton(Algorithm.SORTERS[i].name);

	    // Register name and action listener.
	    algButtons[i].setActionCommand(Algorithm.SORTERS[i].name);
	    algButtons[i].addActionListener(algListener);

	    // Add button to the button group and the panel.
	    group.add(algButtons[i]);
	    panel.add(algButtons[i]);
	}

	// Select the first one (Insertion Sort).
	algButtons[0].setSelected(true);

	// Create a checkbox for restricting the input to integers.
	integersOnly = new JCheckBox("Restrict to Integers");
	integersOnly.addItemListener(new IntegerListener());
	panel.add(integersOnly);
	
	panel.setBorder(BorderFactory.createTitledBorder
			(BorderFactory.createEtchedBorder
			 (EtchedBorder.RAISED),
			 "Pick an Algorithm"));
	return panel;
    }

    // Random input generation panel.  Has an input box for the number
    // of inputs to generate and a button to fire the listener to
    // generate the inputs.
    protected JPanel makeRandomPanel()
    {
	// Create layout panels.
	JPanel panel = new JPanel();
	JPanel subpanel = new JPanel();
	subpanel.setLayout(new BoxLayout(subpanel, BoxLayout.X_AXIS));
	panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

	// Create internal components.
	JLabel randLabel = new JLabel("How Many?");
	randText = new JTextField(3);	
	JButton randButton = new JButton("Generate Numbers");

	// Register listeners.
	randButton.addActionListener(new RandomListener());
	randText.addActionListener(new RandomListener());

	// Add components to panels.
	subpanel.add(randLabel);
	subpanel.add(randText);
	panel.add(subpanel);
	panel.add(randButton);

	panel.setBorder(BorderFactory.createTitledBorder
			    (BorderFactory.createEtchedBorder
			     (EtchedBorder.RAISED),
			     "Make random numbers?"));
	return panel;
    }

    // Clear/Sort panel.  Has one button to clear the list and one
    // button to sort it.
    protected JPanel makeClearSortPanel()
    {
	JPanel panel = new JPanel();
	panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

	JButton clear = new JButton("Clear List");
	clear.addActionListener(new ClearListener());

	JButton sort = new JButton("Sort List");
	sort.addActionListener(new SortListener());

	panel.add(clear);
	panel.add(sort);
	
	panel.setBorder(BorderFactory.createTitledBorder
			(BorderFactory.createEtchedBorder
			 (EtchedBorder.RAISED),
			 "Manipulate List"));
	return panel;
    }

    // Handles the selection of a new algorithm.
    protected class AlgorithmListener implements ActionListener
    {
	public void actionPerformed(ActionEvent ev)
	{
	    String name = ev.getActionCommand();

	    // Match the event source to a specific button and set
	    // algorithm to the corresponding enum.
	    for (int i = 0; i < Algorithm.SORTERS.length; i++)
		if (Algorithm.SORTERS[i].name == name)
		    algorithm = Algorithm.SORTERS[i];

	    if (algorithm == Algorithm.COUNT || 
		algorithm == Algorithm.RADIX) {
		// Counting sort and radix sort can handle only integers.
		integersOnly.setSelected(true);
		integersOnly.setEnabled(false);
	    }
	    else if (algorithm == Algorithm.BUCKET) {
		// Bucket sort can handle only real numbers, however.
		integersOnly.setSelected(false);
		integersOnly.setEnabled(false);
	    }
	    else 
		// All other algorithms can handle both.
		integersOnly.setEnabled(true);
	}
    }

    // When the selection of integersOnly changes, clears the list and
    // changes its formatter appropriately.
    protected class IntegerListener implements ItemListener
    {
	public void itemStateChanged(ItemEvent ev)
	{
	    if (ev.getStateChange() == ItemEvent.SELECTED) {
		NumberFormat nf = NumberFormat.getInstance();
		nf.setParseIntegerOnly(true);
		listPanel.setFormat(nf);
	    }	    
	    else if (ev.getStateChange() == ItemEvent.DESELECTED) {
		NumberFormat nf = NumberFormat.getInstance();
		nf.setParseIntegerOnly(false);
		listPanel.setFormat(nf);
	    }
	}
    }

    // Produces random numbers and adds them to the list.
    protected class RandomListener implements ActionListener
    {
	protected Random rand;

	public RandomListener()
	{
	    rand = new Random();
	}

	public void actionPerformed(ActionEvent ev)
	{
	    int count = Integer.parseInt(randText.getText());
	    if (integersOnly.isSelected()) {
		for (int i = 0; i < count; i++)
		    listPanel.addElement(new Integer(rand.nextInt(100000)));
	    }
	    else {
		for (int i = 0; i < count; i++)
		    listPanel.addElement(new Double(rand.nextDouble()));
	    }
	}
    }

    // Clears the list when the clear button is pushed.
    protected class ClearListener implements ActionListener
    {
	public void actionPerformed(ActionEvent ev)
	{
	    listPanel.clear();
	}
    }

    // Sorts the list when the sort button is pushed.
    protected class SortListener implements ActionListener
    {
	public void actionPerformed(ActionEvent ev)
	{
	    // Get the contents of the list.
	    Object[] objects =  listPanel.toArray();
	    Number[] numbers = new Number[objects.length];

	    // Cast the contents of the list to Number values.
	    for(int i = 0; i < objects.length; i++)
		numbers[i] = (Number) objects[i];

	    if (algorithm == Algorithm.COUNT){
		CountingSort sorter = new CountingSort();

		// Counting Sort requires an array of NonNegativeIntegers.
		NonNegativeInteger[] array = 
		    new NonNegativeInteger[numbers.length];
		for (int i = 0; i < numbers.length; i++)
		    array[i] = new IntegerValue(numbers[i].intValue());

		sorter.sort(array);

		// Repopulate the list with the sorted elements.
		listPanel.clear();
		for(int i = 0; i < array.length; i++)
		    listPanel.addElement(new Integer
					 (array[i].getKey()));
	    }
	    else if (algorithm == Algorithm.RADIX) {
		RadixSort sorter = new RadixSort();

		// Radix Sort requires an array of NonNegativeIntegers.
		NonNegativeInteger[] array = 
		    new NonNegativeInteger[numbers.length];
		for (int i = 0; i < numbers.length; i++)
		    array[i] = new IntegerValue(numbers[i].intValue());

		sorter.sort(array);

		// Repopulate the list with the sorted elements.
		listPanel.clear();
		for (int i = 0; i < array.length; i++)
		    listPanel.addElement(new Integer
					 (array[i].getKey()));
	    }
	    else if (algorithm == Algorithm.BUCKET) {
		BucketSort sorter = new BucketSort();

		// Bucket Sort needs DoubleValued objects.
		DoubleValued[] array = new DoubleValued[numbers.length];
		for (int i = 0; i < numbers.length; i++)
		    array[i] = new Value(numbers[i].doubleValue());

		sorter.sort(array);

		// Repopulate the list with the sorted elements.
		listPanel.clear();
		for (int i = 0; i < array.length; i++)
		    listPanel.addElement(new Double(array[i].getKey()));
	    }
	    else {
		try {
		    // We can use the Class object associated with
		    // each enum to create the rest of the sorters.
		    Sorter sorter = (Sorter) algorithm.factory.newInstance();

		    // They all require Comparable, so use Value.
		    Value[] array = new Value[numbers.length];
		    for (int i = 0; i < numbers.length; i++)
			array[i] = new Value(numbers[i].doubleValue());
		    
		    sorter.sort(array);

		    // Repopulate the list with the sorted elements.
		    listPanel.clear();
		    for (int i = 0; i < array.length; i++)
			listPanel.addElement(new Double(array[i].getKey()));
		}
		catch (InstantiationException ex) {
		    System.err.println("Couldn't Instantiate a sorter");
		}
		catch (IllegalAccessException ex) {
		    System.err.println("Couldn't Instantiate a sorter");
		}
	    }
	}
    }

    public static void main(String[] args)
    {
	JFrame gui = new SortGUI("Sorting Demo");
	gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	gui.pack();
	gui.setVisible(true);
    }
}
