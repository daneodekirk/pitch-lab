package pitchLab.modes.passivePitch;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import pitchLab.instructWindow.InstructionWindow;
import pitchLab.pianoWindow.GToolTip;
import pitchLab.pianoWindow.PianoWindow;
import pitchLab.reference.Calculations;
import pitchLab.reference.DynmVar;

public class PPCPractice implements MouseListener, MouseMotionListener, KeyListener
{ 
	private PianoWindow pw;
	private PassivePitchMethods ppm;
	private int state = 0;
	
	/*
	 * SWITCH STATEMENT state MODES
	 * case 0:	haven't started yet
	 * case 1: 	Working/Busy/Random Tones Playing
	 * case 2:	single note playing, user needs to drag bar to location, enter confirms choice
	 * case 3:	means user can drag the bar! (ie: pressed in the correct location)
	 * case 4:	dead case, before user can 'accidently press enter' -- must touch drag bar first
	 */
	
	public PPCPractice(PianoWindow pw)
	{
		this.pw = pw;
		this.pw.instruct = new InstructionWindow(this.pw);
		this.pw.toolTip = new GToolTip(this.pw);
		this.pw.setTitle("Press the \"Enter\" key to begin.");
		this.ppm = new PassivePitchMethods(pw);
	}

	public void mousePressed(MouseEvent e)
	{		
        int x = e.getX();   // Save the x coord of the click	

        if (DynmVar.dragBarX-1 <= x && x <= DynmVar.dragBarX+1 && state >= 2)
		{
        	if(state == 4)
        		pw.instruct.setCanSetAnswer(true, false);
        	state = 3;
        	pw.instruct.setInstructionPlace(2,true);
        	DynmVar.dragFromX = x - DynmVar.dragBarX; 
			
        	//ToolTip
			pw.toolTip.setTip(Calculations.xToCents(e.getX()));
			pw.toolTip.setLocation(e);
			pw.toolTip.setVisible(true);
			
			pw.repaint();				
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
            
        	pw.instruct.setCurrAnsSelected(pw.toolTip.getTip());
        	
        	pw.repaint();
        }
		
	}
	@Override
	public void mouseReleased(MouseEvent e)
	{		
		if (state == 3)
		{
			state = 2;
			pw.instruct.setInstructionPlace(1,true);
		}
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
					pw.instruct.setInstructionPlace(3,true);
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
					pw.contSine.stop();	//stop continuous wave
					pw.toolTip.setVisible(false);
					//TODO:  make sure that instruct is highlighting the correct #
					pw.instruct.nextRound(Calculations.xToCents(DynmVar.dragBarX),3);
					cycleAndPlay();
					state = 4; 
					break;
			}//END SWITCH
			
			
		}		
	}
	
	//
	//	CYCLE AND PLAY
	//
	private void cycleAndPlay()
	{
		pw.playRands(); // - first set of random tones
		pw.instruct.setInstructionPlace(1,true);
		ppm.pp_setFreq(); 
		pw.instruct.setCurrAnsToSet(ppm.pp_practicePlay());
	}
	
	
	//Ignored Events:
	public void mouseClicked(MouseEvent e){}
	public void mouseEntered(MouseEvent e){}
	public void mouseExited(MouseEvent e){}
	public void mouseMoved(MouseEvent e){}
	public void keyReleased(KeyEvent e){}
	public void keyTyped(KeyEvent e){}
	
}
