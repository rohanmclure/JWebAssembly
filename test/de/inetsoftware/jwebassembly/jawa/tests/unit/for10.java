package de.inetsoftware.jwebassembly.jawa.tests.unit;

import de.inetsoftware.jwebassembly.api.annotation.Export;
import de.inetsoftware.jwebassembly.jawa.tests.RunTest;

import static de.inetsoftware.jwebassembly.jawa.util.Print.puti;

@RunTest(input = "", intInput = 0, output = "0")
@RunTest(input = "", intInput = 5, output = "15")
public class for10 {
    static int t(int a) {
        for (int sum = 0; true; a = a - 1) {
            if (a <= 0) return sum;
            sum = sum + a;
        }
    }

    @Export
    public static void main(String[] args) {
        puti(t(args.length));
    }

}
