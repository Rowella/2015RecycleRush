package org.usfirst.frc.team4729.robot.subsystems;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class AFrameWinch extends Subsystem {
	Talon aFrameWinch = new Talon(4);
	static double MANUAL_A_FRAME_WINCH_SPEED = 0.5;
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void moveUp(){
    	aFrameWinch.set(MANUAL_A_FRAME_WINCH_SPEED);
    }
    
    public void moveDown(){
    	aFrameWinch.set(-MANUAL_A_FRAME_WINCH_SPEED);
    }
}

