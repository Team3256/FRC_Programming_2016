package org.usfirst.frc.team3256.robot.commands;

import org.usfirst.frc.team3256.robot.PID;
import org.usfirst.frc.team3256.robot.Robot;
import org.usfirst.frc.team3256.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */

public class CustomPIDMoveForward extends Command {

	double power;
	double inches;
	double ticks;
	PID pid;
	
    public CustomPIDMoveForward(double inches) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires (Robot.drivetrain);
    	this.inches = inches;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	DriveTrain.resetEncoders();
    	DriveTrain.shiftUp();
    	pid = new PID(0.05,0,0);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	power = pid.calculatePID(DriveTrain.getLeftEncoder(), DriveTrain.inchesToTicks(inches));
    	DriveTrain.setLeftMotorSpeed(power);
		DriveTrain.setRightMotorSpeed(-power);
		System.out.println("Ticks" + DriveTrain.getLeftEncoder());
		System.out.println("Error" + pid.getError(DriveTrain.getLeftEncoder(), DriveTrain.inchesToTicks(inches)));
		System.out.println("Power" + power);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return (DriveTrain.inchesToTicks(inches) - Math.abs(DriveTrain.getLeftEncoder())) < 300;
    }

    // Called once after isFinished returns true
    protected void end() {
    	System.out.println("ENDDDDDDDDDDD");
    	DriveTrain.setLeftMotorSpeed(0);
		DriveTrain.setRightMotorSpeed(0);	
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
