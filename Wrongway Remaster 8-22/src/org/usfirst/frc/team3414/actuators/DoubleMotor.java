package org.usfirst.frc.team3414.actuators;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
//When dealing with intake, the old code references intake motor one, in this case it's the front talon
public class DoubleMotor {
	public TalonSRX front; //MotorOne (Intake)
	public TalonSRX rear; //MotorTwo (Intake)
	double globalspeed;
	double globalspeedfront;
	double globalspeedrear;
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
	public void setSpeedIntake(double speedLeft, double speedRight) { // putting this in as a homage to when TalonSRX or Talon or whatever was called
											// had an actual good method name

		front.set(ControlMode.PercentOutput, speedLeft);
		globalspeedfront = speedLeft;
		rear.set(ControlMode.PercentOutput, speedRight);
		globalspeedfront = speedRight;
	}
	public void setSpeed(double speed) {
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
		return globalspeedfront;
	}
	public double getRearSpeed() {
		return globalspeedrear;
	}
	public double getSpeed() {
		return globalspeed;
	}
	public void spin() {
		front.set(ControlMode.PercentOutput, .5);
		globalspeedfront = .5;
		rear.set(ControlMode.PercentOutput, -.5);
		globalspeedfront = -.5;
	}
	public void teleopInit() {
		front.setNeutralMode(NeutralMode.Brake);
		rear.setNeutralMode(NeutralMode.Brake);

	}
	public void intake() {
		front.set(ControlMode.PercentOutput, -1);
		rear.set(ControlMode.PercentOutput, -.7);
	}
	public void outtakePlace() {
		front.set(ControlMode.PercentOutput, .38);
		rear.set(ControlMode.PercentOutput, .38);
}
	public void outtake() {
		front.set(ControlMode.PercentOutput, .45);
		rear.set(ControlMode.PercentOutput, .45);
	}
	public void off() {
		front.set(ControlMode.PercentOutput, 0);
		rear.set(ControlMode.PercentOutput, 0);
	}
	
}
