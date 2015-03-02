package org.usfirst.frc.team4729.robot.subsystems;

//import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class DriveSubsystem extends Subsystem {
	RobotDrive driveTrain = new RobotDrive(0, 1);
	Encoder leftEncoder = new Encoder(3, 4);
	Encoder rightEncoder = new Encoder(5, 6);
	Gyro gyro = new Gyro(0);
	double rightOffset = 1;
	double leftOffset = 1;
	double leftSpeed = 0;
	double rightSpeed = 0;
	double turnSpeed = 0;
	double forwardSpeed = 0;
	
	double acceleration = 1;
	double speed = 1;
	
	
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
    	gyro.initGyro();
    	//leftEncoder.setDistancePerPulse(1);
    	//rightEncoder.setDistancePerPulse(1);
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void gyroStart() {
    	gyro.initGyro();
    	
    }
    
    public void highSpeed(){
    	speed = 1;
    	acceleration = 0.05;
    }
    
    public void lowSpeed(){
    	speed = 0.5;
    	acceleration = 1;
    }
    
   public int readLeftEncoder() {
    	int leftValue = leftEncoder.get();
    	SmartDashboard.putNumber("Left Encoder", leftValue);
    	return leftValue;
    }
    
    public int readRightEncoder() {
    	int rightValue = rightEncoder.get();
    	SmartDashboard.putNumber("Right Encoder", rightValue);
    	return rightValue;
    }
    
    public void resetEncoders() {
    	leftEncoder.reset();
    	rightEncoder.reset();
    }
    
    public void resetGryo(){
    	gyro.initGyro();
    }
    
    public void arcade(double desiredMove, double desiredTurn) {
    	if ((desiredMove < 0.1) && (desiredMove > -0.1)){
    		desiredMove = 0;
    		forwardSpeed = 0;
    	}
    	if ((desiredTurn < 0.1) && (desiredTurn > -0.1)){
    		desiredTurn = 0;
    		turnSpeed = 0;
    	}
    	
    	if  (((desiredMove > 0) && (forwardSpeed < 0)) || ((desiredMove < 0) && (forwardSpeed > 0))){
    		forwardSpeed = 0;
    	}
    	if (((desiredTurn > 0) && (turnSpeed < 0)) || ((desiredTurn < 0) && (turnSpeed > 0))){
    		turnSpeed = 0;
    	}
    	
    	if (Math.abs(desiredMove) < Math.abs(turnSpeed)){
    		forwardSpeed = desiredMove;
    	}
    	
    	if (Math.abs(desiredTurn) < Math.abs(turnSpeed)) {
    		turnSpeed = desiredTurn;
    	}
    	
    	turnSpeed += (desiredTurn-turnSpeed)*acceleration;
    	forwardSpeed += (desiredMove-forwardSpeed)*acceleration;
    	driveTrain.arcadeDrive(-forwardSpeed*speed, -turnSpeed*speed);
    }
    
    
    
    public void tank (double desiredLeft, double desiredRight) {
    	if ((desiredLeft < 0.1) && (desiredLeft > -0.1)){
    		desiredLeft = 0;
    		leftSpeed = 0;
    	}
    	if ((desiredRight < 0.1) && (desiredRight > -0.1)){
    		desiredRight = 0;
    		leftSpeed = 0;
    	}
    	
    	if  (((desiredLeft > 0) && (leftSpeed < 0)) || ((desiredLeft < 0) && (leftSpeed > 0))){
    		leftSpeed = 0;
    	}
    	if (((desiredRight > 0) && (rightSpeed < 0)) || ((desiredRight < 0) && (rightSpeed > 0))){
    		rightSpeed = 0;
    	}
    	
    	if (Math.abs(desiredLeft) < Math.abs(leftSpeed)){
    		leftSpeed = desiredLeft;
    	}
    	
    	if (Math.abs(desiredRight) < Math.abs(rightSpeed)) {
    		rightSpeed = desiredRight;
    	}
    	driveTrain.tankDrive(-leftSpeed*speed/leftOffset, -rightSpeed*speed/rightOffset);
		if (!((leftSpeed == 0) || (rightSpeed == 0) || (leftEncoder.getRate() == 0) || (rightEncoder.getRate() == 0))) {
			leftOffset *=  Math.abs(leftEncoder.getRate()/leftSpeed);
			rightOffset *= Math.abs(rightEncoder.getRate()/rightSpeed);
			if (leftOffset > rightOffset){
				leftOffset /= rightOffset;
				rightOffset = 1;
			}else {
				rightOffset /= leftOffset;
				leftOffset = 1;
			}
	    	SmartDashboard.putNumber("Left Encoder", leftOffset);
			SmartDashboard.putNumber("Right Encoder", leftOffset);
		}
    }
    
	
	public void greasyDrive(Joystick stick){
		SmartDashboard.putNumber("Gryo", gyro.getAngle());
		if (gyro.getAngle() < (Math.toDegrees(Math.atan2(stick.getY(), stick.getX())) - 15)){
			driveTrain.arcadeDrive(0, Math.min(Math.sqrt(Math.pow(stick.getY(), 2)+Math.pow(stick.getX(), 2)), 1));
		} else if (gyro.getAngle() > (Math.toDegrees(Math.atan2(stick.getY(), stick.getX())) + 15)){
			driveTrain.arcadeDrive(0, Math.max(-Math.sqrt(Math.pow(stick.getY(), 2)+Math.pow(stick.getX(), 2)), -1));
		} else{
			driveTrain.tankDrive(Math.min(1, Math.sqrt(Math.pow(stick.getY(), 2)+Math.pow(stick.getX(), 2)))/leftOffset, Math.min(1, Math.sqrt(Math.pow(stick.getY(), 2)+Math.pow(stick.getX(), 2)))/rightOffset);
		}
	}
	
	
	/*public void OffsetChange(Joystick leftStick, Joystick rightStick) {
		int leftValue = (int) (leftEncoder.get()*leftStick.getY());
		int rightValue = (int) (rightEncoder.get()*rightStick.getY());
    	SmartDashboard.putNumber("Left Encoder", leftValue);
		SmartDashboard.putNumber("Right Encoder", rightValue);
		if (leftValue > rightValue){
			leftOffset *= leftValue/rightValue;
		}else {
			rightOffset *= rightValue/leftValue;
		}
		if (leftOffset > rightOffset) {
			leftOffset *= rightOffset;
			rightOffset = 1;
		} else {
			rightOffset *= leftOffset;
			leftOffset = 1;
		}
	} */
	
}

