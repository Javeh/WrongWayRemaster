package org.usfirst.frc.team3414.auton;

import org.usfirst.frc.team3414.control.Controller;

public class Auton {
	/*
	 * Done using the guide at:
	 * http://www.robotc.net/wikiarchive/Tutorials/Arduino_Projects/Mobile_Robotics/VEX/Using_encoders_to_drive_some_distance  
	 * wrongway wheel is 6.25 in diameter circumference is 19.634954084936207740391521145497
	 * every time the wheel does one rotation, it is equal to 19.634954084936207740391521145497 in traveled.
	 */
	private double cir = 19.634954084936207740391521145497;
	private double tickratioIn =	360/cir;
	static Controller controller = new Controller();
	public double getTravelledInches(double origin, double current) {
		//using this formula Distance traveled = (Encoder ticks / 360) * circumference 
		return ((current/360) * cir) - ((origin/360) * cir);
		
	}
	public void turnLeft() {
		
	}
}
