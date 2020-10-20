package de.inetsoftware.jwebassembly.jawa.tests.unit;

import de.inetsoftware.jwebassembly.api.annotation.Export;
import de.inetsoftware.jwebassembly.jawa.tests.RunTest;

import static de.inetsoftware.jwebassembly.jawa.util.Print.puts;

@RunTest(input = "", intInput = 4, output = "false")
@RunTest(input = "", intInput = 127, output = "true")
public class array03 {
    static boolean m(int x) {
        boolean[] y = new boolean[x];
        y[0] = x > 10;
        return y[0];
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
