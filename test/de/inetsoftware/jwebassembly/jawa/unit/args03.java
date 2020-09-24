package de.inetsoftware.jwebassembly.jawa.unit;

import de.inetsoftware.jwebassembly.api.annotation.Export;
import de.inetsoftware.jwebassembly.jawa.RunTest;

import static de.inetsoftware.jwebassembly.jawa.Print.puti;

@RunTest(input = "", output = "1")
@RunTest(input = "1", output = "2")
@RunTest(input = "1 2", output = "3")
@RunTest(input = "1 2 3", output = "4")
@RunTest(input = "1 2 3 4", output = "33")
public class args03 {
    static int p(int w) {
        return args03.args(w, 1, 2, 3, 4);
    }

    static int args(int w, int a, int b, int c, int d) {
        if (w == 0) return a;
        if (w == 1) return b;
        if (w == 2) return c;
        if (w == 3) return d;
        return 33;
    }
    @Export
    public static void main(String[] args) {
        puti(p(args.length));
    }
}
