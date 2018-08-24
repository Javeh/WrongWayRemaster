package org.usfirst.frc.team3414.control;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import src.org.usfirst.frc.team3414.actuators.ActuatorConfig;

//TODO Make sure you put in else statements to stop motors
public class Controller {
	public TankControl tankControl = new TankControl();
	public GamepadControl gamepadControl = new GamepadControl();
	double drivetrainPower;
	public void init() {
		tankControl.init();
		gamepadControl.init();
	}
	public void autonInit() {
		tankControl.autonInit();
	}
	public void teleopInit() {
		tankControl.teleopInit();
	}

	public void run() {
		tankControl.teleop();
		gamepadControl.teleop();
	}

	public void auton() {
		tankControl.auton();
		gamepadControl.auton();
	}

	// BEGIN RECORDING CODE
	long startTime;
	FileWriter writer;
//TODO IN PLAYBACK, POTENTIALLY BOOST DRIVE TRAIN WITH GLOBAL VARIABLE POWER MODIFIER 
	public void recordInit() throws IOException {
		startTime = System.currentTimeMillis();
		// record the time we started recording

		// put the filesystem location you are supposed to write to as a string
		// as the argument in this method, as of 2015 it is
		// /home/lvuser/recordedAuto.csv

		writer = new FileWriter(ActuatorConfig.autoFile);
	}
	
	public void record() throws IOException {
		if(gamepadControl.isRecording()) {
		if (writer != null) {
			// start each "frame" with the elapsed time since we started recording
			writer.append("" + (System.currentTimeMillis() - startTime));

			// in this chunk, use writer.append to add each type of data you want to record
			// to the frame
			// the 2015 robot used the following motors during auto

			// drive motors
			writer.append("," + tankControl.getLeftJoy());
			writer.append("," + tankControl.getRightJoy());
			// Elevator
			writer.append("," + gamepadControl.getElevatorSpeed());
			// Angler
			writer.append("," + gamepadControl.getAnglerSpeed());
			// Intake
			writer.append("," + gamepadControl.getIntakeSpeed());
			// barrel motors
			/*
			 * writer.append("," + storage.robot.getBarrelMotorLeft().get());
			 * writer.append("," + storage.robot.getBarrelMotorRight().get());
			 * 
			 * // fork motors writer.append("," + storage.robot.getLeftForkLeft().get());
			 * writer.append("," + storage.robot.getLeftForkRight().get());
			 * writer.append("," + storage.robot.getRightForkLeft().get());
			 * writer.append("," + storage.robot.getRightForkRight().get()); /*
			 */
			/*
			 * THE LAST ENTRY OF THINGS YOU RECORD NEEDS TO HAVE A DELIMITER CONCATENATED TO
			 * THE STRING AT THE END. OTHERWISE GIVES NOSUCHELEMENTEXCEPTION
			 */

			// this records a true/false value from a piston
			// writer.append("," + storage.robot.getToteClamp().isExtended() + "\n");

			/*
			 * CAREFUL. KEEP THE LAST THING YOU RECORD BETWEEN THESE TWO COMMENTS AS A
			 * REMINDER TO APPEND THE DELIMITER
			 */
		}
	}
	}

	// this method closes the writer and makes sure that all the data you recorded
	// makes it into the file
	public void endRecording() throws IOException {
		if (writer != null) {
			writer.flush();
			writer.close();
		}
	}

	// BEGIN REPLAY CODE
	Scanner scanner;
	long startTimeReplay;

	boolean onTime = true;
	double nextDouble;

	public void replayInit() throws FileNotFoundException {
		// create a scanner to read the file created during BTMacroRecord
		// scanner is able to read out the doubles recorded into recordedAuto.csv (as of
		// 2015)
		scanner = new Scanner(new File(ActuatorConfig.autoFile));

		// let scanner know that the numbers are separated by a comma or a newline, as
		// it is a .csv file
		scanner.useDelimiter(",|\\n");

		// lets set start time to the current time you begin autonomous
		startTimeReplay = System.currentTimeMillis();
	}

	public void replay() {
		// if recordedAuto.csv has a double to read next, then read it
		if ((scanner != null) && (scanner.hasNextDouble())) {
			double t_delta;

			// if we have waited the recorded amount of time assigned to each respective
			// motor value,
			// then move on to the next double value
			// prevents the macro playback from getting ahead of itself and writing
			// different
			// motor values too quickly
			if (onTime) {
				// take next value
				nextDouble = scanner.nextDouble();
			}

			// time recorded for values minus how far into replaying it we are--> if not
			// zero, hold up
			t_delta = nextDouble - (System.currentTimeMillis() - startTimeReplay);
			// if we are on time, then set motor values
			if (t_delta <= 0) {
				// for 2015 robot. these are all the motors available to manipulate during
				// autonomous.
				// it is extremely important to set the motors in the SAME ORDER as was recorded
				// in BTMacroRecord
				// otherwise, motor values will be sent to the wrong motors and the robot will
				// be unpredicatable
				// ORIGINAL writer.append("," + tankControl.getLeftJoy());
				// ORIGINAL writer.append("," + tankControl.getRightJoy());
				tankControl.autonDrive(scanner.nextDouble(), scanner.nextDouble());
				gamepadControl.elevatorSetSpeed(scanner.nextDouble());
				gamepadControl.anglerSetSpeed(scanner.nextDouble());
				gamepadControl.intakeSetSpeed(scanner.nextDouble());
				/*
				 * storage.robot.getFrontLeftMotor().setX(scanner.nextDouble());
				 * storage.robot.getFrontRightMotor().setX(scanner.nextDouble());
				 * storage.robot.getBackRightMotor().setX(scanner.nextDouble());
				 * storage.robot.getBackLeftMotor().setX(scanner.nextDouble());
				 * 
				 * storage.robot.getBarrelMotorLeft().setX(scanner.nextDouble());
				 * storage.robot.getBarrelMotorRight().setX(scanner.nextDouble());
				 * 
				 * storage.robot.getLeftForkLeft().setX(scanner.nextDouble());
				 * storage.robot.getLeftForkRight().setX(scanner.nextDouble());
				 * storage.robot.getRightForkLeft().setX(scanner.nextDouble());
				 * storage.robot.getRightForkRight().setX(scanner.nextDouble());
				 * 
				 * storage.robot.getToteClamp().set(storage.robot.getToteClamp().isExtended());
				 */
				// go to next double
				onTime = true;
			}
			// else don't change the values of the motors until we are "onTime"
			else {
				onTime = false;
				System.out.println("Error, catch up!");
			}
		}
		// end play, there are no more values to find
		else {
			endReplay();
			if (scanner != null) {
				scanner.close();
				scanner = null;
			}
		}

	}

	// stop motors and end playing the recorded file
	public void endReplay() {
		tankControl.stop();
		gamepadControl.stop();
		/*
		 * storage.robot.getFrontLeftMotor().setX(0);
		 * storage.robot.getBackLeftMotor().setX(0);
		 * storage.robot.getFrontRightMotor().setX(0);
		 * storage.robot.getBackRightMotor().setX(0);
		 * 
		 * storage.robot.getBarrelMotorLeft().setX(0);
		 * storage.robot.getBarrelMotorRight().setX(0);
		 * 
		 * storage.robot.getLeftForkLeft().setX(0);
		 * storage.robot.getLeftForkRight().setX(0);
		 * storage.robot.getRightForkLeft().setX(0);
		 * storage.robot.getRightForkRight().setX(0);
		 */
		// all this mess of a method does is keep the piston in the same state it ended
		// in
		// if you want it to return to a specific point at the end of auto, change that
		// here
		// storage.robot.getToteClamp().set(storage.robot.getToteClamp().isExtended());

		if (scanner != null) {
			scanner.close();
		}

	}

}
