package org.usfirst.frc.team3256.robot.commands;
import edu.wpi.first.wpilibj.command.CommandGroup;
	
/**
 *
 */
public class AutoTurnTest extends CommandGroup{
    public  AutoTurnTest(int current_angle, boolean enabled) {
    	setInterruptible(true);
    	addSequential(new WaitCommand(1));
    	if (enabled){
    		switch (current_angle) {
    		/*case 2: 
    			addSequential(new PIDTurnGeneric(0.1, 0.0003, 0.18, false));
    			break;
    		case 3: 
    			addSequential(new PIDTurnGeneric(0.1, 0.0003, 0.18, false));
    			break;
    		case 4:
    			addSequential(new PIDTurnGeneric(0.1, 0.0003, 0.18, false));
    			break;*/
    		case 5:
    			addSequential(new PIDTurnGeneric(0.08, 0.0003, 0.2, false));
    			break;
    		case 6:
    			addSequential(new PIDTurnGeneric(0.08, 0.0, 0.15, false));
    			break;
    		case 7:
    			addSequential(new PIDTurnGeneric(0.08, 0.0, 0.2, false));
    			break;
    		case 8:
    			addSequential(new PIDTurnGeneric(0.087, 0.0, 0.23, false));
    			break;
    		case 9:
    			addSequential(new PIDTurnGeneric(0.08, 0.0, 0.23, true));
    			break;
    		case 10:
    			addSequential(new PIDTurnGeneric(0.083, 0.0, 0.23, true));
    			break;
    		case 11:
    			addSequential(new PIDTurnGeneric(0.082, 0.0, 0.26, true));
    			break;
    		case 12:
    			addSequential(new PIDTurnGeneric(0.081, 0.0, 0.26, true));
    			break;
    		case 13:
    			addSequential(new PIDTurnGeneric(0.081, 0.0, 0.26, true));
    			break;
    		case 14:
    			addSequential(new PIDTurnGeneric(0.08, 0.0, 0.28, true));
    			break;
    		case 15:
    			addSequential(new PIDTurnGeneric(0.08, 0.0, 0.28, true));
    			break;
    		case 16:
    			addSequential(new PIDTurnGeneric(0.08, 0.0, 0.29, true));
    			break;
    		case 17:
    			addSequential(new PIDTurnGeneric(0.08, 0.0, 0.31, true));
    			break;
    		case 18:
    			addSequential(new PIDTurnGeneric(0.077, 0.0, 0.32, true));
    			break;
    		case 19:
    			addSequential(new PIDTurnGeneric(0.076, 0.0, 0.33, true));
    			break;
    		case 20:
    			addSequential(new PIDTurnGeneric(0.074, 0.0, 0.33, true));
    			break;
    		}
    		addSequential(new ShootnLoad());
    	}
    }
}
