class Solution {
    static String result = "No";
    public String solution(String[] cards1, String[] cards2, String[] goal) {
        String answer = "";
        
        dfs(cards1, cards2, goal, 0, 0, 0);
        
        return result;
    }
    
    public void dfs(String[] cards1, String[] cards2, String[] goal, int idx, int idx2, int gidx) {

        if (goal.length == gidx) {
            result = "Yes";
            return;
        }

        if (idx < cards1.length && cards1[idx].equals(goal[gidx])) {
            dfs(cards1, cards2, goal, idx + 1, idx2, gidx + 1);
            return;
        }

        if (idx2 < cards2.length && cards2[idx2].equals(goal[gidx])) {
            dfs(cards1, cards2, goal, idx, idx2 + 1, gidx + 1);
            return;
        }
        result = "No";
    }
}