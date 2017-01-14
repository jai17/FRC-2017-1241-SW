package com.team1241.frc2017.subsystems;

import com.team1241.frc2017.ElectricalConstants;
import com.team1241.frc2017.NumberConstants;
import com.team1241.frc2017.pid.PIDController;
import com.team1241.frc2017.utilities.LineRegression;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Conveyor extends Subsystem {
    
    //Put methods for controlling this subsystem
    // here. Call these from Commands.
	// Victors

	Victor agitator;
	Victor conveyor;
	
	DoubleSolenoid claw;
	
	Encoder conveyorEncoder;
	
	public PIDController conveyorPID;
	
	LineRegression calcline = new LineRegression();
	
	private double kforward;
	private double bforward;
	
	
	public Conveyor(){
		agitator = new Victor(ElectricalConstants.AGITATOR_MOTOR);
		conveyor = new Victor(ElectricalConstants.CONVEYOR_MOTOR);
		
		claw = new DoubleSolenoid(ElectricalConstants.CLAW_PISTON_A,
								  ElectricalConstants.CLAW_PISTON_B);
		
		conveyorEncoder = new Encoder(ElectricalConstants.CONVEYOR_ENCODER_A,
						   ElectricalConstants.CONVEYOR_ENCODER_B,
						   ElectricalConstants.conveyorEncoderReverse,
						   Encoder.EncodingType.k4X);
		
		conveyorEncoder.setDistancePerPulse(ElectricalConstants.conveyorEncoderDistPerTick);
		
		conveyorPID = new PIDController(NumberConstants.pConveyor,
										NumberConstants.iConveyor,
										NumberConstants.dConveyor);
		
		
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
	}
    
    public void agitatorMotor(double input){
    	agitator.set(input);
    }
    
    public void conveyorMotor(double input){
    	conveyor.set(input);
    }
    
    public void extendPiston(){
    	claw.set(DoubleSolenoid.Value.kForward);
    }
    
    public void retractPiston(){
    	claw.set(DoubleSolenoid.Value.kReverse);
    }
    
    public double getConveyorEncoder(){
    	return conveyorEncoder.getDistance();
    }
    
    public double getConveyorRate(){
    	return conveyorEncoder.getRate() * 60;
    }
    
    public void resetConveyorEncoder(){
    	conveyorEncoder.reset();
    }
    
}