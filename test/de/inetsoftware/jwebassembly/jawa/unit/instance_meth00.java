package de.inetsoftware.jwebassembly.jawa.unit;

import de.inetsoftware.jwebassembly.api.annotation.Export;
import de.inetsoftware.jwebassembly.jawa.RunTest;

import static de.inetsoftware.jwebassembly.jawa.Print.puti;

@RunTest(input="1 2 3 4 5", output = "10")
@RunTest(input="1 2 3 4 5 6 7 8 9 10 11", output = "22")
public class instance_meth00 {


    static class A {
        int v(int a, int b) {
            return b;
        }

        int m(int a, int b) {
            return this.v(a, b) + b;
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
