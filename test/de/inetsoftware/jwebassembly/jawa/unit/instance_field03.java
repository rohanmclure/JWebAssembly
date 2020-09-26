package de.inetsoftware.jwebassembly.jawa.unit;

import de.inetsoftware.jwebassembly.api.annotation.Export;
import de.inetsoftware.jwebassembly.jawa.RunTest;

import static de.inetsoftware.jwebassembly.jawa.Print.puti;

@RunTest(input = "1 2", output = "90")
public class instance_field03 {
    int[] a;
    int f;
    String g;

    static int m(int x) {
        instance_field03 c = new instance_field03();
        c.f = x + 88;
        c.a = null;
        c.g = "";
        return c.f;
    }

    @Export
    public static void main(String[] args) {
        puti(m(args.length));
    }
}
