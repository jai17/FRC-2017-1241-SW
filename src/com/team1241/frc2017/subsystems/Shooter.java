package com.team1241.frc2017.subsystems;

import com.ctre.CANTalon;
import com.team1241.frc2017.ElectricalConstants;
import com.team1241.frc2017.NumberConstants;
import com.team1241.frc2017.pid.PIDController;
import com.team1241.frc2017.utilities.LineRegression;

import edu.wpi.first.wpilibj.Counter;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * @author Kaveesha Siribaddana
 * @since 14/01/17
 */
public class Shooter extends Subsystem {

	CANTalon shooterMotor;

	Counter optical;

	PIDController shooterPID;

	LineRegression calcLine = new LineRegression();

	private double kForward;
	private double bForward;

	public Shooter() {
		shooterMotor = new CANTalon(ElectricalConstants.SHOOTER_MOTOR);

		optical = new Counter();
		optical.setUpSource(ElectricalConstants.OPTICAL_SENSOR);
		optical.setUpDownCounterMode();
		optical.setDistancePerPulse(1);

		shooterPID = new PIDController(NumberConstants.pShooter, NumberConstants.iShooter, NumberConstants.dShooter);

	}
	
	public void setRPM(double rpm){
		double output = shooterPID.calcPID(rpm, getRPM(), 50);
		
		runShooter(rpm * kForward + bForward + output);
	}

	public void runShooter(double input) {
		shooterMotor.set(input);
	}

	public int getOptic() {
		return optical.get();
	}

	public double getRPM() {
		return optical.getRate() * 60;
	}

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}
}
