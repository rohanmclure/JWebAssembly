package de.inetsoftware.jwebassembly.jawa.tests.jolden.bh;

import de.inetsoftware.jwebassembly.api.annotation.Export;
import de.inetsoftware.jwebassembly.jawa.tests.RunTest;

import java.util.Enumeration;

import static de.inetsoftware.jwebassembly.jawa.util.Print.*;
import static de.inetsoftware.jwebassembly.jawa.util.Time.currentTimeMillis;
import de.inetsoftware.jwebassembly.jawa.util.Math;

/**
 * A Java implementation of the <tt>bh</tt> Olden benchmark.
 * The Olden benchmark implements the Barnes-Hut benchmark
 * that is decribed in :
 * <p><cite>
 * J. Barnes and P. Hut, "A hierarchical o(NlogN) force-calculation algorithm",
 * Nature, 324:446-449, Dec. 1986
 * </cite>
 * <p>
 * The original code in the Olden benchmark suite is derived from the
 * <a href="ftp://hubble.ifa.hawaii.edu/pub/barnes/treecode">
 * source distributed by Barnes</a>.
 **/
@RunTest(input = "-b 4096 -m", exception = "1")
public class BH 
{
  /**
   * The user specified number of bodies to create.
   **/
  private static int nbody = 0;

  /**
   * The maximum number of time steps to take in the simulation
   **/
  private static int nsteps = 10;

  /**
   * Should we print information messsages
   **/
  private static boolean printMsgs = false;
  /**
   * Should we print detailed results
   **/
  private static boolean printResults = false;

  static double DTIME = 0.0125;
  private static double TSTOP = 2.0;

  @Export
  public static final void main(String args[])
  {
    parseCmdLine(args);

    if (printMsgs) {
      puts("nbody = ");
      puti(nbody);
      putln();
    }

    long start0 = currentTimeMillis();
    Tree root = new Tree();
    root.createTestData(nbody);
    long end0 = currentTimeMillis();
    if (printMsgs)
      puts("Bodies created\n");

    long start1 = currentTimeMillis();
    double tnow = 0.0;
    int i = 0;
    while ((tnow < TSTOP + 0.1*DTIME) && (i < nsteps)) {
      root.stepSystem(i++);
      tnow += DTIME;
    }
    long end1 = currentTimeMillis();

//    if (printResults) {
//      int j = 0;
//      for (Enumeration e = root.bodies(); e.hasMoreElements(); ) {
//	Body b = (Body)e.nextElement();
//	puts("body "); puti(j++); puts(" -- "); puts(b.pos.toString()); putln();
//      }
//    }

    if (printMsgs) {
      puts("Build Time "); putd((end0 - start0)/1000.0); putln();
      puts("Compute Time "); putd((end1 - start1)/1000.0); putln();
      puts("Total Time "); putd((end1 - start0)/1000.0); putln();
    }
    puts("Done!\n");
  }

  /**
   * Random number generator used by the orignal BH benchmark.
   * @param seed the seed to the generator
   * @return a random number
   **/
  static final double myRand(double seed)
  {
    double t = 16807.0*seed + 1;
    
    seed = t - (2147483647.0 * Math.floor(t / 2147483647.0));
    return seed;
  }

  /**
   * Generate a floating point random number.  Used by
   * the original BH benchmark.
   *
   * @param xl lower bound
   * @param xh upper bound
   * @param r seed
   * @return a floating point randon number
   **/
  static final double xRand(double xl, double xh, double r)
  {
    double res = xl + (xh-xl)*r/2147483647.0;
    return res;
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

      // check for options that require arguments
      if (arg.equals("-b")) {
	if (i < args.length) {
	  nbody = 1024;//new Integer(args[i++]).intValue();
	} else {
//	  throw new Error("-l requires the number of levels");
	}
      } else if (arg.equals("-s")) {
	if (i < args.length) {
	  nsteps = 10;//new Integer(args[i++]).intValue();
	} else {
//	  throw new Error("-l requires the number of levels");
	}
      } else if (arg.equals("-m")) {
	printMsgs  = true;
      } else if (arg.equals("-p")) {
	printResults = true;
      } else if (arg.equals("-h")) {
	usage();
      }
    }
    if (nbody == 0) usage();
  }

  /**
   * The usage routine which describes the program options.
   **/
  private static final void usage()
  {
    println("usage: java BH -b <size> [-s <steps>] [-p] [-m] [-h]");
    println("    -b the number of bodies");
    println("    -s the max. number of time steps (default=10)");
    println("    -p (print detailed results)");
    println("    -m (print information messages");
    println("    -h (this message)");
//    System.exit(0);
  }

}
