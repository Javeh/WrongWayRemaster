//
//package org.usfirst.frc.team3414.auton;
//
//import org.usfirst.frc.team3414.actuators.ActuatorConfig;
//
//import edu.wpi.first.wpilibj.DriverStation;
//import edu.wpi.first.wpilibj.Preferences;
////TODO Autonomous Selection Screen
//public class AutonPicker {
//	static Preferences prefs;
//	static int auton;
//	String gamedata = DriverStation.getInstance().getGameSpecificMessage();
//	public static void pickerInit() {
//		prefs = Preferences.getInstance();
//		auton = prefs.getInt("Enter Robot Position", 1);
//		ActuatorConfig.autonNumber = auton;
//	}
//	public static void autonInit() {
//		ActuatorConfig.autonNumber = auton;
//	}
//	public static void getAuton() {
//		
//
//	}
//
//}
////TODO Make some sort of way to feed back Auton choice and use the auton choice in Controller. 
////TODO Consider Freeing resources at the end of auton(if possible) or running some sort of if statement with the FRC timer to free resources near the end of auton.
////TODO This way, you can make a drive train for whatever purpose and re use it during teleop. 