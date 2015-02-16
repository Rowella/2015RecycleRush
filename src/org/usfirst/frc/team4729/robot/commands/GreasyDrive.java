package org.usfirst.frc.team4729.robot.commands;

import org.usfirst.frc.team4729.robot.Robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class GreasyDrive extends Command {
	Joystick stick;
	
    public GreasyDrive(Joystick stick) {
    	requires(Robot.driveSubsystem);
    	this.stick = stick;
    	
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.driveSubsystem.greasyDrive(stick);
    	SmartDashboard.putString("Drive Type", "");
    	SmartDashboard.putString("G", "G");
    	SmartDashboard.putString("E", "E");
    	SmartDashboard.putString("T", "T");
    	SmartDashboard.putString("H", "H");
    	SmartDashboard.putString("Y", "Y");
    	SmartDashboard.putString("P", "P");
    	SmartDashboard.putString("e", "E");
    	SmartDashboard.putString("!", "!");
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	SmartDashboard.putString("G", ":(");
    	SmartDashboard.putString("E", ":(");
    	SmartDashboard.putString("T", ":(");
    	SmartDashboard.putString("H", ":(");
    	SmartDashboard.putString("Y", ":(");
    	SmartDashboard.putString("P", ":(");
    	SmartDashboard.putString("e", ":(");
    	SmartDashboard.putString("!", ":(");
    }
}
