package org.usfirst.frc.team3256.robot;

import edu.wpi.first.wpilibj.buttons.Button;

import org.usfirst.frc.team3256.robot.commands.*;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	
	private static Joystick joystick1 = new Joystick(RobotMap.Joystick1);
	private static Joystick joystick2 = new Joystick(RobotMap.Joystick2);

	//Joystick controls for joystick 1
		public static Button buttonA1 = new JoystickButton(joystick1, 1);
		public static Button buttonB1 = new JoystickButton(joystick1, 2);
		public static Button buttonX1 = new JoystickButton(joystick1, 3);
		public static Button buttonY1 = new JoystickButton(joystick1, 4);
		public static Button leftBumper1 = new JoystickButton(joystick1, 5);
		public static Button rightBumper1 = new JoystickButton(joystick1, 6);
		public static Button backButton1 = new JoystickButton(joystick1, 7);
		public static Button startButton1 = new JoystickButton(joystick1, 8);
		public static Button leftStickButton1 = new JoystickButton(joystick1, 9);
		public static Button rightStickbutton1 = new JoystickButton(joystick1, 10);
		
		public static boolean getButtonA1(){
			return joystick1.getRawButton(1);
		}
		public static boolean getButtonX1(){
			return joystick1.getRawButton(3);
		}
		public static boolean getButtonLStick1(){
			return joystick1.getRawButton(9);
		}
		public static boolean getButtonRStick1(){
			return joystick1.getRawButton(10);
		}
		public static boolean getButtonRB1(){
			return joystick1.getRawButton(6);
		}
		
		public static boolean getY1() {return joystick1.getRawButton(4);}
		public static boolean getRightBumper1(){return joystick1.getRawButton(6);}
		public static double getLeftY1(){return joystick1.getRawAxis(1);}
	    public static double getLeftX1(){return joystick1.getRawAxis(2);}
	    public static double getRightY1(){return joystick1.getRawAxis(5);}
	    public static double getRightX1(){return joystick1.getRawAxis(4);}
	    public static boolean getRightTrigger1() {return joystick1.getRawAxis(3)>.2;}
	    public static boolean getLeftTrigger1() {return joystick1.getZ()>.5;}
	    
	    //Joysticks controls for joystick 2
	    public static Button buttonA2 = new JoystickButton(joystick2, 1);
	    public static Button buttonB2 = new JoystickButton(joystick2, 2);
	    public static Button buttonX2 = new JoystickButton(joystick2, 3);
	    public static Button buttonY2 = new JoystickButton(joystick2, 4);
	    public static Button leftBumper2 = new JoystickButton(joystick2, 5);
	    public static Button rightBumper2 = new JoystickButton(joystick2, 6);
	    public static Button backButton2 = new JoystickButton(joystick2, 7);
	    public static Button startButton2 = new JoystickButton(joystick2, 8);
	    public static Button leftStickButton2 = new JoystickButton(joystick2, 9);
	    public static Button rightStickbutton2 = new JoystickButton(joystick2, 10);
		

		public static boolean getY2() {return joystick2.getRawButton(4);}
		public static boolean getRightBumper2(){return joystick2.getRawButton(6);}
	    public static double getLeftY2(){return joystick2.getRawAxis(1);}
	    public static double getLeftX2(){return joystick2.getRawAxis(2);}
	    public static double getRightY2(){return joystick2.getRawAxis(5);}
	    public static double getRightX2(){return joystick2.getRawAxis(4);}
	    public static boolean getRightTrigger2() {return joystick2.getRawAxis(3)>.5;}
	    public static boolean getLeftTrigger2() {return joystick2.getZ()>.5;}
	    
	}