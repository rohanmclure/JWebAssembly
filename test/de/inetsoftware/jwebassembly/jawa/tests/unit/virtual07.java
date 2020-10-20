package de.inetsoftware.jwebassembly.jawa.tests.unit;

import de.inetsoftware.jwebassembly.api.annotation.Export;
import de.inetsoftware.jwebassembly.jawa.tests.RunTest;

import static de.inetsoftware.jwebassembly.jawa.util.Print.puti;

@RunTest(input = "", output = "33")
@RunTest(input = "1", output = "88")
@RunTest(input = "2 2", output = "99")
public class virtual07 {

    @Export
    public static void main(String[] args) {
        puti(X.p(args.length));
    }

    static class A {
        int sum() {
            return this.a() + this.b();
        }

        int a() {
            return 11;
        }

        int b() {
            return 22;
        }
    }

    static class B extends A {
        int a() {
            return 55;
        }

        int b() {
            return 33;
        }
    }

    static class C extends B {
        int b() {
            return 44;
        }
    }

    static class X {
        static int p(int x) {
            A a = new A();
            if (x == 1) a = new B();
            if (x == 2) a = new C();
            return a.sum();
        }
    }
}
