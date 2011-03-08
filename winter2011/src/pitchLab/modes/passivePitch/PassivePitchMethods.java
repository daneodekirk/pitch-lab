package pitchLab.modes.passivePitch;

import pitchLab.pianoWindow.PianoWindow;
import pitchLab.reference.Calculations;
import pitchLab.reference.DynmVar;
import sound.Sine;

public class PassivePitchMethods
{

	private PianoWindow pw;
	
	public PassivePitchMethods(PianoWindow pw)
	{
		this.pw = pw;
	}
	
	public void pp_setFreq()
	{
		DynmVar.setFirstFreq(Sine.randFreqGenerator());
	}
	public void pp_recordPlayTime()
	{
		pw.dataHardCopy.appendToLine(Double.toString(DynmVar.getFirstFreq()));
		pw.dataHardCopy.appendToLine(Calculations.freqToCents(DynmVar.getFirstFreq()));
		pw.contSine.play(DynmVar.getFirstFreq());
		DynmVar.cycleStartTime = System.currentTimeMillis();
	}
	public String pp_practicePlay()
	{
		pw.contSine.play(DynmVar.getFirstFreq());
		return Calculations.freqToCents(DynmVar.getFirstFreq());
	}
	public void pp_play()
	{
		pw.contSine.play(DynmVar.getFirstFreq());
	}
}
