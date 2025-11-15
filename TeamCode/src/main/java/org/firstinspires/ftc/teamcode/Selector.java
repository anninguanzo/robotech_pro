package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.utilities.RtTypes;

@TeleOp
public class Selector extends LinearOpMode {

    @Override
    public void runOpMode(){

        Robotech.m_allianceColor = RtTypes.rtColor.RED;
        Robotech.m_initialPosition = 1;

        //wait for operator to press start on driver hub
        waitForStart();

        while(!isStopRequested()) {
            if (gamepad1.dpad_left) {
                Robotech.m_allianceColor = RtTypes.rtColor.RED;
            } else if (gamepad1.dpad_right) {
                Robotech.m_allianceColor = RtTypes.rtColor.BLUE;
            }

            if (gamepad1.dpad_up)
            {
                Robotech.m_initialPosition = 1;
            }
            else if (gamepad1.dpad_down)
            {
                Robotech.m_initialPosition = 2;
            }

            if (Robotech.m_allianceColor == RtTypes.rtColor.RED){
                gamepad1.setLedColor(255, 0 ,0, 100);
                telemetry.addData("Alliance Color = RED / Init Pos = ", "%d", Robotech.m_initialPosition);
            }
            else {
                gamepad1.setLedColor(0, 0, 255, 100);
                telemetry.addData("Alliance Color = BLUE / Init Pos = ", "%d", Robotech.m_initialPosition);
            }

            telemetry.update();
        }
    }
}
