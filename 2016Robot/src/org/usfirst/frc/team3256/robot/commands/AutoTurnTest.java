package org.usfirst.frc.team3256.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
	
/**
 *
 */
public class AutoTurnTest extends CommandGroup {
    public  AutoTurnTest() {
    	//1=Clockwise,0=CounterClockwise
        addSequential(new PIDTurnGyro20_40(40,1));
        addSequential(new PIDTurnGyro10_20(20,1));
        addSequential(new PIDTurnGyro10_20(20,1));
        addSequential(new PIDTurnGyro10_20(10,1));
    }
}
