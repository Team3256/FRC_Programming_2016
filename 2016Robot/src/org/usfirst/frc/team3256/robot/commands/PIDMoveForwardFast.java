package org.usfirst.frc.team3256.robot.commands;

import org.usfirst.frc.team3256.robot.PIDController;
import org.usfirst.frc.team3256.robot.Robot;
import org.usfirst.frc.team3256.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class PIDMoveForwardFast extends Command {

	double Pos;
	double output;
	PIDController pid;
	
    public PIDMoveForwardFast(double Pos) {
    	requires(Robot.drivetrain);
    	this.Pos=Pos;
    	setInterruptible(false);
    	pid=new PIDController(0.03,0.0,0.0161);
    	setTimeout(2.5);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	DriveTrain.resetEncoders();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	output = pid.calculatePID(DriveTrain.ticksToInches(DriveTrain.getRightEncoder()), Pos);
    	if (output >0.7) output = 0.7;
    	DriveTrain.setLeftMotorSpeed(output);
    	DriveTrain.setRightMotorSpeed(-output);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return pid.getError(DriveTrain.ticksToInches(DriveTrain.getRightEncoder()), Pos)<1||isTimedOut();
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
