package org.usfirst.frc.team4729.robot.subsystems;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class LEDs extends Subsystem {
	Talon leds = new Talon(5);
	Timer timer = new Timer();
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void cycleForward(){
    	timer.reset();
    	double i = 0;
    	while (i < 1){
    		i = timer.get()/5;
    		leds.set(i);
    	}
    	timer.reset();
    	i = 0;
    	while (i > -1){
    		i = -timer.get()/5;
    		leds.set(i);
    	}
    }
    
    public void cycleBack(){
    	timer.reset();
    	double i = 0;
    	while (i > -1){
    		i = -timer.get()/5;
    		leds.set(i);
    	}
    	timer.reset();
    	i = 0;
    	while(i < 1){
    		i = timer.get()/5;
    		leds.set(i);
    	}
    }
}

