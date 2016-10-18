package org.usfirst.frc.team3256.robot.commands;

import org.usfirst.frc.team3256.robot.RobotMap;
import org.usfirst.frc.team3256.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Autonomous extends CommandGroup {

    public  Autonomous() {
        setInterruptible(false);
        DriveTrain.resetGyro();
        DriveTrain.resetEncoders();
        int distance = 30;
    	addSequential(new PIDMoveForward(distance, 0.25, 15));
    	//addSequential(new PIDMoveForward(distance, 0.25, 10));
    	System.out.println("Distance " + distance);
    	System.out.println("Ready to align");
    	DriveTrain.resetGyro();
        System.out.println("After align");
    }
}
