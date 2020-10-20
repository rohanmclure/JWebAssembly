package de.inetsoftware.jwebassembly.jawa.tests.unit;

import de.inetsoftware.jwebassembly.api.annotation.Export;
import de.inetsoftware.jwebassembly.jawa.tests.RunTest;

import static de.inetsoftware.jwebassembly.jawa.util.Print.puti;

@RunTest(input = "", intInput = 0, output = "96")
@RunTest(input = "", intInput = 1, output = "97")
@RunTest(input = "", intInput = 2, output = "99")
public class array07 {
    static int c(int i) {
        int[] x = new int[3];
        x[0] = 99;
        x[1] = 97;
        x[2] = 96;
        int[] y = array07.rev(x);
        return y[i];
    }

    static int[] rev(int[] x) {
        int[] y = new int[x.length];
        for (int i = 0; i < y.length; i = i + 1) {
            y[i] = x[y.length - i - 1];
        }
        return y;
    }

    @Export
    public static void main(String[] args) {
        puti(c(args.length));
    }
}
