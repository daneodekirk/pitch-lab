package common;

import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JPanel;

public class GUIMethods
{
	public static int CENTER = GridBagConstraints.CENTER;
	public static int WEST = GridBagConstraints.WEST;
	public static int EAST = GridBagConstraints.EAST;
	public static int NORTH = GridBagConstraints.NORTH;
	public static int NORTHEAST = GridBagConstraints.NORTHEAST;
	public static int NORTHWEST = GridBagConstraints.NORTHWEST;
	public static int SOUTH = GridBagConstraints.SOUTH;
	public static int SOUTHEAST = GridBagConstraints.SOUTHEAST;
	public static int SOUTHWEST = GridBagConstraints.SOUTHWEST;
	/**
	 * 
	 * @param compons
	 * @param bagHeader
	 * @param center
	 * @return
	 */
    public static JComponent makeBag(JComponent[] compons,String bagHeader, boolean center)  
    //public static JComponent makeBag(JComponent[] compons,String bagHeader, int location)  
	{
    	JPanel panel = new JPanel();
	    GridBagLayout gridbag = new GridBagLayout();
	
	    panel.setLayout(gridbag);
	    panel.setBorder(
				BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder(bagHeader),
                BorderFactory.createEmptyBorder(15,15,15,15)));
        
        GridBagConstraints c = new GridBagConstraints();

        if(center)
        {
        	c.gridwidth = GridBagConstraints.CENTER;
 	        c.anchor = GridBagConstraints.CENTER;
 	        c.weightx = 6.0;
        }
        else
        {
	        c.gridwidth = GridBagConstraints.REMAINDER;
	        c.anchor = GridBagConstraints.EAST;
	        c.weightx = 6.0;
        }
        /*c.gridwidth = location;
	    c.anchor = location;
	    c.weightx = 6.0;*/
        
        for (int i = 0; i < compons.length; i++) 
		{
        	panel.add(compons[i],c);
        }
        
        return panel;
    }
    
    /**
     * 
     * @param obj1
     * @param obj2
     * @return
     */
	public static JComponent flowMaker(JComponent obj1, JComponent obj2)
	{
		JPanel panel = new JPanel(new FlowLayout());
		
		panel.add(obj1);
		if (obj2 != null)
			panel.add(obj2);
		
		return panel;
	}
	
	/*public static JComponent flowMaker(JComponent[] components)
	{
		JPanel panel = new JPanel(new FlowLayout());
		for(int i=0 ; i < components.length ; i++ )
		{
			panel.add(components[i]);
		}		
		return panel;
	}*/
}
