package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    //0 means not dug, 1 means dug, and 2 means water
    private int[][] grid;
    private WeightedQuickUnionUF connectedDS;
    private int numberOfOpenSites = 0;

    // create N-by-N grid, with all sites initially blocked
    public Percolation(int N) {
        grid = new int[N][N];
        connectedDS = new WeightedQuickUnionUF(N*N);
    }

    //makes sure the (row, col) is kosher if it is not then it returns FALSE
    private boolean validateRowColCoord(int row, int col) {
        if(row < 0 || row > grid.length - 1) {
            return true;
        } else if(col < 0 || col > grid[0].length - 1) {
            return true;
        }
        return false;
    }

    private int mapRowColToConnectedStructCoords(int row, int col) {
        if(validateRowColCoord(row, col)) {
            throw new RuntimeException("invalid row col coordinates for mapRowToConnectedStructCoords");
        }
        return row * grid.length + col;
    }

    // open the site (row, col) if it is not open already
    public void open(int row, int col) {
        if (validateRowColCoord(row, col)) {
            throw new RuntimeException("invalid row col coordinates for mapRowToConnectedStructCoords");
        }

        if(row == 0) {
            grid[row][col] = 2;
        } else {
            grid[row][col] = 1;
        }
        numberOfOpenSites++;

        //check if any optimizations need to be made?
        //see if any neighbor is open and then run connect on them;

        int[][] neighbors = {{row, col + 1}, {row + 1, col}, {row, col - 1}, {row - 1, col}};

        int[] neighbor;
        int neighborRow, neighborCol;
        for (int i = 0; i < neighbors.length; i++) {
            neighbor = neighbors[i];

            neighborRow = neighbor[0];
            neighborCol = neighbor[1];

            if (validateRowColCoord(neighborRow, neighborCol) == false && (isFull(neighborRow, neighborCol) || isOpen(neighborRow, neighborCol))) {
                int connectedStructCoords = mapRowColToConnectedStructCoords(row, col);
                int neighborConnectedStructCoords = mapRowColToConnectedStructCoords(neighborRow, neighborCol);

                if (!connectedDS.connected(neighborConnectedStructCoords, connectedStructCoords)) {
                    connectedDS.union(neighborConnectedStructCoords, connectedStructCoords);
                }
            }
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        if(validateRowColCoord(row, col)) {
            throw new RuntimeException("invalid row col coordinates for isOpen");
        }

        return grid[row][col] == 1;
    }

    // is the site (row, col) full? This means water reaches it from the top
    public boolean isFull(int row, int col) {
        if(validateRowColCoord(row, col)) {
            throw new RuntimeException("invalid row col coordinates for isFull");
        }

        if(grid[row][col] == 2) {
            return true;
        }

        int map1, map2 = mapRowColToConnectedStructCoords(row, col);

        if(row != 0 ) {
            for (int col2 = 0; col2 < grid[0].length; col2++) {
                map1 = mapRowColToConnectedStructCoords(0, col2);

                if (connectedDS.connected(map1, map2)) {
                    grid[row][col] = 2;
                    return true;
                }
            }
        }
        //Check if connected to top and if-so update the square.

        return false;
    }

    // number of open sites
    public int numberOfOpenSites() {
        return numberOfOpenSites;
    }

    // does the system percolate?
    // need to check if it's connected to the top row
    public boolean percolates() {
        //go through full top row cells and bottom row cells
        //Note: this should definitely be optimized
        for(int topCol = 0; topCol < grid.length; topCol++) {
            if(isFull(0, topCol)) {
                for(int bottomCol = 0; bottomCol < grid.length; bottomCol++) {
                    if(isFull(grid.length - 1, bottomCol)) {
                        int coordTopConnectedCoords = mapRowColToConnectedStructCoords(0, topCol);
                        int coordBottomConnectedCoords = mapRowColToConnectedStructCoords(grid.length, bottomCol);

                        if(connectedDS.connected(coordTopConnectedCoords, coordBottomConnectedCoords)) {
                            return true;
                        }
                    }
                }
            }
        }

        return false;
    }

    // use for unit testing (not required, but keep this here for the autograder)
    public static void main(String[] args) {

    }
}
