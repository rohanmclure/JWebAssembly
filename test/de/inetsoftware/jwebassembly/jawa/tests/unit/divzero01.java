package de.inetsoftware.jwebassembly.jawa.tests.unit;

import de.inetsoftware.jwebassembly.api.annotation.Export;
import de.inetsoftware.jwebassembly.jawa.tests.RunTest;

import static de.inetsoftware.jwebassembly.jawa.util.Print.puti;

@RunTest(input = "", intInput = 8, output = "0")
@RunTest(input = "", intInput = 1, output = "3")
@RunTest(input = "", intInput = 0, exception = "DIV_BY_ZERO")
public class divzero01 {
    static int m(int x) {
        return 3 / x;
    }

    @Export
    public static void main(String[] args) {
        puti(m(args.length));
    }
}
