import UnionFind.*;

public class BubbleGrid {
    private int[][] grid;
    private UnionFind uf;
    /* Create new BubbleGrid with bubble/space locations specified by grid.
     * Grid is composed of only 1's and 0's, where 1's denote a bubble, and
     * 0's denote a space. */
    public BubbleGrid(int[][] grid) {
        this.grid = grid;
        this.uf = new UnionFind(grid.length * grid[0].length);

        for(int xP = 0; xP < 1; xP++) {
            for(int yP = 0; yP < grid[0].length; yP++) {
                //goDown and Right?
               //what's the most efficient way to traverse?

                if(xP+1 < grid.length && grid[xP+1][yP] == 1 && !this.uf.connected(mapToUFCoord(xP, yP), mapToUFCoord(xP+1, yP))) {
                    int[] a = {xP, yP};
                    int[] b = {xP+1, yP};
                    this.markConnected(a, b);
                } else if(yP+1 < grid[0].length && !this.uf.connected(mapToUFCoord(xP, yP), mapToUFCoord(xP, yP+1))) {
                    int[] a = {xP, yP};
                    int[] b = {xP, yP+1};
                    this.markConnected(a, b);
                }
            }
        }
    }

    public void markConnected(int[] coord, int[] neighbor) {
        this.uf.union(mapToUFCoord(coord[0], coord[1]), mapToUFCoord(neighbor[0], neighbor[1]));
        int xP = neighbor[0];
        int yP = neighbor[1];

        if(xP+1 < grid.length && grid[xP+1][yP] == 1 && !this.uf.connected(mapToUFCoord(xP, yP), mapToUFCoord(xP+1, yP))) {
            int[] a = {xP, yP};
            int[] b = {xP+1, yP};
            this.markConnected(a, b);
        } else if(yP+1 < grid[0].length && !this.uf.connected(mapToUFCoord(xP, yP), mapToUFCoord(xP, yP+1))) {
            int[] a = {xP, yP};
            int[] b = {xP, yP+1};
            this.markConnected(a, b);
        }
    }

    public int mapToUFCoord(int x, int y) {
        return x * grid[0].length + y;
    }

    public boolean isStuck(int x, int y) {
        if(x == 0) {
            return true;
        }

        boolean status = false;
        for(int i = 0; i < this.grid[0].length; i++ ) {
            if(grid[0][x] == 1) {
                status = this.uf.connected(mapToUFCoord(x,y), mapToUFCoord(0, i));
            }

            if(status == true) {
               return status;
            }
        }

        return status;
    }



    /* Returns an array whose i-th element is the number of bubbles that
     * fall after the i-th dart is thrown. Assume all elements of darts
     * are unique, valid locations in the grid. Must be non-destructive
     * and have no side-effects to grid. */
    public int[] popBubbles(int[][] darts) {
        // TODO
        //am I in the top row?
        //am I orthogonally connected to something in the top row?
        return null;
    }
}
