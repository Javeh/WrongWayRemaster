package org.usfirst.frc.team3414.actuators;

import edu.wpi.first.wpilibj.DriverStation;

public class ActuatorConfig {
	public static final int LEFT_JOY_ID = 0;
	public static final int RIGHT_JOY_ID = 1;
	public static final int LEFT_FRONT_ID = 1;
	public static final int LEFT_REAR_ID = 0;
	public static final int RIGHT_FRONT_ID = 3;
	public static final int RIGHT_REAR_ID = 2;
	public static final int LIMIT_ID = 4;
	public static final int LIFT_ID_1 = 9;
	public static final int LIFT_ID_2 = 10;
	public static final int INTAKE_ID_1 = 7;
	public static final int INTAKE_ID_2 = 8;
	public static final int ANGLER_ID = 6;
	public static int autonNumber = 1;

	/* imported code
	 * //talons
		talonLeftFront = new TalonSRX(1);//3  1   3
		talonLeftBack = new TalonSRX(0);//4  2   2
		talonRightFront = new TalonSRX(3);//1  3   4
		talonRightBack = new TalonSRX(2);//0  4   1

		talonIntakeOne = new TalonSRX(7);
		talonIntakeTwo = new TalonSRX(8);
		
		talonIntakeAngler = new TalonSRX(6);
		
		talonLiftOne = new TalonSRX(9);
		talonLiftTwo = new TalonSRX(10);
		
		talonWingOne = new TalonSRX(4);
		talonWingTwo = new TalonSRX(5);
		
		servoWingOne = new Servo(0);
		servoWingTwo = new Servo(1);
		
		limitSwitchWings = new LimitSwitchDigital(0, false);
		limitSwitchBottomLift = new LimitSwitchDigital(1, false);
	 */
/*
	public int getLeftFront() {
		return LEFT_FRONT_ID;
	}
	public int getRightFront() {
		return RIGHT_FRONT_ID;
	}
	public int getLeftRear() {
		return RIGHT_FRONT_ID;
	}
	*/
}
