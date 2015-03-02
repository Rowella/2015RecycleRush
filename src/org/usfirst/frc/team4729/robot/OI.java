package org.usfirst.frc.team4729.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

import org.usfirst.frc.team4729.robot.commands.ClampReadValue;
import org.usfirst.frc.team4729.robot.commands.EmuReadValue;
import org.usfirst.frc.team4729.robot.commands.EmuWinchManualDown;
import org.usfirst.frc.team4729.robot.commands.EmuWinchManualUp;
import org.usfirst.frc.team4729.robot.commands.AutoToManual;
import org.usfirst.frc.team4729.robot.commands.LedForward;
import org.usfirst.frc.team4729.robot.commands.TiltReadValue;
import org.usfirst.frc.team4729.robot.commands.GreasyDrive;
import org.usfirst.frc.team4729.robot.commands.ManualToAuto;
import org.usfirst.frc.team4729.robot.commands.ToteClampManualDown;
import org.usfirst.frc.team4729.robot.commands.ToteClampManualUp;
import org.usfirst.frc.team4729.robot.commands.ToteTiltManualDown;
import org.usfirst.frc.team4729.robot.commands.ToteTiltManualUp;
import org.usfirst.frc.team4729.robot.commands.OneStickArcade;
import org.usfirst.frc.team4729.robot.commands.OneStickTank;
import org.usfirst.frc.team4729.robot.commands.TwoStickArcade;
import org.usfirst.frc.team4729.robot.commands.TwoStickTank;

//I will not be there on the Thurs of the comp, contact me at alex+FRC@gormand.com.au from 8:45-10:00 and possibly 1:00-2:00 (I may have 4u maths on at that time)

/**Info for Driving
 * abbreviations:
 * 	f = forward
 *  b = back (Except for the button 'b' on the XBOX controller)
 *  l = left
 *  r = right
 */
/*LeftStick:
 * 	1 (trigger) is for cycling the LEDs (hold to cycle)
 * 	2 is for OneStickTank (f/b on leftStick is left drive f/b, l/r on leftStick is right drive f/b)
 * 	3 is for twoStickTank (f/b on leftStick is left drive f/b, f/b on rightStick is right drive f/b)
 * 	4 is for oneStickArcade (f/b on leftStick is for both drive f/b, l/r on leftStick is for turn l/r)
 * 	5 is for twoStickArcade (f/b on leftStick is for both drive f/b, l/r on rightStick is for turn l/r)
 *
 *RightStick:
 *	3 is for greasyDrive (If you don't know it you don't deserve it)
 *	6 is for displaying the value of the toteClamp to the smartDashboard for testing purposes
 *	7 is for displaying the value of the toteTilt to the smartDashboard for testing purposes
 *	8 if for displaying the value of the emu to the smartDashboard for testing purposes
 *
 *XBOX Controller:
 *	Pressing in leftStick sets most controls to manual mode
 *	Pressing in rightStick sets most controls to semi-auto mode
 *	a moves the toteTilt down (auto keeps going until it reaches the right point)
 *	y moves the toteTilt up (auto keeps going until it reaches the right point) HAS NOT BEEN CALIBRATED YET
 *	x moves the toteClamp down (auto keeps going until the button is hit [The clamp hits a tote]) AUTO NOT TESTED YET
 *	b moves the toteClamp up (auto acts like manual)
 *	lb moves the emu down (auto moves it down to the right point)
 *	rb moves the emu up (auto moves it down to the right point)
 *	
 */
public class OI {
	//NOTE: Emu Winch uses potentiometers ( :) ), Tote Tilt uses Encoders ( :| ) which means it must be in the up state at init and Tote Clamp uses a switch ( >:( ) which means that it cannot use auto to move up
	//NOTE: The chooser for tote/bin and ramp/noRamp will not appear until the first time autonomous is run so make sure to run autonomous at least once before first match (also to calibrate the distances)
	
	/** Wiring
	 */
	/* PWM:
	 * driveBase left is 0, right is 1
	 * toteClamp is 2
	 * toteTilt is 3
	 * emuWinch is 4
	 * LED A is 5
	 * LED B is 6
	 */

	/* Digital In:
	 * Tote or bin switch is 0
	 * Ramp or no switch is 1
	 * Tote Sensor is 2
	 * Left Encoders are 3 and 4
	 * Right Encoders are 5 and 6
	 * Tote Tilt Encoders are 7 and 8
	 */
	
	/*Analog In:
	 * gyro is 0
	 * clampPot is 1 IGNORE: NO LONGER USED
	 * emuPot is 2
	 * tiltPot is 3 IGNORE: NO LONGER USED
	 * 
	 */
	
	/** Constants
	 */
	/*All non-local constants are at the top of Robot.java
	 *Speeds for the emu/toteTilt/toteClamp are in their respective files
	 *Things to calibrate:
	 *	EMU_UP_ANGLE (in Robot) DONE
	 *	EMU_DOWN_ANGLE (in Robot) DONE
	 *	TOTE_TILT_UP_ANGLE (In Robot)
	 *	TOTE_TILT_DOWN_ANGLE (In Robot)
	 *	RAMP_DISTANCE (In AutonomousCommand)
	 *	NO_RAMP_DISTANCE (In AutonomousCommand)
	 *
	 *Things you might want to change:
	 *	AUTO_SPEED (In AutonomousCommand)
	 *	MANUAL_EMU_WINCH_SPEED (In EmuWinch, note this also affects autonomous)
	 *	MANUAL_TOTE_CLAMP_SPEED (In toteClamp, note this also affects autonomous)
	 *	MANUAL_TOTE_TILT_SPEED (In toteTilt, note this also affects autonomous)
	 *	
	 */
	
	// "I believe autonomous has an 'i' in it." - Tamara
	// "Don't quote me on that." -Tamara
	Joystick leftStick   = new Joystick(0);
    Joystick rightStick  = new Joystick(1);
    Joystick xbox        = new Joystick(2);
    Button button1       = new JoystickButton(leftStick,  1);
    Button button2       = new JoystickButton(leftStick,  2);
    Button button3       = new JoystickButton(leftStick,  3);
    Button button4       = new JoystickButton(leftStick,  4);
    Button button5       = new JoystickButton(leftStick,  5);
    Button button6       = new JoystickButton(leftStick,  6);
    Button button7       = new JoystickButton(leftStick,  7);
    Button button8       = new JoystickButton(leftStick,  8);
    Button button9       = new JoystickButton(leftStick,  9);
    Button button10      = new JoystickButton(leftStick,  10);
    Button button11      = new JoystickButton(leftStick,  11);
    Button rightButton1  = new JoystickButton(rightStick, 1);
    Button rightButton2  = new JoystickButton(rightStick, 2);
    Button rightButton3  = new JoystickButton(rightStick, 3);
    Button rightButton4  = new JoystickButton(rightStick, 4);
    Button rightButton5  = new JoystickButton(rightStick, 5);
    Button rightButton6  = new JoystickButton(rightStick, 6);
    Button rightButton7  = new JoystickButton(rightStick, 7);
    Button rightButton8  = new JoystickButton(rightStick, 8);
    Button rightButton9  = new JoystickButton(rightStick, 9);
    Button rightButton10 = new JoystickButton(rightStick, 10);
    Button rightButton11 = new JoystickButton(rightStick, 11);
    Button a             = new JoystickButton(xbox,       1);
    Button b             = new JoystickButton(xbox,       2);
    Button x             = new JoystickButton(xbox,       3);
    Button y             = new JoystickButton(xbox,       4);
    Button lb            = new JoystickButton(xbox,       5);
    Button rb            = new JoystickButton(xbox,       6);
    Button l3            = new JoystickButton(xbox,       9);
    Button r3            = new JoystickButton(xbox,       10);
    
    public OI() {
    	button1.whileHeld  (new LedForward());
    	button2.whenPressed (new OneStickTank(leftStick));
    	button3.whenPressed (new TwoStickTank(leftStick, rightStick));
    	button4.whenPressed (new OneStickArcade(leftStick));
    	button5.whenPressed (new TwoStickArcade(leftStick, rightStick));
    	//button6.whileHeld ();
    	//button7.whileHeld ();
    	//button8.whileHeld ();
    	//button9.whileHeld ();
    	//button10.whileHeld ();
    	//button11.whileHeld ();
    	//rightButton1.whenPressed ();
    	//rightButton2.whileHeld ();
    	rightButton3.whenPressed (new GreasyDrive(leftStick));
    	//rightButton4.whileHeld ();
    	//rightButton5.whileHeld ();
    	rightButton6.whileHeld (new ClampReadValue());
    	rightButton7.whileHeld (new TiltReadValue());
    	rightButton8.whileHeld (new EmuReadValue());
    	//rightButton9.whileHeld ();
    	//rightButton10.whileHeld ();
    	//rightButton11.whileHeld ();
    	a.whileHeld (new ToteTiltManualDown());
    	b.whileHeld (new ToteClampManualUp());
    	x.whileHeld (new ToteClampManualDown());
    	y.whileHeld (new ToteTiltManualUp());
    	lb.whileHeld (new EmuWinchManualDown());
    	rb.whileHeld (new EmuWinchManualUp());
    	l3.whileHeld (new AutoToManual());
    	r3.whileHeld (new ManualToAuto());
    	
    	
    }
    //// CREATING BUTTONS
    // One type of button is a joystick button which is any button on a joystick.
    // You create one by telling it which joystick it's on and which button
    // number it is.
    // Joystick stick = new Joystick(port);
    // Button button = new JoystickButton(stick, buttonNumber);
    
    // There are a few additional built in buttons you can use. Additionally,
    // by subclassing Button you can create custom triggers and bind those to
    // commands the same as any other Button.
    
    //// TRIGGERING COMMANDS WITH BUTTONS
    // Once you have a button, it's trivial to bind it to a button in one of
    // three ways:
    
    // Start the command when the button is pressed and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenPressed(new ExampleCommand());
    
    // Run the command while the button is being held down and interrupt it once
    // the button is released.
    // button.whileHeld(new ExampleCommand());
    
    // Start the command when the button is released  and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenReleased(new ExampleCommand());
}

