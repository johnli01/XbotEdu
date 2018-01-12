package xbot.edubot.subsystems.drive.commands;

import com.google.inject.Inject;

import xbot.common.command.BaseCommand;
import xbot.edubot.operator_interface.OperatorInterface;
import xbot.edubot.subsystems.drive.DriveSubsystem;

public class ArcadeDriveWithJoysticksCommand extends BaseCommand {
	
	DriveSubsystem drive;
	OperatorInterface operate;

	@Inject
	public ArcadeDriveWithJoysticksCommand(DriveSubsystem driveSubsystem, OperatorInterface oi) {
		// TODO Auto-generated constructor stub
		drive = driveSubsystem;
		operate = oi;
		
	}
	
	@Override
	public void initialize() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		
		
		
		// True if Turning
		double x = operate.leftJoystick.getVector().x;
		double y = operate.leftJoystick.getVector().y;
		
		double leftPower = (y + x);
		double rightPower = (y - x);
		
		
		if (leftPower > 1 || leftPower < -1) {
			
			leftPower = leftPower/leftPower;
			rightPower = rightPower/leftPower;
			drive.tankDrive(leftPower,rightPower);
		}
		
		else if (rightPower > 1 || rightPower < -1) {
			
			leftPower= leftPower/rightPower;
			rightPower= rightPower/rightPower;
			drive.tankDrive(leftPower,rightPower);
		}
		
		drive.tankDrive(leftPower,rightPower);
		
	}

}
