package de.inetsoftware.jwebassembly.jawa.unit;

import de.inetsoftware.jwebassembly.api.annotation.Export;
import de.inetsoftware.jwebassembly.jawa.RunTest;

import static de.inetsoftware.jwebassembly.jawa.Print.puts;

@RunTest(input = "", output = "0")
public class null02 {
    static boolean m(int a) {
	String s = "hello";
	return s == null;
    }
    @Export
    public static void main(String[] args) {
        puts(m(args.length) ? "true" : "false");
    }
}
