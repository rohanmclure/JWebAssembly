package de.inetsoftware.jwebassembly.jawa.tests.unit;

import de.inetsoftware.jwebassembly.api.annotation.Export;
import de.inetsoftware.jwebassembly.jawa.tests.RunTest;

import static de.inetsoftware.jwebassembly.jawa.util.Print.puti;

@RunTest(input = "", intInput = 22, output = "55")
@RunTest(input = "", intInput = 11, output = "44")
public class xc_superfield00 {

    static class A {
        int x;
    }

    static class B extends A {
        int x;

        int s() {
            return super.x;
        }
    }

    static class K {
        static int m(int x) {
            B b = new B();
            A a = b;
            a.x = 33;
            b.x = x;
            return b.x + b.s();
        }
    }

    @Export
    public static void main(String[] args) {
        puti(K.m(args.length));
    }
}
