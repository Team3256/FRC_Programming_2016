package org.usfirst.frc.team3256.robot.commands;

import org.usfirst.frc.team3256.robot.RobotMap;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Autonomous extends CommandGroup {
    
    public  Autonomous() {
    	int angle = (int) RobotMap.CamAngle;
        addSequential(new PIDMoveForward(60));
        addSequential(new PIDTurn(40));
        addSequential(new AutoTurnTest(angle, true));
    }
}
