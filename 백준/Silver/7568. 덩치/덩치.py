N = int(input())
big = [tuple(map(int, input().split())) for _ in range(N)]
rank = [1 for i in range(N)]
for i in range(N):
    for j in range(N):
        if big[i][0] < big[j][0] and big[i][1] < big[j][1]:
            rank[i] += 1
for i in rank:
    print(i, end=" ")
    