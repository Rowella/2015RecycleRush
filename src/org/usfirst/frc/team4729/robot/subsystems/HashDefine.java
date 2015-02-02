package org.usfirst.frc.team4729.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class HashDefine extends Subsystem{
	//This is where all of the constants are placed
	
	//the next few are for Teleop
	final static double TOTE_TILT_UP_ANGLE = 1;
	final static double TOTE_TILT_DOWN_ANGLE = 0.1;
	final static double TOTE_CLAMP_UP_ANGLE = 1;
	final static double TOTE_CLAMP_DOWN_ANGLE = 0.1;
	final static double EMU_UP_ANGLE = 1;
	final static double EMU_DOWN_ANGLE = 0.1;
	
	//the next few are for Autonomous only
	final static int RAMP_DISTANCE = 100;
	final static int NO_RAMP_DISTANCE = 0;
	final static double AUTO_SPEED = 0.65;
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public double toteTiltUpAngle() {
    	return TOTE_TILT_UP_ANGLE;
    }
    
    public double toteTiltDownAngle() {
    	return TOTE_TILT_DOWN_ANGLE;
    }
    
    public double toteClampUpAngle() {
    	return TOTE_CLAMP_UP_ANGLE;
    }
    
    public double toteClampDownAngle() {
    	return TOTE_CLAMP_DOWN_ANGLE;
    }
    
    public double emuUpAngle() {
    	return EMU_UP_ANGLE;
    }
    
    public double emuDownAngle(){
    	return EMU_DOWN_ANGLE;
    }
    
    
    public int rampDistance(){
    	return RAMP_DISTANCE;
    }
    
    public int noRampDistance(){
    	return NO_RAMP_DISTANCE;
    }
    
    public double autoSpeed(){
    	return AUTO_SPEED;
    }
}

