package org.usfirst.frc.team4729.robot.commands;

import org.usfirst.frc.team4729.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class ToteClampManualUp extends Command {

    public ToteClampManualUp() {
    	requires(Robot.toteClamp);
    	//requires(Robot.manualOrAuto);
    	//requires(Robot.hashDefine);
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	SmartDashboard.putString("Tote Clamp Manual State", "Going Up");
    	Robot.toteClamp.moveUp(); //NOTE: This has no auto part as it is using a switch and has no "up" switch
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
    	Robot.toteClamp.stop();
    	SmartDashboard.putString("Tote Clamp Manual State", "Inactive");
    }
}
