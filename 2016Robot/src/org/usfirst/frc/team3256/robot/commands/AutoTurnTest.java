package org.usfirst.frc.team3256.robot.commands;

import org.usfirst.frc.team3256.robot.RobotMap;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
	
/**
 *
 */
public class AutoTurnTest extends CommandGroup {
    public  AutoTurnTest() {
    	addSequential(new PIDTurnGyro10_20());
    	System.out.println("First Turn Done");
    	addSequential(new WaitCommand(3));
    	addSequential(new PIDTurnGyro10_20());
    }
}
