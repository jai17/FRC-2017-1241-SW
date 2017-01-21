package com.team1241.frc2017.auto;

import com.team1241.frc2017.Robot;

import edu.wpi.first.wpilibj.command.InstantCommand;

/**
 *
 */
public class ConveyorPistonCommand extends InstantCommand {

	private boolean actuate;
	
    public ConveyorPistonCommand(boolean actuate) {
        super();
        this.actuate = actuate;
    }

    // Called once when the command executes
    protected void initialize() {
    	if(actuate)
    		Robot.conveyor.openClaw();
    	else
    		Robot.conveyor.closeClaw();
    }

}
