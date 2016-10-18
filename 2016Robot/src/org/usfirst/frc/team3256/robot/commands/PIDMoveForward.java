package org.usfirst.frc.team3256.robot.commands;

import org.usfirst.frc.team3256.robot.PIDController;
import org.usfirst.frc.team3256.robot.Robot;
import org.usfirst.frc.team3256.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class PIDMoveForward extends Command {
	double distance;
	double speed;
	
    public PIDMoveForward(double distance, double speed, double timeout) {
    	requires(Robot.drivetrain);
    	this.distance = distance;
    	this.speed = speed;
    	setInterruptible(false);
    	setTimeout(timeout);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	DriveTrain.resetEncoders();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	//System.out.println("Running");
    	DriveTrain.setLeftMotorSpeed(speed);
    	DriveTrain.setRightMotorSpeed(-speed);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return distance < (DriveTrain.ticksToInches(DriveTrain.getRightEncoder())+distance/5+2)*0.92 || isTimedOut();
    }

    // Called once after isFinished returns true
    protected void end() {
    	DriveTrain.setLeftMotorSpeed(0);
    	DriveTrain.setRightMotorSpeed(0);
    	System.out.println("PIDMOVEFORWARD DONEEEEEEEEE");
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
