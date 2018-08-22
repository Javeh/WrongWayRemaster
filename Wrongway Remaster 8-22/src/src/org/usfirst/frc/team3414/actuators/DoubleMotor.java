package org.usfirst.frc.team3414.actuators;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

public class DoubleMotor {
	public TalonSRX front;
	public TalonSRX rear;
	double globalspeed;
	public DoubleMotor(int frontID, int rearID) {
		front = new TalonSRX(frontID);
		rear = new TalonSRX(rearID);
		front.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 10);
		rear.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 10);
		front.getSensorCollection().setQuadraturePosition(0, 10);
		rear.getSensorCollection().setQuadraturePosition(0, 10);
	}
	public void autonInit() {
		//Thank you mentor Francis for the reset code
		front.getSensorCollection().setQuadraturePosition(0, 10);
		rear.getSensorCollection().setQuadraturePosition(0, 10);
		front.setNeutralMode(NeutralMode.Brake);
		rear.setNeutralMode(NeutralMode.Brake);
	}
	public int getEncoderFront() {
		return front.getSensorCollection().getQuadraturePosition();
	}
	public void autonInitFront() {
		front.getSensorCollection().setQuadraturePosition(0, 10);

	}
	public void autonInitRear() {
		rear.getSensorCollection().setQuadraturePosition(0, 10);
	}
	public void setSpeed(double speed) { // putting this in as a homage to when TalonSRX or Talon or whatever was called
											// had an actual good method name
		globalspeed = speed;
		front.set(ControlMode.PercentOutput, speed);
		rear.set(ControlMode.PercentOutput, speed);
	}

	public void setInverted(boolean invert) {
		front.setInverted(invert);
		rear.setInverted(invert);
	}

	public int getFrontID() {
		return front.getDeviceID();
	}

	public int getRearID() {
		return rear.getDeviceID();
	}
	public void free() {
		//front.reset
	}
	public double getFrontSpeed() {
		return globalspeed;
	}
	public double getRearSpeed() {
		return globalspeed;
	}
	public double getSpeed() {
		return globalspeed;
	}
	public void spin() {
		front.set(ControlMode.PercentOutput, -1);
		rear.set(ControlMode.PercentOutput, 1);
	}
	public void teleopInit() {
		front.setNeutralMode(NeutralMode.Brake);
		rear.setNeutralMode(NeutralMode.Brake);

	}
	
}
