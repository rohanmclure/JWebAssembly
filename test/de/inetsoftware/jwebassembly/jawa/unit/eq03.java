package de.inetsoftware.jwebassembly.jawa.unit;

import de.inetsoftware.jwebassembly.api.annotation.Export;
import de.inetsoftware.jwebassembly.jawa.RunTest;

import static de.inetsoftware.jwebassembly.jawa.Print.puts;

@RunTest(input = "", intInput = 0, output = "true")
public class eq03 {
    static boolean m(int a) {
        String s = "foo";
        return s == s;
    }

    @Export
    public static void main(String[] args) {
        if (m(args.length)) puts("true");
        else puts("false");
    }
}
