package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.DrivetrainConstants;

public class drivetrain extends SubsystemBase {
    private final CANSparkMax left_1, left_2, right_1, right_2;
    private final RelativeEncoder left_e, right_e;
    private final DifferentialDrive diffdrive;
    private final MotorControllerGroup leftMotors, rightMotors;
    
    public drivetrain() {
        left_1 = new CANSparkMax(DrivetrainConstants.left_1motorID, MotorType.kBrushless);
        left_2 = new CANSparkMax(DrivetrainConstants.left_2motorID, MotorType.kBrushless);
        right_1 = new CANSparkMax(DrivetrainConstants.right_1motorID, MotorType.kBrushless);
        right_2 = new CANSparkMax(DrivetrainConstants.right_2motorID, MotorType.kBrushless);

        left_1.setIdleMode(IdleMode.kBrake);
        left_2.setIdleMode(IdleMode.kBrake);
        right_1.setIdleMode(IdleMode.kBrake);
        right_2.setIdleMode(IdleMode.kBrake);
        
        left_1.setInverted(DrivetrainConstants.leftMotorInverted);
        left_2.setInverted(DrivetrainConstants.leftMotorInverted);
        right_1.setInverted(DrivetrainConstants.rightMotorInverted);
        right_2.setInverted(DrivetrainConstants.rightMotorInverted);

        leftMotors = new MotorControllerGroup(left_1, left_2);
        rightMotors = new MotorControllerGroup(right_1, right_2);
        
        left_e = left_1.getEncoder();
        right_e = left_2.getEncoder();

        left_e.setPositionConversionFactor(DrivetrainConstants.ticksToInches);
        right_e.setPositionConversionFactor(DrivetrainConstants.ticksToInches);


        diffdrive = new DifferentialDrive(leftMotors, rightMotors);
    }

    @Override
    public void periodic() {}

    public void diffydrive(double leftMotorValue, double rightMotorValue) {
        diffdrive.arcadeDrive(leftMotorValue, rightMotorValue);
    }

    public void resetPosition(double resetPosition) {
        left_e.setPosition(resetPosition);
        right_e.setPosition(resetPosition);
    }

    public double leftPos() {
        return left_e.getPosition();
    }

    public double rightPos() {
        return right_e.getPosition();
    }

    public double avgPos() {
        return (leftPos() + rightPos()) / 2;
    }
}
