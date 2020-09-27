package de.inetsoftware.jwebassembly.jawa.unit;

import de.inetsoftware.jwebassembly.api.annotation.Export;
import de.inetsoftware.jwebassembly.jawa.RunTest;

import static de.inetsoftware.jwebassembly.jawa.Print.puti;

@RunTest(input = "", intInput = 3, output = "4")
public class fieldstatic {
    static int a;

    static int foo(int b) {

        fieldstatic a = new fieldstatic();
        fieldstatic.bar();
        return fieldstatic.a;

    }

    static void bar() {
        fieldstatic.a = 4;
    }

    @Export
    public static void main(String[] args) {
        puti(foo(args.length));
    }
}