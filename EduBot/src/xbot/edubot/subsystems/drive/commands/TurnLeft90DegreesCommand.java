package xbot.edubot.subsystems.drive.commands;

import com.google.inject.Inject;

import xbot.common.command.BaseCommand;
import xbot.edubot.rotation.MockHeadingSensor;
import xbot.edubot.subsystems.drive.DriveSubsystem;

public class TurnLeft90DegreesCommand extends BaseCommand {
	
	DriveSubsystem drive;
	double goal;
	double oldError;
	double changeInError;
	
	
	@Inject
	public TurnLeft90DegreesCommand(DriveSubsystem driveSubsystem) {
		this.drive = driveSubsystem;
	}
	
	@Override
	public void initialize() {
		// TODO Auto-generated method stub
		goal = drive.gyro.getYaw() + 90;
		if	(goal > 180) {
			goal -= 360;
		}
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
	    double error = Math.abs(goal - drive.gyro.getYaw());
	    double changeInError = oldError - error;
	    
		double power = (.06 * error) - (0.45 * changeInError);
				
		drive.tankDrive(-(power), power);
		
		oldError = error;
	}
	
	@Override
	public boolean isFinished() {
		// TODO Auto-generated method stub
			
		 if (Math.abs(goal - drive.gyro.getYaw()) <= 0.1) {
			 if ((changeInError <= 0.1) && (changeInError >= -0.1)) {
			return true;
			 } 
		 }
		return false;
	}
}
