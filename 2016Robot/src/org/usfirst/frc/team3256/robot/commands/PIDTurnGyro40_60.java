package org.usfirst.frc.team3256.robot.commands;

import org.usfirst.frc.team3256.robot.PIDController;
import org.usfirst.frc.team3256.robot.Robot;
import org.usfirst.frc.team3256.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class PIDTurnGyro40_60 extends Command {

	double degrees;
	double output;
	PIDController pid;
    
	public PIDTurnGyro40_60(double degrees) {
        requires(Robot.drivetrain);
        this.degrees=degrees;
        setInterruptible(false);
        pid = new PIDController(0.01,0.0002,0.028);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	DriveTrain.resetGyro();
    	pid.resetPID();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	output = pid.calculatePID(DriveTrain.getAngle(), degrees);
    	DriveTrain.setLeftMotorSpeed(-output);
    	DriveTrain.setRightMotorSpeed(-output);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return pid.getError(DriveTrain.getAngle(), degrees)<1;
    }

    // Called once after isFinished returns true
    protected void end() {
    	DriveTrain.setLeftMotorSpeed(0);
    	DriveTrain.setRightMotorSpeed(0);
    	System.out.println("DONEEEEEEEEEEEEEE");
    	DriveTrain.initGyro();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
