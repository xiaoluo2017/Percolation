import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation{
  private int n;
	private int num;
	private boolean[][] per;
	private WeightedQuickUnionUF w;
	private WeightedQuickUnionUF w2;
	
	public Percolation(int n) {
		if(n <= 0){
			throw new IllegalArgumentException();
		}
		this.n = n;
		num = 0;
		w = new WeightedQuickUnionUF(n * n + 1);
		w2 = new WeightedQuickUnionUF(n * n + 2);
		per = new boolean[n][n];
		// create n-by-n grid, with all sites blocked
	}
	
	public void open(int row, int col) {
		if (row <= 0 || row > n || col <= 0 || col > n) {
			throw new IndexOutOfBoundsException();
		}
		if (per[row - 1][col - 1]) {
			return;
		}
		per[row - 1][col - 1] = true;
		num++;
		int curr = index(row, col);
		int prev;
		if (row == 1) {
			w.union(curr, 0);
			w2.union(curr, 0);
		}
		if (row == n) {
			w2.union(curr, n * n + 1);
		}
		if (row > 1 && per[row - 2][col - 1]) {
			prev = index(row - 1, col);
			w.union(curr, prev);
			w2.union(curr, prev);
		}
		if (row < n && per[row][col - 1]) {
			prev = index(row + 1, col);
			w.union(curr, prev);
			w2.union(curr, prev);
		}
		if (col > 1 && per[row - 1][col - 2]) {
			prev = index(row, col - 1);
			w.union(curr, prev);
			w2.union(curr, prev);
		}
		if (col < n && per[row - 1][col]) {
			prev = index(row, col + 1);
			w.union(curr, prev);
			w2.union(curr, prev);
		}
		// open site (row, col) if it is not open already
	}
	
	private int index(int row, int col) {
		return (row - 1) * n + col;
	}
	
	public boolean isOpen(int row, int col) {
		if(row <= 0 || row > n || col <= 0 || col > n){
			throw new IndexOutOfBoundsException();
		}
		return per[row - 1][col - 1];

		// is site (row, col) open?
	}
	public boolean isFull(int row, int col) {
		if (row <= 0 || row > n || col <= 0 || col > n) {
			throw new IndexOutOfBoundsException();
		}
		return w.connected(0, index(row, col));
		
		// is site (row, col) full?
	}
	public int numberOfOpenSites() {
		return num;
		// number of open sites
	}
	public boolean percolates() {
		return w2.connected(0, n * n + 1);
		// does the system percolate?
	}

	public static void main(String[] args) {
		
		// test client (optional)
	}
}
