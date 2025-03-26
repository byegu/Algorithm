class Solution {
    public int solution(String[] friends, String[] gifts) {
        int answer = 0;
        int n = friends.length;
        int[][] counts = new int[n][n];
        String[] name = new String[1];
        int[] gift_index = new int[n];
        int[] result = new int[n];
            
        for (int i = 0; i < gifts.length; i++) {
            int p = 0;
            int q = 0;
            name = gifts[i].split(" ");
            
            for(int j = 0; j < n; j++) {
                if (name[0].equals(friends[j])) {
                    p = j;
                }

                if (name[1].equals(friends[j])) {
                    q = j;
                }
            }
            counts[p][q]++;
        }
        
        for (int i = 0; i < n; i++) {
            int given = 0;
            int received = 0;
            for (int j = 0; j < n; j++) {
                given += counts[i][j];
                received += counts[j][i];
            }
            gift_index[i] = given - received;
}
        
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (counts[i][j] > counts[j][i]) {
                    result[i]++;
                } else if (counts[i][j] < counts[j][i]) {
                    result[j]++;
                } else {
                    if (gift_index[i] > gift_index[j]) {
                        result[i]++;
                    } else if (gift_index[i] < gift_index[j]) {
                        result[j]++;
                    }
                
                }
            }
        }
        
    for (int i = 0; i < result.length; i++) {
        if (result[i] > answer) {
            answer = result[i];
        }
    }

    return answer;
    }
}