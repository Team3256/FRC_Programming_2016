package org.usfirst.frc.team3256.robot.subsystems;
import org.usfirst.frc.team3256.robot.RobotMap;
import org.usfirst.frc.team3256.robot.commands.PIDTurn;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;

public class DriveTrain extends Subsystem {
	static VictorSP leftFront = new VictorSP(RobotMap.leftFrontMotor); 
	static VictorSP leftRear = new VictorSP(RobotMap.leftRearMotor);
	static VictorSP rightFront = new VictorSP(RobotMap.rightFrontMotor);
	static VictorSP rightRear = new VictorSP(RobotMap.rightRearMotor);
	static Encoder rightEncoder = new Encoder(RobotMap.rightDriveEncoderA, RobotMap.rightDriveEncoderB);
	static Encoder leftEncoder = new Encoder(RobotMap.leftDriveEncoderA, RobotMap.leftDriveEncoderB);
	static DoubleSolenoid shifterPancake = new DoubleSolenoid (RobotMap.ShifterIn,RobotMap.ShifterOut);
	static AnalogGyro gyro = new AnalogGyro(RobotMap.gyroPort);
	
	static double robotTrack = 24.383;
	static double ticksPerRotation = 101.86;
	
    public DriveTrain() {
    }

	public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
   
    //initializes gyro
    public static void initGyro(){
    	gyro.initGyro();
    }
    
    //resets gyro value
    public static void resetGyro(){
    	gyro.reset();
    }
    
    //gets gyro angle
    public static double getAngle(){
    	double factor = 90/88;
    	return Math.abs(gyro.getAngle()*factor);
    }
    
    //calibrates gyro
    public static void calibrateGyro(){
    	gyro.calibrate();
    }
    
    
    //shift transmissions
    public static void shiftPancake(boolean getRightBumper){
    	//test later which is which
    	if (getRightBumper){
    		shifterPancake.set(DoubleSolenoid.Value.kReverse);
     	}
    	else{
    		shifterPancake.set(DoubleSolenoid.Value.kForward);
    	}
    }
    
    //shifts to low gear
    public static void shiftDown(){
    	shifterPancake.set(DoubleSolenoid.Value.kReverse);
    }
    
    //shifts to high gear
    public static void shiftUp(){
    	shifterPancake.set(DoubleSolenoid.Value.kForward);
    }
    
    //sets left sides motor power
    public static void setLeftMotorSpeed(double speed){
    	leftFront.set(0.8*speed);
    	leftRear.set(0.8*speed);
    }

    //sets right sides motor power
    public static void setRightMotorSpeed(double speed){
    	rightFront.set(1*speed);
    	rightRear.set(1*speed);
    }
    
    //gets right encoder value
    public static double getRightEncoder(){
		return Math.abs(rightEncoder.get());
    }
    
    //gets left encoder value
    public static double getLeftEncoder(){
    	return Math.abs(leftEncoder.get());
    }
    
    public static double getAvgEncoder(){
    	return Math.abs((leftEncoder.get()+rightEncoder.get())/2);
    }
    
    //resets encoders
    public static void resetEncoders(){
    	rightEncoder.reset();
    	leftEncoder.reset();
    }

    public static double inchesToTicks(double distance){
	    return (distance*101.68);
    }
    
    public static double ticksToInches(double ticks){
	    return (ticks/101.68);
    }
    
    public static double degreesToTicks(double degrees){
    	return inchesToTicks(robotTrack*Math.PI*degrees/360);
    }
    
    public static double ticksToDegrees(double ticks){
    	return ticksToInches(ticks)*360/(24.383*Math.PI);
    }
    //tankdrive
    public static void tankDrive(double left, double right){
    	//clipping values
    	if (Math.abs(right)<0.2) {
    		right = 0;
    	}
    	if (Math.abs(left)<0.2) {
    		left = 0;
    	}
    	leftFront.set(left);
    	leftRear.set(left);
    	rightFront.set(-right);
    	rightRear.set(-right);
    }
    
    public static void tankDriveReversable(double left, double right, boolean rightBumper1){
    	if (Math.abs(right)<0.2) {
    		right = 0;
    	}
    	if (Math.abs(left)<0.2) {
    		left = 0;
    	}
    	if (rightBumper1){
    		leftFront.set(-left);
    		leftRear.set(-left);
    		rightFront.set(right);
    		rightRear.set(right);
    	}
    	else 
    		leftFront.set(left);
    		leftRear.set(left);
    		rightFront.set(-right);
    		rightRear.set(-right);
    }
    //arcadedrive
    public void arcadeDrive(double throttle, double turn, boolean slow, boolean enabled){
    	if (enabled){
			if (Math.abs(throttle)<0.3) {
	    		throttle = 0;
	    	}
	    	if (Math.abs(turn)<0.3) {
	    		turn = 0;
	    	}
	    	
	    	double left = throttle-turn;
	    	double right = throttle+turn;
	    	
	    	if (left > 1){
	    		left = 1;
	    	}
	    	if (left < -1){
	    		left = -1;
	    	}
	    	if (right > 1){
	    		right = 1;
	    	}
	    	if (right < -1){
	    		right = -1;
	    	}
	    	/*if (slow){
	    		leftFront.set(left/2);
	        	leftRear.set(left/2);
	        	rightFront.set(-right/2);
	        	rightRear.set(-right/2);
	    	}
	    	else{*/
	    		leftFront.set(left);
	    		leftRear.set(left);
	    		rightFront.set(-right);
	    		rightRear.set(-right);
	    	//}
    	}
    
    }

    public void arcadeDriveReverse(double throttle, double turn, boolean slow, boolean enabled){
    	if (enabled){
    		if (Math.abs(throttle)<0.2) {
	    		throttle = 0;
	    	}
	    	if (Math.abs(turn)<0.2) {
	    		turn = 0;
	    	}
	    	throttle = -throttle;
	    	turn = -turn;
	    	
	    	double left = throttle+turn;
	    	double right = throttle-turn;
	    	
	    	if (left > 1){
	    		left = 1;
	    	}
	    	if (left < -1){
	    		left = -1;
	    	}
	    	if (right > 1){
	    		right = 1;
	    	}
	    	if (right < -1){
	    		right = -1;
	    	}
	    	/*if (slow){
	    		leftFront.set(left/2);
	    		leftRear.set(left/2);
	    		rightFront.set(-right/2);
	    		rightRear.set(-right/2);
	    	}*/
	    	//else {
	    		leftFront.set(left);
	    		leftRear.set(left);
	    		rightFront.set(-right);
	    		rightRear.set(-right);
	    	//}
    	}
	
    }
    
    public static double degreesToInches(double degrees){
    	return degrees*(robotTrack*Math.PI/360);
    }
}
