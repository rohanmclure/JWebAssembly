package de.inetsoftware.jwebassembly.jawa.tests.unit;

import de.inetsoftware.jwebassembly.api.annotation.Export;
import de.inetsoftware.jwebassembly.jawa.tests.RunTest;

import static de.inetsoftware.jwebassembly.jawa.util.Print.puts;

@RunTest(input = "", intInput = 0, output = "false")
@RunTest(input = "", intInput = 1, output = "true")
@RunTest(input = "", intInput = 2, output = "true")
public class gteq00 {
    static boolean m(int a) {
        return a >= 1;
    }

    @Export
    public static void main(String[] args) {
        if (m(args.length)) puts("true");
        else puts("false");
    }
}
