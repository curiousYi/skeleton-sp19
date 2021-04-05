public class UnionFind {

    // TODO - Add instance variables?
    private int[] container;

    /* Creates a UnionFind data structure holding n vertices. Initially, all
       vertices are in disjoint sets. */
    public UnionFind(int n) {
        this.container = new int[n];

        for(int i = 0; i < n; i++) {
            this.container[i] = -1;
        }
    }

    /* Throws an exception if v1 is not a valid index. */
    private void validate(int vertex) {
        if(vertex < 0 || vertex >= this.container.length) {
            throw new RuntimeException("Invalid vertex");
        }
        return;
    }

    /* Returns the size of the set v1 belongs to. */
    //For size we just use negative numbers -4 means size of 4
    public int sizeOf(int v1) {
        return this.container[-1] * -1;
    }

    /* Returns the parent of v1. If v1 is the root of a tree, returns the
       negative size of the tree for which v1 is the root. */
    public int parent(int v1) {
        return container[v1];
    }

    /* Returns true if nodes v1 and v2 are connected. */
    public boolean connected(int v1, int v2) {
        if(container[v1] == -1 || container[v2] == -1) {
            return false;
        }
        return container[v1] == container[v2];
    }

    /* Connects two elements v1 and v2 together. v1 and v2 can be any valid 
       elements, and a union-by-size heuristic is used. If the sizes of the sets
       are equal, tie break by connecting v1's root to v2's root. Unioning a 
       vertex with itself or vertices that are already connected should not 
       change the sets but may alter the internal structure of the data. */
    public void union(int v1, int v2) {
        //two roots
        int v1Point = container[v1];
        int v2Point = container[v2];

        if(v1Point < 0 && v2Point < 0) {
            int smaller, bigger;
            if(v2Point < v1Point) {
                bigger = v2;
                smaller = v1;
            } else{
                bigger = v1;
                smaller = v2;
            }
            //update size
            container[bigger] = v2Point + v1Point;
            //update pointers
            container[smaller] = bigger;

            for(int i = 0; i < container.length ; i++) {
                if(container[i] == smaller) {
                    container[i] = bigger;
                }
            }
        } else if (v1Point < 0 || v2Point < 0) {
            int notRootVertex;

            if(v1Point < 0) {
                union(v1, container[v2]);
            } else {
                union(container[v1], v2);
            }
        } else {
            union(container[v1], container[v2]);
        }
    }

    /* Returns the root of the set V belongs to. Path-compression is employed
       allowing for fast search-time. */
    public int find(int vertex) {
        if(container[vertex] < 0) {
            return vertex;
        }
        return container[vertex];

    }

}
