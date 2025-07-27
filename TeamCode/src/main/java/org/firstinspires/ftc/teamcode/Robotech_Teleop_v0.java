package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.utilities.RtTypes;

@TeleOp
public class Robotech_Teleop_v0 extends LinearOpMode {

    //class attributes

    //robotech hw class, common to all opmodes
    Robotech m_robotech;

    @Override
    public void runOpMode(){
        //create all the robotech hardware instances and initialize
        m_robotech = new Robotech(hardwareMap, telemetry);

        //wait for operator to press start on driver hub
        waitForStart();

        while(!isStopRequested()) {
            m_robotech.rtDriveTrain.drive(gamepad1.left_stick_x, gamepad1.left_stick_y, gamepad1.right_stick_x, gamepad1.right_bumper);
            m_robotech.rtWrist.wrist(gamepad2.y, gamepad2.a);
            m_robotech.rtLift.lift(gamepad2.left_stick_y);
            m_robotech.rtClaw.claw(gamepad2.right_bumper, gamepad2.left_bumper);

            if (m_robotech.rtTouchSensor.isTouched())
            {
                m_robotech.rtLedLight.setColor(RtTypes.rtColor.GREEN);
            }
            else
            {
                RtTypes.rtColor detectedColor = m_robotech.rtColorSensor.getColor();
                if ( detectedColor != RtTypes.rtColor.UNKNOWN ) {
                    m_robotech.rtLedLight.setColor(detectedColor);
                }
                else {
                    m_robotech.rtLedLight.setColor(RtTypes.rtColor.OFF);
                }
            }
        }
    }
}
