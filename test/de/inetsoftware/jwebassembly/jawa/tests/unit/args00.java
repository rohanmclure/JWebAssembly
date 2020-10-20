package de.inetsoftware.jwebassembly.jawa.tests.unit;

import de.inetsoftware.jwebassembly.api.annotation.Export;
import de.inetsoftware.jwebassembly.jawa.tests.RunTest;

import static de.inetsoftware.jwebassembly.jawa.util.Print.puti;

@RunTest(input = "1", output = "1")
@RunTest(input = "1 2", output = "2")
public class args00 {
    static int m(int a) {
        return args00.f(a);
    }

    static int f(int a) {
        return a;
    }

    @Export
    public static void main(String[] args) {
        puti(m(args.length));
    }
}
