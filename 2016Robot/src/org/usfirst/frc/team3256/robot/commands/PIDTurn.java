package org.usfirst.frc.team3256.robot.commands;

import org.usfirst.frc.team3256.robot.PID;
import org.usfirst.frc.team3256.robot.Robot;
import org.usfirst.frc.team3256.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class PIDTurn extends Command {

	double angle;
	double PIDOUTPUT;
	PID TurnPID;
	
    public PIDTurn(double angle) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.drivetrain);
    	this.angle = angle;
    	setTimeout(2);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	DriveTrain.resetEncoders();
    	//TurnPID = new PID(1,0,0);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	PIDOUTPUT = TurnPID.calculatePID(Math.abs(DriveTrain.getRightEncoder()), 101.68*DriveTrain.degreesToInches(angle));
    	System.out.println("Degrees To Ticks" + 101.68*DriveTrain.degreesToInches(angle));
    	System.out.println("Current" + DriveTrain.getLeftEncoder());
    	System.out.println("OUPUT" + PIDOUTPUT);
    	DriveTrain.setLeftMotorSpeed(-PIDOUTPUT);
    	DriveTrain.setRightMotorSpeed(-PIDOUTPUT);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return TurnPID.getError(Math.abs(DriveTrain.getRightEncoder()), 101.68*DriveTrain.degreesToInches(angle))<10 ;
        
    }

    // Called once after isFinished returns true
    protected void end() {
    	System.out.println("Finished");
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
