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
	double current_degrees=0;
	private final double kP = 0.007, kI = 0.00035, kD = 0.0032;
    public PIDTurn(double degrees) {
    	requires(Robot.drivetrain);
    	this.degrees=degrees;
    	setInterruptible(false);
    	pid = new PIDController(kP, kI, kD); //0.007,0.00035,0.0032
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	DriveTrain.resetEncoders();
    	current_degrees = 0;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {

    	current_degrees = DriveTrain.ticksToDegrees(DriveTrain.getAvgEncoder());
    	output = pid.calculatePID(current_degrees, degrees);
    	DriveTrain.setLeftMotorSpeed(-output);
    	DriveTrain.setRightMotorSpeed(-output);
    	System.out.println("Current Degrees: "+ current_degrees +" " + "Current Ticks: " + DriveTrain.getAvgEncoder() +" "+ "Motor Ouptut: " + output + " " + "\n");
    	
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
