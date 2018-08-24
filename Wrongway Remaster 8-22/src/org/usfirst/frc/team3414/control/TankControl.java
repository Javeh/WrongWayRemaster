package org.usfirst.frc.team3414.control;

import org.usfirst.frc.team3414.actuators.ActuatorConfig;
import org.usfirst.frc.team3414.actuators.Drive;

import edu.wpi.first.wpilibj.Joystick;

public class TankControl {
	Joystick leftJoy;
	Joystick rightJoy;
	Drive drive = new Drive();
	public void init() {
	leftJoy = new Joystick(ActuatorConfig.LEFT_JOY_ID);
	rightJoy = new Joystick(ActuatorConfig.RIGHT_JOY_ID);
	System.out.println("Joysticks online!");
	drive.init();
	
	}
	public void teleop() {
		drive.teleop(leftJoy.getY(), rightJoy.getY());
	}
	public void auton() {
		
	}
	public void goStraight(double distance) {
		
	}
	public double getLeftJoy() {
		return leftJoy.getY();
	}
	public double getRightJoy() {
		return rightJoy.getY();
	}
	public void autonDrive(double left, double right) {
		drive.teleop(left, right);
	}
	public void stop() {
		drive.teleop(0, 0);
	}
	public void teleopInit() {
		drive.teleopInit();
	}
	public void autonInit() {
		drive.autonInit();
	}
}
 