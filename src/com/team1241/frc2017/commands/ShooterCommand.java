package com.team1241.frc2017.commands;

import com.team1241.frc2017.NumberConstants;
import com.team1241.frc2017.Robot;
import com.team1241.frc2017.subsystems.Shooter;

import edu.wpi.first.wpilibj.command.Command;

/**
 *@author Kaveesha Siribaddana
 *@since 15/01/17
 */
public class ShooterCommand extends Command {
	
	private SetRPM rpm;

    public ShooterCommand() {
        requires(Robot.shooter);
        
        rpm = new SetRPM(NumberConstants.ShotRPM);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    
    	if(Robot.oi.getToolLeftBumper()){
    		rpm.start();
    	} else if (Robot.oi.getToolBackButton()){
    		Robot.shooter.setShooterState(false);
    	}
			
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
