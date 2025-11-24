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
        Boolean isRobotCentric = true;

        //create all the robotech hardware instances and initialize
        m_robotech = new Robotech(hardwareMap, telemetry);

        //wait for operator to press start on driver hub
        waitForStart();

        while(!isStopRequested()) {

            //drive train
            if (gamepad1.x){ //toggle between robot centric and field centric when x button is pushed
                isRobotCentric = !isRobotCentric;
            }
            if (isRobotCentric) {
                m_robotech.rtDriveTrain.driveRC(gamepad1.left_stick_x, gamepad1.left_stick_y, gamepad1.right_stick_x, gamepad1.right_bumper);
            }
            else //isFieldCentric
            {
                m_robotech.rtDriveTrain.driveFC(gamepad1.left_stick_x, gamepad1.left_stick_y, gamepad1.right_stick_x, gamepad1.options);
            }

            //intake
            m_robotech.rtIntake.intake(gamepad2.a, gamepad2.b, gamepad2.y);

            //launcher
            m_robotech.rtLaunch.launch(gamepad2.right_bumper, gamepad2.left_bumper);

            //april tag detection
            if (m_robotech.rtCamera.detectedAprilTag())
            {
                m_robotech.rtLedLight.setColor(RtTypes.rtColor.GREEN);
                m_robotech.rtLog.print("AprilTag #", "%d %s",
                        m_robotech.rtCamera.m_aprilTagId,
                        m_robotech.rtCamera.m_aprilTagName);
            }

            telemetry.update();
        }
    }
}
