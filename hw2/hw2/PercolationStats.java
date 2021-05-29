package hw2;
import edu.princeton.cs.introcs.StdRandom;
import edu.princeton.cs.introcs.StdStats;

public class PercolationStats {
    double[] samplePercentages;
    double cachedMean = -1.0;
    double cachedStdDev = -1.0;

    // perform T independent experiments on an N-by-N grid
    public PercolationStats(int N, int T, PercolationFactory pf)  {
        //do T experiments
        samplePercentages = new double[T];
        Percolation perc;
        double fullSitesToPercolateForCurrExper;
        for(int i = 1; i <= T; i++) {
            perc = pf.make(N);
            fullSitesToPercolateForCurrExper = 0;

            int randoRow, randoCol;
            while(!perc.percolates()) {
                randoRow = StdRandom.uniform(N);
                randoCol = StdRandom.uniform(N);

                if(!(perc.isFull(randoRow, randoCol) || perc.isOpen(randoRow, randoCol))) {
                    perc.open(randoRow, randoCol);
                    fullSitesToPercolateForCurrExper++;
                }
            }

            samplePercentages[i - 1] = fullSitesToPercolateForCurrExper / (N * N);
        }
    }

    // sample mean of percolation threshold
    public double mean() {
        if(cachedMean != -1.0) {
            return cachedMean;
        }
        double cum = 0;

        for(int i = 0; i < samplePercentages.length; i++) {
            cum += samplePercentages[i];
        }

        return cum / samplePercentages.length;
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        if(cachedStdDev != -1.0) {
            return cachedStdDev;
        }
        return StdStats.stddevp(samplePercentages);
    }
    // low endpoint of 95% confidence interval
    public double confidenceLow() {
        return mean() -(( stddev() * 1.96 ) / (Math.sqrt(samplePercentages.length)));
    }

    // high endpoint of 95% confidence interval
    public double confidenceHigh(){

        return mean() +(( stddev() * 1.96 ) / (Math.sqrt(samplePercentages.length)));
    }

    public static void main(String[] args) {
        System.out.println("Starting experiments");
        PercolationStats run = new PercolationStats(50, 100, new PercolationFactory());
        System.out.printf("Mean:  %f", run.mean());
        System.out.printf("Std Dev:  %f", run.stddev());
        System.out.printf("CI:  (%f, %f)", run.confidenceLow(), run.confidenceHigh());
    }
}
