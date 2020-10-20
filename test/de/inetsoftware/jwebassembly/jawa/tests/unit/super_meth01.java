package de.inetsoftware.jwebassembly.jawa.tests.unit;

import de.inetsoftware.jwebassembly.api.annotation.Export;
import de.inetsoftware.jwebassembly.jawa.tests.RunTest;

import static de.inetsoftware.jwebassembly.jawa.util.Print.puti;

@RunTest(input = "", intInput = 5, output = "115")
@RunTest(input = "", intInput = 7, output = "117")
public class super_meth01 {

    static class A {
        int x() {
            return 10;
        }
    }

    static class B extends A {
        int x() {
            return 100;
        }

        int s(int a) {
            return a + this.x() + super.x();
        }
    }

    static class UU {
        static int mm(int a) {
            B b = new B();
            return b.s(a);
        }
    }

    @Export
    public static void main(String[] args) {
        puti(UU.mm(args.length));
    }
}
