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

	/**
	 * Sets the command TankDrive as the default command for this subsystem.
	 */
	public void initDefaultCommand() {
		setDefaultCommand(new TankDrive());
	}

	/**
	 * Sends supplied power value to the left drive motors.
	 *
	 * @param power
	 *            Power value sent to motors (-1.0 to 1.0)
	 */
	public void runLeftDrive(double power) {
		leftDriveFront.set(power);
		leftDriveBack.set(power);
	}

	/**
	 * Sends supplied power value to the right drive motors.
	 *
	 * @param poewr
	 *            Power value sent to motors (-1.0 to 1.0)
	 */
	public void runRightDrive(double power) {
		rightDriveFront.set(power);
		rightDriveBack.set(power);
	}
	
	/**
	 * This function returns the distance traveled from the left encoder in
	 * inches.
	 *
	 * @return Returns distance traveled by encoder in inches
	 */
	public double getLeftEncoderDist() {
		return leftDriveEncoder.getDistance();
	}

	/**
	 * This function returns the distance traveled from the right encoder in
	 * inches.
	 *
	 * @return Returns distance traveled by encoder in inches
	 */
	public double getRightEncoderDist() {
		return rightDriveEncoder.getDistance();
	}

	/**
	 * Gets the average distance between both encoders.
	 *
	 * @return Returns the average distance
	 */
	public double getAverageDistance() {
		return (getLeftEncoderDist() + getRightEncoderDist()) / 2;
	}
}

