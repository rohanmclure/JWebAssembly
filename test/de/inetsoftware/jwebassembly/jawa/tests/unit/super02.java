package de.inetsoftware.jwebassembly.jawa.tests.unit;

import de.inetsoftware.jwebassembly.api.annotation.Export;
import de.inetsoftware.jwebassembly.jawa.tests.RunTest;

import static de.inetsoftware.jwebassembly.jawa.util.Print.puti;

@RunTest(input = "", intInput = 0, output = "3")
public class super02 {

    static class A {

        int a;
        int b;

    }

    static class B extends A {

        boolean a;

        static int foo(int sfasdfa) {
            B c = new B();
            c.setA();
            c.a = true;
            return c.getA();
        }

        void setA() {
            super.a = 3;
        }

        int getA() {
            return super.a;
        }

    }

    @Export
    public static void main(String[] args) {
        puti(B.foo(args.length));
    }
}
