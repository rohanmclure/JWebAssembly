package de.inetsoftware.jwebassembly.jawa.tests.unit;

import de.inetsoftware.jwebassembly.api.annotation.Export;
import de.inetsoftware.jwebassembly.jawa.tests.RunTest;

import static de.inetsoftware.jwebassembly.jawa.util.Print.puti;

@RunTest(input = "", intInput = 0, output = "0")
@RunTest(input = "", intInput = 1, output = "0")
public class xc_break01 {
    static int m(int a) {
        while (true) {
            if (a == 0) break;
            a = a - 1;
        }
        return 0;
    }

    @Export
    public static void main(String[] args) {
        puti(m(args.length));
    }
}
