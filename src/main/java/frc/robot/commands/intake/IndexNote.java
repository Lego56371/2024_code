// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.intake;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.intake.IntakeSubsystem;



public class IndexNote extends Command {
  /** Creates a new IndexNote. */
  private IntakeSubsystem INTAKE_SUBSYSTEM;
  public IndexNote(IntakeSubsystem intake) {
    // Use addRequirements() here to declare subsystem dependencies.
    INTAKE_SUBSYSTEM = intake;

    addRequirements(INTAKE_SUBSYSTEM);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    INTAKE_SUBSYSTEM.set( 0.6, 0.6); 
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {}

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {INTAKE_SUBSYSTEM.stop();}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return INTAKE_SUBSYSTEM.hasNote();
  }
}
