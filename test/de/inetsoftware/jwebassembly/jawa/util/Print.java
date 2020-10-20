package de.inetsoftware.jwebassembly.jawa.util;

import de.inetsoftware.jwebassembly.api.annotation.Import;

public class Print {

    @Import( module = "jawa", name = "\u003B\u0004\u0000puti",  js = "(x) => x")
    public static void puti(int i) {}

    @Import( module = "jawa", name = "\u003B\u0004\u0000puts",  js = "(x) => x")
    public static void puts(String s) {}

    public static void putiln(int i) {
        puti(i);
        puts("\n");
    }

    public static void putsln(String s) {
        puts(s);
        puts("\n");
    }

    public static void putln() {
        puts("\n");
    }

    public static void println() { putln(); }

    public static void println(String s) {
        puts(s);
        putln();
    }

    public static void print(String s) {
        puts(s);
    }

    public static void print(int i) {
        puti(i);
    }

    public static void putd(double i) {
        long l = (long) i;
        putl(l);

        i = (i - l) * 1_000_000;

        l = (long) (i < 0 ? -i : i);
        puts(".");

        if (l == 0) {
            puti(0);
            return;
        }

        int len = 0;
        for (long test = l; test != 0; test = test / 10) {
            len++;
        }
        while (len != 6) {
            puti(0);
            len++;
        }

        while (l % 10 == 0)
            l = l / 10;

        putl(l);
    }


//    public static void print(double i, int precision) {
//        long l = (long) i;
//        putl(l);
//
//        i = (i - l) * (Math.pow(10,precision));
//
//        l = (long) (i < 0 ? -i : i);
//
//        puts(".");
//        putl(l);
//    }

    public static void putl(long i) {
        if (i == Long.MIN_VALUE) {
            puts("-9223372036854775808");
            return;
        }
        if (i < 0) {
            puts("-");
            i = -i;
        }
        if (i / 10 != 0)
            putl(i/10);
        puti((int) (i % 10));
    }

}
