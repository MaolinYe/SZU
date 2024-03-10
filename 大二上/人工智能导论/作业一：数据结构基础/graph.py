def dfs(graph, start):
    open = []
    close = []
    result = []
    open.append(start)
    close.append(start)
    while open:
        node = open.pop()
        result.append(node)
        neighors = graph[node]
        for neighor in neighors:
            if neighor not in close:
                close.append(neighor)
                open.append(neighor)
    print(result)


def bfs(graph, start):
    open = []
    close = []
    result = []
    open.append(start)
    close.append(start)
    while open:
        node = open.pop()
        result.append(node)
        neighors = graph[node]
        for neighor in neighors:
            if neighor not in close:
                close.append(neighor)
                open.insert(0, neighor)
    print(result)


if __name__ == '__main__':
    graph = {
        '1': ['2', '3', '4', '5'],
        '2': ['1', '3', '4', '5'],
        '3': ['1', '2', '4', '5'],
        '4': ['1', '2', '3', '5'],
        '5': ['1', '2', '3', '4']
    }
    G = {
        'A': ['B', 'C', 'G'],
        'B': ['A', 'D', 'G', 'E'],
        'C': ['A'],
        'D': ['B', 'C', 'E'],
        'E': ['B', 'D', 'F'],
        'F': ['E'],
        'G': ['A', 'B']
    }
    tree = {
        '1': ['2', '3'],
        '2': ['1', '4', '5'],
        '3': ['1', '6', '7'],
        '4': ['2'],
        '5': ['2'],
        '6': ['3'],
        '7': ['3']
    }
    dfs(tree, '1')
    bfs(tree, '1')
