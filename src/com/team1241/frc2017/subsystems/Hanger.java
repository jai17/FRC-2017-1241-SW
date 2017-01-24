package com.team1241.frc2017.subsystems;
import com.ctre.CANTalon;
import com.team1241.frc2017.ElectricalConstants;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Hanger extends Subsystem {
    
	//*********************************************************************************************
	//*********************************** VARIABLES ***********************************************
	//*********************************************************************************************
	
	CANTalon hangmotorleft;
	CANTalon hangmotorright;
	
	DoubleSolenoid hangpiston;
	
	Servo fingerservo;
	
	public Hanger(){
		
		hangmotorleft = new CANTalon(ElectricalConstants.LEFT_HANG_MOTOR);
		hangmotorright = new CANTalon(ElectricalConstants.RIGHT_HANG_MOTOR);
		
		hangpiston = new DoubleSolenoid(ElectricalConstants.HANG_PISTON_A,
										ElectricalConstants.HANG_PISTON_B);
	
		fingerservo = new Servo(ElectricalConstants.HANG_SERVO_MOTOR);
	}
	
	public void HangMotor(double input){
	    	hangmotorleft.set(input);
	    	hangmotorright.set(input);
    }
	
	public void hangPiston(){
    	hangpiston.set(DoubleSolenoid.Value.kForward);
    }		
		
	public void retractPiston(){
    	hangpiston.set(DoubleSolenoid.Value.kReverse);
    }
	
	public void moveservo(double input){
		fingerservo.set(input);
		
	}
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

