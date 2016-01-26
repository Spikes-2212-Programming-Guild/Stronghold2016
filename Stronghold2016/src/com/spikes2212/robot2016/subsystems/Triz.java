package com.spikes2212.robot2016.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Triz extends Subsystem {
	private Talon trizTalon;
	private DigitalInput upLimit, downLimit, underPortcullis;
	private Encoder encoder;

	public Triz(Talon trizTalon, DigitalInput up, DigitalInput down, DigitalInput underPortcullis, Encoder encoder) {
		this.trizTalon = trizTalon;
		this.upLimit = up;
		this.downLimit = down;
		this.underPortcullis = underPortcullis;
		this.encoder=encoder;
	}

	public Triz(int trizTalonPort, int upPort, int downPort, int underPortcullisChannel, int encoderChannelA, int encoderChannelB) {
		this(new Talon(trizTalonPort), new DigitalInput(upPort), new DigitalInput(downPort),
				new DigitalInput(underPortcullisChannel), new Encoder(encoderChannelA, encoderChannelB));
	}

	public void moveTriz(double speed) {
		trizTalon.set(speed);
	}

	public void stop() {
		trizTalon.set(0);
	}

	public boolean isUp() {
		return !upLimit.get();
	}

	public boolean isDown() {
		return !downLimit.get();
	}
	public boolean isUnderPortcullis() {
		return !underPortcullis.get();
	}
	public double getDistance(){
		return encoder.getDistance();
	}

	// Put methods for controlling this subsystem
	// here. Call these from Commands.

	public void initDefaultCommand() {
	}
}
