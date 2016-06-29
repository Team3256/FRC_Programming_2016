package org.usfirst.frc.team3256.robot.commands;
import edu.wpi.first.wpilibj.command.CommandGroup;
	
/**
 *
 */
public class AutoTurnTest extends CommandGroup{
    public  AutoTurnTest(int current_angle, boolean enabled) {
    	if (enabled){
	    	if (current_angle<=10){
	    		addSequential(new PIDTurnGyro0_10());
	    		addSequential(new WaitCommand(0.5));
	    		addSequential(new ShootnLoad());
	    	}
	    	else if (current_angle>10&&current_angle<=20){
	    		addSequential(new PIDTurnGyro10_20());
	    		addSequential(new WaitCommand(0.5));
	    		addSequential(new ShootnLoad());
	    	}
	    	//Still need to turn
	    	else if (current_angle>20&&current_angle<=30){
	    		addSequential(new PIDTurnGyro20_30());
	    		addSequential(new WaitCommand(0.5));
	    		addSequential(new ShootnLoad());
	    	}
    	}
    }
}
