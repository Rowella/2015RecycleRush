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
	
	final static double RAMP_DISTANCE = 0;
	final static double NO_RAMP_DISTANCE = 0;
	final static double AUTO_SPEED = -0.65;
	final static double TO_TOTE_DISTANCE = 3;
	
	public static boolean toteOrNo = false;
	public static boolean rampOrNo = false;
	
	
	double tiltUp;
	double emuUp;
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
    static int counter = 0;
    // Called just before this Command runs the first time
    protected void initialize() {
    	
    	rampChooser.addDefault("Going on Ramp", new GoOnRamp());
    	rampChooser.addObject("Not Goi"
    			+ "ng on Ramp", new DontGoOnRamp());
    	SmartDashboard.putData("Ramp Chooser", rampChooser);
    	counter = 0;
    }


    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(counter == 0){
    		timer.reset();
    		timer.start();
    	}
    	if(Robot.driveSubsystem.readLeftEncoder() < Robot.autoTime){
    		Robot.driveSubsystem.tank(AUTO_SPEED, AUTO_SPEED);
    	} else if (Robot.emuWinch.emuPotRead() < Robot.EMU_UP_ANGLE){
    		Robot.emuWinch.moveUp();
    	} else{
    		Robot.driveSubsystem.tank(0, 0);
    	}
    	counter++;
    	

    	/*SmartDashboard.putString("Au", "Tilt Down");
    	while(Robot.toteTilt.readTiltEncoder() > Robot.TOTE_TILT_DOWN_ANGLE){
    		Robot.toteTilt.moveDown();
    	}
    	//Robot.driveSubsystem.resetEncoders();
    	SmartDashboard.putString("Au", "chooser");
    	Command toteDeterminer = (Command) toteChooser.getSelected();
    	toteDeterminer.start();
    	Command rampDeterminer = (Command) rampChooser.getSelected();
    	rampDeterminer.start();
    	timer.reset();
    	timer.start();
    	
    	if (toteOrNo) { //Going for tote
    		SmartDashboard.putString("Au", "1sec");
    		while (Robot.driveSubsystem.readLeftEncoder() < TO_TOTE_DISTANCE){
    			Robot.driveSubsystem.tank(AUTO_SPEED, AUTO_SPEED);
    		}
    		Robot.driveSubsystem.tank(0,0);
    		//Robot.driveSubsystem.resetEncoders();
    		SmartDashboard.putString("Au", "clamp");
    		Robot.toteClamp.moveDown();
    		//while (!Robot.toteClamp.readToteSensor());
    		Robot.toteClamp.stop();
    		SmartDashboard.putString("Au", "Tilt");
    		Robot.toteTilt.moveUp();
    		while (Robot.toteTilt.readTiltEncoder() < tiltUp){//wait until tote is fully up
    			Robot.toteTilt.moveUp();
    		}
    		Robot.toteTilt.stop();
    		timer.reset();
        	timer.start();
    		if (rampOrNo) { //Going across ramp
    			while ((Robot.driveSubsystem.readLeftEncoder()) < RAMP_DISTANCE){
    				Robot.driveSubsystem.tank(AUTO_SPEED, AUTO_SPEED);
    			}
    		}
    		else { //Not going across ramp
    			while ((Robot.driveSubsystem.readLeftEncoder()) < NO_RAMP_DISTANCE){
    				Robot.driveSubsystem.tank(AUTO_SPEED, AUTO_SPEED);
    			}
    		}
    	}
    
    	else { //Going for bin
    		Robot.emuWinch.stop();
    		while (Robot.emuWinch.emuPotRead() < emuUp); // wait until bin is fully up
    		Robot.emuWinch.stop();
    		timer.reset();
        	timer.start();
    		if (rampOrNo) { //Going across ramp
    			while ((Robot.driveSubsystem.readLeftEncoder()) < RAMP_DISTANCE){
    	        	Robot.driveSubsystem.tank(AUTO_SPEED, AUTO_SPEED);
    			}
    		}
    		else {
    			while ((Robot.driveSubsystem.readLeftEncoder()) < NO_RAMP_DISTANCE){
    	        	Robot.driveSubsystem.tank(AUTO_SPEED, AUTO_SPEED);
    			}
    		}
    	}
    	SmartDashboard.putString("Au", "End");
		Robot.driveSubsystem.tank(0, 0);
		while (true);*/
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
 