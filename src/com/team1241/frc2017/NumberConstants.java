package com.team1241.frc2017;

/**
 * This class is used to save constant values that will be changed every once in awhile. 
 * This can include PID tuned values or autonomous distances, etc...
 *
 *@author Kaveesha Siribaddana
 * @since 11/01/17
 */


public class NumberConstants {
	
	//**************************************************************************
    //*************************** PID VALUES (DRIVE) ***************************
    //**************************************************************************
	
	public static final double pDrive 									 = 0.00;
	public static final double iDrive 									 = 0.00;
	public static final double dDrive 									 = 0.00;
	
	public static final double Drive_Scale 								 = 0.00;
	
	//**************************************************************************
    //**************************** PID VALUES (GYRO) ***************************
    //**************************************************************************
	
	public static final double pGyro 									 = 0.00;
	public static final double iGyro 									 = 0.00;
	public static final double dGyro 									 = 0.00;
	
	//**************************************************************************
    //************************** PID VALUES (SHOOTER) **************************
    //**************************************************************************
	
	public static final double pShooter = 0.00;
	public static final double iShooter = 0.00;
	public static final double dShooter = 0.00;
	
	public static final int[] RPMS = new int[] {4000, 4100, 4250, 4500, 5000};
	public static final double[] POWERS = new double[] {0.6, 0.65, 0.7, 0.78, 0.88};


}
