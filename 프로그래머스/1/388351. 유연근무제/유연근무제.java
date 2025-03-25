class Solution {
    public int solution(int[] schedules, int[][] timelogs, int startday) {
        boolean[] prize = new boolean[schedules.length];
        int result = 0;
        
        for (int i = 0; i < schedules.length; i++) {
            if((schedules[i]+10) % 100 >= 60) {
                schedules[i] = schedules[i] + 50;
            }
            else {
                schedules[i] += 10;
            }
            prize[i] = true;
        }
        
        for (int i = 0; i < schedules.length; i++) {
            for (int j = 0; j < 7; j++) {
                if (startday > 7) {
                    startday -= 7;
                } else if (startday >= 6) {
                    startday += 1;
                    continue;
                }
                
                if (schedules[i] >= timelogs[i][j] && startday < 6 && prize[i] == true) {
                    prize[i] = true;
                } else {
                    prize[i] = false;
                }
                
                startday += 1;
            }
            if (prize[i] == true) {
                result++;
            }
        }
        
        
        int answer = result;
        return answer;
    }
}