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
	PIDController pid;
	double output;
	double current_degrees;
	
    public PIDTurn(double degrees) {
    	requires(Robot.drivetrain);
    	this.degrees=degrees;
    	setInterruptible(false);
    	pid = new PIDController(0.006,0.0005,0.0001);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	DriveTrain.resetEncoders();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	current_degrees = DriveTrain.ticksToDegrees(DriveTrain.getRightEncoder());
    	output = pid.calculatePID(current_degrees, degrees);
    	DriveTrain.setLeftMotorSpeed(output);
    	DriveTrain.setRightMotorSpeed(output);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return pid.getError(current_degrees, degrees)<1;
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
