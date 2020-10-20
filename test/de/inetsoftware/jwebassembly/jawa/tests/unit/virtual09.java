package de.inetsoftware.jwebassembly.jawa.tests.unit;

import de.inetsoftware.jwebassembly.api.annotation.Export;
import de.inetsoftware.jwebassembly.jawa.tests.RunTest;

import static de.inetsoftware.jwebassembly.jawa.util.Print.puti;

@RunTest(input = "", output = "-107")
@RunTest(input = "1", output = "-110")
@RunTest(input = "2 2", output = "-111")
@RunTest(input = "3 3 3", output = "99")
public class virtual09 {

    @Export
    public static void main(String[] args) {
        puti(W.qq(args.length));
    }

    static class A {
        int x() {
            return -107;
        }

        int y() {
            return -108;
        }

        int z() {
            return -109;
        }
    }

    static class B extends A {
        int y() {
            return -110;
        }
    }

    static class C extends B {
        int z() {
            return -111;
        }
    }

    static class W {
        static int qq(int x) {
            A a = new C();
            if (x == 0) return a.x();
            if (x == 1) return a.y();
            if (x == 2) return a.z();
            return 99;
        }
    }
}
