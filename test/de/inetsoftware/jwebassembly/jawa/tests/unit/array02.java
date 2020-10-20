package de.inetsoftware.jwebassembly.jawa.tests.unit;

import de.inetsoftware.jwebassembly.api.annotation.Export;
import de.inetsoftware.jwebassembly.jawa.tests.RunTest;

import static de.inetsoftware.jwebassembly.jawa.util.Print.puti;

@RunTest(input = "", intInput = 9, output = "9")
@RunTest(input = "", intInput = 120, output = "120")
public class array02 {
    static int m(int x) {
        int[] y = new int[x];
        y[x - 1] = x;
        return y[x - 1];
    }

    @Export
    public static void main(String[] args) {
        puti(m(args.length));
    }
}
