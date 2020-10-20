package de.inetsoftware.jwebassembly.jawa.tests.unit;

import de.inetsoftware.jwebassembly.api.annotation.Export;
import de.inetsoftware.jwebassembly.jawa.tests.RunTest;

import static de.inetsoftware.jwebassembly.jawa.util.Print.puti;

@RunTest(input = "", intInput = 0, output = "0")
@RunTest(input = "", intInput = 5, output = "15")
public class while05 {
    static int t(int a) {
        int sum = 0;
        while (true) {
            if (a <= 0) return sum;
            sum = sum + a;
            a = a - 1;
        }
    }

    @Export
    public static void main(String[] args) {
        puti(t(args.length));
    }
}
