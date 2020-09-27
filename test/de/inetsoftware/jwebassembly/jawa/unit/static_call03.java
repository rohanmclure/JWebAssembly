package de.inetsoftware.jwebassembly.jawa.unit;

import de.inetsoftware.jwebassembly.api.annotation.Export;
import de.inetsoftware.jwebassembly.jawa.RunTest;

import static de.inetsoftware.jwebassembly.jawa.Print.puti;

@RunTest(input = "", intInput = 1, output = "8")
public class static_call03 {
    static int calls;

    static int m1(int a) {
        static_call03.m2();
        static_call03.m2();
        return static_call03.calls;
    }

    static void m2() {
        static_call03.m3();
        static_call03.m3();
    }

    static void m3() {
        static_call03.m4();
        static_call03.m4();
    }

    static void m4() {
        static_call03.calls = static_call03.calls + 1;
    }

    @Export
    public static void main(String[] args) {
        puti(m1(args.length));
    }
}
