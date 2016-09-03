package org.usfirst.frc.team3256.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Autonomous extends CommandGroup {

    public  Autonomous() {
    	addSequential(new EngageBallActuators());
        addSequential(new PIDMoveForward(150));
    	addSequential(new PIDTurn(40));
    	addSequential(new DisengageBallActuators());
    	addSequential(new PIDMoveForward(45));
        addSequential(new AutoTurnTest((int) SmartDashboard.getNumber("CameraAngle", 100),true));
        System.out.println("DONEEEE");
    }
}
