package org.usfirst.frc.team3256.robot.commands;

import org.usfirst.frc.team3256.robot.PIDController;
import org.usfirst.frc.team3256.robot.Robot;
import org.usfirst.frc.team3256.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class PIDTurnGeneric extends Command {	
	double direction;
	double init_direction;
	double init_angle;
	double input_angle;
	double output;
	
    public PIDTurnGeneric() {
        requires(Robot.drivetrain);
        setInterruptible(true);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	init_direction = SmartDashboard.getNumber("Direction", 0);
        init_angle = SmartDashboard.getNumber("CameraAngle", 0);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	direction = SmartDashboard.getNumber("Direction", 0);
    	double curr_cameraAngle = SmartDashboard.getNumber("CameraAngle", 0);
    	if (init_direction != direction){
    		input_angle = Math.abs(curr_cameraAngle + init_angle);
    	}
    	else
    		input_angle = init_angle - curr_cameraAngle;
    	output = 0.5;
    	System.out.println("Setpoint: " + init_angle);
    	System.out.println("Cam angle: " + curr_cameraAngle);
    	System.out.println("Current: " + (input_angle));
    	
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
        return SmartDashboard.getNumber("CameraAngle", 0)<1;
    }

    // Called once after isFinished returns true
    protected void end() {
    	DriveTrain.setLeftMotorSpeed(0);
    	DriveTrain.setRightMotorSpeed(0);
    	System.out.println("DONEEEEEEEE");
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
