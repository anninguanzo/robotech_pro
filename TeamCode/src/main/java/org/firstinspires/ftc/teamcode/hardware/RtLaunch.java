package org.firstinspires.ftc.teamcode.hardware;

import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.robotcore.external.Telemetry;


public class RtLaunch {
    private Telemetry m_telemetry;
    private DcMotor m_launchMotor;


    public RtLaunch(DcMotor parLaunchMotor, Telemetry parTelemetry) {
        m_launchMotor = parLaunchMotor;
        m_telemetry = parTelemetry;
    }

    public void launchArtifact() {
        m_telemetry.addLine("RtLaunch launch");
        if (hwExists()) {
            double power = -1.0;
            m_launchMotor.setPower(power);
        }
    }

    public void stop() {
        m_telemetry.addLine("RtLaunch stop");
        if (hwExists()) {
            m_launchMotor.setPower(0);
        }
    }

    public void launch(boolean parLaunch, boolean parStop)
    {
        if(parLaunch)
        {
            launchArtifact();
        }
        else if(parStop)
        {
            stop();
        }
    }
    private boolean hwExists() {
        boolean exists = true;

        if (m_launchMotor == null)
        {
            exists = false;
            m_telemetry.addLine("RtLaunch HW NOT CONNECTED");
            //m_telemetry.update();
        }
        return exists;
    }
}
