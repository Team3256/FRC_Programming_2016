package org.usfirst.frc.team3256.robot.commands;

import org.usfirst.frc.team3256.robot.PIDController;
import org.usfirst.frc.team3256.robot.Robot;
import org.usfirst.frc.team3256.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class PIDMoveForwardFast extends Command {
	double distance;
	
    public PIDMoveForwardFast(double distance) {
    	requires(Robot.drivetrain);
    	this.distance = distance;
    	setInterruptible(false);
    	setTimeout(2.5);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	DriveTrain.resetEncoders();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	DriveTrain.setLeftMotorSpeed(0.45);
    	DriveTrain.setRightMotorSpeed(0.45);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Math.abs(distance - DriveTrain.ticksToInches(DriveTrain.getRightEncoder())) < 1 || isTimedOut();
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
