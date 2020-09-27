package de.inetsoftware.jwebassembly.jawa.unit;

import de.inetsoftware.jwebassembly.api.annotation.Export;
import de.inetsoftware.jwebassembly.jawa.RunTest;

import static de.inetsoftware.jwebassembly.jawa.Print.puts;

@RunTest(input = "", intInput = 0, output = "false")
@RunTest(input = "", intInput = 1, output = "false")
@RunTest(input = "", intInput = 2, output = "true")
public class gt01 {
    static boolean m(int a) {
        int b = 1;
        return a > b;
    }

    @Export
    public static void main(String[] args) {
        if (m(args.length)) puts("true");
        else puts("false");
    }
}
