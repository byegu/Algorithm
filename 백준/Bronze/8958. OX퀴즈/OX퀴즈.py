T = int(input())
point = 0
add = 0
for i in range(T):
    quiz = list(input())
    for j in range(len(quiz)):
        add += 1
        if quiz[j] == 'O':
            point += add
        elif quiz[j] == 'X':
            add = 0
    add = 0
    print(point)
    point = 0