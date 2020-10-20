package de.inetsoftware.jwebassembly.jawa.tests.unit;

import de.inetsoftware.jwebassembly.api.annotation.Export;
import de.inetsoftware.jwebassembly.jawa.tests.RunTest;

import static de.inetsoftware.jwebassembly.jawa.util.Print.puti;

@RunTest(input = "", intInput = 0, output = "1")
@RunTest(input = "", intInput = 2, output = "1")
@RunTest(input = "", intInput = 3, output = "2")
@RunTest(input = "", intInput = 4, output = "6")
public class for13 {
    static int f;

    static int t(int a) {
        for13.f = a;
        int prod = 1;
        for (int i = 1; i < a; i = i + 1) {
            prod = prod * i;
        }
        return prod;
    }

    @Export
    public static void main(String[] args) {
        puti(t(args.length));
    }

}
