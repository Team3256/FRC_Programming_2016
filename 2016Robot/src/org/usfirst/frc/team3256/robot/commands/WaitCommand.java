package org.usfirst.frc.team3256.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

public class WaitCommand extends Command{

	
	public WaitCommand (double time){
		setTimeout(time);
	}
	
	protected void initialize() {
		// TODO Auto-generated method stub
	}


	protected void execute() {
		// TODO Auto-generated method stub
		
	}

	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return isTimedOut();
	}

	protected void end() {
		// TODO Auto-generated method stub
		
	}


	protected void interrupted() {
		// TODO Auto-generated method stub
		
	}

}
