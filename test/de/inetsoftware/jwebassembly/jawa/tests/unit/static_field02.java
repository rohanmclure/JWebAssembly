package de.inetsoftware.jwebassembly.jawa.tests.unit;

import de.inetsoftware.jwebassembly.api.annotation.Export;
import de.inetsoftware.jwebassembly.jawa.tests.RunTest;

import static de.inetsoftware.jwebassembly.jawa.util.Print.puti;

@RunTest(input = "", intInput = 94, output = "94")
@RunTest(input = "", intInput = 101, output = "101")
public class static_field02 {
    static int f;

    static int n(int x) {
        D.f = x;
        static_field02.f = -x;
        return D.f;
    }

    static class D {
        static int f;
    }

    @Export
    public static void main(String[] args) {
        puti(n(args.length));
    }
}
