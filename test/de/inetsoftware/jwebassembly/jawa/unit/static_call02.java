package de.inetsoftware.jwebassembly.jawa.unit;

import de.inetsoftware.jwebassembly.api.annotation.Export;
import de.inetsoftware.jwebassembly.jawa.RunTest;

import static de.inetsoftware.jwebassembly.jawa.Print.puti;

@RunTest(input = "", intInput = 1, output = "45")
public class static_call02 {
    static int m1(int a) {
        static_call02.m2();
        static_call02.m2();
        return 45;
    }

    static void m2() {
        static_call02.m3();
        static_call02.m3();
    }

    static void m3() {
        static_call02.m4();
        static_call02.m4();
    }

    static void m4() {
    }


    @Export
    public static void main(String[] args) {
        puti(m1(args.length));
    }
}
