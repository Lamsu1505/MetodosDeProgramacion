package DivideVenceras;

public class MajorityElement {

    // Método principal
    public static int findMajorityElement(int[] nums) {
        int candidato = majorityRec(nums, 0, nums.length - 1);

        // Validar que sea realmente mayoritario
        int count = 0;
        for (int n : nums) {
            if (n == candidato) count++;
        }
        return count > nums.length / 2 ? candidato : -1;
    }

    // Método recursivo (Divide y Vencerás)
    private static int majorityRec(int[] nums, int l, int r) {
        if (l == r) return nums[l]; // caso base

        int mid = l + (r - l) / 2;
        int left = majorityRec(nums, l, mid);
        int right = majorityRec(nums, mid + 1, r);

        if (left == right) return left;

        int leftCount = 0, rightCount = 0;
        for (int i = l; i <= r; i++) {
            if (nums[i] == left) leftCount++;
            if (nums[i] == right) rightCount++;
        }
        return leftCount > rightCount ? left : right;
    }
}
