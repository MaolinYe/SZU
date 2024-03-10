import sys

puzzle = [
    [0, 0, 6, 0, 1, 8, 4, 5, 9],
    [3, 0, 0, 2, 0, 4, 0, 0, 0],
    [1, 0, 4, 0, 0, 0, 0, 3, 0],
    [0, 3, 0, 9, 5, 0, 0, 6, 0],
    [9, 0, 0, 4, 0, 6, 0, 0, 7],
    [0, 6, 0, 0, 8, 7, 0, 9, 0],
    [0, 4, 0, 0, 0, 0, 9, 0, 5],
    [0, 0, 0, 7, 0, 5, 0, 0, 3],
    [6, 7, 5, 8, 9, 0, 2, 0, 0],
]


def done():  # test if the current situation is goal
    for i in range(0, 9):
        for j in range(0, 9):
            if puzzle[i][j] == 0:
                return False
    return True


def feasible(x, y, number):  # check if the number is feasible
    if number in puzzle[x]:
        return False
    for i in range(0, 9):
        if number == puzzle[i][y]:
            return False
    for i in range(int(x / 3) * 3, int(x / 3) * 3 + 3):
        for j in range(int(y / 3) * 3, int(y / 3) * 3 + 3):
            if puzzle[i][j] == number:
                return False
    return True


def dfs(x, y):  # DFS
    if done():  # find one solution
        for i in range(0, 9):
            print(puzzle[i])
        print()
        sys.exit(0)
    if x >= 9:
        return
    if y >= 9 and x <= 7:
        x = x + 1
        y = 0
    if puzzle[x][y] != 0:
        dfs(x, y + 1)
    for number in range(1, 10):
        if feasible(x, y, number):
            puzzle[x][y] = number
            dfs(x, y + 1)
            puzzle[x][y] = 0  # backtrack


dfs(0, 0)
