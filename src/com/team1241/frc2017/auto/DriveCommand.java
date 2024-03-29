package com.team1241.frc2017.auto;

import com.team1241.frc2017.Robot;

import edu.wpi.first.wpilibj.command.Command;

/** 
 * @author Bryan Kristiono
 * @author Kaveesha Siribaddana
 * @since 13/01/17
 */
public class DriveCommand extends Command {
	private double distance;
	private double speed;
	private double angle;
	private double timeOut;
	private double tolerance;

	public DriveCommand(double setPoint, double speed, double angle, double timeOut) {
		this(setPoint, speed, angle, timeOut, 1);
	}

    public DriveCommand(double setPoint, double speed, double angle, double timeOut, double tolerance) {
    	this.distance = setPoint;
    	this.speed = speed;
    	this.angle = angle;
    	this.timeOut = timeOut;
    	this.tolerance = tolerance;
    	requires(Robot.drive);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.drive.resetEncoders();
    	setTimeout(timeOut);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.drive.driveSetpoint(distance, speed, angle, tolerance);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return Robot.drive.drivePIDDone() || isTimedOut();
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.drive.runLeftDrive(0);
    	Robot.drive.runRightDrive(0);
    	Robot.drive.resetPID();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.drive.runLeftDrive(0);
		Robot.drive.runRightDrive(0);
		Robot.drive.resetPID();
    }
}
