package org.usfirst.frc.team4729.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

import org.usfirst.frc.team4729.robot.commands.ChangeOffsets;
import org.usfirst.frc.team4729.robot.commands.EmuWinchManualDown;
import org.usfirst.frc.team4729.robot.commands.EmuWinchManualUp;
import org.usfirst.frc.team4729.robot.commands.AutoToManual;
import org.usfirst.frc.team4729.robot.commands.BottomPotTest;
import org.usfirst.frc.team4729.robot.commands.ManualToAuto;
import org.usfirst.frc.team4729.robot.commands.ToteClampManualDown;
import org.usfirst.frc.team4729.robot.commands.ToteClampManualUp;
import org.usfirst.frc.team4729.robot.commands.ToteTiltManualDown;
import org.usfirst.frc.team4729.robot.commands.ToteTiltManualUp;
import org.usfirst.frc.team4729.robot.commands.OneStickArcade;
import org.usfirst.frc.team4729.robot.commands.OneStickTank;
import org.usfirst.frc.team4729.robot.commands.TwoStickArcade;
import org.usfirst.frc.team4729.robot.commands.TwoStickTank;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	/* PWM:
	 * driveBase left is 0, right is 1
	 * toteClamp is 2
	 * toteTilt is 3
	 * aFrameWinch is 4
	 */
	
	/* Digital In:
	 * Tote or bin switch is 0
	 * Ramp or no switch is 1
	 * Tote Sensor is 2
	 * Left Encoders are 3 and 4
	 * Right Encoders are 5 and 6
	 */
	
	/*Analog In:
	 * tiltPot is 0
	 * clampPot is 1
	 * emuPot is 2
	 */
	
	//All non-local constants are in the HashDefine subsystem
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
    	button1.whileHeld (new BottomPotTest());
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
    	rightButton1.whenPressed (new ChangeOffsets(leftStick, rightStick));
    	//rightButton2.whileHeld ();
    	//rightButton3.whileHeld ();
    	//rightButton4.whileHeld ();
    	//rightButton5.whileHeld ();
    	//rightButton6.whileHeld ();
    	//rightButton7.whileHeld ();
    	//rightButton8.whileHeld ();
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

