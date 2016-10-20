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
    	DriveTrain.setLeftMotorSpeed(speed*0.98/0.92);
    	DriveTrain.setRightMotorSpeed(-speed);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return distance < 1.132738142857*(DriveTrain.ticksToInches(DriveTrain.getRightEncoder()))+14.95237514286 || isTimedOut();
        // used data points and found a regression line to accurately move forward
        // works for distance >= 40, doesn't work well for values less
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
