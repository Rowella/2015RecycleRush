package org.usfirst.frc.team4729.robot.subsystems;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class DriveSubsystem extends Subsystem {
	RobotDrive driveTrain = new RobotDrive(0, 1);
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void twoStickArcade(Joystick leftStick, Joystick rightStick) {
    	driveTrain.arcadeDrive(leftStick.getY(), -rightStick.getX());
    }
    
    public void oneStickArcade(Joystick stick) {
    	driveTrain.arcadeDrive(stick.getY(), -stick.getX());
    }
    
    public void twoStickTank (Joystick leftStick, Joystick rightStick) {
    	driveTrain.tankDrive(rightStick, leftStick);
    }
    
	public void oneStickTank(Joystick stick) {
		driveTrain.tankDrive(stick.getY(), stick.getX());
		
	}

}

