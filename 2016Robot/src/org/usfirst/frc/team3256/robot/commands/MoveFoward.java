package org.usfirst.frc.team3256.robot.commands;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.usfirst.frc.team3256.robot.MotionProfileController;
import org.usfirst.frc.team3256.robot.Robot;
import org.usfirst.frc.team3256.robot.Segment;
import org.usfirst.frc.team3256.robot.TrajectoryGenerator;
import org.usfirst.frc.team3256.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class MoveFoward extends Command {
	double error;
	double parameter;
	double time_initial;
	double time_current;
	double speed;
	double pos_current;
	TrajectoryGenerator leftTrajectoryGenerator;
	TrajectoryGenerator rightTrajectoryGenerator;
	Segment[] leftTrajectory;
	Segment[] rightTrajectory;
	MotionProfileController leftDriveStraight;
	MotionProfileController rightDriveStraight;
	int step=0;

	public MoveFoward(double error, double parameter) {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		requires(Robot.drivetrain);
		this.error = error;
		this.parameter = parameter;
		
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		double accel_max = 15;
		double total_distance = ((1.1*error)/12);
		double vel_max_l = 12.0;
		double time_accel_l = 12.0/accel_max;
		double distance_accel_l = time_accel_l*12*0.5;
		leftTrajectoryGenerator = new TrajectoryGenerator(total_distance, vel_max_l, accel_max, time_accel_l, distance_accel_l, 50);
		//Right Side needs to accelerate faster since the right side moves mechanically slower.
		double time_accel_r = 12/accel_max;
		double distance_accel_r=(time_accel_r*(12)*(0.5));
		rightTrajectoryGenerator = new TrajectoryGenerator(total_distance, 12, accel_max, time_accel_r, distance_accel_r, 50);
		leftTrajectory = leftTrajectoryGenerator.getTrajectory();
		rightTrajectory = rightTrajectoryGenerator.getTrajectory();
		System.out.println(leftTrajectory.length);
		System.out.println(rightTrajectory.length);
		System.out.println(leftTrajectoryGenerator.getTimeTotal());
		DriveTrain.resetEncoders();
		//DriveTrain.resetGyro();
		time_initial = Timer.getFPGATimestamp();
		DriveTrain.shiftUp();
		//System.out.println(leftTrajectory.length);
		leftDriveStraight = new MotionProfileController(leftTrajectory, 12.0, 0.01, 0.0, 0.0,0.0);
		rightDriveStraight = new MotionProfileController(rightTrajectory, 12, 0.01, 0.0, 0.0,0.0);
		step=0;
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		double left_speed = leftDriveStraight.getSpeed(step, Math.abs(DriveTrain.getLeftEncoder()));
		double right_speed = rightDriveStraight.getSpeed(step, Math.abs(DriveTrain.getRightEncoder()));
		System.out.println(left_speed + "////////////" + right_speed + "-----"+ leftTrajectoryGenerator.getTimeTotal() + "[[[[[[" + rightTrajectoryGenerator.getTimeTotal());
		if (left_speed<0.0){
			left_speed = 0.0;
		}
		if (right_speed < 0.0){
			right_speed = 0.0;
		}
		DriveTrain.setLeftMotorSpeed(parameter*right_speed);
		DriveTrain.setRightMotorSpeed(-parameter*left_speed);
		step++;
		/*time_current = Timer.getFPGATimestamp() - time_initial;
    	pos_current = ((Math.abs(DriveTrain.getLeftEncoder())+Math.abs(DriveTrain.getRightEncoder()))/2);
    	speed = PIDController.driveStraight(error/12, time_current, pos_current);

		System.out.println("speed" + speed);
    	if(speed<0.0){
    		speed=0.0;
    	}
    	if (speed > 0.875)
    		DriveTrain.setLeftMotorSpeed(0.875);
    	else
    		DriveTrain.setLeftMotorSpeed(speed);
    		DriveTrain.setRightMotorSpeed(-speed);
		 

		//System.out.print("Left Speed " + left_speed + "/////////////////////  Right Speed " + right_speed + "\n");
*/		/*double PIDOutput;
		PIDOutput = PID.calculatePID(DriveTrain.getLeftEncoder(), DriveTrain.inchesToTicksHG(20));*/
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return leftDriveStraight.isFinished() && rightDriveStraight.isFinished();
		/*time_current = Timer.getFPGATimestamp() - time_initial;
    	if (time_current > PIDController.getTimeTotal()){
    		return true;
    	}
    	else
    		return false;*/
		/*if (DriveTrain.getLeftEncoder()>=DriveTrain.inchesToTicksHG(20)){
			return true;
		}
		else {
			return false;
		}*/
	}

	// Called once after isFinished returns true
	protected void end() {
		/*List<Segment> list = new ArrayList<Segment>(Arrays.asList(leftTrajectory));
		list.clear();
		leftTrajectory = list.toArray(new Segment[0]);
		List<Segment> list2 = new ArrayList<Segment>(Arrays.asList(rightTrajectory));
		list2.clear();
		rightTrajectory = list2.toArray(new Segment[0]);
		DriveTrain.resetEncoders();*/
		//step=0;
		System.out.println("______________________Finished");
		DriveTrain.setLeftMotorSpeed(0);
		DriveTrain.setRightMotorSpeed(0);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
}
