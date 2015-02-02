package org.usfirst.frc.team4729.robot.subsystems;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class DriveSubsystem extends Subsystem {
	RobotDrive driveTrain = new RobotDrive(0, 1);
	Encoder leftEncoder = new Encoder(3, 4);
	Encoder rightEncoder = new Encoder(5, 6);
	static double rightOffset = 1;
	static double leftOffset = 1;
	
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
    	leftEncoder.setDistancePerPulse(1);
    	rightEncoder.setDistancePerPulse(1);
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public int readLeftEncoder() {
    	return leftEncoder.get();
    }
    
    public int readRightEncoder() {
    	return rightEncoder.get();
    }
    
    public void resetEncoders() {
    	leftEncoder.reset();
    	rightEncoder.reset();
    }
    
    public void twoStickArcade(Joystick leftStick, Joystick rightStick) {
    	driveTrain.arcadeDrive(leftStick.getY(), rightStick.getX());
    }
    
    public void oneStickArcade(Joystick stick) {
    	driveTrain.arcadeDrive(stick.getY(), stick.getX());
    }
    
    public void twoStickTank (Joystick leftStick, Joystick rightStick) {
    	driveTrain.tankDrive(leftStick.getY()/leftOffset, rightStick.getY()/rightOffset);
    }
    
	public void oneStickTank(Joystick stick) {
		driveTrain.tankDrive(stick.getY()/leftOffset, stick.getX()/rightOffset);
	}
	
	public void autoTank (double leftSpeed, double rightSpeed) {
		driveTrain.tankDrive(leftSpeed, rightSpeed);
	}	

}

