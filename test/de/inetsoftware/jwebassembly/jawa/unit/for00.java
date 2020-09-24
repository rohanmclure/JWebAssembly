package de.inetsoftware.jwebassembly.jawa.unit;

import de.inetsoftware.jwebassembly.api.annotation.Export;
import de.inetsoftware.jwebassembly.jawa.RunTest;

import static de.inetsoftware.jwebassembly.jawa.Print.puti;

@RunTest(input = "1", output = "2")
public class for00 {
    static int foo(int a) {
        int bar = 3;
        for (int i = 0; i < 3; i = i + 1) {
            bar = i;
        }
        return bar;
    }

    @Export
    public static void main(String[] args) {
        puti(foo(args.length));
    }
}
