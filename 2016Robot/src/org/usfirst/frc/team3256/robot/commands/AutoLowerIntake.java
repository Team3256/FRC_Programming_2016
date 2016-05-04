package org.usfirst.frc.team3256.robot.commands;

import org.usfirst.frc.team3256.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoLowerIntake extends CommandGroup {
    
    public  AutoLowerIntake() {
        setInterruptible(false);
        DriveTrain.resetEncoders();
        addParallel(new ReEngageWinch());
        addSequential(new ShiftDown());
        addParallel(new CatapultWinchAutomatic());
        addSequential(new MoveFoward(5,.5));
        addSequential(new IntakePosAuto());
    }
}
