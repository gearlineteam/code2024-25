package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
@Autonomous(name = "PIR")
public class PIR extends LinearOpMode {
    DcMotor lf, lb, rf, rb, kl;
    Servo sr;
    ElapsedTime timer = new ElapsedTime();

    double distance(int enc) {
        return (enc / 538.7) * Math.PI * 10.4;
    }

    public void runOpMode() {
        lf = hardwareMap.get(DcMotor.class, "motorFrontLeft");
        lb = hardwareMap.get(DcMotor.class, "motorBackLeft");
        rb = hardwareMap.get(DcMotor.class, "motorBackRight");
        rf = hardwareMap.get(DcMotor.class, "motorFrontRight");
        kl = hardwareMap.get(DcMotor.class, "motorCenter");
        lf.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        lb.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rf.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rb.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        kl.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        waitForStart();
//        forward(120, 0.1);
//        right(64,0.05);
//        right_run(60,0.1);
//        left_run(120,0.1);
//        left(64,0.05);
//        back(240,0.1);
        right(30.5,0.05);
    }


    public void forward(double dist, double power) {
        lf.setDirection(DcMotorSimple.Direction.REVERSE); // Mb nado pomenyat na rb i rf, protestit'
        lb.setDirection(DcMotorSimple.Direction.REVERSE);
        rf.setDirection(DcMotorSimple.Direction.FORWARD); // Mb nado pomenyat na rb i rf, protestit'
        rb.setDirection(DcMotorSimple.Direction.FORWARD);//Nado chtobi pri podache 1 na vse motori kb robot ehal vpered
        double position = distance(lf.getCurrentPosition());
        double err = 100;
        double previous_err = err;
        double summary_err = 0;
        double k = 0.1;
        double ki = 0.01;
        double a;
        timer.reset();
        while ((Math.round(err) != 0 || (err - previous_err) / timer.seconds() > 1) && opModeIsActive()) {
            err = dist - Math.abs(distance(lf.getCurrentPosition()) - position);
            if (Math.abs(err) < 20) {
                summary_err = summary_err + err * timer.seconds();
            }
            a = power * (err * k + summary_err * ki);
            rf.setPower(a);
            rb.setPower(a);
            lf.setPower(a);
            lb.setPower(a);
            previous_err = err;
            timer.reset();
        }
        rf.setPower(0);
        lf.setPower(0);
        rb.setPower(0);
        lb.setPower(0);
    }
    public void left_run( double dist, double power){
        lf.setDirection(DcMotorSimple.Direction.REVERSE); // Mb nado pomenyat na rb i rf, protestit'
        lb.setDirection(DcMotorSimple.Direction.REVERSE);
        rf.setDirection(DcMotorSimple.Direction.FORWARD); // Mb nado pomenyat na rb i rf, protestit'
        rb.setDirection(DcMotorSimple.Direction.FORWARD);//Nado chtobi pri podache 1 na vse motori kb robot ehal vpered
        double position = distance(rf.getCurrentPosition());
        double err = 100;
        double previous_err = err;
        double summary_err = 0;
        double k = 0.1;
        double ki = 0.02;
        double a;
        timer.reset();
        while ((Math.round(err)!= 0 || (err - previous_err) / timer.seconds() > 1) && opModeIsActive()) {
            err = dist - Math.abs(distance(rf.getCurrentPosition()) - position);
            if (Math.abs(err) < 20) {
                summary_err = summary_err + err * timer.seconds();
            }
            a = power * (err * k + summary_err * ki);
            rf.setPower(a);
            rb.setPower(-a);
            lf.setPower(-a);
            lb.setPower(a);
            previous_err = err;
            timer.reset();
        }
        rf.setPower(0);
        lf.setPower(0);
        rb.setPower(0);
        lb.setPower(0);
    }
    public void right_run( double dist, double power){
        lf.setDirection(DcMotorSimple.Direction.REVERSE); // Mb nado pomenyat na rb i rf, protestit'
        lb.setDirection(DcMotorSimple.Direction.REVERSE);
        rf.setDirection(DcMotorSimple.Direction.FORWARD); // Mb nado pomenyat na rb i rf, protestit'
        rb.setDirection(DcMotorSimple.Direction.FORWARD);//Nado chtobi pri podache 1 na vse motori kb robot ehal vpered
        double position = distance(rb.getCurrentPosition());
        double err = 100;
        double previous_err = err;
        double summary_err = 0;
        double k = 0.1;
        double ki = 0.02;
        double a;
        timer.reset();
        while ((Math.round(err)!= 0 || (err - previous_err) / timer.seconds() > 1) && opModeIsActive()) {
            err = dist - Math.abs(distance(rb.getCurrentPosition()) - position);
            if (Math.abs(err) < 20) {
                summary_err = summary_err + err * timer.seconds();
            }
            a = power * (err * k + summary_err * ki);
            rf.setPower(-a);
            rb.setPower(a);
            lf.setPower(a);
            lb.setPower(-a);
            previous_err = err;
            timer.reset();
        }
        rf.setPower(0);
        lf.setPower(0);
        rb.setPower(0);
        lb.setPower(0);
    }
    public void back(double dist, double power) {
        lf.setDirection(DcMotorSimple.Direction.FORWARD); // Mb nado pomenyat na rb i rf, protestit'
        lb.setDirection(DcMotorSimple.Direction.FORWARD);
        rf.setDirection(DcMotorSimple.Direction.REVERSE); // Mb nado pomenyat na rb i rf, protestit'
        rb.setDirection(DcMotorSimple.Direction.REVERSE);//Nado chtobi pri podache 1 na vse motori kb robot ehal vpered
        double position = distance(lf.getCurrentPosition());
        double err = 100;
        double previous_err = err;
        double summary_err = 0;
        double k = 0.1;
        double ki = 0.01;
        double a;
        timer.reset();
        while ((Math.round(err) != 0 || (err - previous_err) / timer.seconds() > 1) && opModeIsActive()) {
            err = dist - Math.abs(distance(lf.getCurrentPosition()) - position);
            if (Math.abs(err) < 20) {
                summary_err = summary_err + err * timer.seconds();
            }
            a = power * (err * k + summary_err * ki);
            rf.setPower(a);
            rb.setPower(a);
            lf.setPower(a);
            lb.setPower(a);
            previous_err = err;
            timer.reset();
        }
        rf.setPower(0);
        lf.setPower(0);
        rb.setPower(0);
        lb.setPower(0);
    }
    public void right( double dist, double power){
        lf.setDirection(DcMotorSimple.Direction.REVERSE); // Mb nado pomenyat na rb i rf, protestit'
        lb.setDirection(DcMotorSimple.Direction.REVERSE);
        rf.setDirection(DcMotorSimple.Direction.FORWARD); // Mb nado pomenyat na rb i rf, protestit'
        rb.setDirection(DcMotorSimple.Direction.FORWARD);//Nado chtobi pri podache 1 na vse motori kb robot ehal vpered
        double position = distance(lf.getCurrentPosition());
        double err = 100;
        double previous_err = err;
        double summary_err = 0;
        double k = 0.1;
        double ki = 0.02;
        double a;
        timer.reset();
        while ((Math.round(err)!= 0 || (err - previous_err) / timer.seconds() > 1) && opModeIsActive()) {
            err = dist - Math.abs(distance(lf.getCurrentPosition()) - position);
            if (Math.abs(err) < 20) {
                summary_err = summary_err + err * timer.seconds();
            }
            a = power * (err * k + summary_err * ki);
            rf.setPower(-a);
            rb.setPower(-a);
            lf.setPower(a);
            lb.setPower(a);
            previous_err = err;
            timer.reset();
        }
        rf.setPower(0);
        lf.setPower(0);
        rb.setPower(0);
        lb.setPower(0);
    }//dist 30.5=45 gradusov, dist 61=90 gradusov  dist 91.5=135 gradusov
    public void left( double dist, double power){
        lf.setDirection(DcMotorSimple.Direction.REVERSE); // Mb nado pomenyat na rb i rf, protestit'
        lb.setDirection(DcMotorSimple.Direction.REVERSE);
        rf.setDirection(DcMotorSimple.Direction.FORWARD); // Mb nado pomenyat na rb i rf, protestit'
        rb.setDirection(DcMotorSimple.Direction.FORWARD);//Nado chtobi pri podache 1 na vse motori kb robot ehal vpered
        double position = distance(rf.getCurrentPosition());
        double err = 100;
        double previous_err = err;
        double summary_err = 0;
        double k = 0.1;
        double ki = 0.02;
        double a;
        timer.reset();
        while ((Math.round(err)!= 0 || (err - previous_err) / timer.seconds() > 1) && opModeIsActive()) {
            err = dist - Math.abs(distance(rf.getCurrentPosition()) - position);
            if (Math.abs(err) < 20) {
                summary_err = summary_err + err * timer.seconds();
            }
            a = power * (err * k + summary_err * ki);
            rf.setPower(a);
            rb.setPower(a);
            lf.setPower(-a);
            lb.setPower(-a);
            previous_err = err;
            timer.reset();
        }
        rf.setPower(0);
        lf.setPower(0);
        rb.setPower(0);
        lb.setPower(0);
        }//dist 32=45 gradusov, dist 64=180 gradusov  dist 96=135 gradusov
}