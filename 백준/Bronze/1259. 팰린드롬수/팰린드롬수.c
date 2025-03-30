#include <stdio.h>
#include <string.h>

int main() {
    while(1) {
        int isPalindrome = 1;
        char str[6];
        int length;

        scanf("%s", str);
        if (str[0] == '0') {
            break;
        }

        int len = strlen(str);

        for (int i = 0, j = len - 1; i < j; i++, j--) {
            if (str[i] != str[j]) {
                isPalindrome = 0;
                break;
            }
        }
        if (isPalindrome) {
            printf("yes\n");
        } else {
            printf("no\n");
        }
    }


    return 0;
}