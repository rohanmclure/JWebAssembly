package de.inetsoftware.jwebassembly.jawa.unit;

import de.inetsoftware.jwebassembly.api.annotation.Export;
import de.inetsoftware.jwebassembly.jawa.RunTest;

import static de.inetsoftware.jwebassembly.jawa.Print.puti;

@RunTest(input = "", intInput = 44, output = "44")
@RunTest(input = "", output = "-9")
public class static_field04 {
    static int a;
    static boolean f;
    static int g;

    static int n(int x) {
        D.f = x;
        static_field04.a = 99;
        static_field04.f = false;
        static_field04.g = 77;
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
