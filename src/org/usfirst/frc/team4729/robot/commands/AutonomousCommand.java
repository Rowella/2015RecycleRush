package org.usfirst.frc.team4729.robot.commands;

import org.usfirst.frc.team4729.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AutonomousCommand extends Command {
	Timer timer = new Timer()
	static int TOTE_UP_ANGLE = 100;
	static int TOTE_CLAMP_ANGLE = 6;
	static int EMU_UP_ANGLE = 100;
	static int RAMP_TIME = 100;
	static int NO_RAMP_TIME = 0;
	static double AUTO_SPEED = 0.65;
	
	
    public AutonomousCommand() {
    	requires(Robot.driveSubsystem);
    	requires(Robot.toteClamp);
    	requires(Robot.toteTilt);
    	requires(Robot.emuWinch);
    	requires(Robot.swtiches);
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	timer.reset();
    	timer.start();
    	if (Robot.swtiches.ToteBin()) { //Going for tote
    		Robot.toteClamp.moveDown();
    		while (Robot.toteClamp.readClampPot() < TOTE_CLAMP_ANGLE);
    		Robot.toteClamp.stop();
    		Robot.toteTilt.moveUp();
    		while (Robot.toteTilt.readTiltPot() < TOTE_UP_ANGLE); //wait until tote is fully up
    		Robot.toteTilt.stop();
    		if (Robot.swtiches.RampNo()) { //Going across ramp
    			Robot.driveSubsystem.autoTank(AUTO_SPEED, AUTO_SPEED);
    		}
    		else { //Going anti across ramp
    			
    		}
    	}
    
    	else { //Going for bin
    		Robot.emuWinch.stop();
    		while (Robot.emuWinch.emuPotRead() < EMU_UP_ANGLE); // wait until bin is fully up
    		Robot.emuWinch.stop();
    		if (Robot.swtiches.RampNo() == true) { //Going across ramp
    			
    		}
    		else {
    			
    		}
    	}

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
 