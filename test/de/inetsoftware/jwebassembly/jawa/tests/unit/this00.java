package de.inetsoftware.jwebassembly.jawa.tests.unit;

import de.inetsoftware.jwebassembly.api.annotation.Export;
import de.inetsoftware.jwebassembly.jawa.tests.RunTest;

import static de.inetsoftware.jwebassembly.jawa.util.Print.puti;

@RunTest(input = "", intInput = 1, output = "4")
public class this00 {
    int a;
    int b;

    static int foo(int asfsf) {
        this00 ayy = new this00();
        ayy.a = 3;
        ayy.setA();
        return ayy.getA();
    }

    void setA() {
        this.a = 4;
    }

    int getA() {
        return this.a;
    }


    @Export
    public static void main(String[] args) {
        puti(foo(args.length));
    }
}