class Solution {
    static boolean[] nums = new boolean[10];
    public int solution(int[] numbers) {
        int answer = 0;
        for (int i : numbers) {
            nums[i] = true;
        }
        
        for (int i = 0; i < nums.length; i++) {
            if (!nums[i]) answer += i;
        }
        
        return answer;
    }
}