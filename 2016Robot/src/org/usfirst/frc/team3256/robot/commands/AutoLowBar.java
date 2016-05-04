package org.usfirst.frc.team3256.robot.commands;

import org.usfirst.frc.team3256.robot.subsystems.DriveTrain;
import org.usfirst.frc.team3256.robot.subsystems.Shooter;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoLowBar extends CommandGroup {
    
    public  AutoLowBar() {
       setInterruptible(false);
       DriveTrain.resetEncoders();
       addParallel(new ReEngageWinch());
       addSequential(new ShiftUp());
       addSequential(new MoveFoward(5,0.5));
       addSequential(new IntakePosAuto());
       //addSequential(new CatapultWinchAutomatic());
      
       //if (Shooter.isLoaded())
    	//   addSequential (new EngageBallActuators());
       //addSequential(new WaitCommand(1));
       addSequential(new MoveFoward(210,1));
       addSequential(new WaitCommand(1));
       //addSequential(new PIDTurn(25));
       //addSequential(new WaitCommand(1));
       //addSequential(new PIDAutoTurn());
       //addSequential(new ShootnLoad());
    }
}
