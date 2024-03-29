
package com.team1241.frc2017;

import com.team1241.frc2017.auto.NoAuto;
import com.team1241.frc2017.subsystems.Conveyor;
import com.team1241.frc2017.subsystems.Drivetrain;
import com.team1241.frc2017.subsystems.Hanger;
import com.team1241.frc2017.subsystems.Hopper;
import com.team1241.frc2017.subsystems.Intake;
import com.team1241.frc2017.subsystems.Shooter;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 * 
 * @author Kaveesha Siribaddana
 */
public class Robot extends IterativeRobot {

	public static OI oi;
	public static Drivetrain drive;
	public static Intake intake;
	public static Shooter shooter;
	public static Conveyor conveyor;
	public static Hopper hopper;
	public static Hanger hanger;
	
	Preferences pref;
	public static double rpm;
	public static double power;
	public static double powerC;
	public static double p;
	
	Command autonomousCommand;
	SendableChooser autoChooser;

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	public void robotInit() {
		
		oi = new OI();
		drive = new Drivetrain();
		intake = new Intake();
		shooter = new Shooter();
		hopper = new Hopper();
		hanger = new Hanger();
		
		autoChooser = new SendableChooser();

		autoChooser.addDefault("No Auton", new NoAuto());
	}

	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	public void disabledInit() {

	}

	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString code to get the auto name from the text box below the Gyro
	 *
	 * You can add additional auto modes by adding additional commands to the
	 * chooser code above (like the commented example) or additional comparisons
	 * to the switch structure below with additional strings & commands.
	 */
	public void autonomousInit() {
		autonomousCommand = (Command) autoChooser.getSelected();

		/*
		 * String autoSelected = SmartDashboard.getString("Auto Selector",
		 * "Default"); switch(autoSelected) { case "My Auto": autonomousCommand
		 * = new MyAutoCommand(); break; case "Default Auto": default:
		 * autonomousCommand = new ExampleCommand(); break; }
		 */

		// schedule the autonomous command (example)
		if (autonomousCommand != null)
			autonomousCommand.start();
	}

	/**
	 * This function is called periodically during autonomous
	 */
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	public void teleopInit() {
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
		if (autonomousCommand != null)
			autonomousCommand.cancel();
	}

	/**
	 * This function is called periodically during operator control
	 */
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This function is called periodically during test mode
	 */
	public void testPeriodic() {
		LiveWindow.run();
	}
	
	public void updateSmartDashboard(){
		rpm = pref.getDouble("RPM", 0.0);
		power = pref.getDouble("Shooter Power", 0.0);
		powerC = pref.getDouble("Conveyor Power", 0.0);
		p = pref.getDouble("Shooter pGain", 0.0);
		
		SmartDashboard.putBoolean("Can Shoot", shooter.shooterPID.isDone());
		SmartDashboard.putNumber("Shooter RPM", shooter.getRPM());
		SmartDashboard.putNumber("Set RPM", rpm);
		SmartDashboard.putNumber("Set Power", power);
	}
}
