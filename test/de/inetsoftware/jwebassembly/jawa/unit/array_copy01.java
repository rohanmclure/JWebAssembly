package de.inetsoftware.jwebassembly.jawa.unit;

import de.inetsoftware.jwebassembly.api.annotation.Export;
import de.inetsoftware.jwebassembly.jawa.RunTest;

import static de.inetsoftware.jwebassembly.jawa.Print.puti;

@RunTest(input = "", intInput = 9, output = "16")
@RunTest(input = "", intInput = 11, output = "18")
public class array_copy01 {
    static int m(int a) {
        int[] x = new int[3];
        x[0] = 4;
        x[1] = a + 7;
        x[2] = 5;
        int[] y = array_copy01.copy(x);
        x[1] = 99;
        return y[1];
    }

    static int[] copy(int[] x) {
        int[] r = new int[x.length];
        for (int i = 0; i < x.length; i = i + 1) {
            r[i] = x[i];
        }
        return r;
    }

    @Export
    public static void main(String[] args) {
        puti(m(args.length));
    }

}
