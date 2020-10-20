package de.inetsoftware.jwebassembly.jawa.util;

import de.inetsoftware.jwebassembly.api.annotation.WasmTextCode;

public class Math {

    public static final double PI = 3.14159265358979323846;
    public static final double E = 2.7182818284590452354;

    public static double ln(double x) {
        double old_sum = 0.0;
        double xmlxpl = (x - 1) / (x + 1);
        double xmlxpl_2 = xmlxpl * xmlxpl;
        double denom = 1.0;
        double frac = xmlxpl;
        double term = frac;
        double sum = term;

        while (sum != old_sum) {
            old_sum = sum;
            denom += 2.0;
            frac *= xmlxpl_2;
            sum += frac / denom;
        }
        return 2.0 * sum;
    }

    public static double log( double x ) {
        return ln(x) / 2.3025850929940456840179914546844;
    }

    public static double pow(double x, double y){
        return exp((float) (y*log(x)));
    }

//    @WasmTextCode("local.get 0"
//            + "i32.reinterpret_f32"
//            +
//            + "i32.shr_s"
//    )
//    public static float log(float log) {
//        return 0.0f;
//    }

    @WasmTextCode("local.get 0 "
            + "f32.const 184 "
            + "f32.mul "
            + "f32.const 16249 "
            + "f32.add "
//            + "i32.trunc_sat_f32_s "
            + "i32.reinterpret_f32 "
            + "i32.const 65535 "
            + "i32.and "
            + "f32.reinterpret_i32 "
            + "return")
    public static float exp(float x) {
        return 0.0f;
    }

    public static double pow(double x, int n) {

        if(n < 0) {
            if(n == Integer.MIN_VALUE) {
                n = (n+1)*(-1);
                return 1.0/(pow(x*x, n));
            }
            n = n*(-1);
            return 1.0/pow(x, n);
        }
        double y = 1;
        while(n > 0) {
            if(n%2 == 0) {
                x = x*x;
            }
            else {
                y = y*x;
                x = x*x;
            }
            n = n/2;
        }
        return y;
    }

    @WasmTextCode("local.get 0 " //
            + "f64.sqrt " //
            + "return")
    public static double sqrt(double x) {
        return 0; // for Java compiler
    }

    @WasmTextCode("local.get 0 " //
            + "f64.ceil " //
            + "return")
    public static double ceil(double x) {
        return 0; // for Java compiler
    }

    @WasmTextCode("local.get 0 " //
            + "f64.floor " //
            + "return")
    public static double floor(double x) {
        return 0; // for Java compiler
    }

    @WasmTextCode("local.get 0 " //
            + "f64.nearest " //
            + "return")
    public static double rint(double x) {
        return 0; // for Java compiler
    }

    @WasmTextCode("local.get 0 " //
            + "f64.abs " //
            + "return")
    public static double abs(double x) {
        return 0; // for Java compiler
    }

    @WasmTextCode("local.get 0 " //
            + "f32.abs " //
            + "return")
    public static float abs(float x) {
        return 0; // for Java compiler
    }

    @WasmTextCode("local.get 0 " //
            + "local.get 1 " //
            + "f64.max " //
            + "return")
    public static double max(double a, double b) {
        return 0; // for Java compiler
    }

    @WasmTextCode("local.get 0 " //
            + "local.get 1 " //
            + "f32.max " //
            + "return")
    public static float max(float a, float b) {
        return 0; // for Java compiler
    }

    @WasmTextCode("local.get 0 " //
            + "local.get 1 " //
            + "f64.min " //
            + "return")
    public static double min(double a, double b) {
        return 0; // for Java compiler
    }

    @WasmTextCode("local.get 0 " //
            + "local.get 1 " //
            + "f32.min " //
            + "return")
    public static float min(float a, float b) {
        return 0; // for Java compiler
    }

    @WasmTextCode("local.get 0 " //
            + "local.get 1 " //
            + "f64.copysign " //
            + "return")
    public static double copySign(double a, double b) {
        return 0; // for Java compiler
    }


    @WasmTextCode("local.get 0 " //
            + "local.get 1 " //
            + "f32.copysign " //
            + "return")
    public static float copySign(float a, float b) {
        return 0; // for Java compiler
    }

}
