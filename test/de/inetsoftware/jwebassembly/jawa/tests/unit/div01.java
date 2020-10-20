package de.inetsoftware.jwebassembly.jawa.tests.unit;

import de.inetsoftware.jwebassembly.api.annotation.Export;
import de.inetsoftware.jwebassembly.jawa.tests.RunTest;

import static de.inetsoftware.jwebassembly.jawa.util.Print.puti;

@RunTest(input = "", intInput = 77, output = "9")
@RunTest(input = "", intInput = 0, output = "-12")
public class div01 {
    static int run(int a) {
        return a / 8;
    }

    @Export
    public static void main(String[] args) {
        if (args.length == 0) puti(run(-99));
        else puti(run(args.length));
    }
}
