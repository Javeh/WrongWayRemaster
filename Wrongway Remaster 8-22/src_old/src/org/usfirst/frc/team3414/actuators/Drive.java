package org.usfirst.frc.team3414.actuators;

public class Drive {
	DoubleMotor left;
	DoubleMotor right;

	public void init() {
		left = new DoubleMotor(ActuatorConfig.LEFT_FRONT_ID, ActuatorConfig.LEFT_REAR_ID);
		right = new DoubleMotor(ActuatorConfig.RIGHT_FRONT_ID, ActuatorConfig.RIGHT_REAR_ID);
		
		System.out.println("Drivetrain Online!");
	}

	public void teleop(double leftSpeed, double rightSpeed) {
		left.setSpeed(leftSpeed);
		right.setSpeed(rightSpeed);

	}
	public void auton() {
		
	}

	public void autonInit() {
		left.autonInit();
		right.autonInit();
	}

	public void teleopInit() {
		left.teleopInit();
		right.teleopInit();
	}


}
