package com.team1241.frc2017.auto;

import com.team1241.frc2017.Robot;

import edu.wpi.first.wpilibj.command.InstantCommand;

/**
 *
 */
public class HopperPistonCommand extends InstantCommand {

	private boolean actuate;
	
    public HopperPistonCommand(boolean actuate) {
        super();
        this.actuate = actuate;
    }

    // Called once when the command executes
    protected void initialize() {
    	if(actuate)
    		Robot.hopper.ExtendHopper();
    	else
    		Robot.hopper.RetractHopper();
    }

}
