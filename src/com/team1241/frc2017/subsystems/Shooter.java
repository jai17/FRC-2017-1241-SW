package com.team1241.frc2017.subsystems;

import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;
import com.ctre.CANTalon.FeedbackDeviceStatus;
import com.ctre.CANTalon.TalonControlMode;
import com.team1241.frc2017.ElectricalConstants;
import com.team1241.frc2017.NumberConstants;
import com.team1241.frc2017.commands.ShooterCommand;
import com.team1241.frc2017.pid.PIDController;
import com.team1241.frc2017.utilities.LineRegression;

import edu.wpi.first.wpilibj.Counter;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * @author Kaveesha Siribaddana
 * @since 14/01/17
 */
public class Shooter extends Subsystem {

	CANTalon rightMaster;
	CANTalon leftSlave;

	Counter optical;

	public PIDController shooterPID;

	boolean shooterState;

	LineRegression calcLine = new LineRegression();

	private double kForward;
	private double bForward;
	


	public Shooter() {
		rightMaster = new CANTalon(ElectricalConstants.RIGHT_SHOOTER_MOTOR);
		rightMaster.reverseSensor(false);
		
		leftSlave = new CANTalon(ElectricalConstants.LEFT_SHOOTER_MOTOR);
		leftSlave.changeControlMode(TalonControlMode.Follower);
		leftSlave.set(ElectricalConstants.RIGHT_SHOOTER_MOTOR);
		leftSlave.reverseOutput(true);
		
		rightMaster.setProfile(0);
		rightMaster.setPID(0, 0, 0);
		rightMaster.setF(0);
		
		rightMaster.changeControlMode(TalonControlMode.Speed);

		optical = new Counter();
		optical.setUpSource(ElectricalConstants.OPTICAL_SENSOR);
		optical.setUpDownCounterMode();
		optical.setDistancePerPulse(1);

		shooterPID = new PIDController(NumberConstants.pShooter, NumberConstants.iShooter, NumberConstants.dShooter);

		shooterState = false;

		calcLine.setValues(NumberConstants.RPMS_SHOOTER, NumberConstants.RPMS_SHOOTER);
		kForward = calcLine.getSlope();
		bForward = calcLine.getIntercept();
	}

	public void initDefaultCommand() {
		setDefaultCommand(new ShooterCommand());
	}

	// SHOOTER COMMANDS

	public boolean getShooterState() {
		return shooterState;
	}

	public void setShooterState(boolean state) {
		shooterState = state;
	}

	public void setRPM(double rpm) {
		double output = shooterPID.calcPID(rpm, getRPM(), 50);

		setShooter(rpm * kForward + bForward + output);
	}
	
	public void useFeedForward(double rpm) {
		rightMaster.changeControlMode(TalonControlMode.PercentVbus);
		setShooter(rpm * kForward + bForward);
	}

	public void setShooter(double input) {
		rightMaster.changeControlMode(TalonControlMode.Speed);
		rightMaster.set(input);
	}
	
	public void stopShooter(){
		rightMaster.changeControlMode(TalonControlMode.PercentVbus);
		rightMaster.set(0);
	}	
	
	public int getOptic() {
		return optical.get();
	}
	
	public double getRPM() {
		return optical.getRate() * 60;
	}

	// SHOOTER PID

	public void resetPID() {
		shooterPID.resetPID();
	}
}
