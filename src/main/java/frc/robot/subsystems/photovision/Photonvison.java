// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.photovision;


import org.photonvision.PhotonCamera;
import edu.wpi.first.math.geometry.Transform3d;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Photonvison extends SubsystemBase {
  /** Creates a new Photonvison. */

  // Change this to match the name of your camera
  PhotonCamera camera = new PhotonCamera("camera-1");

  // Query the latest result from PhotonVision
  // var result = camera.getLatestResult();

  // Check if the latest result has any targets.
  // boolean hasTargets = result.hasTargets();

  public Photonvison() {
    // Check if the latest result has any targets.
    var result = camera.getLatestResult();

    // double pipelineindex = camera.getPipelineIndex();

    // boolean hasTargets = result.hasTargets();

    periodic();

    ShuffleBoardInitInit();

    // Get a list of currently tracked targets.
    // List<PhotonTrackedTarget> targets = result.getTargets();

    // Get information from target.
    // int targetID = target.getFiducialId();
    // double poseAmbiguity = target.getPoseAmbiguity();
    // Transform3d bestCameraToTarget = target.getBestCameraToTarget();
    // Transform3d alternateCameraToTarget = target.getAlternateCameraToTarget();

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run

  }

  /**
   * Initializes the Shuffleboard for PhotonVision.
   * Adds various properties related to camera targets to the Shuffleboard tab "PhotonVision".
   * This method retrieves information from the camera and displays it on the Shuffleboard.
   */
  
  public void ShuffleBoardInitInit() {
    Shuffleboard.getTab("PhotonVision").add("pipelineindex", camera.getPipelineIndex());
    // SmartDashboard.putBoolean("hasTargets", );

    Shuffleboard.getTab("PhotonVision").add("Has Targets", camera.getLatestResult().hasTargets());

    Shuffleboard.getTab("PhotonVision").add("Target Count", camera.getLatestResult().getTargets().size());
    //Shuffleboard.getTab("PhotonVision").add("Target 1", camera.getLatestResult().getTargets().get(0).getPitch());
    //



    //Shuffleboard.getTab("PhotonVision").add("Target ID", camera.getLatestResult().getTargets().get(0).getFiducialId());

  }
}
