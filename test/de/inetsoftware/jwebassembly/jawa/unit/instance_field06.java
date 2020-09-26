package de.inetsoftware.jwebassembly.jawa.unit;

import de.inetsoftware.jwebassembly.api.annotation.Export;
import de.inetsoftware.jwebassembly.jawa.RunTest;

import static de.inetsoftware.jwebassembly.jawa.Print.puti;

@RunTest(input = "1 2 3", output = "80")
public class instance_field06 {

    static class B {
        int f;
        boolean y;
    }

    static class C extends B {
        int[] a;
        String g;

        static int m(int x) {
            B b = new C();
            b.f = x + 77;
            C c = new C();
            c.f = 99;
            c.y = true;
            c.a = null;
            c.g = "";
            return b.f;
        }
    }

    @Export
    public static void main(String[] args) {
        puti(C.m(args.length));
    }
}
