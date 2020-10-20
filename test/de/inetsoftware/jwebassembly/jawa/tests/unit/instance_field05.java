package de.inetsoftware.jwebassembly.jawa.tests.unit;

import de.inetsoftware.jwebassembly.api.annotation.Export;
import de.inetsoftware.jwebassembly.jawa.tests.RunTest;

import static de.inetsoftware.jwebassembly.jawa.util.Print.puti;

@RunTest(input = "1 2 3", output = "80")
public class instance_field05 {

    static class B {
        int f;
        boolean y;
    }

    static class C extends B {
        int[] a;
        String g;

        static int m(int x) {
            C c = new C();
            c.f = x + 77;
            c.a = null;
            c.g = "";
            C d = new C();
            d.f = 99;
            d.y = true;
            return c.f;
        }
    }

    @Export
    public static void main(String[] args) {
        puti(C.m(args.length));
    }

}
