import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private int trials;
    private double[] res;
	
    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0) {
            throw new IllegalArgumentException();
        }
        this.trials = trials;
        res = new double[trials];
	    for (int i = 0; i < trials; i++) {
		    Percolation p = new Percolation(n);
            while (!p.percolates()) {
                int row = StdRandom.uniform(n) + 1;
                int col = StdRandom.uniform(n) + 1;
                p.open(row, col);
        }
            res[i] = (double)p.numberOfOpenSites() / (n * n);
        }
        // perform trials independent experiments on an n-by-n grid
    }
	
    public double mean() {
		return StdStats.mean(res);
		// sample mean of percolation threshold
	}
	
	public double stddev() {
		return StdStats.stddev(res);
		// sample standard deviation of percolation threshold
	}
	
	public double confidenceLo() {
		return StdStats.mean(res) - 1.96 * StdStats.stddev(res) / Math.sqrt(trials);
		// low  endpoint of 95% confidence interval
	}
	
	public double confidenceHi() {
		return StdStats.mean(res) + 1.96 * StdStats.stddev(res) / Math.sqrt(trials);
		// high endpoint of 95% confidence interval
	}
	
    public static void main(String[] args) {
		// test client (described below)
	}
}
