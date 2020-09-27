package de.inetsoftware.jwebassembly.jawa.unit;

import de.inetsoftware.jwebassembly.api.annotation.Export;
import de.inetsoftware.jwebassembly.jawa.RunTest;

import static de.inetsoftware.jwebassembly.jawa.Print.puti;

@RunTest(input = "", intInput = 87, output = "87")
@RunTest(input = "", intInput = 7, output = "7")
public class ret04 {
    static int m(int x) {
        int f = ret04.n(x);
        ret04.p();
        return f;
    }

    static int n(int x) {
        return x + x - x;
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
