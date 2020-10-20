package de.inetsoftware.jwebassembly.jawa.tests.unit;

import de.inetsoftware.jwebassembly.api.annotation.Export;
import de.inetsoftware.jwebassembly.jawa.tests.RunTest;

import static de.inetsoftware.jwebassembly.jawa.util.Print.puti;

@RunTest(input = "", intInput = 0, output = "0")
@RunTest(input = "", intInput = 5, output = "10")
public class for06 {
    static int f;

    static int t(int a) {
        for06.f = a;
        int sum = 0;
        for (; for06.next(); ) {
            sum = sum + for06.f;
        }
        return sum;
    }

    static boolean next() {
        boolean ok = for06.f > 0;
        for06.f = for06.f - 1;
        return ok;
    }

    @Export
    public static void main(String[] args) {
        puti(t(args.length));
    }

}
