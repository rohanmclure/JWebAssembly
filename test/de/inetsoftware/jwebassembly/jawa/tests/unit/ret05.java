package de.inetsoftware.jwebassembly.jawa.tests.unit;

import de.inetsoftware.jwebassembly.api.annotation.Export;
import de.inetsoftware.jwebassembly.jawa.tests.RunTest;

import static de.inetsoftware.jwebassembly.jawa.util.Print.puti;

@RunTest(input = "", intInput = 87, output = "87")
@RunTest(input = "", intInput = 12, output = "12")
public class ret05 {
    static int m(int x) {
        int f = ret05.n(x);
        ret05.p();
        return f;
    }

    static int n(int x) {
        if (true) return x;
        else return 0;
    }

    static void p() {
        if (true) return;
        return;
    }

    @Export
    public static void main(String[] args) {
        puti(m(args.length));
    }
}
