import StateModule
def AStar(state=None):
    open = []
    close = []
    open.append(state)
    while open:
        min = open[0].step + open[0].cost
        mini = 0
        for i in range(len(open)):
            if open[i].step + open[i].cost < min:
                min = open[i].step + open[i].cost
                mini = i
        test = open.pop(mini)
        close.append(test)
        if test.IsGoal():
            return test
        test.Expand()
        for it in test.child:
            open.append(it)
    return False
