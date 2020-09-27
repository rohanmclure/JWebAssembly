package de.inetsoftware.jwebassembly.jawa.unit;

import de.inetsoftware.jwebassembly.api.annotation.Export;
import de.inetsoftware.jwebassembly.jawa.RunTest;

import static de.inetsoftware.jwebassembly.jawa.Print.puts;

@RunTest(input = "", intInput = 4, output = "true")
@RunTest(input = "", intInput = 127, output = "true")
public class array04 {
    static boolean m(int x) {
        String[] y = new String[x];
        String s = "foo";
        y[0] = s;
        return y[0] == s;
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
