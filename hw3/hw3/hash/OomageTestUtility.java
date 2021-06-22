package hw3.hash;

import java.util.List;

public class OomageTestUtility {
    public static boolean haveNiceHashCodeSpread(List<Oomage> oomages, int M) {
        /* TODO:
         * Write a utility function that returns true if the given oomages
         * have hashCodes that would distribute them fairly evenly across
         * M buckets. To do this, convert each oomage's hashcode in the
         * same way as in the visualizer, i.e. (& 0x7FFFFFFF) % M.
         * and ensure that no bucket has fewer than N / 50
         * Oomages and no bucket has more than N / 2.5 Oomages.
         */

        int[] buckets = new int[M];
        int n = oomages.size();

        for(int i = 0; i < n; i++) {
            Oomage pointer = oomages.get(i);

            int bucketNum = (pointer.hashCode() & 0x7FFFFFFF) % M;

            buckets[bucketNum]++;
        }

        for(int j = 0; j < buckets.length; j++) {
            int count = buckets[j];

            if(count < n / 50 || count > n / 2.5) {
                return false;
            }
        }
        return true;
    }
}
