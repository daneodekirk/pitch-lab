package pitchLab.modes.passivePitch;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import pitchLab.pianoWindow.GToolTip;
import pitchLab.pianoWindow.PianoWindow;
import pitchLab.reference.Calculations;
import pitchLab.reference.DynmVar;


public class PassivePitchControl implements MouseListener, MouseMotionListener, KeyListener
{
	/*
	 * SWITCH STATEMENT state MODES
	 * case 0:	haven't started yet
	 * case 1: 	Working/Busy/Random Tones Playing
	 * case 2:	single note playing, user needs to drag bar to location, enter confirms choice
	 * case 3:	means user can drag the bar! (ie: pressed in the correct location)
	 * case 4:	dead case, before user can 'accidently press enter' -- must touch drag bar first
	 */
	private PianoWindow pw;
	private PassivePitchMethods ppm;
	private int state = 0;
	
	public PassivePitchControl(PianoWindow pw)
	{
		this.pw = pw;
		this.pw.toolTip = new GToolTip(pw);
		this.pw.setTitle("Press the \"Enter\" key to begin.");
		this.ppm = new PassivePitchMethods(pw);
	}
	
	public void mousePressed(MouseEvent e)
	{
        int x = e.getX();   // Save the x coord of the click	
        
        if (DynmVar.dragBarX-1 <= x && x <= DynmVar.dragBarX+1 && state >= 2)
		{
            state = 3;
			DynmVar.dragFromX = x - DynmVar.dragBarX; 
			
			pw.toolTip.setTip(Calculations.xToCents(e.getX()));
			pw.toolTip.setLocation(e);
			pw.toolTip.setVisible(true);
        } 
		
	}
	
	
	public void mouseDragged(MouseEvent e)
	{
		if (state == 3) 
		{   // True only if button was pressed on the line        	
        	//--- line pos from mouse and original click displacement
            DynmVar.dragBarX = e.getX() - DynmVar.dragFromX;
            
            int xp = e.getX();
            xp = Math.max(xp, 0);
            xp = Math.min(xp, DynmVar.window_Width);
            
            //--- Don't move the line off the screen sides
            DynmVar.dragBarX = Math.max(DynmVar.dragBarX, 0);
            DynmVar.dragBarX = Math.min(DynmVar.dragBarX, DynmVar.window_Width);
            
        	pw.toolTip.setTip(Calculations.xToCents(xp));
        	pw.toolTip.setLocation(e);
        	
        	pw.repaint();
        }
		
	}
	
	public void mouseReleased(MouseEvent e)
	{
		if (state == 3)
			state = 2;
	}
	

	public void keyPressed(KeyEvent e)
	{
		if(e.getKeyCode() == KeyEvent.VK_ENTER)
		{
			switch(state)
			{
				//
				//	CASE WHEN TEST HASN'T STARTED YET
				//
				case 0:	//haven't started yet!
					state = 1; //working
					pw.setTitle("Testing....");
					pw.repaint(); // - why? does this need to happen?
					cycleAndPlay();
					state = 4; // - continuous tone is playing	
					break;
					
				//
				//	CASE WHEN TEST TONE PLAYING AND USER IS ABLE TO GRAB BAR
				//	- when enter pressed her, user has confirmed answer
				//
				case 2:
					state = 1; // - Set state to busy 
					
					if(DynmVar.syncResults)
					{
						//First Write the time Elapsed
						pw.timeStamp(System.currentTimeMillis() - DynmVar.cycleStartTime);
						//Now write drop location
						pw.dataHardCopy.appendToLine(Calculations.xToCents(DynmVar.dragBarX));		
					}

					pw.contSine.stop();	//stop continuous wave
					pw.toolTip.setVisible(false);
					pw.repaint();
					
					if (DynmVar.count < DynmVar.cycles)
					{
						DynmVar.count++;
						pw.setTitle("Testing." + pw.getCyclesString());
						cycleAndPlay();
						state = 4;
					}
					else
						pw.exit();
					
					break;
			}//END SWITCH
		}
		
	}
	
	//
	//	CYCLE AND PLAY
	//
	private void cycleAndPlay()
	{
		pw.playRands();	//play random cycle
		ppm.pp_setFreq();
		if (DynmVar.syncResults)
			ppm.pp_recordPlayTime();
		else
			ppm.pp_play();
	}


	//Ignored Events:
	public void keyReleased(KeyEvent e){}
	public void keyTyped(KeyEvent e){}
	public void mouseEntered(MouseEvent e){}
	public void mouseExited(MouseEvent e){}
	public void mouseMoved(MouseEvent e){}
	public void mouseClicked(MouseEvent e){}
	
}
