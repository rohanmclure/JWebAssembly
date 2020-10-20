package de.inetsoftware.jwebassembly.jawa.tests.jolden.bisort;

import de.inetsoftware.jwebassembly.api.annotation.Export;
import de.inetsoftware.jwebassembly.jawa.tests.RunTest;

import static de.inetsoftware.jwebassembly.jawa.util.Print.*;
import static de.inetsoftware.jwebassembly.jawa.util.Time.currentTimeMillis;

/**
 * A Java implementation of the <tt>bisort</tt> Olden benchmark.  The Olden
 * benchmark implements a Bitonic Sort as described in :
 * <p><cite>
 * G. Bilardi and A. Nicolau, "Adaptive Bitonic Sorting: An optimal parallel
 * algorithm for shared-memory machines." SIAM J. Comput. 18(2):216-228, 1998.
 * </cite>
 * <p>
 * The benchmarks sorts N numbers where N is a power of 2.  If the user provides
 * an input value that is not a power of 2, then we use the nearest power of
 * 2 value that is less than the input value.
 **/
@RunTest(input = "-s 250000 -m", exception = "asd")
public class BiSort {
    /**
     * The number of values to sort.
     **/
    private static int size = 0;

    /**
     * Print information messages
     **/
    private static boolean printMsgs = false;
    /**
     * Print the tree after each step
     **/
    private static boolean printResults = false;

    /**
     * The main routine which creates a tree and sorts it a couple of times.
     *
     * @param args the command line arguments
     **/
    @Export
    public static final void main(String[] args) {
        parseCmdLine(args);

        if (printMsgs) {
            puts("Bisort with ");
            puti(size);
            puts(" values");
            putln();
        }

        long start2 = currentTimeMillis();
        Value tree = Value.createTree(size, 12345678);
        long end2 = currentTimeMillis();

        int sval = Value.random(245867) % Value.RANGE;

        if (printResults) {
            tree.inOrder();
            puti(sval);
            putln();
        }

        if (printMsgs)
            println("BEGINNING BITONIC SORT ALGORITHM HERE");


        long start0 = currentTimeMillis();
        sval = tree.bisort(sval, Value.FORWARD);
        long end0 = currentTimeMillis();

        if (printResults) {
            tree.inOrder();
            puti(sval);
            putln();
        }


        long start1 = currentTimeMillis();
        sval = tree.bisort(sval, Value.BACKWARD);
        long end1 = currentTimeMillis();

        if (printResults) {
            tree.inOrder();
            puti(sval);
            putln();
        }

        if (printMsgs) {
            puts("Creation time: ");
            putd((end2 - start2) / 1000.0);
            putln();
            puts("Time to sort forward = ");
            putd((end0 - start0) / 1000.0);
            putln();
            puts("Time to sort backward = ");
            putd((end1 - start1) / 1000.0);
            putln();
            puts("Total: ");
            putd((end1 - start0) / 1000.0);
            putln();
        }
        puts("Done!\n");
    }

    /**
     * Parse the command line options.
     *
     * @param args the command line options.
     **/
    private static final void parseCmdLine(String[] args) {
        int i = 0;
        String arg;

        while (i < args.length) {
//        while (i < args.length && args[i].startsWith("-")) {
            arg = args[i++];

            // check for options that require arguments
            if (arg.equals("-s")) {
                if (i < args.length) {
                    size = 100;
//                    size = new Integer(args[i++]).intValue();
                } else {
                    return;
//                    throw new Error("-l requires the number of levels");
                }
            } else if (arg.equals("-m")) {
                printMsgs = true;
            } else if (arg.equals("-p")) {
                printResults = true;
            } else if (arg.equals("-h")) {
                usage();
            }
        }
        if (size == 0) usage();
    }

    /**
     * The usage routine which describes the program options.
     **/
    private static final void usage() {
        println("usage: java BiSort -s <size> [-p] [-i] [-h]");
        println("    -s the number of values to sort");
        println("    -m (print informative messages)");
        println("    -p (print the binary tree after each step)");
        println("    -h (print this message)");
//        System.exit(0);
    }

}
