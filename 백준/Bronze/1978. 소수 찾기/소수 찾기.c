#include <stdio.h>

int main() {
    int N;
    scanf("%d", &N);
    int arr[N];
    for (int i = 0; i < N; i++) {
        scanf("%d", &arr[i]);
    }

    int isPrime = 0;
    int count = 0;
    for(int i = 0; i < N; i++) {
        for(int j = 1; j <= arr[i]; j++) {
            if(arr[i] % j == 0) {
                count++;
                }
            }
        if(count == 2) {
            isPrime++;
        }
        count = 0;
    }

    printf("%d\n", isPrime);

    return 0;
}