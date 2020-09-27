package de.inetsoftware.jwebassembly.jawa.unit;

import de.inetsoftware.jwebassembly.api.annotation.Export;
import de.inetsoftware.jwebassembly.jawa.RunTest;

import static de.inetsoftware.jwebassembly.jawa.Print.puts;

@RunTest(input = "", intInput = 0, output = "true")
@RunTest(input = "", intInput = 1, output = "false")
@RunTest(input = "", intInput = 2, output = "true")
public class array06 {
    static boolean m(int i) {
        int[] x = new int[3];
        int s = 33;
        int t = 44;
        //	x[0] = t;
        x[1] = s;
        //	x[2] = t;
        int[] y = new int[3];
        //	y[0] = t;
        y[1] = s;
        //	y[2] = s;
        x[i] = -1;
        return x[1] == y[1];
    }

    @Export
    public static void main(String[] args) {
        if (m(args.length)) {
            puts("true");
        } else {
            puts("false");
        }
    }
}
