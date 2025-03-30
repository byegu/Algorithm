#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define MAX 10001

typedef struct {
    int top;
    int data[MAX];
} Stack;

void init(Stack* stack) {
    stack->top = -1;
}

int empty(Stack* stack) {
    if (stack->top == -1) {
        return 1;
    } else {
        return 0;
    }
}

void empty2(Stack* stack) {
    printf("%d\n", empty(stack));
}


void top(Stack* stack) {
    if (empty(stack)) {
        printf("-1\n");
    } else {
        printf("%d\n", stack->data[stack->top]);
    }
}

void size(Stack* stack) {
    printf("%d\n", stack->top + 1);
}

void push(Stack* stack, int X) {
    if (stack->top == MAX - 1) {
        return;
    }
    stack->data[++(stack->top)] = X;
}

int pop(Stack* stack) {
    if (empty(stack)) {
        printf("-1\n");
        return -1;
    } else {
        printf("%d\n", stack->data[stack->top]);

        return stack->data[stack->top--];
    }
}

int main() {
    Stack stack;
    init(&stack);

    int cmd;
    scanf("%d", &cmd);

    for (int i = 0; i < cmd; i++) {
        char ch[10];
        scanf("%s", ch);

        if (strcmp(ch, "push") == 0) {
            int n;
            scanf("%d", &n);
            push(&stack, n);
        } else if (strcmp(ch, "pop") == 0) {
            pop(&stack);
        } else if (strcmp(ch, "size") == 0) {
            size(&stack);
        } else if (strcmp(ch, "empty") == 0) {
            empty2(&stack);
        } else if (strcmp(ch, "top") == 0) {
            top(&stack);
        }
    }

    return 0;
}
