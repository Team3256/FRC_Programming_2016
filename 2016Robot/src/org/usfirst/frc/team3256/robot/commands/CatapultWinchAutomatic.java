package org.usfirst.frc.team3256.robot.commands;

import org.usfirst.frc.team3256.robot.Robot;
import org.usfirst.frc.team3256.robot.subsystems.Shooter;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class CatapultWinchAutomatic extends Command {

    public CatapultWinchAutomatic() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	setTimeout(2);
    	requires(Robot.shooter);
    	setInterruptible(false);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Shooter.engageWinch();  }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Shooter.winchBack();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Shooter.isWinched() || isTimedOut();
    }

    // Called once after isFinished returns true
    protected void end() {
    	System.out.println("Winch End");
    	Shooter.stopWinchBack();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	System.out.println("Winch Inter");
    }
}
