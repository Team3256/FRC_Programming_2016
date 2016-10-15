package org.usfirst.frc.team3256.robot.commands;

import org.usfirst.frc.team3256.robot.RobotMap;
import org.usfirst.frc.team3256.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Autonomous extends CommandGroup {

    public  Autonomous() {
        setInterruptible(false);
        DriveTrain.resetGyro();
        DriveTrain.resetEncoders();
        //addSequential(new CatapultWinchAutomatic());
        //addSequential(new EngageBallActuators());
        //addSequential(new ShiftUp());
        //addSequential(new PIDMoveForwardFast(30));
        //addSequential(new IntakePosAuto());
        //addSequential(new PIDMoveForward(5));
        //addSequential(new DisengageBallActuators());
    	//addSequential(new PIDTurn(30));
    	addSequential(new PIDMoveForward(40));
    	//addSequential(new WaitCommand(0.5));
    	System.out.println("Ready to align");
    	DriveTrain.resetGyro();
        //addSequential(new PIDTurnGeneric());
        System.out.println("After align");
    	//addSequential(new WaitCommand(0.5));
    	//addSequential(new Shoot());
    	
    	
    	
    	/*//addSequential(new EngageBallActuators());
        addSequential(new PIDMoveForward(150));
    	//addSequential(new DisengageBallActuators());
    	System.out.println("Before MV Forward");
    	addSequential(new PIDMoveForward(45));
    	System.out.println("After MV Forward");
        System.out.println("DONEEEE");*/
    }
}
