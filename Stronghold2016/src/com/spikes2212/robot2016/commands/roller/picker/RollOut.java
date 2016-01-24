package com.spikes2212.robot2016.commands.roller.picker;

import edu.wpi.first.wpilibj.command.Command;
import com.spikes2212.robot2016.Permanents;
import com.spikes2212.robot2016.RobotMap;
import com.spikes2212.robot2016.subsystems.roller.Picker;
import static com.spikes2212.robot2016.Robot.*;

/**
 *
 */
public class RollOut extends Command {

    public RollOut() {
    	requires(picker);
    	// Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    picker.roll(-Permanents.PICKER_ROLL_OUT_SPEED);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	picker.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    end();
    }
}
