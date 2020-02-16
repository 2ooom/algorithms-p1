/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {

    private double _mean;
    private double _stddev;
    private double _confLo;
    private double _confHi;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0) {
            throw new IllegalArgumentException();
        }
        double[] x = new double[trials];
        double len = n * n;
        for (int i = 0; i < trials; i++) {
            Percolation perc = new Percolation(n);
            for (int j = 0; j < n * n; j++) {
                int row, column;
                do {
                    row = StdRandom.uniform(1, n + 1);
                    column = StdRandom.uniform(1, n + 1);
                } while (perc.isOpen(row, column));
                perc.open(row, column);
                if (perc.percolates()) {
                    x[i] = (double)perc.numberOfOpenSites() / len;
                    break;
                }
            }
        }
        _stddev = StdStats.stddev(x);
        _mean =  StdStats.mean(x);
        _confLo = _mean-1.96d*_stddev/Math.sqrt(n);
        _confHi = _mean+1.96d*_stddev/Math.sqrt(n);
    }

    // sample mean of percolation threshold
    public double mean() {
        return _mean;
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return _stddev;
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        return _confLo;
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return _confHi;
    }

    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int trials = Integer.parseInt(args[1]);
        PercolationStats stats = new PercolationStats(n, trials);
        System.out.println("mean                    = " + stats.mean());
        System.out.println("stddev                  = " + stats.stddev());
        System.out.println("95% confidence interval = [" + stats.confidenceHi() +", " + stats.confidenceLo() + "]");
    }
}
