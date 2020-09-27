package de.inetsoftware.jwebassembly.jawa.unit;

import de.inetsoftware.jwebassembly.api.annotation.Export;
import de.inetsoftware.jwebassembly.jawa.RunTest;

import static de.inetsoftware.jwebassembly.jawa.Print.puts;

@RunTest(input = "", intInput = 0, output = "true")
@RunTest(input = "", intInput = 1, output = "false")
public class str00 {
    static boolean ee(int x) {
        String s = "foo";
        String t = "bar";
        if (x == 0) s = str00.id(t);
        return s == t;
    }

    static String id(String y) {
        return y;
    }

    @Export
    public static void main(String[] args) {
        if (ee(args.length)) puts("true");
        else puts("false");
    }
}
