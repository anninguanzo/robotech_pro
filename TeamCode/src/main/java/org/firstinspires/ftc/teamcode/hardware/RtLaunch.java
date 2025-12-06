package org.firstinspires.ftc.teamcode.hardware;

import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.robotcore.external.Telemetry;


public class RtLaunch {
    private Telemetry m_telemetry;
    private DcMotor m_launchMotor1;
    private DcMotor m_launchMotor2;

    private Boolean m_launchToggle = false;

    public RtLaunch(DcMotor parLaunchMotor1, DcMotor parLaunchMotor2, Telemetry parTelemetry) {
        m_launchMotor1 = parLaunchMotor1;
        m_launchMotor2 = parLaunchMotor2;
        m_telemetry = parTelemetry;
    }

    public void launchArtifact() {
        m_telemetry.addLine("RtLaunch launch");
        if (hwExists()) {
            double power = -1.0;
            m_launchMotor1.setPower(power);
            m_launchMotor2.setPower(power);
        }
    }

    public void stop() {
        m_telemetry.addLine("RtLaunch stop");
        if (hwExists()) {
            m_launchMotor1.setPower(0);
            m_launchMotor2.setPower(0);
        }
    }

    public void launch(boolean parLaunchToggle)
    {
        if (parLaunchToggle) {
            m_launchToggle = !m_launchToggle;

            if (m_launchToggle) {
                launchArtifact();
            } else {
                stop();
            }
        }
    }
    private boolean hwExists() {
        boolean exists = true;

        if (m_launchMotor1 == null || m_launchMotor2 == null)
        {
            exists = false;
            m_telemetry.addLine("RtLaunch HW NOT CONNECTED");
            //m_telemetry.update();
        }
        return exists;
    }
}
