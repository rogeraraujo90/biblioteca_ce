/**           __  __
 *    _____ _/ /_/ /_    Computational Intelligence Library (CIlib)
 *   / ___/ / / / __ \   (c) CIRG @ UP
 *  / /__/ / / / /_/ /   http://cilib.net
 *  \___/_/_/_/_.___/
 */
package ufrpe.ppgia.ce.util;

/**
 * BASEADO NO CÓDIGO FONTE PRESENTE EM:
 * http://grepcode.com/file/repo1.maven.org/maven2/net.cilib/library_2.9.2/0.7.5/net/sourceforge/cilib/functions/continuous/unconstrained/Griewank.java
 */

/**
 * Generalised Griewank function.
 *
 * <p>
 * Characteristics:
 * <ul>
 * <li>Multi-modal</li>
 * <li>Non seperable</li>
 * <li>Regular</li>
 * </ul>
 *
 * f(x) = 0; x = (0,0,...,0);
 * x_i e (-600,600)
 *
 * R(-600, 600)^30
 *
 */
public class GriewankFunction {

	/**
     * {@inheritDoc}
     */
    public Double apply(Double[] input) {
        double sumsq = 0;
        double prod = 1;
        
        for (int i = 0; i < input.length; ++i) {
            sumsq += input[i] * input[i];
            prod *= Math.cos(input[i] / Math.sqrt(i + 1));
        }
        
        return 1 + sumsq * (1.0 / 4000.0) - prod;
    }
}
