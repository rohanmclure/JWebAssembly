package de.inetsoftware.jwebassembly.jawa.tests.unit;

import de.inetsoftware.jwebassembly.api.annotation.Export;
import de.inetsoftware.jwebassembly.jawa.tests.RunTest;

import static de.inetsoftware.jwebassembly.jawa.util.Print.puti;

@RunTest(input = "1", output = "77")
@RunTest(input = "1 2", output = "77")
public class alloc_loop01 {
    int f;
    int g;

    static int m(int x) {
        alloc_loop01 c = null;
        for (int j = 0; j < x; j = j + 1) {
            c = new alloc_loop01();
            c.f = 33;
            c.g = 44;
        }
        return c.f + c.g;
    }

    @Export
    public static void main(String[] args) {
        puti(m(args.length));
    }
}
