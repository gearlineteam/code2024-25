package org.firstinspires.ftc.teamcode;
import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.gamepad2;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
public class servo_avto{
    Servo Ser;
    public void init(HardwareMap Hard){
        Ser=Hard.servo.get("ServoGrab");
        Ser.setDirection(Servo.Direction.REVERSE);
        Ser.setPosition(0.5);
    }
    public void Gamepad_servo() {
            Ser.setPosition(gamepad2.right_trigger);
    }
}
