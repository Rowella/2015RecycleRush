package org.usfirst.frc.team4729.robot.subsystems;

import org.usfirst.frc.team4729.robot.Robot;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.interfaces.Potentiometer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class DistanceSensor extends Subsystem {
	 AnalogInput distanceSensor = new AnalogInput(1);
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void senseDistance(){
    	double distance = distanceSensor.getVoltage();
    	SmartDashboard.putNumber("Distance", distance);
    	if (Robot.ledMovement == false){
    		if (distance > 1.8) {
    			Robot.leds.set(0, -1);
    		}
    		else if(distance > 1.5) {
    			Robot.leds.set(-1, 1);
    		}
    		else if(distance > 1) {
    			Robot.leds.set(-1, 0);
    		}
    		else {
    			Robot.leds.set(1, 1);
    		}
    	}
    }
}

