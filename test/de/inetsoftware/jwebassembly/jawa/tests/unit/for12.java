package de.inetsoftware.jwebassembly.jawa.tests.unit;

import de.inetsoftware.jwebassembly.api.annotation.Export;
import de.inetsoftware.jwebassembly.jawa.tests.RunTest;

import static de.inetsoftware.jwebassembly.jawa.util.Print.puti;

@RunTest(input = "", intInput = 0, output = "0")
@RunTest(input = "", intInput = 5, output = "25")
public class for12 {
    static int f;

    static int t(int a) {
        for12.f = a;
        int sum = 0;
        for (int i = 0; i < for12.f; i = i + 1) {
            sum = sum + for12.f;
        }
        return sum;
    }

    @Export
    public static void main(String[] args) {
        puti(t(args.length));
    }

}
