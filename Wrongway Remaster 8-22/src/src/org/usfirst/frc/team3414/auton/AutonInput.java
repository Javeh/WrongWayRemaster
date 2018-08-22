package org.usfirst.frc.team3414.auton;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class AutonInput {
	public static int getPosInput() {
		int position;
		position =  Integer.parseInt((SmartDashboard.getString("Position", "1")));
		return position;
		
		
	}
	public static boolean getRecordingInput(){
		boolean record = false;
		record = SmartDashboard.getBoolean ("Record this teleop?", false);
		return record;
	}
	public static String getRecordingLayout() {
		String layout;
		layout = SmartDashboard.getString("If recording, what's the layout of the arcade?", "LLR");
		return layout;
	}
}
