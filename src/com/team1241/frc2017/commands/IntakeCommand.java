package com.team1241.frc2017.commands;

import com.team1241.frc2017.Robot;
import com.team1241.frc2017.utilities.ToggleBoolean;

import edu.wpi.first.wpilibj.command.Command;

/**
 * @author Kaveesha Siribaddana
 * @since 14/01/17
 */
public class IntakeCommand extends Command {

	ToggleBoolean toggle = new ToggleBoolean();

	public IntakeCommand() {
		requires(Robot.intake);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {

		if (Robot.oi.getToolXButton()) {
			Robot.intake.intake(1);
		} else if (Robot.oi.getToolAButton()) {
			Robot.intake.outtake(-1);
		}

		toggle.set(Robot.oi.getToolRightBumper());

		if (toggle.get())
			Robot.intake.extendIntake();
		else
			Robot.intake.retractIntake();
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
