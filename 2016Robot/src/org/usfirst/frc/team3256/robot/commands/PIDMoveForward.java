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
    	this.Pos = Pos;
    	setInterruptible(false);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	DriveTrain.resetEncoders();
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	//Robot.drivetrain.setSetpoint(Pos);
    	Robot.drivetrain.setDriveSetpoint(Pos);
    	//System.out.println("Setpoint" + Robot.drivetrain.getSetpoint());
    	//System.out.println("CurrentR"+ DriveTrain.getRightEncoder());    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	System.out.println("Error " + (Robot.drivetrain.getDriveSetpoint()-Robot.drivetrain.getDriveCurrent()));
    	return Math.abs(Robot.drivetrain.getDriveSetpoint()-Robot.drivetrain.getDriveCurrent())<1;
    }

    // Called once after isFinished returns true
    protected void end() {
    	System.out.println("Finished /////////////////////////////////////////");
    	DriveTrain.setRightMotorSpeed(0);
    	DriveTrain.setLeftMotorSpeed(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
