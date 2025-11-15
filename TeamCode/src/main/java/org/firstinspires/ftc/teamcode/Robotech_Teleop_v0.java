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

            //drive
            m_robotech.rtDriveTrain.drive(gamepad1.left_stick_x, gamepad1.left_stick_y, gamepad1.right_stick_x, gamepad1.right_bumper);

            //intake
            if (gamepad2.a) {
                m_robotech.rtIntake.retrieveArtifact();
            }
            else if (gamepad2.b)
            {
                m_robotech.rtIntake.expelArtifact();
            }
            else if(gamepad2.y)
            {
                m_robotech.rtIntake.stop();
            }

            //launch
        }
    }
}
