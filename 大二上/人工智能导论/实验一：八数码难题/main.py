from BFSModule import BFS
from StateModule import State
from AStarModule import AStar
from DFSModule import DFS

if __name__ == "__main__":
    test = [2, 8, 3, 1, 6, 4, 7, 0, 5]
    state = State(test)
    # result = BFS(state)
    # result = DFS(state)
    result = AStar(state)
    path = []
    if result:
        while result:
            path.insert(0, result)
            result = result.parent
        for it in path:
            it.ShowState()
        print('times=', State.times)
    else:
        print("NO Solution!")
