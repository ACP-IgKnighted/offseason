// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.drivetrain;

public class auton extends CommandBase {
  private final drivetrain m_Drivetrain;
  private final double distance;
  private final double speed;

  /** Creates a new auton. */
  public auton(drivetrain drivetrain, double distance, double speed) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.m_Drivetrain = drivetrain;
    this.distance = distance;
    this.speed = speed;
    addRequirements(drivetrain);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_Drivetrain.resetPosition(0);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_Drivetrain.diffydrive(speed, 0);

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_Drivetrain.diffydrive(0, 0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return m_Drivetrain.avgPos() >= Math.abs(distance);
  }
}
