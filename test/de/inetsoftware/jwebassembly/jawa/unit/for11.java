package de.inetsoftware.jwebassembly.jawa.unit;

import de.inetsoftware.jwebassembly.api.annotation.Export;
import de.inetsoftware.jwebassembly.jawa.RunTest;

import static de.inetsoftware.jwebassembly.jawa.Print.puti;

@RunTest(input = "", intInput = 0, output = "0")
@RunTest(input = "", intInput = 5, output = "10")
public class for11 {
    static int f;

    static int t(int a) {
        for11.f = a;
        int sum = 0;
        for (int i = 0; for11.next(); i = i + 1) {
            sum = sum + for11.f;
        }
        return sum;
    }

    static boolean next() {
        boolean ok = for11.f > 0;
        for11.f = for11.f - 1;
        return ok;
    }

    @Export
    public static void main(String[] args) {
        puti(t(args.length));
    }

}
