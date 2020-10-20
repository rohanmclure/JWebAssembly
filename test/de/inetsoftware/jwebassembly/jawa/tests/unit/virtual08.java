package de.inetsoftware.jwebassembly.jawa.tests.unit;

import de.inetsoftware.jwebassembly.api.annotation.Export;
import de.inetsoftware.jwebassembly.jawa.tests.RunTest;

import static de.inetsoftware.jwebassembly.jawa.util.Print.puti;

@RunTest(input = "", output = "88")
@RunTest(input = "1", output = "-22")
@RunTest(input = "2 2", output = "1815")
@RunTest(input = "3 3 3", output = "0")
public class virtual08 {

    @Export
    public static void main(String[] args) {
        puti(X.m(args.length));
    }

    static class Exp {
        void y() {
        }

        int op(int a, int b) {
            return 0;
        }

        int x() {
            return -1;
        }
    }

    static class Add extends Exp {
        int op(int a, int b) {
            return a + b;
        }

        int x() {
            return -5;
        }

        void z() {
        }
    }

    static class Sub extends Exp {
        int op(int a, int b) {
            return a - b;
        }

        void f1() {
        }

        void f2() {
        }

        void f3() {
        }
    }

    static class Mul extends Exp {
        int op(int a, int b) {
            return a * b;
        }

        void f4() {
        }

        void f5() {
        }

        void f6() {
        }
    }

    static class X {
        static int m(int a) {
            Exp e = new Exp();
            if (a == 0) e = new Add();
            if (a == 1) e = new Sub();
            if (a == 2) e = new Mul();
            return e.op(33, 55);
        }
    }
}