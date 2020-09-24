package de.inetsoftware.jwebassembly.jawa.unit;

import de.inetsoftware.jwebassembly.api.annotation.Export;
import de.inetsoftware.jwebassembly.jawa.RunTest;

import static de.inetsoftware.jwebassembly.jawa.Print.puti;

@RunTest(input = "3 3 3", output = "80")
public class virtual10 {

    @Export
    public static void main(String[] args) {
        puti(R.m(args.length));
    }

    static class A {
        int v(int a, int b) {
            return a + b;
        }
    }

    static class B extends A {
        int m(int a, int b) {
            return v(a, b) + a + b;
        }
    }

    static class R {
        static int m(int a) {
            B b = new B();
            return b.m(a, 37);
        }
    }

}


