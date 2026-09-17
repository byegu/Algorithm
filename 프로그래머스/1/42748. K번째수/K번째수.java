import java.util.*;

class Solution {
    public int[] solution(int[] array, int[][] commands) {
        int[] answer = new int[commands.length];
        
        for (int i = 0; i < commands.length; i++) {
            int first = commands[i][0] - 1; 
            int second = commands[i][1];
            int third = commands[i][2] - 1;
            
            List<Integer> list = new ArrayList<>();
            
            for (int j = first; j < second; j++) {
                list.add(array[j]);
            }
            
            Collections.sort(list);

            if (!list.isEmpty()) {
            answer[i] = list.get(third);
            }
            
        }
        return answer;
    }
}