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
		//TODO currently hardcoded to record the teleop with just the layout being relevant.
		if(pad.getRawButton(GamepadConfig.RECORD_BUTTON)) {}
			recordboolean = true;
		//	SmartDashboard.putString("Recording:", "This teleop session will be recorded under the file name" + ActuatorConfig.autoFile);
		return recordboolean;
		
	}
	public void teleop() {
		if(pad.getRawButton(GamepadConfig.INTAKE_BUTTON)) {
			angler.intake();
		}
		else if (pad.getRawButton(GamepadConfig.SPIT_BUTTON)) {
			angler.outtake();
		}
		else if ((pad.getRawButton(GamepadConfig.SPIT_BUTTON)) && (pad.getRawButton(GamepadConfig.PLACE_BUTTON))) {
			angler.outtakePlace();
		}
		
		else if(pad.getRawButton(GamepadConfig.UNJAM_BUTTON)) {
			angler.unjam();
		}
		else {
			angler.intakeOff();
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
		else {
			angler.off();
		}
		if(pad.getRawButton((GamepadConfig.DOWN_BUTTON))) {
			elevator.goDown();
		}
		else if(pad.getRawButton((GamepadConfig.UP_BUTTON))) { //TODO original code uses talon limit. The goUp method uses a DI
			elevator.goUp();
		}
		else {
			elevator.off();
		}
	}
//		if (gamepad.getButtonState(5) &&
//				!(ActuatorConfig.getInstance().getLiftTalonTwo().getSensorCollection().isFwdLimitSwitchClosed()))
//		// top
//		{
//			ActuatorConfig.getInstance().getLift().setSpeed(-.75);//-55,-.45 ,-.40
//			
////			System.out.println(ActuatorConfig.getInstance().getLiftTalonTwo().getSelectedSensorVelocity(0));
////			System.out.println("Quad:" + ActuatorConfig.getInstance().getLiftTalonTwo().getSensorCollection().getQuadraturePosition());
////		
////		//motion magic	
////			 double liftGamepad = gamepad.getY();
////		//	 double targetPos = liftGamepad *4096 * 10.0;
////			 double targetPos = 29545;
////
////
////		 ActuatorConfig.getInstance().getLiftTalonTwo().set(ControlMode.MotionMagic, targetPos); 		
//		
//		}
//
//		else if (gamepad.getButtonState(7) &&
//				(!(ActuatorConfig.getInstance().getLiftTalonTwo().getSensorCollection().isRevLimitSwitchClosed())
//						|| ActuatorConfig.getInstance().limitSwitchBottomLift().isHit()))
//		// bottom
//		{
//			ActuatorConfig.getInstance().getLift().setSpeed(.55);//.45,.37, .35
//			
////			System.out.println(ActuatorConfig.getInstance().getLiftTalonTwo().getSelectedSensorVelocity(0));
////			System.out.println("Quad:" + ActuatorConfig.getInstance().getLiftTalonTwo().getSensorCollection().getQuadraturePosition());
////			
//////			//motion magic	
////			 double liftGamepad = gamepad.getY();
////			// double targetPos = liftGamepad *4096 * 10.0;
////			 double targetPos = 0;
////
////		 ActuatorConfig.getInstance().getLiftTalonTwo().set(ControlMode.MotionMagic, targetPos);
//			
//		}
//
//		else
//		{
//			ActuatorConfig.getInstance().getLift().setSpeed(0);
//		}
//
//	}
		
	
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
