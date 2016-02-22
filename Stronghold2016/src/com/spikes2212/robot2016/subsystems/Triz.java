package com.spikes2212.robot2016.subsystems;

import static com.spikes2212.robot2016.Robot.oi;

import com.spikes2212.robot2016.Constants;
import com.spikes2212.robot2016.commands.triz.JoystickMoveTriz;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Triz extends Subsystem {

	private SpeedController motor;
	private DigitalInput up, down;
	public Encoder encoder;
	private double phase;

	public Triz(SpeedController motor, DigitalInput up, DigitalInput down, Encoder encoder) {
		this.motor = motor;
		this.up = up;
		this.down = down;
		this.encoder = encoder;
		this.encoder.setDistancePerPulse(Constants.TRIZ_ANGLE_PER_PULSE);
		this.phase = Constants.TRIZ_CONTRACTED_ANGLE;
	}

	public Triz(int trizVictorPort, int upPort, int downPort, int underPortcullisChannel, int encoderChannelA,
			int encoderChannelB) {
		this(new VictorSP(trizVictorPort), new DigitalInput(upPort), new DigitalInput(downPort),
				new Encoder(encoderChannelA, encoderChannelB));
	}

	public boolean canMove(double speed) {
		return !(speed < 0 && isUp() || speed > 0 && isDown());
	}

	public void tryMove(double speed) {
		if (canMove(speed)) {
			motor.set(speed);
		}
	}

	public void stop() {
		motor.set(0);
	}

	public boolean isUp() {
		return !up.get();
	}

	public boolean isDown() {
		return !down.get();
	}

	public void calibrate() {
		if (isUp()) {
			phase = Constants.TRIZ_CONTRACTED_ANGLE;
		} else if (isDown()) {
			phase = Constants.TRIZ_EXPANDED_ANGLE;
		} else {
			phase = getAngle();
		}
		encoder.reset();
	}

	public double getAngle() {
		return phase + encoder.getDistance();
	}

	public void initDefaultCommand() {
		setDefaultCommand(new JoystickMoveTriz(() -> {
			double y = oi.getNavigatorStraight();
			if (y > 0.5) {
				return -Constants.TRIZ_UP_SPEED;
			}
			if (y < -0.5) {
				return Constants.TRIZ_DOWN_SPEED;
			}
			return 0;
		}));
	}

	public void resetEncoder() {
		encoder.reset();
	}

}
