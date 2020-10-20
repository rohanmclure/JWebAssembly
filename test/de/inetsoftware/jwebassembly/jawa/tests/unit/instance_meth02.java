package de.inetsoftware.jwebassembly.jawa.tests.unit;

import de.inetsoftware.jwebassembly.api.annotation.Export;
import de.inetsoftware.jwebassembly.jawa.tests.RunTest;

import static de.inetsoftware.jwebassembly.jawa.util.Print.puti;

@RunTest(input = "1 2 3 4 5 6 7", output = "80")
public class instance_meth02 {

    static class A {
        int v(int a, int b) {
            return a + b;
        }

        int m(int a, int b) {
            return this.v(a, b) + a + b;
        }
    }

    static class R {
        static int m(int a) {
            A o = new A();
            return o.m(a, 33);
        }
    }

    @Export
    public static void main(String[] args) {
        puti(R.m(args.length));
    }
}
