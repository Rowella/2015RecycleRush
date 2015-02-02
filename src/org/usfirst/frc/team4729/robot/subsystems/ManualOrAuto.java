package org.usfirst.frc.team4729.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class ManualOrAuto extends Subsystem {
	boolean manual = true;
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
    	
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void changeToManual() {
    	manual = true;
    }
    
    public void changeToAuto() {
    	manual = false;
    }
    
    public boolean readManualState() {
    	return manual;
    }
}

