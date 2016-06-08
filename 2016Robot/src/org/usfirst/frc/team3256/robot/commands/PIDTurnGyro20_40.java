package org.usfirst.frc.team3256.robot.commands;

import org.usfirst.frc.team3256.robot.PIDController;
import org.usfirst.frc.team3256.robot.Robot;
import org.usfirst.frc.team3256.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class PIDTurnGyro20_40 extends Command {

	double degrees;
	double direction;
	double output;
	PIDController pid;
    public PIDTurnGyro20_40(double degrees, double direction) {
       requires(Robot.drivetrain);
       setInterruptible(false);
       this.degrees=degrees;
       this.direction=direction;
       pid = new PIDController(0.013,0.0003,0.003);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	DriveTrain.resetGyro();
    	pid.resetPID();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	output = pid.calculatePID(DriveTrain.getAngle(), degrees);
    	if (direction==1){
    		DriveTrain.setLeftMotorSpeed(-output);
        	DriveTrain.setRightMotorSpeed(-output);
    	}
    	else if (direction==0){
    		DriveTrain.setLeftMotorSpeed(output);
    		DriveTrain.setRightMotorSpeed(output);
    	}
    	else{
    		System.out.println("INVALID DIRECTION");
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return pid.getError(DriveTrain.getAngle(), degrees)<1;
    }

    // Called once after isFinished returns true
    protected void end() {
    	DriveTrain.setLeftMotorSpeed(0);
    	DriveTrain.setRightMotorSpeed(0);
    	System.out.println("DONEEEEEEEE");
    	double final_angle = DriveTrain.getAngle();
    	SmartDashboard.putNumber("Final Angle:", final_angle);
    	
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
