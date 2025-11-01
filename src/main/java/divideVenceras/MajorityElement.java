package divideVenceras;

public class MajorityElement{

    public static int findMajorityElement(int[] nums) {
        return majorityElementRec(nums, 0, nums.length - 1);
    }

    private static int majorityElementRec(int[] nums, int left, int right) {
        // Caso base: solo un elemento
        if (left == right) {
            return nums[left];
        }

        int mid = (left + right) / 2;

        // Divide
        int leftMajor = majorityElementRec(nums, left, mid);
        int rightMajor = majorityElementRec(nums, mid + 1, right);

        // Conquista
        if (leftMajor == rightMajor) {
            return leftMajor;
        }

        // Cuenta cuántas veces aparece cada uno
        int leftCount = countInRange(nums, leftMajor, left, right);
        int rightCount = countInRange(nums, rightMajor, left, right);

        return leftCount > rightCount ? leftMajor : rightMajor;
    }

    private static int countInRange(int[] nums, int num, int left, int right) {
        int count = 0;
        for (int i = left; i <= right; i++) {
            if (nums[i] == num) count++;
        }
        return count;
    }
}
