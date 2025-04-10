T = int(input())
for i in range(T):
    schnum = int(input())
    tempb = 0
    for j in range(schnum):
        sch, alc = map(str, input().split())
        a, b = sch, int(alc)
        if b > tempb:
            tempb = b
            tempa = a
    print(tempa)