package org.usfirst.frc.team3256.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class EncoderTurnTest extends CommandGroup {
    
	public  EncoderTurnTest(int curr_angle, boolean enabled) {
    	setInterruptible(false);
    	if (enabled){
    		if (curr_angle>17&&curr_angle<=20) {
    			addSequential(new EncoderTurn(0.38,0,0.20,true));
    		}
    		//addSequential(new ShootnLoad());
    	}
	}
}
