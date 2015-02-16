package org.usfirst.frc.team4729.robot.subsystems;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class LEDs extends Subsystem {
	Victor ledA = new Victor(5);
	Victor ledB = new Victor(6);
	Timer timer = new Timer();
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void cycleForward(){
    	timer.reset();
    	double ledBValue = 0;
    	double ledAValue = 0;
    	ledB.set(ledBValue);
    	while (ledAValue < 1){
    		ledAValue = timer.get()/5;
    		ledA.set(ledAValue);
    	}
    	ledA.set(1);
    	timer.reset();
    	while (ledBValue < 1) {
    		ledBValue = timer.get()/5;
    		ledB.set(ledBValue);
    	}
    	ledB.set(1);
    	ledAValue = 1;
    	while (ledAValue > -1){
    		ledAValue = 1-timer.get()/2.5;
    		ledA.set(ledAValue);
    	}
    	ledA.set(-1);
    	ledBValue = 1;
    	while (ledBValue > -1){
    		ledBValue = 1-timer.get()/2.5;
    		ledB.set(ledBValue);
    	}
    }
    
    /*public void cycleBack(){
    	timer.reset();
    	double ledAValue = 0;
    	while (ledAValue > -1){
    		ledAValue = -timer.get()/5;
    		ledA.set(ledAValue);
    	}
    	timer.reset();
    	ledAValue = 0;
    	while(ledAValue < 1){
    		ledAValue = timer.get()/5;
    		ledA.set(ledAValue);
    	}
    }*/
    
}

