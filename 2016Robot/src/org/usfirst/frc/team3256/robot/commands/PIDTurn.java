package org.usfirst.frc.team3256.robot.commands;

import org.usfirst.frc.team3256.robot.PIDController;
import org.usfirst.frc.team3256.robot.Robot;
import org.usfirst.frc.team3256.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class PIDTurn extends Command {
	double degrees;
	
    public PIDTurn(double degrees) {
    	requires(Robot.drivetrain);
    	this.degrees = degrees;
    	setInterruptible(false);
    }
    // Called just before this Command runs the first time
    protected void initialize() {
    	DriveTrain.resetEncoders();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	DriveTrain.setLeftMotorSpeed(-0.1);
    	DriveTrain.setRightMotorSpeed(-0.1);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Math.abs(degrees - DriveTrain.ticksToDegrees(DriveTrain.getAvgEncoder())) < 1.5;
    }

    // Called once after isFinished returns true
    protected void end() {
    	DriveTrain.setLeftMotorSpeed(0);
    	DriveTrain.setRightMotorSpeed(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
