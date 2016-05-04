package org.usfirst.frc.team3256.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoChivalDeFrise extends CommandGroup {
    
    public AutoChivalDeFrise() {
       setInterruptible(false);
       addParallel(new ReEngageWinch());
       addSequential(new ShiftUp());
       addSequential(new CatapultWinchAutomatic());
    }
}
