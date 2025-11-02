package DivideVenceras;

public class MajorityElement {

    // Encuentra el elemento mayoritario (que aparece más de n/2 veces)
    public static int findMajorityElement(int[] arr) {
        return majorityElementRec(arr, 0, arr.length - 1);
    }

    private static int majorityElementRec(int[] arr, int left, int right) {
        if (left == right)
            return arr[left];

        int mid = (left + right) / 2;
        int leftMajor = majorityElementRec(arr, left, mid);
        int rightMajor = majorityElementRec(arr, mid + 1, right);

        if (leftMajor == rightMajor)
            return leftMajor;

        int leftCount = countInRange(arr, leftMajor, left, right);
        int rightCount = countInRange(arr, rightMajor, left, right);

        return leftCount > rightCount ? leftMajor : rightMajor;
    }

    private static int countInRange(int[] arr, int num, int left, int right) {
        int count = 0;
        for (int i = left; i <= right; i++)
            if (arr[i] == num)
                count++;
        return count;
    }
}
