package de.inetsoftware.jwebassembly.jawa.tests.unit;

import de.inetsoftware.jwebassembly.api.annotation.Export;
import de.inetsoftware.jwebassembly.jawa.tests.RunTest;

import static de.inetsoftware.jwebassembly.jawa.util.Print.puts;

@RunTest(input = "", intInput = 1, output = "No Exception Caught")
@RunTest(input = "", intInput = 4, output = "Caught ArrayIndexOutOfBounds")
public class except00 {
    static class A {

    }
    static void run(int x) {
        try {
            A[] a = new A[3];
            a[x] = null;
            puts("No Exception Caught");
        } catch (ArrayIndexOutOfBoundsException e) {
            puts("Caught ArrayIndexOutOfBounds");
        }
    }

    @Export
    public static void main(String[] args) {
        run(args.length);
    }
}
