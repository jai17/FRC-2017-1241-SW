package com.team1241.frc2017.subsystems;

import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;
import com.ctre.CANTalon.FeedbackDeviceStatus;
import com.ctre.CANTalon.TalonControlMode;
import com.team1241.frc2017.ElectricalConstants;
import com.team1241.frc2017.NumberConstants;
import com.team1241.frc2017.commands.TankDrive;
import com.team1241.frc2017.pid.PIDController;
import com.team1241.frc2017.utilities.Nav6;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 * @author Kaveesha Siribaddana
 * @since 11/01/17
 *
 */
public class Drivetrain extends Subsystem {

	/** Drive Talons */
	private CANTalon leftMaster;
	private CANTalon leftSlave;
	private CANTalon rightMaster;
	private CANTalon rightSlave;

	/** Encoders on the drive */
	private boolean leftEncoderConnected = false;
	private boolean rightEncoderConnected = false;

	/** Gyro on the drive */
	private SerialPort serialPort;
	private Nav6 gyro;

	/** The drive PID controller. */
	public PIDController drivePID;

	/** The gyro PID conteroller. */
	public PIDController gyroPID;

	/**
	 * Instantiates a new drivetrain subsystem, this includes initializing all
	 * components related to the subsystem
	 */
	public Drivetrain() {
		try {
			serialPort = new SerialPort(57600, SerialPort.Port.kOnboard);

			// You can add a second parameter to modify the
			// update rate (in hz) from 4 to 100. The default is 100.
			// If you need to minimize CPU load, you can set it to a
			// lower value, as shown here, depending upon your needs.

			// You can also use the IMUAdvanced class for advanced
			// features.

			byte update_rate_hz = 50;
			gyro = new Nav6(serialPort, update_rate_hz);

			if (!gyro.isCalibrating()) {
				Timer.delay(0.3);
				gyro.zeroYaw();
			}
		} catch (Exception e) {
			gyro = null;
		}

		// Initialize Talons
		leftMaster = new CANTalon(ElectricalConstants.LEFT_DRIVE_FRONT);
		leftMaster.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Relative);
		leftMaster.reverseSensor(false);
		
		leftSlave = new CANTalon(ElectricalConstants.LEFT_DRIVE_BACK);
		leftSlave.changeControlMode(TalonControlMode.Follower);
		leftSlave.set(ElectricalConstants.LEFT_DRIVE_FRONT);

		rightMaster = new CANTalon(ElectricalConstants.RIGHT_DRIVE_FRONT);
		rightMaster.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Relative);
		rightMaster.reverseSensor(false);
		
		rightSlave = new CANTalon(ElectricalConstants.RIGHT_DRIVE_BACK);
		rightSlave.changeControlMode(TalonControlMode.Follower);
		rightSlave.set(ElectricalConstants.RIGHT_DRIVE_FRONT);

		FeedbackDeviceStatus leftStatus = leftMaster.isSensorPresent(FeedbackDevice.CtreMagEncoder_Relative);
		FeedbackDeviceStatus rightStatus = rightMaster.isSensorPresent(FeedbackDevice.CtreMagEncoder_Relative);
		
		switch(leftStatus){
		case FeedbackStatusPresent:
			leftEncoderConnected = true;
			break;
		case FeedbackStatusNotPresent:
			break;
		case FeedbackStatusUnknown:
			break;
		}
		
		switch(rightStatus){
		case FeedbackStatusPresent:
			rightEncoderConnected = true;
			break;
		case FeedbackStatusNotPresent:
			break;
		case FeedbackStatusUnknown:
			break;
		}
		
		// Initialize PID controllers
		drivePID = new PIDController(NumberConstants.pDrive, NumberConstants.iDrive, NumberConstants.dDrive);
		gyroPID = new PIDController(NumberConstants.pGyro, NumberConstants.iGyro, NumberConstants.dGyro);
	}

	public void initDefaultCommand() {
		setDefaultCommand(new TankDrive());
	}

	public void runLeftDrive(double power) {
		leftMaster.set(power);
		leftSlave.set(power);
	}

	public void runRightDrive(double power) {
		rightMaster.set(power);
		rightSlave.set(power);
	}

	public void driveStraight(double setPoint, double speed, double setAngle, double epsilon) {
		double output = drivePID.calcPIDDrive(setPoint, getAverageDistance(), epsilon);
		double angle = gyroPID.calcPID(setAngle, getYaw(), epsilon);

		runLeftDrive((output + angle) * speed);
		runRightDrive((-output + angle) * speed);
	}

	public void driveAngle(double setAngle, double speed) {
		double angle = gyroPID.calcPID(setAngle, getYaw(), 1);

		runLeftDrive(speed + angle);
		runRightDrive(-speed + angle);
	}

	public void turnDrive(double setAngle, double speed, double epsilon) {
		double angle = gyroPID.calcPID(setAngle, getYaw(), epsilon);

		runLeftDrive(angle * speed);
		runRightDrive(angle * speed);
	}
	
	// ENCODER FUNCTIONS
	
	public double getLeftDriveEncoder(){
		return leftMaster.getPosition();
	}
	
	public double getRightDriveEncoder(){
		return rightMaster.getPosition();
	}
	
	public double getAverageDistance(){
		return (getLeftDriveEncoder()+getRightDriveEncoder())/2;
	}
	
	public boolean isLeftEncoderConnected(){
		return leftEncoderConnected;
	}
	
	public boolean isRightEncoderConnected(){
		return rightEncoderConnected;
	}
	
	public void resetEncoders(){
		leftMaster.setPosition(0);
		rightMaster.setPosition(0);
	}
	

	// GYRO FUNCTIONS

	public boolean gyroConnected() {
		return gyro.isConnected();
	}

	public boolean gyroCalibrating() {
		return gyro.isCalibrating();
	}

	public double getYaw() {
		return gyro.getYaw();
	}

	public double getPitch() {
		return gyro.getPitch();
	}

	public double getRoll() {
		return gyro.getRoll();
	}

	public double getCompassHeading() {
		return gyro.getCompassHeading();
	}

	public void resetGyro() {
		gyro.zeroYaw();
	}

	public void reset() {
		resetEncoders();
		resetGyro();
	}
}
