while(True):
    A, B, C = map(int, input().split())
    if A == 0 and B == 0 and C == 0:
        break
    if A > B:
        A, B = B, A
    if B > C:
        B, C = C, B
    
    if A*A + B*B == C*C:
        print("right")
    else :
        print("wrong")