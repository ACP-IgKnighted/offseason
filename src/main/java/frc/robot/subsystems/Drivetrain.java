package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.DrivetrainConstants;

public class Drivetrain extends SubsystemBase {
    private final CANSparkMax left_1, left_2, right_1, right_2;
    private final RelativeEncoder leftEncoder, rightEncoder;
    private final DifferentialDrive diffDrive;
    private final MotorControllerGroup leftControllers, rightControllers;
    
    public Drivetrain() {
        
        left_1 = new CANSparkMax(DrivetrainConstants.leftMotorId, MotorType.kBrushless);
        left_2 = new CANSparkMax(DrivetrainConstants.leftMotor2Id, MotorType.kBrushless);
        right_1 = new CANSparkMax(DrivetrainConstants.rightMotorId, MotorType.kBrushless);
        right_2 = new CANSparkMax(DrivetrainConstants.rightMotor2Id, MotorType.kBrushless);
        
        left_1.setIdleMode(IdleMode.kBrake);
        left_2.setIdleMode(IdleMode.kBrake);
        right_1.setIdleMode(IdleMode.kBrake);
        right_2.setIdleMode(IdleMode.kBrake);

        left_1.setInverted(DrivetrainConstants.leftMotorInverted);
        left_2.setInverted(DrivetrainConstants.leftMotorInverted);
        right_1.setInverted(DrivetrainConstants.rightMotorInverted);
        right_2.setInverted(DrivetrainConstants.rightMotorInverted);

        leftControllers = new MotorControllerGroup(left_1, left_2);
        rightControllers = new MotorControllerGroup(right_1, right_2);

        leftEncoder = left_1.getEncoder();
        rightEncoder = right_1.getEncoder();

        leftEncoder.setPositionConversionFactor(DrivetrainConstants.ticksToInches);
        rightEncoder.setPositionConversionFactor(DrivetrainConstants.ticksToInches);

        diffDrive = new DifferentialDrive(leftControllers, rightControllers);


    }

    @Override

    public void periodic() {

    }

    public void differentialDrive(double leftMotorValue, double rightMotorValue) {
        diffDrive.arcadeDrive(leftMotorValue, rightMotorValue);
    }

    public void resetPosition(double resetPos) {
        leftEncoder.setPosition(resetPos);
        rightEncoder.setPosition(resetPos);
    }

    public double getLeftPosition() {
        return leftEncoder.getPosition();
    }

    public double getRightPosition() {
        return rightEncoder.getPosition();
    }

    public double getAveragePosition() {
        return (getLeftPosition() + getRightPosition())/2;
    }
}
