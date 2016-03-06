package org.usfirst.frc.team3256.robot.commands;

import org.usfirst.frc.team3256.robot.Robot;
import org.usfirst.frc.team3256.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class PIDMoveForward extends Command {
	
	double Pos;

    public PIDMoveForward(double Pos) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.drivetrain);
    	this.Pos = DriveTrain.inchesToTicks(Pos);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	DriveTrain.resetEncoders();
    	Robot.drivetrain.setSetpoint(Pos);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return Math.abs(Robot.drivetrain.getSetpoint()-Robot.drivetrain.getPosition())<1000;
    }

    // Called once after isFinished returns true
    protected void end() {
    	System.out.println("out");
    	DriveTrain.setRightMotorSpeed(0);
    	DriveTrain.setLeftMotorSpeed(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
