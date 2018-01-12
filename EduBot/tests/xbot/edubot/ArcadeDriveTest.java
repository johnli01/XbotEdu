package xbot.edubot;

import static org.junit.Assert.*;

import org.junit.Test;

import xbot.common.command.BaseCommand;
import xbot.common.controls.sensors.MockJoystick;
import xbot.edubot.operator_interface.OperatorInterface;
import xbot.edubot.subsystems.drive.commands.ArcadeDriveWithJoysticksCommand;

public class ArcadeDriveTest extends BaseDriveTest {

	@Test
	public void test() {
		OperatorInterface oi = this.injector.getInstance(OperatorInterface.class);
		
		BaseCommand command = injector.getInstance(ArcadeDriveWithJoysticksCommand.class);
			
		MockJoystick left = (MockJoystick)oi.leftJoystick;
		
		command.initialize();
		
		command.execute();
		this.assertDrive(0, 0);
		
		left.setY(2.0);
		left.setX(2.0);
		command.execute();
		this.assertDrive(1, 1);
		
		left.setY(0.0);
		left.setX(1.0);
		command.execute();
		this.assertDrive(1, -1);
		
		left.setY(0.8);
		left.setX(0.8);
		command.execute();
		assertTrue(this.mockRobotIO.getPWM(1) > this.mockRobotIO.getPWM(2));
	}
}