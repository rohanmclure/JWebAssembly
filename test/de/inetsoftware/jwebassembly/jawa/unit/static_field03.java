package de.inetsoftware.jwebassembly.jawa.unit;

import de.inetsoftware.jwebassembly.api.annotation.Export;
import de.inetsoftware.jwebassembly.jawa.RunTest;

import static de.inetsoftware.jwebassembly.jawa.Print.puti;

@RunTest(input="", output = "-9")
@RunTest(input="",intInput=23,output="23")
public class static_field03 {
    static int a;
    static int f;
    static int g;

    static int n(int x) {
        static_field03.f = x;
        static_field03.a = 99;
        static_field03.g = 77;
        return static_field03.f;
    }

    @Export
    public static void main(String[] args) {
        if (args.length == 0) puti(n(-9));
        else puti(n(args.length));
    }
}
