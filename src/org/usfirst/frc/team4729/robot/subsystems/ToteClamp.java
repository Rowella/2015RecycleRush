package org.usfirst.frc.team4729.robot.subsystems;

import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.interfaces.Potentiometer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class ToteClamp extends Subsystem {
	Talon toteClamp = new Talon(2);
	Potentiometer clampPot = new AnalogPotentiometer(1);
	DigitalInput toteSensor = new DigitalInput(2);
	static double MANUAL_TOTE_CLAMP_SPEED = 0.5;
	
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public double readClampPot() {
    	SmartDashboard.putNumber("clampPot", clampPot.get());
    	return clampPot.get();
	}
    
    public boolean readToteSensor() {
    	return toteSensor.get();
    }
    
    public void moveUp(){
    	toteClamp.set(MANUAL_TOTE_CLAMP_SPEED);
    }
    
    public void moveDown(){
    	toteClamp.set(-MANUAL_TOTE_CLAMP_SPEED);
    }
    
    public void stop() {
    	toteClamp.set(0);
    }
}

