package com.team1241.frc2017.subsystems;

import com.team1241.frc2017.ElectricalConstants;
import com.team1241.frc2017.NumberConstants;
import com.team1241.frc2017.commands.TankDrive;
import com.team1241.frc2017.pid.PIDController;
import com.team1241.frc2017.utilities.Nav6;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 * @author Kaveesha Siribaddana
 * @since 11/01/17
 *
 */
public class Drivetrain extends Subsystem {

	/** Drive Talons */
	private CANTalon leftDriveFront;
	private CANTalon leftDriveBack;
	private CANTalon rightDriveFront;
	private CANTalon rightDriveBack;

	/** Encoders on the drive */
	private Encoder leftDriveEncoder;
	private Encoder rightDriveEncoder;

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
		leftDriveFront = new CANTalon(ElectricalConstants.LEFT_DRIVE_FRONT);
		leftDriveBack = new CANTalon(ElectricalConstants.LEFT_DRIVE_BACK);

		rightDriveFront = new CANTalon(ElectricalConstants.RIGHT_DRIVE_FRONT);
		rightDriveBack = new CANTalon(ElectricalConstants.RIGHT_DRIVE_BACK);

		// Initialize Encoders
		leftDriveEncoder = new Encoder(ElectricalConstants.LEFT_DRIVE_ENCODER_A,
				ElectricalConstants.LEFT_DRIVE_ENCODER_B, ElectricalConstants.leftDriveTrainEncoderReverse,
				Encoder.EncodingType.k4X);

		leftDriveEncoder.setDistancePerPulse(ElectricalConstants.driveEncoderDistPerTick);

		rightDriveEncoder = new Encoder(ElectricalConstants.RIGHT_DRIVE_ENCODER_A,
				ElectricalConstants.RIGHT_DRIVE_ENCODER_B, ElectricalConstants.rightDriveTrainEncoderReverse,
				Encoder.EncodingType.k4X);

		rightDriveEncoder.setDistancePerPulse(ElectricalConstants.driveEncoderDistPerTick);

		// Initialize PID controllers
		drivePID = new PIDController(NumberConstants.pDrive, NumberConstants.iDrive, NumberConstants.dDrive);
		gyroPID = new PIDController(NumberConstants.pGyro, NumberConstants.iGyro, NumberConstants.dGyro);
	}

	public void initDefaultCommand() {
		setDefaultCommand(new TankDrive());
	}

	public void runLeftDrive(double power) {
		leftDriveFront.set(power);
		leftDriveBack.set(power);
	}

	public void runRightDrive(double power) {
		rightDriveFront.set(power);
		rightDriveBack.set(power);
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
	
	//ENCODER FUNCTIONS
	
	public double getLeftEncoderDist() {
		return leftDriveEncoder.getDistance();
	}

	public double getRightEncoderDist() {
		return rightDriveEncoder.getDistance();
	}

	public double getAverageDistance() {
		return (getLeftEncoderDist() + getRightEncoderDist()) / 2;
	}
	
	public void resetEncoders() {
		leftDriveEncoder.reset();
		rightDriveEncoder.reset();
	}

	// GYRO FUNCTIONS

	public boolean gyroConnected() {
		return gyro.isConnected();
	}
	
	public boolean gyroCalibrating() {
		return gyro.isCalibrating();
	}

	public double getYaw() {
		return gyro.getYaw() / 88.5 * 90;
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
