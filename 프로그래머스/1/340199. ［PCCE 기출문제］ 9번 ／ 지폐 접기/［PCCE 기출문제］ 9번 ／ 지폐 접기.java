class Solution {
    public int solution(int[] wallet, int[] bill) {
        int count = 0;
        
        while(true) {
            if (bill[0] <= wallet[0] && bill[1] <= wallet[1]
                || bill[1] <= wallet[0] && bill[0] <= wallet[1]) {
                break;
            }
            
            if (bill[0] >= bill[1]) {
                bill[0] = bill[0] / 2;
            } else {
                bill[1] = bill[1] / 2;
            }
            
            count++;
            
            if (bill[0] <= wallet[0] && bill[1] <= wallet[1]
                || bill[1] <= wallet[0] && bill[0] <= wallet[1]) {
                break;
            }
        }
        
        int answer = count;
        return answer;
    }
}