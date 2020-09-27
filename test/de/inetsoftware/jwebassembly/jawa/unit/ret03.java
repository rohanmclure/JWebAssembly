package de.inetsoftware.jwebassembly.jawa.unit;

import de.inetsoftware.jwebassembly.api.annotation.Export;
import de.inetsoftware.jwebassembly.jawa.RunTest;

import static de.inetsoftware.jwebassembly.jawa.Print.puti;

@RunTest(input = "", intInput = 85, output = "85")
@RunTest(input = "", intInput = 13, output = "13")
public class ret03 {
    static int m(int x) {
        int f = ret03.n(x);
        int g = ret03.n(0 - x);
        return f;
    }

    static int n(int x) {
        return x;
    }

    @Export
    public static void main(String[] args) {
        puti(m(args.length));
    }
}
