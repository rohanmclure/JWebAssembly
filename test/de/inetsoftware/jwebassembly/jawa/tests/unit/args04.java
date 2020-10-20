package de.inetsoftware.jwebassembly.jawa.tests.unit;

import de.inetsoftware.jwebassembly.api.annotation.Export;
import de.inetsoftware.jwebassembly.jawa.tests.RunTest;

import static de.inetsoftware.jwebassembly.jawa.util.Print.puti;

@RunTest(input = "1", output = "2")
public class args04 {
    static int p(int w) {
        return args04.args(w, 1, 2, 3, 4);
    }

    static int args(int w, int a, int b, int c, int d) {
        return b;
    }

    @Export
    public static void main(String[] args) {
        puti(p(args.length));
    }
}
