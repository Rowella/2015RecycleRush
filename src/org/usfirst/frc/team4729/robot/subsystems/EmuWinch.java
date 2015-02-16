package org.usfirst.frc.team4729.robot.subsystems;

import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.interfaces.Potentiometer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class EmuWinch extends Subsystem {
	Talon emuWinch = new Talon(4);
	Potentiometer emuPot = new AnalogPotentiometer(2);
	static double MANUAL_EMU_WINCH_SPEED = 1;
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void moveUp(){
    	emuWinch.set(MANUAL_EMU_WINCH_SPEED);
    }
    public void moveDown(){
    	emuWinch.set(-MANUAL_EMU_WINCH_SPEED);
    }
    
    public void stop() {
    	emuWinch.set(0);
    }
    
    /*public int readEmuEncoder(){
    	SmartDashboard.putNumber("Emu Encoder", emuEncoder.get());
    	return emuEncoder.get();
    } */
    
    /*public void resetEncoder(){
    	emuEncoder.reset();
    }*/

    public double emuPotRead(){
    	SmartDashboard.putNumber("emuPot", emuPot.get());
    	return emuPot.get();
    } 
}

