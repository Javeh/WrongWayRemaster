package org.usfirst.frc.team3414.actuators;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.Timer;

public class Angler {
	DoubleMotor intake;
	TalonSRX angler;
	double anglerSpeed;
	double intakeSpeed;
	public void init() {
		intake = new DoubleMotor(ActuatorConfig.INTAKE_ID_1,ActuatorConfig.INTAKE_ID_2);
		angler = new TalonSRX(ActuatorConfig.ANGLER_ID);
		System.out.println("Angler online!");
	}
	public void goUp() {
		angler.set(ControlMode.PercentOutput, .45);
		anglerSpeed = .45;
	}
	public void goDown() {
		angler.set(ControlMode.PercentOutput, .45);
		anglerSpeed = .45;

	}
	public void stopAngler() {
		angler.set(ControlMode.PercentOutput, 0);
		anglerSpeed = 0;
	}

	public void unjam() {
		intake.spin();
	}
	public void autonGoUp() {
		goUp();
		Timer.delay(1.2);
	}
	public void autonGoDown() {
		goDown();
		Timer.delay(1.2);
	}
	public double getAnglerSpeed() {
		return anglerSpeed;
	}
	public double getIntakeSpeed() {
		return intake.getSpeed();
	}
	public void off() {
		angler.set(ControlMode.PercentOutput, 0);
		anglerSpeed = 0;
	}
	public void setSpeed(double speed) {		
		angler.set(ControlMode.PercentOutput, speed);
	}
	public void intakeSetSpeed(double speed) {
		intake.setSpeed(speed);
	}
	public void outtakePlace() {
		intake.outtakePlace();
	}
	public void outtake() {
		intake.outtake();
	}
	public void intake() {
		intake.intake();
	}
	public void intakeOff() {
		intake.off();
	}
	}

