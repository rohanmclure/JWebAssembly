package de.inetsoftware.jwebassembly.jawa.unit;

import de.inetsoftware.jwebassembly.api.annotation.Export;
import de.inetsoftware.jwebassembly.jawa.RunTest;

import static de.inetsoftware.jwebassembly.jawa.Print.puti;

@RunTest(input = "", intInput = 1, output = "3")
public class super01 {

    static class A {
        int a;
        int b;
    }

    static class B extends A {
        boolean a;

        static int foo(int asdas) {
            B bee = new B();
            bee.a = true;
            bee.setA();
            return bee.getA();
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
