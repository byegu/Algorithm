import java.util.*;

class Solution {
    public String[] solution(String[] record) {
        Map<String, String> map = new HashMap<>();
        String[][] log = new String[record.length][3];
        int change = 0;
        
        for (int i = 0; i < record.length; i++) {
            StringTokenizer st = new StringTokenizer(record[i]);
            String cmd = st.nextToken();
            if (cmd.equals("Leave")) {
                log[i][0] = cmd;
                log[i][1] = st.nextToken();
                continue;
            }
            
            if (cmd.equals("Change")) {
                change++;
            }
            log[i][0] = cmd;
            log[i][1] = st.nextToken();
            log[i][2] = st.nextToken();
            
            
            if (map.containsKey(log[i][1])) {
                map.remove(log[i][1]);
                map.put(log[i][1], log[i][2]);
            } else {
                map.put(log[i][1], log[i][2]);
            }
        }
        
        String[] answer = new String[record.length-change];
        
        int idx = 0;

        for (int i = 0; i < log.length; i++) {
            if (log[i][0].equals("Enter")) {
                answer[idx++] = map.get(log[i][1]) + "님이 들어왔습니다.";
            } else if (log[i][0].equals("Leave")) {
                answer[idx++] = map.get(log[i][1]) + "님이 나갔습니다.";
            }
        }
        
        return answer;
    }
}