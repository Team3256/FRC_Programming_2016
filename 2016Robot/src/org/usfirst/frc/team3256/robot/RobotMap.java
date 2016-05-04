package org.usfirst.frc.team3256.robot;
/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
    // For example to map the left and right motors, you could define the
    // following variables to use with your drivetrain subsystem.
    // public static int leftMotor = 1;
    // public static int rightMotor = 2;
    
    // If you are using multiple modules, make sure to define both the port
    // number and the module. For example you with a rangefinder:
    // public static int rangefinderPort = 1;
    // public static int rangefinderModule = 1;
	
	
//-------------------------------PWM-------------------------------
	//motors
	public static final int leftFrontMotor = 0;
	public static final int leftRearMotor = 6;
	public static final int	rightFrontMotor = 2;
	public static final int	rightRearMotor = 3;
	public static final int	intakePivotMotors = 8;
	public static final int	intakeRollerMotor = 7;
	//switched with leftFrontMotor (1=old leftRearMotor)
	
	public static final int	winchMotors = 1;
	public static final int	hangerPivotMotors = 4;
	public static final int catapultWinch = 5;
			
//--------------------------------DIO-------------------------------
	//encoders
	public static final int rightDriveEncoderA = 0;
	public static final int	rightDriveEncoderB = 1;
	public static final int leftDriveEncoderA = 2;
	public static final int	leftDriveEncoderB = 3;
	
	//Sensors
	public static final int catapultLimitSwitch = 9;
	public static final int ballIR = 4;
	public static final int intakeLimitL = 7;
	public static final int intakeLimitR = 8;
	
//-------------------------------Analog-------------------------------
	//pot constants
	public static final int intakePotPort = 1; 
	public static final double intakePotFactor = 360*100/2.3114120618015037; //data obtained empirically
	public static final double intakePotOffset = -18.82139146465336; //data obtained empirically
	
	//gyro
	public static final int gyroPort = 0;
	
//-------------------------------PCM-------------------------------
	//actuators
	public static final int ShifterIn = 3;
	public static final int ShifterOut = 4;
	public static final int winchEngage = 1;
	public static final int winchDisengage = 6;
	//ballHolder and HangerHolder were swapped
	public static final int ballHolderEngage = 0;
	public static final int ballHolderDisengage = 7;
	public static final int hangerHolderEngage = 2;
	public static final int hangerHolderDisengage = 5;
	
	//relays
	public static final int SpikePort = 0;
	
	
//-------------------------------joysticks-------------------------------
	public static final int Joystick1 = 0;
	public static final int Joystick2 = 1;
	
//-------------------------------Intake Constants-------------------------------
	public static final int intakePos = 0;
	public static final int stowPos = 1;
	
//-------------------------------Global Variables-------------------------------
	public static double gyroPos;
	public static double photoCenterOfGravityX;
	public static double photoCenterOfGravityY;
	
	public static double CamAngle = 0;
	public static double CamDirection = 0;
	
	public static String shooterCameraIP = "10.32.56.11";
	
	public static boolean isShooting = false;

	
}
