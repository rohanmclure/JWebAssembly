package de.inetsoftware.jwebassembly.jawa.tests.unit;

import de.inetsoftware.jwebassembly.api.annotation.Export;
import de.inetsoftware.jwebassembly.jawa.tests.RunTest;

import static de.inetsoftware.jwebassembly.jawa.util.Print.puti;

@RunTest(input = "1", output = "81")
@RunTest(input = "1 2 3", output = "83")
public class args02 {
    static int m(int a) {
        int j = args02.f();
        return a;
    }

    static int f() {
        return 99888;
    }

    @Export
    public static void main(String[] args) {
        puti(m(args.length + 80));
    }
}
