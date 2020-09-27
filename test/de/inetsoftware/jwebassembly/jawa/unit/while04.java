package de.inetsoftware.jwebassembly.jawa.unit;

import de.inetsoftware.jwebassembly.api.annotation.Export;
import de.inetsoftware.jwebassembly.jawa.RunTest;

import static de.inetsoftware.jwebassembly.jawa.Print.puti;

@RunTest(input = "", intInput = 0, output = "0")
@RunTest(input = "", intInput = 5, output = "10")
public class while04 {
    static int f;

    static int t(int a) {
        while04.f = a;
        int sum = 0;
        while (while04.next()) {
            sum = sum + while04.f;
        }
        return sum;
    }

    static boolean next() {
        boolean ok = while04.f > 0;
        while04.f = while04.f - 1;
        return ok;
    }

    @Export
    public static void main(String[] args) {
        puti(t(args.length));
    }
}
