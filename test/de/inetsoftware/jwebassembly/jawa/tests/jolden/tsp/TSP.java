package de.inetsoftware.jwebassembly.jawa.tests.jolden.tsp;

import de.inetsoftware.jwebassembly.api.annotation.Export;
import de.inetsoftware.jwebassembly.jawa.tests.RunTest;

import static de.inetsoftware.jwebassembly.jawa.util.Print.*;
import static de.inetsoftware.jwebassembly.jawa.util.Time.currentTimeMillis;
import de.inetsoftware.jwebassembly.jawa.util.*;

/**
 * A Java implementation of the <tt>tsp</tt> Olden benchmark, the traveling
 * salesman problem.
 * <p>
 * <cite>
 * R. Karp, "Probabilistic analysis of partitioning algorithms for the 
 * traveling-salesman problem in the plane."  Mathematics of Operations Research 
 * 2(3):209-224, August 1977
 * </cite>
 **/
@RunTest(input = "-c 10000 -m", exception = "asdasd")
public class TSP
{
  /**
   * Number of cities in the problem.
   **/
  private static int cities;
  /**
   * Set to true if the result should be printed
   **/
  private static boolean printResult = false;
  /**
   * Set to true to print informative messages
   **/
  private static boolean printMsgs = false;

  /**
   * The main routine which creates a tree and traverses it.
   * @param args the arguments to the program
   **/
  @Export
  public static void main(String args[])
  {
    parseCmdLine(args);

    if (printMsgs) {
      puts("Building tree of size ");
      puti(cities);
      putln();
    }

    long start0 = currentTimeMillis();
    Tree  t = Tree.buildTree(cities, false, 0.0, 1.0, 0.0, 1.0);
    long end0 = currentTimeMillis();

    long start1 = currentTimeMillis();
    t.tsp(150);
    long end1 = currentTimeMillis();

    if (printResult) {
      // if the user specifies, print the final result
      t.printVisitOrder();
    }

    if (printMsgs) {
      puts("Tsp build time ");
      putd((end0 - start0)/1000.0);
      putln();
      puts("Tsp time ");
      putd((end1 - start1)/1000.0);
      putln();
      puts("Tsp total time ");
      putd((end1 - start0)/1000.0);
      putln();
    }
    puts("Done!\n");
  }

  /**
   * Parse the command line options.
   * @param args the command line options.
   **/
  private static final void parseCmdLine(String args[])
  {
    int i = 0;
    String arg;

    while (i < args.length) { // && args[i].startsWith("-")) {
      arg = args[i++];

      if (arg.equals("-c")) {
	if (i < args.length)
	  cities = 20; //new Integer(args[i++]).intValue();
//	else throw new Error("-c requires the size of tree");
      } else if (arg.equals("-p")) {
	printResult = true;
      } else if (arg.equals("-m")) {
	printMsgs = true;
      } else if (arg.equals("-h")) {
	usage();
      }
    }
    if (cities == 0) usage();
  }

  /**
   * The usage routine which describes the program options.
   **/
  private static final void usage()
  {
    puts("usage: java TSP -c <num> [-p] [-m] [-h]"); putln();
    puts("    -c number of cities (rounds up to the next power of 2 minus 1)"); putln();
    puts("    -p (print the final result)"); putln();
    puts("    -m (print informative messages)"); putln();
    puts("    -h (print this message)"); putln();
//    System.exit(0);
  }

}

