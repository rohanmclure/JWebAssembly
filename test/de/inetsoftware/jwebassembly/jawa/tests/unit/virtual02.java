package de.inetsoftware.jwebassembly.jawa.tests.unit;

import de.inetsoftware.jwebassembly.api.annotation.Export;
import de.inetsoftware.jwebassembly.jawa.tests.RunTest;

import static de.inetsoftware.jwebassembly.jawa.util.Print.puti;

@RunTest(input = "1", output = "44")
@RunTest(input = "", output = "33")
public class virtual02 {

    @Export
    public static void main(String[] args) {
        if (args.length == 0)
            puti(C.m(-1));
        else
            puti(C.m(1));
    }

    static class A {
        int m() {
            return 33;
        }
    }

    static class B extends A {
        int m() {
            return 44;
        }
    }

    static class C {
        static int m(int x) {
            A a = null;
            if (x < 0) a = new A();
            else a = new B();
            return a.m();
        }
    }
}