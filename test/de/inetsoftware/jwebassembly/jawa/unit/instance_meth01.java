package de.inetsoftware.jwebassembly.jawa.unit;

import de.inetsoftware.jwebassembly.api.annotation.Export;
import de.inetsoftware.jwebassembly.jawa.RunTest;

import static de.inetsoftware.jwebassembly.jawa.Print.puti;

@RunTest(input = "1 2 3", output = "66")
@RunTest(input = "1 2 3 4 5", output = "66")
public class instance_meth01 {

    static class A {
        int v(int a, int b) {
            return a;
        }

        int m(int a, int b) {
            return this.v(a, b) + a;
        }
    }

    static class R {
        static int m(int a) {
            A o = new A();
            return o.m(33, a);
        }
    }

    @Export
    public static void main(String[] args) {
        puti(R.m(args.length));
    }
}
