package org.usfirst.frc.team4729.robot.commands;

import org.usfirst.frc.team4729.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class AutonomousCommand extends Command {
	//All constants are in HashDefine
	Timer timer = new Timer();
	
	final static int RAMP_DISTANCE = 100;
	final static int NO_RAMP_DISTANCE = 0;
	final static double AUTO_SPEED = 0.65;
	
	public static boolean toteOrNo = false;
	public static boolean rampOrNo = false;
	
	
	double tiltUp;
	double emuUp;
	SendableChooser toteChooser = new SendableChooser();
	SendableChooser rampChooser = new SendableChooser();
	
	
    public AutonomousCommand(double tiltUp, double emuUp) {
    	requires(Robot.driveSubsystem);
    	requires(Robot.toteClamp);
    	requires(Robot.toteTilt);
    	requires(Robot.emuWinch);
    	requires(Robot.switches);
    	//requires(Robot.hashDefine);
    	this.tiltUp = tiltUp;
    	this.emuUp = emuUp;
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	toteChooser.addDefault("Going for tote", new GoForTote());
    	toteChooser.addObject("Going for bin", new DontGoForTote());
    	SmartDashboard.putData("Tote Chooser", toteChooser);
    	rampChooser.addDefault("Going on Ramp", new GoOnRamp());
    	rampChooser.addObject("Not Going on Ramp", new DontGoOnRamp());
    	SmartDashboard.putData("Ramp Chooser", rampChooser);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {

    	Robot.driveSubsystem.resetEncoders();
    	Command toteDeterminer = (Command) toteChooser.getSelected();
    	toteDeterminer.start();
    	Command rampDeterminer = (Command) rampChooser.getSelected();
    	rampDeterminer.start();
    	
    	if (toteOrNo) { //Going for tote
    		Robot.toteClamp.moveDown();
    		while (!Robot.toteClamp.readToteSensor());
    		Robot.toteClamp.stop();
    		Robot.toteTilt.moveUp();
    		while (Robot.toteTilt.readTiltEncoder() < tiltUp); //wait until tote is fully up
    		Robot.toteTilt.stop();
    		timer.reset();
        	timer.start();
    		Robot.driveSubsystem.tank(AUTO_SPEED, AUTO_SPEED);
    		if (rampOrNo) { //Going across ramp
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
        	Robot.driveSubsystem.tank(AUTO_SPEED, AUTO_SPEED);
    		if (rampOrNo) { //Going across ramp
    			while ((Robot.driveSubsystem.readLeftEncoder() + Robot.driveSubsystem.readRightEncoder()) < RAMP_DISTANCE);
    		}
    		else {
    			while ((Robot.driveSubsystem.readLeftEncoder() + Robot.driveSubsystem.readRightEncoder()) < NO_RAMP_DISTANCE);
    		}
    	}
		Robot.driveSubsystem.tank(0, 0);
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
 