white_board = [['W', 'B', 'W', 'B', 'W', 'B', 'W', 'B'],
               ['B', 'W', 'B', 'W', 'B', 'W', 'B', 'W'],
               ['W', 'B', 'W', 'B', 'W', 'B', 'W', 'B'],
               ['B', 'W', 'B', 'W', 'B', 'W', 'B', 'W'],
               ['W', 'B', 'W', 'B', 'W', 'B', 'W', 'B'],
               ['B', 'W', 'B', 'W', 'B', 'W', 'B', 'W'],
               ['W', 'B', 'W', 'B', 'W', 'B', 'W', 'B'],
               ['B', 'W', 'B', 'W', 'B', 'W', 'B', 'W']]

black_board = [['B', 'W', 'B', 'W', 'B', 'W', 'B', 'W'],
               ['W', 'B', 'W', 'B', 'W', 'B', 'W', 'B'],
               ['B', 'W', 'B', 'W', 'B', 'W', 'B', 'W'],
               ['W', 'B', 'W', 'B', 'W', 'B', 'W', 'B'],
               ['B', 'W', 'B', 'W', 'B', 'W', 'B', 'W'],
               ['W', 'B', 'W', 'B', 'W', 'B', 'W', 'B'],
               ['B', 'W', 'B', 'W', 'B', 'W', 'B', 'W'],
               ['W', 'B', 'W', 'B', 'W', 'B', 'W', 'B']]

def white(x, y):
    count = 0
    for i in range(8):
        for j in range(8):
            if chess_board[i + x][j + y] != white_board[i][j]:
                count += 1
    return count

def black(x, y):
    count = 0
    for i in range(8):
        for j in range(8):
            if chess_board[i + x][j + y] != black_board[i][j]:
                count += 1
    return count

if __name__ =="__main__":
    x, y = map(int, input().split())
    chess_board = [list(input().strip()) for _ in range(x)]
    result = 1000000
    
    for i in range(x - 7):
        for j in range(y - 7):
            W = white(i, j)
            B = black(i, j)
            result = min(result, W, B)
    
    print(result)