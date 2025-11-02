package divideVenceras;

public class SmallerElementsCount {

    static class Pair {
        int val, idx;
        Pair(int v, int i) { val = v; idx = i; }
    }

    public static int[] contarMenores(int[] nums) {
        int n = nums.length;
        int[] counts = new int[n];
        Pair[] pairs = new Pair[n];
        for (int i = 0; i < n; i++) pairs[i] = new Pair(nums[i], i);
        mergeCount(pairs, 0, n - 1, counts);
        return counts;
    }

    private static void mergeCount(Pair[] pairs, int l, int r, int[] counts) {
        if (l >= r) return;
        int m = (l + r) / 2;
        mergeCount(pairs, l, m, counts);
        mergeCount(pairs, m + 1, r, counts);

        Pair[] temp = new Pair[r - l + 1];
        int i = l, j = m + 1, k = 0, rightSmaller = 0;

        while (i <= m && j <= r) {
            if (pairs[j].val < pairs[i].val) {
                temp[k++] = pairs[j++];
                rightSmaller++;
            } else {
                counts[pairs[i].idx] += rightSmaller;
                temp[k++] = pairs[i++];
            }
        }
        while (i <= m) {
            counts[pairs[i].idx] += rightSmaller;
            temp[k++] = pairs[i++];
        }
        while (j <= r) temp[k++] = pairs[j++];
        System.arraycopy(temp, 0, pairs, l, temp.length);
    }
}
