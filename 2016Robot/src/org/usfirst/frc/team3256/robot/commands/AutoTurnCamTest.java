package org.usfirst.frc.team3256.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoTurnCamTest extends CommandGroup {
    
    public  AutoTurnCamTest() {
    	addSequential(new WaitCommand(1));
    	//addSequential(new PIDTurnCamera());
    }
}
