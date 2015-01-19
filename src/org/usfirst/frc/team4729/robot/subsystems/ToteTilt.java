package org.usfirst.frc.team4729.robot.subsystems;

import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.interfaces.Potentiometer;


/**
 *
 */
public class ToteTilt extends Subsystem {
	static double MANUAL_TOTE_TILT_SPEED = 0.5;
    Talon toteTilt = new Talon(3);
    Potentiometer bottomPot = new AnalogPotentiometer(0, 360, 30);
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
    	
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public double bottomPotTest(){
    	return bottomPot.get(); //max 392, min 30
    }
    
    public void moveUp(){
    	toteTilt.set(MANUAL_TOTE_TILT_SPEED);
    }
    
    public void moveDown(){
    	toteTilt.set(-MANUAL_TOTE_TILT_SPEED);
    }
}

