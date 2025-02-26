package org.firstinspires.ftc.teamcode;
import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.gamepad2;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
public class motorKleshna_avto{
    DcMotor motor;
    public int a;

    public void init(HardwareMap Hard) {
        motor = Hard.dcMotor.get("motorCenter");
        motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motor.setDirection(DcMotorSimple.Direction.REVERSE);
        a = motor.getCurrentPosition();


    }
    public void run(){
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