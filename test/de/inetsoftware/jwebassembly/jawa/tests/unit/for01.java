package de.inetsoftware.jwebassembly.jawa.tests.unit;

import de.inetsoftware.jwebassembly.api.annotation.Export;
import de.inetsoftware.jwebassembly.jawa.tests.RunTest;

import static de.inetsoftware.jwebassembly.jawa.util.Print.puti;

@RunTest(input = "", intInput = 1, output = "0")
@RunTest(input = "1", output = "0")
public class for01 {
    static int foo(int a) {
        int bar = 3;
        for (int i = 0; i < 3; ) {
            if (i == 1)
                break;
            bar = i;
            i = i + 1;
        }
        return bar;
    }

    @Export
    public static void main(String[] args) {
        puti(foo(args.length));
    }
}
