package de.inetsoftware.jwebassembly.jawa.tests.unit;

import de.inetsoftware.jwebassembly.api.annotation.Export;
import de.inetsoftware.jwebassembly.jawa.tests.RunTest;

import static de.inetsoftware.jwebassembly.jawa.util.Print.puti;

@RunTest(input = "", output = "-9")
@RunTest(input = "", intInput = 123, output = "123")
public class static_field05 {
    static int[] a;
    static boolean f;
    static String g;

    static int n(int x) {
        D.f = x;
        static_field05.a = null;
        static_field05.f = false;
        static_field05.g = "help";
        return D.f;
    }

    static class D {
        static int f;
    }

    @Export
    public static void main(String[] args) {
        if (args.length == 0) puti(n(-9));
        else puti(n(args.length));
    }
}
