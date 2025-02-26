package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
@TeleOp(name = "tutorial")
public class GearLine extends LinearOpMode {
    DcMotor motor;
    public void runOpMode() {
        motor = hardwareMap.dcMotor.get("motorCenter");
        motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motor.setDirection(DcMotorSimple.Direction.REVERSE);
        waitForStart();
        int a = motor.getCurrentPosition();
        while (opModeIsActive()) {
            if (Math.abs(a - motor.getCurrentPosition()) < 538.7*2.5 && Math.abs(a - motor.getCurrentPosition())>80) {
                if (gamepad2.dpad_up) {motor.setPower(0.45);}
                else if (gamepad2.dpad_down) {motor.setPower(-0.45);}
                else {motor.setPower(0);}
            }
            else if (Math.abs(a - motor.getCurrentPosition()) > 538.7*2.5) {
                if (gamepad2.dpad_down) {motor.setPower(-0.45);}
                else{motor.setPower(0);}
            }
            else if (Math.abs(a - motor.getCurrentPosition())<80) {
                if (gamepad2.dpad_up) {motor.setPower(0.45);}
                else{motor.setPower(0);}
            }
        }

    }
}