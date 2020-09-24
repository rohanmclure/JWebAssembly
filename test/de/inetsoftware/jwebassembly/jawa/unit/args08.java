package de.inetsoftware.jwebassembly.jawa.unit;

import de.inetsoftware.jwebassembly.api.annotation.Export;
import de.inetsoftware.jwebassembly.jawa.RunTest;

import static de.inetsoftware.jwebassembly.jawa.Print.puti;

@RunTest(input = "1 2", output = "24")
public class args08 {
    static int m(int a) {
        return args08.f(a, 12);
    }

    static int f(int a, int b) {
        return a * b;
    }

    @Export
    public static void main(String[] args) {
        puti(m(args.length));
    }
}

