package org.usfirst.frc.team3256.robot.subsystems;

import org.usfirst.frc.team3256.robot.RobotMap;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Shooter extends Subsystem{

	static VictorSP catapultWinch = new VictorSP(RobotMap.catapultWinch);
	static DoubleSolenoid winchActuator = new DoubleSolenoid(RobotMap.winchEngage, RobotMap.winchDisengage);
	static DoubleSolenoid ballActuator = new DoubleSolenoid(RobotMap.ballHolderEngage, RobotMap.ballHolderDisengage);
	static DigitalInput catapultLimitSwitch = new DigitalInput(RobotMap.catapultLimitSwitch);
	static DigitalInput ballIR = new DigitalInput(RobotMap.ballIR);
	
	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
	}
	
	//pulls the catapult back
	public static void winchBack(){
		catapultWinch.set(-.5);
	}
	
	public static void stopWinchBack(){
		catapultWinch.set(0);
	}
	//releases the winch and shoots the ball via pancake actuator
	public static void disengageWinch(){
		winchActuator.set(DoubleSolenoid.Value.kForward);
	}
	
	//engages the winch to be able to pull back and reload via pancake actuator
	public static void engageWinch (){
		winchActuator.set(DoubleSolenoid.Value.kReverse);
	}
	
	//engages ball holding actuators
	public static void engageBallActuators(){
		ballActuator.set(DoubleSolenoid.Value.kForward);
	}
	
	//disengages ball holding actuators 
    public static void disengageBallActuators(){
    	ballActuator.set(DoubleSolenoid.Value.kReverse);
    } 
	
		
	//based on if the catapult is pulled back via limit switch
	public static boolean isWinched(){
		return !catapultLimitSwitch.get();
	}
	
	//based on if the ball is detected via IR Breaker
	public static boolean isLoaded(){
		return !ballIR.get();
	}
	

}
