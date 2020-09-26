package de.inetsoftware.jwebassembly.jawa.unit;

import de.inetsoftware.jwebassembly.api.annotation.Export;
import de.inetsoftware.jwebassembly.jawa.RunTest;

import static de.inetsoftware.jwebassembly.jawa.Print.puti;

@RunTest(input = "1 2 3 4 5 6", output = "6")
public class instance_field02 {
    int f;

    static int m(int x) {
        instance_field02 c = new instance_field02();
        c.f = x;
        return c.f;
    }

    @Export
    public static void main(String[] args) {
        puti(m(args.length));
    }
}
