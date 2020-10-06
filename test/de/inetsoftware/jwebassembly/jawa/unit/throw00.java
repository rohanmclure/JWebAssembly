package de.inetsoftware.jwebassembly.jawa.unit;

import de.inetsoftware.jwebassembly.api.annotation.Export;
import de.inetsoftware.jwebassembly.jawa.RunTest;

import static de.inetsoftware.jwebassembly.jawa.Print.puts;

@RunTest(input = "", intInput = 1, output = "No Exception Caught")
@RunTest(input = "", intInput = 4, output = "Caught ArrayIndexOutOfBounds")
public class throw00 {
    static class A {

    }
    static void run(int x) {
        try {
            if (x < 3)
                throw new Exception("Hello");
            puts("No Exception Caught");
        } catch (Exception e) {
            puts("\"Caught ArrayIndexOutOfBounds\"");
        }
    }

    @Export
    public static void main(String[] args) {
        run(args.length);
    }
}
