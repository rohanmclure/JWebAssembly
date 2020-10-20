package de.inetsoftware.jwebassembly.jawa.tests.unit;

import de.inetsoftware.jwebassembly.api.annotation.Export;
import de.inetsoftware.jwebassembly.jawa.tests.RunTest;

import static de.inetsoftware.jwebassembly.jawa.util.Print.puti;

@RunTest(input = "1 2 3", output = "4")
public class args06 {
    static int p(int w) {
        return args06.args(w, 1, 2, 3, 4);
    }

    static int args(int w, int a, int b, int c, int d) {
        return d;
    }

    @Export
    public static void main(String[] args) {
        puti(p(args.length));
    }
}
