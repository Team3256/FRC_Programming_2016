package org.usfirst.frc.team3256.robot.commands;

import org.usfirst.frc.team3256.robot.PIDController;
import org.usfirst.frc.team3256.robot.Robot;
import org.usfirst.frc.team3256.robot.RobotMap;
import org.usfirst.frc.team3256.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class PIDTurnGyro10_20 extends Command {

	double degrees;
	double direction;
	double output;
	double final_angle;
	PIDController pid;
	
    public PIDTurnGyro10_20() {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.drivetrain);
        setInterruptible(false);
        pid = new PIDController(0.015,0.0009,0.002);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        this.degrees=SmartDashboard.getNumber("CameraAngle", 0);
        this.direction=SmartDashboard.getNumber("Direction", 0);
    	DriveTrain.resetGyro();
    	pid.resetPID();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	output = pid.calculatePID(DriveTrain.getAngle(), degrees);
    	System.out.println("Angle: "+ DriveTrain.getAngle());
    	if (direction==0){
    		DriveTrain.setLeftMotorSpeed(-output);
        	DriveTrain.setRightMotorSpeed(-output);
    	}
    	else if (direction==1){
    		DriveTrain.setLeftMotorSpeed(output);
    		DriveTrain.setRightMotorSpeed(output);
    	}
    	else {
    		System.out.println("INVALID DIRECTION");
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return pid.getError(DriveTrain.getAngle(), degrees)<1;
    }

    // Called once after isFinished returns true
    protected void end() {
    	DriveTrain.setLeftMotorSpeed(0);
    	DriveTrain.setRightMotorSpeed(0);
    	System.out.println("DONEEEEEEEE");
        final_angle = DriveTrain.getAngle();
        SmartDashboard.putNumber("Final Angle", final_angle);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
