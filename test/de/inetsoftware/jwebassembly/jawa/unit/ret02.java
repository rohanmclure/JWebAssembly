package de.inetsoftware.jwebassembly.jawa.unit;

import de.inetsoftware.jwebassembly.api.annotation.Export;
import de.inetsoftware.jwebassembly.jawa.RunTest;

import static de.inetsoftware.jwebassembly.jawa.Print.puti;

@RunTest(input = "", intInput = 87, output = "87")
@RunTest(input = "", intInput = 33, output = "33")
public class ret02 {
    static int m(int x) {
        int f = ret02.n(x);
        ret02.p();
        return f;
    }

    static int n(int x) {
        return x;
    }

    static void p() {
    }

    @Export
    public static void main(String[] args) {
        puti(m(args.length));
    }
}
