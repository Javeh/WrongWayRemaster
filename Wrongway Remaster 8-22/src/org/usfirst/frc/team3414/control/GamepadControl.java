package org.usfirst.frc.team3414.control;

import org.usfirst.frc.team3414.actuators.ActuatorConfig;
import org.usfirst.frc.team3414.actuators.Angler;
import org.usfirst.frc.team3414.actuators.Elevator;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class GamepadControl {
	Angler angler = new Angler();
	Elevator elevator = new Elevator();
	Joystick pad;
	boolean recordboolean = false;
	public void init() {
		pad = new Joystick(GamepadConfig.GAMEPAD_CHANNEL);
		angler.init();
		elevator.init();
	}
	public void auton() {
	}
	public boolean isRecording() {
		if(pad.getRawButton(GamepadConfig.RECORD_BUTTON)) {
			recordboolean = true;
			SmartDashboard.putString("Recording:", "This teleop session will be recorded under the file name" + ActuatorConfig.autoFile);
		}
		return recordboolean;
		
	}
	public void teleop() {
		if(pad.getRawButton(GamepadConfig.INTAKE_BUTTON)) {
			angler.intake();
		}
		else if (pad.getRawButton(GamepadConfig.SPIT_BUTTON)) {
			angler.outtake();
		}
		else if(pad.getRawButton(GamepadConfig.UNJAM_BUTTON)) {
			angler.unjam();
		}
/*		if(pad.getRawButton((GamepadConfig.ANGLE_DOWN_BUTTON))) {
			angler.goDown();
	}*/	
		if ((pad.getPOV() == 0) || (pad.getPOV() == 45) || (pad.getPOV() == 315)){
			angler.goUp();
		}
		else if ((pad.getPOV() == 180) || (pad.getPOV() == 225) || (pad.getPOV() == 135)) {
			angler.goDown();
		}
	
		else if(pad.getRawButton((GamepadConfig.ANGLE_UP_BUTTON))) {
			angler.goUp();
		}
		else {
			angler.off();
		}
		if(pad.getRawButton((GamepadConfig.DOWN_BUTTON))) {
			elevator.goDown();
		}
		else if(pad.getRawButton((GamepadConfig.UP_BUTTON))) {
			elevator.goUp();
		}
		else {
			elevator.off();
		}
		
	}
	public void elevatorSetSpeed(double speed) {
		elevator.setSpeed(speed);
	}
	public double getElevatorSpeed() {
		return elevator.getSpeed();
	}
	public double getAnglerSpeed(){
		return angler.getAnglerSpeed();
	}
	public double getIntakeSpeed() {
		return angler.getIntakeSpeed();
	}
	public void stop() {
		elevator.off();
		angler.off();
		angler.intakeOff();
	}
	public void anglerSetSpeed(double speed) {
		angler.setSpeed(speed);
	}
	public void intakeSetSpeed(double speed) {
		angler.intakeSetSpeed(speed);
	}
}

/*	(gamepad.getButtonState(1))
//				&& ActuatorConfig.getInstance().talonIntakeAngler().getSensorCollection().getQuadraturePosition() < -150)
//				!(ActuatorConfig.getInstance().talonIntakeAngler().getSensorCollection().isFwdLimitSwitchClosed()))
		{
			ActuatorConfig.getInstance().getMotorIntakeAngler().setSpeed(.45);//0.45, 40
*/
