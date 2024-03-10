import StateModule
def DFS(state=None):
    open=[]
    close=[]
    open.append(state)
    while open:
        test=open.pop()
        close.append(test)
        if test.IsGoal():
           return test
        test.Expand()
        for it in test.child:
            if it.step>10:
                continue
            open.append(it)
    return False