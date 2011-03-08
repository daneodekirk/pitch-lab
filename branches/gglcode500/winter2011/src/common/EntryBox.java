package common;
//
//  EntryBox.java
//  (Version 0.6)
//
//	
//
//  Created by Gavin Shriver on 4/27/09.
//  Copyright 2009 __MyCompanyName__. All rights reserved.
//
import java.awt.*;

import javax.swing.*;


import javax.swing.JLabel;
import javax.swing.JPanel;


public class EntryBox extends JPanel
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/********************************************************************************
	 * Begin Variables
	 ********************************************************************************/

	public static final int INT = 0;
	public static final int DOUBLE = 1;
	public static final int STRING = 2;
	public static final int PASS = 3;
		
	private int mode;
		
	private int fieldSize = 20;
	private JTextField inputfield;
	private JPasswordField passfield;
	
	
	
	
	/********************************************************************************
	 * END Variables
	 * Begin Constructors
	 ********************************************************************************/
	
	//String
	public EntryBox(int fieldSize, int mode, String text, String initialValue)
	{		
		setMode(mode);
		setFieldSize(fieldSize);
		windowMaker(text);
		setTo(initialValue);
	}
	public EntryBox(int fieldSize, int mode, String text, String initialValue,boolean enabled)
	{		
		setMode(mode);
		setFieldSize(fieldSize);
		windowMaker(text);
		setTo(initialValue);
		setEnabled(enabled);
	}
	
	
	//Number
	public EntryBox(int fieldSize, int mode, String text, Number initialValue)
	{		
		setMode(mode);
		setFieldSize(fieldSize);
		windowMaker(text);
		setTo(initialValue);
	}
	public EntryBox(int fieldSize, int mode, String text, Number initialValue, boolean enabled)
	{		
		setMode(mode);
		setFieldSize(fieldSize);
		windowMaker(text);
		setTo(initialValue);
		setEnabled(enabled);
	}
	
	//char[]	
	public EntryBox(int fieldSize, int mode, String text, char[] initialValue)
	{		
		setMode(mode);
		setFieldSize(fieldSize);
		windowMaker(text);
		setTo(initialValue);
	}
	
	//char[]	
	public EntryBox(int fieldSize, int mode, String text, char[] initialValue, boolean enabled)
	{		
		setMode(mode);
		setFieldSize(fieldSize);
		windowMaker(text);
		setTo(initialValue);
		setEnabled(enabled);
	}
	
	//no defaults:
	public EntryBox(int fieldSize, int mode, String text)
	{		
		setMode(mode);
		setFieldSize(fieldSize);
		windowMaker(text);
	}
	public EntryBox(int fieldSize, int mode, String text, boolean enabled)
	{		
		setMode(mode);
		setFieldSize(fieldSize);
		windowMaker(text);
		setEnabled(enabled);

	}
	public EntryBox(int fieldSize, int mode)
	{		
		setMode(mode);
		setFieldSize(fieldSize);
		windowMaker(null);
	}
	public EntryBox(int fieldSize, int mode, boolean enabled)
	{		
		setMode(mode);
		setFieldSize(fieldSize);
		windowMaker(null);
		setEnabled(enabled);
	}
	
	/********************************************************************************
	 * END Constructors
	 * Begin helper methods
	 ********************************************************************************/
	
	private void windowMaker(String text)
	 {
		this.setLayout(new BorderLayout());
		
		 if (text != null)
			 this.add(new JLabel(text), BorderLayout.LINE_START);
			 
		 if(mode != PASS)
		 {
			 inputfield = new JTextField(fieldSize);
			 this.add(inputfield, BorderLayout.LINE_END);
		 }
		 else if(mode == PASS)
		 {
			 passfield = new JPasswordField(fieldSize);
			 this.add(passfield, BorderLayout.LINE_END);
		 }
	 }
	
	
	/********************************************************************************
	 * END helper methods
	 * Begin get/set methods
	 ********************************************************************************/
	
	
	public void setFieldSize(int fieldSize)
	{
		this.fieldSize = fieldSize;
	}
	
	public void setMode(int mode)
	{
		this.mode = mode;
	}
	
	public void setEditable(boolean editable)
	{
		inputfield.setEditable(editable);
	}
	
	public void setEnabled(boolean enabled)
	{
		if (mode != PASS)
			inputfield.setEnabled(enabled);
		if (mode == PASS)
			passfield.setEnabled(enabled);
	}
	
	
	public void setTo(char[] value)
	{		
		passfield.setText(value.toString());
	}
	
	public void setTo(String value)
	{
		inputfield.setText(value);
	}
	
	public void setTo(Number value)
	{
		inputfield.setText(value.toString());
	}
	
	public Object getValue()
	{
		if (mode == INT)
			return (int)Integer.valueOf(inputfield.getText());
		else if (mode == DOUBLE)
			return (double)Double.valueOf(inputfield.getText());
		else if (mode == STRING)
			return (String)inputfield.getText();
		else if (mode == PASS)
			return (char[])passfield.getPassword();
		else
			return null;
	}



}





















