package de.inetsoftware.jwebassembly.jawa.tests.unit;

import de.inetsoftware.jwebassembly.api.annotation.Export;
import de.inetsoftware.jwebassembly.jawa.tests.RunTest;

import static de.inetsoftware.jwebassembly.jawa.util.Print.puti;

@RunTest(input = "", intInput = 9, output = "9")
@RunTest(input = "", intInput = 100, output = "100")
public class static_field01 {
    static int f;

    static int n(int x) {
        static_field01.f = x;
        return static_field01.f;
    }

    @Export
    public static void main(String[] args) {
        puti(n(args.length));
    }
}
