/**
 * Created by hug.
 */
public class ExperimentHelper {

    /** Returns the internal path length for an optimum binary search tree of
     *  size N. Examples:
     *  N = 1, OIPL: 0
     *  N = 2, OIPL: 1
     *  N = 3, OIPL: 2
     *  N = 4, OIPL: 4
     *  N = 5, OIPL: 6
     *  N = 6, OIPL: 8
     *  N = 7, OIPL: 10
     *  N = 8, OIPL: 13
     */
    public static int optimalIPL(int N) {
//        int count = N;
//        int cumulativePaths = 0;
//        int currentHeight = 0;
//        int numberOfNodesForLevel = 0;
//
//        for(int level = 1; level <= (Math.log10(N) / Math.log10(2)); level++) {
//            if ( level == (Math.floor(Math.log10(N)/Math.log10(2)))) {
//                numberOfNodesForLevel = N - (int) (Math.pow(2.0, (double) level) - 1);
//            } else {
//                numberOfNodesForLevel = //total nodes at this height - total nodes at previous level height
//            }
//            //Height * number of nodes
//            cumulativePaths += currentHeight * (numberOfNodesForLevel);
//            currentHeight += 1;
//        }
//
//        return cumulativePaths;
        return 1;
    }

    /** Returns the average depth for nodes in an optimal BST of
     *  size N.
     *  Examples:
     *  N = 1, OAD: 0
     *  N = 5, OAD: 1.2
     *  N = 8, OAD: 1.625
     * @return
     */
    public static double optimalAverageDepth(int N) {
        return 0;
    }
}
