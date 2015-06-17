
package org.usfirst.frc.team4729.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team4729.robot.commands.AutoClose;
import org.usfirst.frc.team4729.robot.commands.AutoFar;
import org.usfirst.frc.team4729.robot.commands.AutoMedium;
import org.usfirst.frc.team4729.robot.commands.AutoNone;
import org.usfirst.frc.team4729.robot.commands.AutonomousCommand;
import org.usfirst.frc.team4729.robot.commands.FindDistance;
import org.usfirst.frc.team4729.robot.commands.TwoStickArcade;
import org.usfirst.frc.team4729.robot.subsystems.DistanceSensor;
import org.usfirst.frc.team4729.robot.subsystems.DriveSubsystem;
import org.usfirst.frc.team4729.robot.subsystems.EmuWinch;
//import org.usfirst.frc.team4729.robot.subsystems.HashDefine;
import org.usfirst.frc.team4729.robot.subsystems.LEDs;
//import org.usfirst.frc.team4729.robot.subsystems.ManualOrAuto;
import org.usfirst.frc.team4729.robot.subsystems.Switches;
import org.usfirst.frc.team4729.robot.subsystems.ToteClamp;
import org.usfirst.frc.team4729.robot.subsystems.ToteTilt;
import org.usfirst.frc.team4729.robot.subsystems.ExampleSubsystem;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
	Preferences prefs;
	Joystick leftStick = new Joystick(0);
	Joystick rightStick = new Joystick(1);
	

	public static  ExampleSubsystem exampleSubsystem;
	public static  DriveSubsystem driveSubsystem;
	public static  ToteTilt toteTilt;
	public static  ToteClamp toteClamp;
	public static  EmuWinch emuWinch; 
	public static  Switches switches;
	public static  DistanceSensor distanceSensor;
	//public static  ManualOrAuto manualOrAuto;
	//public static  HashDefine hashDefine;
	public static  LEDs leds;
	public static OI oi;
	
	public static boolean manual = true;
	public static boolean ledMovement = true;
	//This is where all of the constants are placed
	
	final public static double TOTE_TILT_UP_ANGLE = 270;
	final public static double TOTE_TILT_DOWN_ANGLE = 0;
	final public static double EMU_UP_ANGLE = 0.074;
	final public static double EMU_DOWN_ANGLE = 0.084;

	public static double autoTime = 365;

	SendableChooser toteChooser = new SendableChooser();

	
    Command autonomousCommand;

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
    	exampleSubsystem = new ExampleSubsystem();
    	driveSubsystem = new DriveSubsystem();
    	toteTilt = new ToteTilt();
    	toteClamp = new ToteClamp();
    	emuWinch = new EmuWinch(); 
    	switches = new Switches();
    	distanceSensor = new DistanceSensor();
    	//manualOrAuto = new ManualOrAuto();
    	//hashDefine = new HashDefine();
    	leds = new LEDs();
		System.out.println("Before OI");
    	oi = new OI();
    	System.out.println("After OI");
    	toteChooser.addDefault("Medium", new AutoMedium());
    	toteChooser.addObject("Short", new AutoClose());
    	toteChooser.addObject("Long", new AutoFar());
    	toteChooser.addObject("Nothing", new AutoNone());
    	SmartDashboard.putData("Speed", toteChooser);
        // instantiate the command used for the autonomous period
        autonomousCommand = new AutonomousCommand(TOTE_TILT_UP_ANGLE, EMU_UP_ANGLE);//TOTE_CLAMP_UP_ANGLE, TOTE_TILT_UP_ANGLE, EMU_UP_ANGLE);
    }
	
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

    public void autonomousInit() {
    	toteChooser.getSelected();
        // schedule the autonomous command (example)
        if (autonomousCommand != null) autonomousCommand.start();
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
    }

    public void teleopInit() {
		// This makes sure that the autonomous stops running when
        // teleop starts running. If you want the autonomous to 
        // continue until interrupted by another command, remove
        // this line or comment it out.
        if (autonomousCommand != null) autonomousCommand.cancel();
        Joystick leftStick = new Joystick(0);
        Joystick rightStick = new Joystick(1);
        TwoStickArcade twoStickArcade = new TwoStickArcade(leftStick, rightStick);
		FindDistance findDistance = new FindDistance();
		findDistance.start();
        twoStickArcade.start();
    }

    /**
     * This function is called when the disabled button is hit.
     * You can use it to reset subsystems before shutting down.
     */
    public void disabledInit(){

    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
        LiveWindow.run();
    }
}
