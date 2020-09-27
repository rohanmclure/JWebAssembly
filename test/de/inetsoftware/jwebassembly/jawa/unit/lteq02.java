package de.inetsoftware.jwebassembly.jawa.unit;

import de.inetsoftware.jwebassembly.api.annotation.Export;
import de.inetsoftware.jwebassembly.jawa.RunTest;

import static de.inetsoftware.jwebassembly.jawa.Print.puts;

@RunTest(input = "", intInput = 10, output = "true")
@RunTest(input = "", intInput = 11, output = "true")
@RunTest(input = "", intInput = 15, output = "false")
public class lteq02 {
    static boolean m(int a) {
        int b = 11;
        return a <= b;
    }

    @Export
    public static void main(String[] args) {
        if (m(args.length)) puts("true");
        else puts("false");
    }
}
