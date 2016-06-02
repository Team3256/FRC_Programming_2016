package org.usfirst.frc.team3256.robot;

import org.usfirst.frc.team3256.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.Timer;

public class MotionProfilingTest {

	public MotionProfilingTest(){

	}
	static double time_total;
	static double time_cruise;
	static double max_v=12;
	static double max_accel=15;
	static double time_accel = 12/15;
	static double kV = 1/max_v;
	static double kA = 0.0;
	static double distance_accel = 4.8;
	static double distance_deaccel = 4.8;
	static double distance_cruise;
	static double kP = 0;
	static double kI = 0;
	static double kD = 0;
	static double P;
	static double I;
	static double D;
	static double error;
	static double time_initial = 0;
	static double sumError;
	static double prevError;
	static double changeError;
	static double PID;

	public static double getTimeTotal(){
		return time_total;
	}


	/**
	 * This method calculates the velocity in ft/s at a specific time.
	 * @param  time (time in seconds based off of FPGA Timestamp)
	 * @return velocity (velocity of robot at time in ft/s)
	 */
	public static double calculateV(double time){
		double time_deaccel = time_total-time_accel;
		double velocity = 0;

		if (time<time_accel){
			velocity = max_accel*time;
		}
		else if (time > time_accel && time < time_deaccel){
			velocity = max_v;
		}
		else {
			velocity = max_v-(max_accel*(time-time_deaccel));
		}
		return velocity;
	}

	/**
	 * This method calculates the acceleration in ft/s^2 at a specific time.
	 * @param  time (time in seconds based off of FPGA Timestamp)
	 * @return acceleration (acceleration of robot at time in ft/s^2)
	 */
	public static double calculateA(double time){
		double acceleration = 0;
		double time_deaccel = time_total-time_accel;
		if (time<time_accel){
			acceleration = max_accel;
		}
		else if (time > time_accel && time < time_deaccel){
			acceleration = 0;
		}
		else {
			acceleration = -max_accel;
		}
		return acceleration;

	}

	/**
 	* This method calculates the position of the robot in feet at a specific time.
 	* @param  time (time in seconds based off of FPGA Timestamp)
 	* @return positon (position of robot at time in feet)
 	*/
	public static double calculateS(double time){
		double position = 0;
		double time_deaccel = time_total-time_accel;
		if (time > time_accel && time < time_deaccel){
			position = max_v*time;
		}
		else {
			position = (max_accel/2)*time*time;
		}
		return position;
	}

	/**
	 * This method calculates the PID output at a certain position based off of a setpoint.
	 * @param setpoint (position the robot needs to be at)
	 * @param current (position that the robot is currently at)
	 * @return PID (motor output that the motors need to drive at)
	 */
	public static double calculatePID(double setpoint, double current){
		error = setpoint-current;
		P = kP * error;
		sumError = sumError + error;
		I = sumError * kI;
		changeError = prevError - error;
		D = changeError * kD;
		PID = P+I+D;
		prevError = error;
		return PID;


	}

	/**
	 * This is the main motion profiling controller that gets called every 20ms by the MoveForward command.
	 * @param feet (setpoint that the robot needs to achieve)
	 * @param time (ammount time that has passed since MoveForward initialize)
	 * @param current (the current position of the robot)
	 * @return output (the motor value that the motor needs to be driving at)
	 */
	public static double driveStraight(double feet, double time, double current){
		distance_cruise = feet-(distance_accel+distance_deaccel);
		time_cruise = distance_cruise/6;
		time_total = (time_cruise + time_accel + time_accel);
		//System.out.println("Time: "+time_total+"\n");
		//System.out.println("Velocity: "+calculateV(time)+ "////// Acceleration:" +calculateA(time)+"\n");
		//This calculates the motor output at a specific time by getting the Velocity, Acceleration, and Position.
		double output = (kV * calculateV(time))
										+(kA*calculateA(time));
										//+calculatePID(calculateS(time), current));
		//double output = 0.0;
		return output;
	}
}
