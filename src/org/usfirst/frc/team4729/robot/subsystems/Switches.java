package org.usfirst.frc.team4729.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Switches extends Subsystem {
	DigitalInput autoSwitchToteBin = new DigitalInput(0);
	DigitalInput autoSwitchRampNo = new DigitalInput(1);
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

	public boolean ToteBin() {
		return autoSwitchToteBin.get();
	}
	
	public boolean RampNo() {
		return autoSwitchRampNo.get();
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
}

