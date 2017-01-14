package com.team1241.frc2017.subsystems;

import com.team1241.frc2017.ElectricalConstants;

import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Conveyor extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	// Victors

	Victor agitator;
	Victor conveyor;
	
	public Conveyor(){
		agitator = new Victor(ElectricalConstants.AGITATOR_MOTOR);
	}
	
	
	
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
	}
}
