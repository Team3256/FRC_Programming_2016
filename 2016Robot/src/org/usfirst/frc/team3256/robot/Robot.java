package org.usfirst.frc.team3256.robot;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.vision.USBCamera;

import org.usfirst.frc.team3256.robot.subsystems.DriveTrain;
import org.usfirst.frc.team3256.robot.subsystems.Intake;
import org.usfirst.frc.team3256.robot.subsystems.Shooter;

import org.usfirst.frc.team3256.robot.commands.*;
import org.usfirst.frc.team3256.robot.RobotMap;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
	public static DriveTrain drivetrain;
	public static Compressor compressor;
	public static Intake intake;
	public static Shooter shooter;
	public static NetworkTable networkTable;
	public static SmartDashboard smartdashboard;
    //DriveTrain
    Command ShiftUp;
    Command ShiftDown;
    Command MoveForward;
    Command MoveBackward;
    Command PIDMoveForward;
    Command PIDTurn;

    //Intake
    Command IntakeIncrementIn;
    Command IntakeIncrementOut;
    Command IntakeStopPivot;
    Command IntakeIntakeRollers;
    Command IntakeOuttakeRollers;
    Command IntakeStopRollers;
    Command IntakePosAuto;
    //Shooter
    Command ShootBall;
    Command ReEngageWinch;
    Command CatapultWinch;
    Command CatapultWinchAutomatic;
    Command CatapultWinchStop;
    Command EngageBallActuators;
    Command DisengageBallActuators;
    CommandGroup ShootnLoad;
    
    //Autonomous
    Command AutoCommand;
    Command AutoDoNothingCommand;
    SendableChooser AutoChooser;
    CommandGroup AutoDriveForward;
    CommandGroup AutoLowBar;
    CommandGroup AutoTurnTest;
    CommandGroup AutoTurnCamTest;
    Command PIDTurnCamera;
    Command Autonomous;
    Command EncoderTurn;
    Command EncoderTurnTest;
    Preferences prefs;
    boolean drive_status;
    boolean pid_status;
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
    	System.out.println("Init");
    	networkTable = NetworkTable.getTable("Smartdashboard");
    	networkTable.initialize();
		
		CameraServer USBCam = CameraServer.getInstance();
		USBCam.setQuality(50);
		USBCam.startAutomaticCapture("cam0");
		//subsystems
    	drivetrain = new DriveTrain();
		compressor = new Compressor(0);
		intake = new Intake();
		shooter = new Shooter();
	    //commands
		MoveForward = new MoveFoward(90,1);
		PIDMoveForward = new PIDMoveForward(180);
		PIDTurn = new PIDTurn(90);
		ShiftUp = new ShiftUp();
		ShiftDown = new ShiftDown();
		IntakeIncrementIn = new IntakeIncrementIn();
		IntakeIncrementOut = new IntakeIncrementOut();
		IntakeStopPivot = new IntakeStopPivot();
		IntakeIntakeRollers = new IntakeIntakeRollers();
		IntakeOuttakeRollers = new IntakeOuttakeRollers();
		IntakeStopRollers = new IntakeStopRollers();
		IntakePosAuto = new IntakePosAuto();
		ShootBall = new ShootBall();
		ReEngageWinch = new ReEngageWinch();
		CatapultWinch = new CatapultWinch();
		CatapultWinchAutomatic = new CatapultWinchAutomatic();
		CatapultWinchStop = new CatapultWinchStop();
		EngageBallActuators = new EngageBallActuators();
		DisengageBallActuators = new DisengageBallActuators();
		ShootnLoad = new ShootnLoad();
		AutoDoNothingCommand = new AutoDoNothingCommand();
		AutoLowBar = new AutoLowBar();
		//AutoTurnTest=new AutoTurnTest();
		AutoTurnCamTest = new AutoTurnCamTest();
		Autonomous = new Autonomous();
		EncoderTurn = new EncoderTurn(0.175,0,0.07,false);
		//compressor
		compressor.setClosedLoopControl(true);
		drivetrain.calibrateGyro();
		drivetrain.initGyro();
		AutoChooser = new SendableChooser();
    }

	public void disabledPeriodic() {
		AutoChooser.addDefault("AutoDoNothing", AutoDoNothingCommand);
		AutoChooser.addObject("AutoLowBar", AutoLowBar);
		AutoChooser.addObject("PIDMoveForward", PIDMoveForward);
		AutoChooser.addObject("PIDTurn", PIDTurn);
		AutoChooser.addObject("AutoTurnTest", new AutoTurnTest((int)SmartDashboard.getNumber("CameraAngle",0), true));
		AutoChooser.addObject("PIDTurnCam", PIDTurnCamera);
		AutoChooser.addObject("Auto", Autonomous);
		AutoChooser.addObject("Encoder", EncoderTurn);
		AutoChooser.addObject("Encoder_Turn", new EncoderTurnTest((int)SmartDashboard.getNumber("CameraAngle",0), true));
		smartdashboard.putData("Auto Mode Chooser", AutoChooser);
        SmartDashboard.putBoolean("isWinched", shooter.isWinched());
        SmartDashboard.putBoolean("intake", intake.isIntakePosL());
        SmartDashboard.putNumber("Gyro", drivetrain.getAngle());	
        smartdashboard.putNumber("AVG ENC" , DriveTrain.getAvgEncoder());
		Scheduler.getInstance().run();
	}

   public void autonomousInit() {
	   	AutoChooser.initTable(AutoChooser.getTable());
	   	AutoCommand = (Command) AutoChooser.getSelected();
	   	AutoCommand.start();
	    drivetrain.resetEncoders();
	   	drivetrain.shiftUp();
	   	drivetrain.resetGyro();
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
    	RobotMap.CamAngle = SmartDashboard.getNumber("CameraAngle", 0);
        SmartDashboard.putNumber("Gyro", drivetrain.getAngle());
        Scheduler.getInstance().run();
    }

    public void teleopInit() {
		// This makes sure that the autonomous stops running when
        // teleop starts running. If you want the autonomous to 
        // continue until interrupted by another command, remove
        // this line or comment it out.
        //if (autonomousCommand != null) autonomousCommand.cancel();
 
        Intake.stopIntake();
        drivetrain.resetEncoders();
        drivetrain.resetGyro();
        //Shooter.disengageBallActuators();
        //Shooter.engageWinch();
        
    }

    /**
     * This function is called when the disabled button is hit.
     * You can use it to reset subsystems before shutting down.
     */
    public void disabledInit(){
    	prefs = Preferences.getInstance();
	   	double kP = prefs.getDouble("kP", 0.0);
	   	double kI = prefs.getDouble("kI", 0.0);
	   	double kD = prefs.getDouble("kD", 0.0);
    	PIDTurnCamera = new PIDTurnCamera(kP,kI,kD);
    	DriveTrain.resetGyro();
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic1(){
    	Scheduler.getInstance().run();
    	OI.buttonA1.whenActive(new TeleopTurnTest((int)SmartDashboard.getNumber("CameraAngle", 0), true));
        OI.rightBumper1.whenPressed(ShootnLoad);
    }
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
        //OI.buttonA1.whenPressed(new AutoTurnTest((int) SmartDashboard.getNumber("CameraAngle", 0), pid_status));
        /*if(OI.getRightTrigger1() false){
        	drive_status = false;
        	pid_status = true;
        	OI.buttonA1.whenActive(new AutoTurnTest((int)SmartDashboard.getNumber("CameraAngle", 0), pid_status));
        	//whenActive(new AutoTurnTest((int)SmartDashboard.getNumber("CameraAngle", 0), pid_status));
        	//Scheduler.getInstance().add(new AutoTurnTest((int)SmartDashboard.getNumber("CameraAngle", 0), pid_status));
        }
        else {*/
        	drive_status = true;
        	pid_status = false;
        
        	if (OI.getRightBumper1()){
            	drivetrain.arcadeDriveReverse(OI.getLeftY1(), OI.getRightX1(), OI.getRightTrigger1(),drive_status);
            }
        
            else drivetrain.arcadeDrive(OI.getLeftY1(), OI.getRightX1(), OI.getRightTrigger1(),drive_status);
        //}
        OI.leftBumper1.whenPressed(ShiftDown);
        OI.leftBumper1.whenReleased(ShiftUp);
        
        //Shooting
        OI.rightBumper2.whenPressed(ShootnLoad);
        //Intake
        OI.buttonA2.whileHeld(IntakeIntakeRollers);
        OI.buttonY2.whileHeld(IntakeOuttakeRollers);
        OI.buttonA2.whenReleased(IntakeStopRollers);
        OI.buttonY2.whenReleased(IntakeStopRollers);
        OI.leftBumper2.whenPressed(EngageBallActuators);
        OI.leftBumper2.whenReleased(DisengageBallActuators);
        OI.buttonB2.whenPressed(IntakePosAuto);
        OI.buttonX2.whileHeld(IntakeIncrementIn);
        OI.buttonX2.whenReleased(IntakeStopPivot);
        
/*-----------------------------------------Update Dashboard-----------------------------------------*/

        RobotMap.CamAngle = SmartDashboard.getNumber("CameraAngle", 0);
        RobotMap.CamDirection = SmartDashboard.getNumber("Direction", 0);
        
        SmartDashboard.putBoolean("isWinched", shooter.isWinched());
        SmartDashboard.putBoolean("isLoaded", shooter.isLoaded());
        System.out.println("Left Enc: "+ drivetrain.getLeftEncoder());
        System.out.println("Right ENc"+ drivetrain.getRightEncoder());
        SmartDashboard.putNumber("Gyro", drivetrain.getAngle());	
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
        LiveWindow.run();
    }
}
