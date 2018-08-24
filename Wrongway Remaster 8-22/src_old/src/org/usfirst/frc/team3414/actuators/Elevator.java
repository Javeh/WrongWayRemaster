package org.usfirst.frc.team3414.actuators;

import edu.wpi.first.wpilibj.DigitalInput;

public class Elevator {
	DigitalInput liftLimit;
	DoubleMotor liftMotors;

	public void init() {
		liftLimit = new DigitalInput(ActuatorConfig.LIMIT_ID);
		liftMotors = new DoubleMotor(ActuatorConfig.LIFT_ID_1,ActuatorConfig.LIFT_ID_2);
		System.out.println("Elevator Online!");
	}

	public void goUp() {
		if (liftLimit.get() != true) { //when the switch is not tripped

			liftMotors.setSpeed(1);
		}
	}

	public void goDown() {
		if (liftLimit.get()) { // when the switch is tripped and this method is running, go down.
			liftMotors.setSpeed(-1);

		}
	}
	public void setSpeed(double speed) {
		liftMotors.setSpeed(speed);
	}
	public void off() {
		liftMotors.setSpeed(0);
	}
	public double getSpeed() {
		return liftMotors.getSpeed();
	}

	public void stop() {
		
	}
	

}
