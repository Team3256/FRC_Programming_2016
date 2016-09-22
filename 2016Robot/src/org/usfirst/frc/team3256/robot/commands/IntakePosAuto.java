package org.usfirst.frc.team3256.robot.commands;

import org.usfirst.frc.team3256.robot.Robot;
import org.usfirst.frc.team3256.robot.subsystems.Intake;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class IntakePosAuto extends Command {

    public IntakePosAuto() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.intake);
    	setInterruptible(true);
    	setTimeout(10);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Intake.incrementOut(.45);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Intake.isIntakePosL() || Intake.isIntakePosR();
    }

    // Called once after isFinished returns true
    protected void end() {
    	Intake.stopIntakePivot();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
