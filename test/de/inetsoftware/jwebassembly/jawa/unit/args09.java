package de.inetsoftware.jwebassembly.jawa.unit;

import de.inetsoftware.jwebassembly.api.annotation.Export;
import de.inetsoftware.jwebassembly.jawa.RunTest;

import static de.inetsoftware.jwebassembly.jawa.Print.puti;

@RunTest(input = "12", output = "0")
public class args09 {
    static int m(int a) {
        return args09.f(a, 12, 13);
    }

    static int f(int a, int b, int c) {
        return 0;
    }

    @Export
    public static void main(String[] args) {
        puti(m(args.length));
    }
}
