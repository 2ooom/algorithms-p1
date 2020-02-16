import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private WeightedQuickUnionUF union;
    private boolean[] open;
    private int nbOpen;
    private int n;
    private int end;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException();
        }
        end = n*n + 1;
        open = new boolean[end + 1];
        union = new WeightedQuickUnionUF(open.length);
        nbOpen = 0;
        this.n = n;
    }

    // 1, 1 -> 0*10 + 1 = 1
    // 1, 2  -> 0*10 + 2 = 2
    // 1, 10 -> 0*10 + 10 = 10
    // 2, 1 -> 1*10 + 1 = 11
    private int getIndex(int row, int col) {
        if (row <= 0 || col <= 0 || row > n || col > n) {
            return -1;
        }
        return (row - 1) * n + col;
    }
    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        int i = getIndex(row, col);
        if (i < 0) {
            throw new IllegalArgumentException();
        }
        open[i] = true;
        nbOpen++;
        int[] neighbours = new int[]{
                getIndex(row, col - 1),
                getIndex(row, col + 1),
                getIndex(row - 1, col),
                getIndex(row + 1, col)
        };
        for (int j = 0; j < neighbours.length; j ++) {
            if (neighbours[j] > 0 && open[neighbours[j]]) {
                union.union(neighbours[j], i);
            }
        }
        if (row == 1) {
            union.union(0, i);
        }
        else if (row == n) {
            union.union(end, i);
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        int i = getIndex(row, col);
        if (i < 0) {
            throw new IllegalArgumentException();
        }
        return open[i];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        int i = getIndex(row, col);
        if (i < 0) {
            throw new IllegalArgumentException();
        }
        return union.connected(0, i);
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return nbOpen;
    }

    // does the system percolate?
    public boolean percolates() {
        return union.connected(0, end);
    }

    // test client (optional)
    public static void main(String[] args) {

    }
}