package com.team1241.frc2017;

/**
 *        The ElectricalConstants is a mapping from the ports sensors and
 *        actuators are wired into to a variable name. This provides flexibility
 *        changing wiring, makes checking the wiring easier and significantly
 *        reduces the number of magic numbers floating around.
 *        
 *@author Kaveesha Siribaddana
 *@since 11/01/17
 */

public class ElectricalConstants {

	// ***************************************************************************
	// ****************************** DRIVE MOTORS *******************************
	// ***************************************************************************

	public static final int LEFT_DRIVE_FRONT 								= 1;
	public static final int LEFT_DRIVE_BACK 								= 2;

	public static final int RIGHT_DRIVE_FRONT 								= 3;
	public static final int RIGHT_DRIVE_BACK 								= 4;
	
	// ***************************************************************************
	// ***************************** INTAKE MOTORS *******************************
	// ***************************************************************************
	
	public static final int MAIN_INTAKE_ROLLERS								= 0;
	public static final int SIDE_INTAKE_ROLLERS                             = 1;
	
	// ***************************************************************************

	// **************************** SHOOTER MOTORS *******************************
	// ***************************************************************************
	
	public static final int LEFT_SHOOTER_MOTOR									= 7;
	public static final int RIGHT_SHOOTER_MOTOR								= 8;
	
	// ***************************************************************************
	// *************************** OPTICAL ENCODER *******************************
	// ***************************************************************************
	
	public static final int OPTICAL_SENSOR                                  = 9;
	

	// **************************** CONVEYOR MOTORS ******************************
	// ***************************************************************************
	
	public static final int AGITATOR_MOTOR 									= 5;
	public static final int CONVEYOR_MOTOR									= 6;
	
	// ***************************************************************************
	// ******************************* PISTONS ***********************************
	// ***************************************************************************
	
    public static final int CLAW_PISTON_A 									= 0;
    public static final int CLAW_PISTON_B  									= 1;
    
    public static final int INTAKE_PISTON_A									= 2;
    public static final int INTAKE_PISTON_B									= 3;
    
    public static final int HOPPER_PISTON_A                                 = 4;
    public static final int HOPPER_PISTON_B 								= 5;
    
    public static final int GEAR_PISTON_A									= 6;
    public static final int GEAR_PISTON_B									= 7;
	
	// ***************************************************************************
	// *************************** DIGITAL SENSORS *******************************
	// ***************************************************************************
    
    public static final int CONVEYOR_ENCODER_A								= 0;
    public static final int CONVEYOR_ENCODER_B								= 1;
    
    //****************************************************************************
  	//************************ CONVEYOR ENCODER CONSTANTS ************************
  	//****************************************************************************
  	public static final int conveyorPullyRadius = 4;//wheel radius in inches
  	public static final int conveyorPulsePerRotation = 128; //encoder pulse per rotation
  	public static final double conveyorGearRatio = 1/1; //ratio between wheel and encoder
  	public static final double conveyorEncoderPulsePerRot = conveyorPulsePerRotation*conveyorGearRatio; //pulse per rotation * gear ratio
  	public static final double conveyorEncoderDistPerTick =(Math.PI*2*conveyorPullyRadius)/conveyorEncoderPulsePerRot;
  	public static final boolean conveyorEncoderReverse = false;

}
