package org.usfirst.frc.team3256.robot.commands;

import org.usfirst.frc.team3256.robot.PIDController;
import org.usfirst.frc.team3256.robot.Robot;
import org.usfirst.frc.team3256.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class EncoderTurn extends Command {

	double inches;
	double direction;
	double output;
	double final_angle;
	PIDController pid;
	boolean cameraStop;
	
    public EncoderTurn(double p, double i, double d, boolean cameraStop) {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.drivetrain);
        pid = new PIDController(p,i,d);
        this.cameraStop=cameraStop;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	DriveTrain.resetEncoders();
    	this.inches=DriveTrain.degreesToInches(SmartDashboard.getNumber("CameraAngle", 0));
        this.direction=SmartDashboard.getNumber("Direction", 0);
    	DriveTrain.resetGyro();
    	System.out.println("PID Turn STarted");
    	pid.resetPID();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	output = pid.calculatePID(DriveTrain.ticksToInches(DriveTrain.getAvgEncoder()), inches);
    	System.out.println("PID TURN RUNNING");
    	System.out.println("Current " + DriveTrain.getAvgEncoder());
    	System.out.println("Target " + DriveTrain.inchesToTicks(inches));    	
    	//System.out.println("Angle: "+ DriveTrain.getAngle());
    	//System.out.println("Dashboard Angle: " + SmartDashboard.getNumber("CameraAngle", 0));
    	if (direction==0){
    		DriveTrain.setLeftMotorSpeed(-output);
        	DriveTrain.setRightMotorSpeed(-output);
    	}
    	else if (direction==1){
    		DriveTrain.setLeftMotorSpeed(output);
    		DriveTrain.setRightMotorSpeed(output);
    	}
    	else {
    		System.out.println("INVALID DIRECTION");
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Math.abs(DriveTrain.getAvgEncoder()-DriveTrain.inchesToTicks(inches))<10;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
