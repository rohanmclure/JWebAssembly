package de.inetsoftware.jwebassembly.jawa.unit;

import de.inetsoftware.jwebassembly.api.annotation.Export;
import de.inetsoftware.jwebassembly.jawa.RunTest;

import static de.inetsoftware.jwebassembly.jawa.Print.puti;

@RunTest(input = "", intInput = 0, output = "0")
@RunTest(input = "", intInput = 5, output = "15")
public class for05 {
    static int t(int a) {
        int sum = 0;
        for (; a > 0; ) {
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
