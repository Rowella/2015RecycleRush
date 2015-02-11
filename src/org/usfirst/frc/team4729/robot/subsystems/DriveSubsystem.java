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
	//Gyro gyro = new Gyro(3);
	static double rightOffset = 1;
	static double leftOffset = 1;
	
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
    	//leftEncoder.setDistancePerPulse(1);
    	//rightEncoder.setDistancePerPulse(1);
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
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
    //	gyro.initGyro();
    }
    
    public void twoStickArcade(Joystick leftStick, Joystick rightStick) {
    	driveTrain.arcadeDrive(-leftStick.getY(), -rightStick.getX());
    }
    
    public void oneStickArcade(Joystick stick) {
    	driveTrain.arcadeDrive(-stick.getY(), -stick.getX());
    }
    
    public void twoStickTank (Joystick leftStick, Joystick rightStick) {
    	driveTrain.tankDrive(-leftStick.getY()/leftOffset, -rightStick.getY()/rightOffset);
		if (!((leftStick.getY() == 0) || (rightStick.getY() == 0) || (leftEncoder.getRate() == 0) || (rightEncoder.getRate() == 0))) {
			leftOffset *=  Math.abs(leftEncoder.getRate()/leftStick.getY());
			rightOffset *= Math.abs(rightEncoder.getRate()/rightStick.getY());
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
    
	public void oneStickTank(Joystick stick) {
		driveTrain.tankDrive(-stick.getY()/leftOffset, -stick.getX()/rightOffset);
		if (!((stick.getY() == 0) || (stick.getX() == 0) || (leftEncoder.getRate() == 0) || (rightEncoder.getRate() == 0))) {
			leftOffset *=  Math.abs(leftEncoder.getRate()/stick.getX());
			rightOffset *= Math.abs(rightEncoder.getRate()/stick.getY());
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
	
	public void autoTank (double leftSpeed, double rightSpeed) {
		driveTrain.tankDrive(-leftSpeed/leftOffset, -rightSpeed/rightOffset  );
		if (!((leftSpeed == 0) || (leftSpeed == 0) || (leftEncoder.getRate() == 0) || (rightEncoder.getRate() == 0))) {
			leftOffset *=  Math.abs(leftEncoder.getRate()/leftSpeed);
			rightOffset *= Math.abs(rightEncoder.getRate()/leftSpeed);
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
//		if (gyro.getAngle() < Math.atan2(stick.getY(), stick.getX()) - 15){
//			driveTrain.arcadeDrive(0, Math.min(Math.sqrt(Math.pow(stick.getY(), 2)+Math.pow(stick.getX(), 2)), 1));
//		} else if (gyro.getAngle() > Math.atan2(stick.getY(), stick.getX()) + 15){
//			driveTrain.arcadeDrive(0, Math.max(-Math.sqrt(Math.pow(stick.getY(), 2)+Math.pow(stick.getX(), 2)), -1));
//		} else{
//			driveTrain.tankDrive(Math.min(1, Math.sqrt(Math.pow(stick.getY(), 2)+Math.pow(stick.getX(), 2)))/leftOffset, Math.min(1, Math.sqrt(Math.pow(stick.getY(), 2)+Math.pow(stick.getX(), 2)))/rightOffset);
//		}
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

