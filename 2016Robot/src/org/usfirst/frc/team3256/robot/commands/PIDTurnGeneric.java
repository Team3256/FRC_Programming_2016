package org.usfirst.frc.team3256.robot.commands;

import org.usfirst.frc.team3256.robot.PIDController;
import org.usfirst.frc.team3256.robot.Robot;
import org.usfirst.frc.team3256.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class PIDTurnGeneric extends Command {

	double degrees;
	double direction;
	double output;
	double final_angle;
	PIDController pid;
	boolean cameraStop;
	
    public PIDTurnGeneric() {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.drivetrain);
        setInterruptible(true);
        //pid = new PIDController(p, i, d);
        //this.cameraStop = cameraStop;
        //for high gear
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	//0.06,0.003,0.0012
        //.1,.0001,.21
        System.out.println("////////////////////////////////////" + (int)SmartDashboard.getNumber("CameraAngle",0));
        switch ((int)SmartDashboard.getNumber("CameraAngle",0)) {
		/*case 2: 
			addSequential(new PIDTurnGeneric(0.1, 0.0003, 0.18, false));
			break;
		case 3: 
			addSequential(new PIDTurnGeneric(0.1, 0.0003, 0.18, false));
			break;
		case 4:
			addSequential(new PIDTurnGeneric(0.1, 0.0003, 0.18, false));
			break;*/
		case 5:
			pid = new PIDController(0.08, 0.0003, 0.2);
			break;
		case 6:
			pid = new PIDController(0.08, 0.0, 0.15);
			break;
		case 7:
			pid = new PIDController(0.08, 0.0, 0.2);
			break;
		case 8:
			pid = new PIDController(0.087, 0.0, 0.23);
			break;
		case 9:
			pid = new PIDController(0.08, 0.0, 0.23);
			break;
		case 10:
			pid = new PIDController(0.083, 0.0, 0.23);
			break;
		case 11:
			pid = new PIDController(0.082, 0.0, 0.26);
			break;
		case 12:
			pid = new PIDController(0.081, 0.0, 0.26);
			break;
		case 13:
			pid = new PIDController(0.081, 0.0, 0.26);
			break;
		case 14:
			pid = new PIDController(0.08, 0.0, 0.28);
			break;
		case 15:
			pid = new PIDController(0.08, 0.0, 0.28);
			break;
		case 16:
			pid = new PIDController(0.08, 0.0, 0.29);
			break;
		case 17:
			pid = new PIDController(0.08, 0.0, 0.31);
			break;
		case 18:
			pid = new PIDController(0.077, 0.0, 0.32);
			break;
		case 19:
			pid = new PIDController(0.076, 0.0, 0.33);
			break;
		case 20:
			pid = new PIDController(0.074, 0.0, 0.33);
			break;
		default:
			pid = new PIDController(0.08, 0.0, 0.28);
			System.out.println("NO GOAL FOUND");
			break;
		}
    	this.degrees=SmartDashboard.getNumber("CameraAngle", 0);
        this.direction=SmartDashboard.getNumber("Direction", 0);
    	DriveTrain.resetGyro();
    	pid.resetPID();
    	System.out.println("PID Turn Started; Angle: "+ degrees);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	output = pid.calculatePID(DriveTrain.getAngle(), degrees);
    	System.out.println("PID Val: " + pid.calculatePID(DriveTrain.getAngle(), degrees));
    	if (output >=.6) output = .6; 
    	System.out.println("PID TURN RUNNING; Angle: "+ degrees);
    	//System.out.println("Angle: "+ DriveTrain.getAngle());
    	//System.out.println("Dashboard Angle: " + Smartashboard.getNumber("CameraAngle", 0));
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
        return pid.getError(DriveTrain.getAngle(), degrees)<1||(cameraStop && SmartDashboard.getNumber("CameraAngle", 0)<1);
    }

    // Called once after isFinished returns true
    protected void end() {
    	DriveTrain.setLeftMotorSpeed(0);
    	DriveTrain.setRightMotorSpeed(0);
    	System.out.println("DONEEEEEEEE");
        final_angle = DriveTrain.getAngle();
        SmartDashboard.putNumber("Final Angle", final_angle);
        output = 0;
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
