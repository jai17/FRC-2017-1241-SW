package com.team1241.frc2017.subsystems;

import com.team1241.frc2017.ElectricalConstants;
import com.team1241.frc2017.commands.IntakeCommand;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *@author Kaveesha Siribaddana
 *@since 14/01/17
 */
public class Intake extends Subsystem {

	Spark mainIntakeRollers;
    Spark sideIntakeRollers;
    
    public Intake(){
    	
    	//Initialize Sparks
    	mainIntakeRollers = new Spark(ElectricalConstants.MAIN_INTAKE_ROLLERS);
    	sideIntakeRollers = new Spark(ElectricalConstants.SIDE_INTAKE_ROLLERS);
    	
    }
    
    public void intake(double speed){
    	mainIntakeRollers.set(speed);
    	sideIntakeRollers.set(speed);
    }
    
    public void outtake(double speed){
    	mainIntakeRollers.set(speed);
    }

    public void initDefaultCommand() {
        setDefaultCommand(new IntakeCommand());
    }
}

