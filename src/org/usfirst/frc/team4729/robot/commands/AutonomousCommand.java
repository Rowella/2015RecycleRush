package org.usfirst.frc.team4729.robot.commands;

import org.usfirst.frc.team4729.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class AutonomousCommand extends Command {
	Timer timer = new Timer();
	
	static int RAMP_DISTANCE = 100;
	static int NO_RAMP_DISTANCE = 0;
	static double AUTO_SPEED = 0.65;
	double clampUp;
	double tiltUp;
	double emuUp;
	
	
    public AutonomousCommand(double clampUp, double tiltUp, double emuUp) {
    	requires(Robot.driveSubsystem);
    	requires(Robot.toteClamp);
    	requires(Robot.toteTilt);
    	requires(Robot.emuWinch);
    	requires(Robot.switches);
    	this.clampUp = clampUp;
    	this.tiltUp = tiltUp;
    	this.emuUp = emuUp;
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.driveSubsystem.resetEncoders();
    	if (SmartDashboard.getBoolean("Going for Tote?")) { //Going for tote
    		Robot.toteClamp.moveDown();
    		while (Robot.toteClamp.readClampPot() < clampUp);
    		Robot.toteClamp.stop();
    		Robot.toteTilt.moveUp();
    		while (Robot.toteTilt.readTiltPot() < tiltUp); //wait until tote is fully up
    		Robot.toteTilt.stop();
    		timer.reset();
        	timer.start();
    		Robot.driveSubsystem.autoTank(AUTO_SPEED, AUTO_SPEED);
    		if (SmartDashboard.getBoolean("Going on Ramp?")) { //Going across ramp
    			while ((Robot.driveSubsystem.readLeftEncoder() + Robot.driveSubsystem.readRightEncoder()) < RAMP_DISTANCE);
    		}
    		else { //Not going across ramp
    			while ((Robot.driveSubsystem.readLeftEncoder() + Robot.driveSubsystem.readRightEncoder()) < NO_RAMP_DISTANCE);
    		}
    	}
    
    	else { //Going for bin
    		Robot.emuWinch.stop();
    		while (Robot.emuWinch.emuPotRead() < emuUp); // wait until bin is fully up
    		Robot.emuWinch.stop();
    		timer.reset();
        	timer.start();
        	Robot.driveSubsystem.autoTank(AUTO_SPEED, AUTO_SPEED);
    		if (SmartDashboard.getBoolean("Going on Ramp")) { //Going across ramp
    			while ((Robot.driveSubsystem.readLeftEncoder() + Robot.driveSubsystem.readRightEncoder()) < RAMP_DISTANCE);
    		}
    		else {
    			while ((Robot.driveSubsystem.readLeftEncoder() + Robot.driveSubsystem.readRightEncoder()) < NO_RAMP_DISTANCE);
    		}
    	}
		Robot.driveSubsystem.autoTank(0, 0);
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
    }
}
 