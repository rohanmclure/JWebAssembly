package de.inetsoftware.jwebassembly.jawa.tests.unit;

import de.inetsoftware.jwebassembly.api.annotation.Export;
import de.inetsoftware.jwebassembly.jawa.tests.RunTest;

import static de.inetsoftware.jwebassembly.jawa.util.Print.puti;

@RunTest(input = "", intInput = 0, output = "0")
@RunTest(input = "", intInput = 5, output = "15")
public class while03 {
    static int t(int a) {
        int sum = 0;
        while (a > 0) {
            sum = sum + a;
            a = a - 1;
        }
        return sum;
    }

    @Export
    public static void main(String[] args) {
        puti(t(args.length));
    }
}
