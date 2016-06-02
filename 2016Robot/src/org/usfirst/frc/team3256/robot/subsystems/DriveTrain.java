package org.usfirst.frc.team3256.robot.subsystems;
import org.usfirst.frc.team3256.robot.RobotMap;
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
	private PIDOutput Driveoutput = new PIDOutput() {
		public void pidWrite(double output) {
			useDrivePIDOutput(output);
	    }
	  };
	  private PIDSource Drivesource = new PIDSource() {
		  public void setPIDSourceType(PIDSourceType pidSource) {}

		  public PIDSourceType getPIDSourceType() {
			  return PIDSourceType.kDisplacement;
		  }

		  public double pidGet() {
			  return returnDrivePIDInput();
		  }
	  };
	 PIDController Drivecontroller = new PIDController(0.011,0.0,0.025,1/12,Drivesource,Driveoutput);
	 private PIDOutput Turnoutput = new PIDOutput() {
			public void pidWrite(double output) {
				useDrivePIDOutput(output);
		    }
		  };
		  private PIDSource Turnsource = new PIDSource() {
			  public void setPIDSourceType(PIDSourceType pidSource) {}

			  public PIDSourceType getPIDSourceType() {
				  return PIDSourceType.kDisplacement;
			  }

			  public double pidGet() {
				  return returnDrivePIDInput();
			  }
		  };
	  PIDController Turncontroller = new PIDController(0.011,0.0,0.025,1/12,Drivesource,Driveoutput);
	// Initialize your subsystem here
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
    	double factor = 360.0/350.0;
    	return gyro.getAngle()*factor;
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
    	leftFront.set(speed);
    	leftRear.set(speed);
    }

    //sets right sides motor power
    public static void setRightMotorSpeed(double speed){
    	rightFront.set(speed);
    	rightRear.set(speed);
    }
    
    //gets right encoder value
    public static double getRightEncoder(){
		return Math.abs(rightEncoder.get());
    }
    
    //gets left encoder value
    public static double getLeftEncoder(){
    	return Math.abs(leftEncoder.get());
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
    	return inchesToTicks(robotTrack*degrees/360);
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
    public static void arcadeDrive(double throttle, double turn, boolean slow){
    	
    	if (Math.abs(throttle)<0.2) {
    		throttle = 0;
    	}
    	if (Math.abs(turn)<0.2) {
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
    	if (slow){
    		leftFront.set(left/2);
        	leftRear.set(left/2);
        	rightFront.set(-right/2);
        	rightRear.set(-right/2);
    	}
    	else{
    		leftFront.set(left);
    		leftRear.set(left);
    		rightFront.set(-right);
    		rightRear.set(-right);
    	}
    }

    public static void arcadeDriveReverse(double throttle, double turn, boolean slow){
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
    	if (slow){
    		leftFront.set(left/2);
    		leftRear.set(left/2);
    		rightFront.set(-right/2);
    		rightRear.set(-right/2);
    	}
    	else {
    		leftFront.set(left);
    		leftRear.set(left);
    		rightFront.set(-right);
    		rightRear.set(-right);
    	}
    }
    
    public static double degreesToInches(double degrees){
    	return degrees*(robotTrack*Math.PI/360);
    }
  
    public void setDriveSetpoint(double setpoint) {
		  Drivecontroller.setSetpoint(setpoint);
    }
	
    public void enableDrivePID() {
		  Drivecontroller.enable();	
	}
	
    public void disableDrivePID(){
		  Drivecontroller.disable();
    }
	
    public double getDriveSetpoint() {
		  return Drivecontroller.getSetpoint();
    }

	public double getDriveCurrent() {
		  return returnDrivePIDInput();
	
	}
    protected double returnDrivePIDInput() {
    	return ticksToInches(rightEncoder.get());	
    }
    
    protected void useDrivePIDOutput(double output) {
    	if (output < 0)
    		output = 0;
    	leftFront.pidWrite(output);
    	rightFront.pidWrite(0.96*-output);
    	leftRear.pidWrite(output);
    	rightRear.pidWrite(0.96*-output);
    }
}
