// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.leds;

import com.ctre.phoenix.led.CANdle;
import com.ctre.phoenix.led.StrobeAnimation;
import com.ctre.phoenix.led.TwinkleAnimation;
import com.ctre.phoenix.led.LarsonAnimation.BounceMode;
import com.ctre.phoenix.led.LarsonAnimation;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.DriverStation.Alliance;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.subsystems.limelight.LimelightHelpers;
import frc.robot.subsystems.shooter.ShooterSubsystem;

import java.util.Optional;
import com.ctre.phoenix.led.RainbowAnimation;

public class Leds extends SubsystemBase {

  Optional<Alliance> alliance;
  // * This is for the blue alliance color
  private TwinkleAnimation twinkleblueAnimation;
  // * This is for the red alliance color
  private TwinkleAnimation twinkleredAnimation;
  // * This is for the strobe animation when locked onto the target
  private StrobeAnimation strobeimation;
  // * This is for the strobe animation when intaking
  private StrobeAnimation strobeintakeAnimation;
  // * This is for the rainbow animation
  private RainbowAnimation rainbowAnimation;
  // * This if for the strobe animation when ready for amp
  private StrobeAnimation ampAnimation;
  // * This is for the Larson effect while climbing.
  private LarsonAnimation climbingAnimation;

  private final double STROBE_SPEED = 98.0 / 256.0;

  private CANdle candle1;
  private static boolean intaking;
  private static boolean amping;
  private static boolean climbing;
  ShooterSubsystem SHOOTER_SUBSYSTEM;

  /** Creates a new CANdle */
  public Leds(ShooterSubsystem shooter) {
    setupLeds();
    SHOOTER_SUBSYSTEM = shooter;
  }

  private void setupLeds() {
    // * This is for the blue alliance color
    twinkleblueAnimation = new TwinkleAnimation(0, 0, 255);
    // * This is for the red alliance color
    twinkleredAnimation = new TwinkleAnimation(255, 0, 0);
    // * This creates the CANdle
    candle1 = new CANdle(48, "canivore");
    // * This is for the strobe animation when locked onto the target
    strobeimation = new StrobeAnimation(0, 255, 0, 255, 98.0 / 256.0, 68, 0);
    // * This is for the strobe animation when intaking
    strobeintakeAnimation = new StrobeAnimation(255, 255, 255, 255, STROBE_SPEED, 148, 0);
    // * This is for the intaking boolean
    intaking = false;
    amping = false;
    climbing = false;
    // * This is for the rainbow animation
    rainbowAnimation = new RainbowAnimation(1, .75, 148);
    // * This is for the strobe animation when ready for amp
    ampAnimation = new StrobeAnimation(255, 255, 0, 0, STROBE_SPEED, 148, 0);
    // * This is for the Larson effect while climbing.
    climbingAnimation = new LarsonAnimation(127, 0, 255, 0, STROBE_SPEED, 147, BounceMode.Front, 7, 0);
  }

  @Override
  public void periodic() {
    
  }

  public void teleopLEDs(){
    if ((LimelightHelpers.getTX("limelight") < 5) &&
        (LimelightHelpers.getTY("limelight") < 2) &&
        (LimelightHelpers.getTX("limelight") > -5) &&
        (LimelightHelpers.getTY("limelight") > -2) &&
        LimelightHelpers.getTV("limelight") &&
        SHOOTER_SUBSYSTEM.getRPM() > 4000) {
      setLedsToStrobe();
    } else if (intaking) {
      setLedsIntake();
    } else if (amping) {
      setAmpReady();
    } else if (climbing) {
      setClimbing();
    }
    else {
      setLedsUsingAllianceColor();
    }
  }

  public void setLedsToStrobe() {
    candle1.animate(strobeimation);
  }

  public void rainbow(){
    candle1.animate(rainbowAnimation);
  }

  public void setLedsIntake() {
    candle1.animate(strobeintakeAnimation);
  }

  public void setLedsUsingAllianceColor() {
    alliance = DriverStation.getAlliance();
    if (alliance.isPresent()) {
      if (alliance.get() == Alliance.Red) {
        candle1.animate(twinkleredAnimation);
      }
      if (alliance.get() == Alliance.Blue) {
        candle1.animate(twinkleblueAnimation);
      }
    }
  }

  public static void setIntakeStatus(boolean status) {
    intaking = status;
  }
  public static void setAmpStatus(boolean status){
    amping = status;
  }
  public static void setClimbingStatus(boolean status){
    climbing = status;
  }

  public void setAmpReady() {
    candle1.animate(ampAnimation);
  }

  public void setClimbing() {
    candle1.animate(climbingAnimation);
  }
}