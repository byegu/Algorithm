class Solution {
    public String solution(String video_len, String pos, String op_start, String op_end, String[] commands) {
        int video_len_time = convert(video_len);
        int pos_time = convert(pos);
        int op_start_time = convert(op_start);
        int op_end_time = convert(op_end);
        
        if (pos_time >= op_start_time && pos_time <= op_end_time) {
            pos_time = op_end_time;
        }
        
        for (int i = 0; i < commands.length; i++) {
            String cmd = commands[i];
            
            if (cmd.equals("next")) {
                pos_time += 10;
            } else if (cmd.equals("prev")) {
                pos_time -= 10;
            }
            
            if (pos_time < 0) {
            pos_time = 0;
            } else if (pos_time > video_len_time) {
            pos_time = video_len_time;
            }
            
            if (pos_time >= op_start_time && pos_time <= op_end_time) {
            pos_time = op_end_time;
        }
        }
        
        if (pos_time >= op_start_time && pos_time <= op_end_time) {
            pos_time = op_end_time;
        }
        

        
        String min = String.format("%02d", pos_time / 60);
        String sec = String.format("%02d", pos_time % 60);
        
        String answer = min + ":" + sec;
        return answer;
    }
    
    public static int convert(String time) {
        String[] time_arr = time.split(":");
        int min = Integer.parseInt(time_arr[0]);
        int sec = Integer.parseInt(time_arr[1]);
        
        return (min * 60) + sec;
    }
}