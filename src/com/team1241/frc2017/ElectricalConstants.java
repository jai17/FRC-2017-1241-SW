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
	// **************************** CONVEYOR MOTORS ******************************
	// ***************************************************************************
	
	public static final int AGITATOR_MOTOR 									= 5;
	public static final int CONVEYER_MOTOR									= 6;
	
	

}
