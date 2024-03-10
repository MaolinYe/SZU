import StateModule
def BFS(state=None):
    open=[]
    close=[]
    open.append(state)
    while open:
        test=open.pop(0)
        close.append(test)
        if test.IsGoal():
           return test
        elif test.state == state.state and open:
            continue
        test.Expand()
        for it in test.child:
                open.append(it)
    return False