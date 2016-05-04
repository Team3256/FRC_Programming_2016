package org.usfirst.frc.team3256.robot.commands;

import org.usfirst.frc.team3256.robot.PID;
import org.usfirst.frc.team3256.robot.Robot;
import org.usfirst.frc.team3256.robot.RobotMap;
import org.usfirst.frc.team3256.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class PIDAutoTurn extends Command {
	
	PID TurnPID;
	double PIDOutput;
	
    public PIDAutoTurn() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.drivetrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	//setTimeout(2);
    	DriveTrain.shiftDown();
    	//TurnPID = new PID(0.045,0.0001,7);
     }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	PIDOutput = 0.45 * TurnPID.calculatePID(RobotMap.CamAngle, 0);
    	System.out.println("CAMANGLE" + RobotMap.CamAngle + "CAMDIRECTION" + RobotMap.CamDirection);
    	if (RobotMap.CamDirection == 0.0){
    		DriveTrain.setLeftMotorSpeed(PIDOutput);
    		DriveTrain.setRightMotorSpeed(PIDOutput);
    	}
    	else {
    		DriveTrain.setLeftMotorSpeed(-PIDOutput);
    		DriveTrain.setRightMotorSpeed(-PIDOutput);
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Math.abs(TurnPID.getError(RobotMap.CamAngle, 0)) < 1;
    }

    // Called once after isFinished returns true
    protected void end() {
    	DriveTrain.setLeftMotorSpeed(0);
    	DriveTrain.setRightMotorSpeed(0);
    	System.out.println("END");
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
