package de.inetsoftware.jwebassembly.jawa.tests.unit;

import de.inetsoftware.jwebassembly.api.annotation.Export;
import de.inetsoftware.jwebassembly.jawa.tests.RunTest;

import static de.inetsoftware.jwebassembly.jawa.util.Print.puti;

@RunTest(input = "", output = "41")
@RunTest(input = "1", output = "42")
public class args01 {
    static int m(int a) {
        return args01.f(a, 12, 13, 14);
    }

    static int f(int a, int b, int c, int d) {
        return a - b - c - d;
    }

    @Export
    public static void main(String[] args) {
        puti(m(args.length + 80));
    }
}
