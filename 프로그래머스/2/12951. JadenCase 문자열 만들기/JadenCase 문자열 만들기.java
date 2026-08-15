class Solution {
    public String solution(String s) {
        String answer = "";
        boolean first = true;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (c == ' ') {
                answer += c;
                first = true;
            } else {
                if (first) {
                    answer += Character.toUpperCase(c);
                    first = false;
                } else {
                    answer += Character.toLowerCase(c);
                }
            }
        }

        return answer;
    }
}