package org.usfirst.frc.team3256.robot.commands;

import org.usfirst.frc.team3256.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class Autonomous extends CommandGroup {

    public  Autonomous() {
        setInterruptible(false);
        DriveTrain.resetGyro();
        DriveTrain.resetEncoders();
        addSequential(new CatapultWinchAutomatic());
        addSequential(new EngageBallActuators());
        addSequential(new ShiftUp());
        addSequential(new PIDMoveForward(30, 0.5, 10));
        addSequential(new IntakePosAuto());
        addSequential(new PIDMoveForward(125, 0.5, 10));
        addSequential(new DisengageBallActuators());
    	addSequential(new PIDTurn(30));
    	addSequential(new PIDMoveForward(40, 0.5, 10));
    	addSequential(new WaitCommand(0.5));
    	System.out.println("Ready to align");
    	DriveTrain.resetGyro();
        addSequential(new PIDTurnGeneric());
        System.out.println("After align");
    	addSequential(new WaitCommand(0.5));
    	addSequential(new Shoot());
    }
}
