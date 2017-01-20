package com.team1241.frc2017.auto;

import com.team1241.frc2017.Robot;

import edu.wpi.first.wpilibj.command.InstantCommand;

/**
 *
 */
public class IntakePistonCommands extends InstantCommand {

	private boolean actuate;
	
    public IntakePistonCommands(boolean actuate) {
        super();
        this.actuate = actuate;
    }

    // Called once when the command executes
    protected void initialize() {
    	if(actuate)
    		Robot.intake.extendIntake();
    	else
    		Robot.intake.retractIntake();
    }

}
