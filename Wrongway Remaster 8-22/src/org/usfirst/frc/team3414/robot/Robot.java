/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team3414.robot;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.usfirst.frc.team3414.auton.AutonInput;
import org.usfirst.frc.team3414.control.Controller;

import edu.wpi.first.wpilibj.IterativeRobot;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.properties file in the
 * project.
 */
public class Robot extends IterativeRobot {
	private static final String kDefaultAuto = "Default";
	private static final String kCustomAuto = "My Auto";
	private String m_autoSelected;
	static Controller control = new Controller();
	/**
	 * This function is run when the robot is first started up and should be used
	 * for any initialization code.
	 */
	@Override
	public void robotInit() {
		//AutonPicker.pickerInit();
		AutonInput.getPosInput();
		AutonInput.getRecordingInput();
		AutonInput.getRecordingLayout();
		control.init();
		
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable chooser
	 * code works with the Java SmartDashboard. If
	 *  you prefer the LabVIEW Dashboard,
	 * remove all of the chooser code and uncomment the getString line to get the
	 * auto name from the text box below the Gyro
	 *
	 * <p>
	 * You can add additional auto modes by adding additional comparisons to the
	 * switch structure below with additional strings. If using the SendableChooser
	 * make sure to add them to the chooser code above as well.
	 */
	@Override
	public void autonomousInit() {
		control.autonInit();
		try {
			control.replayInit();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * This function is called periodically during autonomous.
	 */
	@Override
	public void autonomousPeriodic() {
		control.replay();
	}

	//Called once
	public void teleopInit() {
		try {
			control.recordInit();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		
	}
	/**
	 * This function is called periodically during operator control.
	 */
	public void teleopPeriodic() {
		control.run();
		try {
			control.record();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/*
		int i = 0;
		while(i <15) {
			if(joy.getRawButton(i));{
			
			System.out.println(i);
			i++;
			
			}
		}*/
	}

	/**
	 * This function is called periodically during test mode.
	 */
	@Override
	public void testPeriodic() {
	}
}
