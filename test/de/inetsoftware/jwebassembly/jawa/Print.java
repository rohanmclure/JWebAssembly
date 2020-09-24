package de.inetsoftware.jwebassembly.jawa;

import de.inetsoftware.jwebassembly.api.annotation.Import;

public class Print {

    @Import( module = "jawa", name = "\u003B\u0004\u0000puti",  js = "(x) => x")
    public static void puti(int i) {}

    @Import( module = "jawa", name = "\u003B\u0004\u0000puts",  js = "(x) => x")
    public static void puts(String s) {}

}
