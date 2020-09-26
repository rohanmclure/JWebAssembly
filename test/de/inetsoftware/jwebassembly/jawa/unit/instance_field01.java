package de.inetsoftware.jwebassembly.jawa.unit;

import de.inetsoftware.jwebassembly.api.annotation.Export;
import de.inetsoftware.jwebassembly.jawa.RunTest;

import static de.inetsoftware.jwebassembly.jawa.Print.puti;

@RunTest(input = "1", output = "999")
public class instance_field01 {
    int f;

    static int m(int x) {
        instance_field01 c = new instance_field01();
        c.f = 999;
        return c.f;
    }

    @Export
    public static void main(String[] args) {
        puti(m(args.length));
    }
}
