package de.inetsoftware.jwebassembly.jawa.tests.unit;

import de.inetsoftware.jwebassembly.api.annotation.Export;
import de.inetsoftware.jwebassembly.jawa.tests.RunTest;

import static de.inetsoftware.jwebassembly.jawa.util.Print.puts;

@RunTest(input = "", output = "A")
@RunTest(input = "1", output = "B")
@RunTest(input = "1 2", exception = "FAILED_CAST")
public class checkcast01 {
    static class A {
        @Override
        public String toString() {
            return "A";
        }
    }
    static class B extends A {
        @Override
        public String toString() {
            return "B";
        }
    }
    static class C {
        @Override
        public String toString() {
            return "C";
        }
    }

    static Object run(int a) {
        Object type;
        if (a == 0) {
            type = new A();
        } else if (a == 1) {
            type = new B();
        } else {
            type = new C();
        }
        return type;
    }

    @Export
    public static void main(String[] args) {
        A type = (A) run(args.length);
        puts(type.toString());
    }
}
