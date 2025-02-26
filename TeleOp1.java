package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;
@TeleOp(name = "TeleOp1")
public class TeleOp1 extends LinearOpMode {
    Servo Ser;
    int previu;
    motorKleshna_avto Motor = new motorKleshna_avto();
    Gamepad_servo Serv=new Gamepad_servo();
    public void runOpMode(){
        Serv.start();
        Ser = hardwareMap.servo.get("ServoGrab");
        Ser.setDirection(Servo.Direction.REVERSE);
        Ser.setPosition(0.5);
        Motor.init(hardwareMap);
        waitForStart();
        while (opModeIsActive()) {
            if (previu !=gamepad2.right_trigger){
                Ser.setPosition(gamepad2.right_trigger);
                previu= (int) gamepad2.right_trigger;
            }
            if (gamepad2.dpad_up||gamepad2.dpad_down){
            }
        }
        Serv.isInterrupted();
    }
    class Gamepad_servo extends Thread{
        boolean a = false;
        public void run(){
            while (!isInterrupted()){
                if (a) {
                    Motor.run();
                }
            }
        }
    }
}
