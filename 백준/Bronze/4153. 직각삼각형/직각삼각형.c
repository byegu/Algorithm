#include <stdio.h>

int main() {
    int A, B, C;

    while(1) {
        scanf("%d %d %d", &A, &B, &C);
        if (A == 0 && B == 0 && C == 0) {
            break;
        }
        if(A > B) {
            int temp = A;
            A = B;
            B = temp;
        } 
        if (B > C) {
            int temp = B;
            B = C; 
            C = temp;
        }

        if(A*A + B*B == C*C) {
            printf("right\n");
        } else {
            printf("wrong\n");
        }
    }

    return 0;
}