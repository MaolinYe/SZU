import copy


class State:
    goal = [1, 2, 3, 8, 0, 4, 7, 6, 5]
    times = 0

    def __init__(self, state=[], parent=None):  # room-> 0 or empty place
        State.times = State.times + 1
        self.state = state
        self.child = []
        self.parent = parent
        if parent:
            self.step = parent.step + 1
        else:
            self.step = 0
        self.cost = 0
        for i in range(9):
            if self.state[i] != State.goal[i]:
                self.cost = self.cost + 1
        for i in range(9):
            if state[i] == 0:
                self.room = i
                break

    def IsGoal(self):
        if self.state == State.goal:
            return True
        return False

    def Up(self, room):
        if room < 6:
            state = copy.deepcopy(self.state)
            state[room], state[room + 3] = state[room + 3], state[room]
            self.child.append(State(state, self))

    def Down(self, room):
        if room >= 3:
            state = copy.deepcopy(self.state)
            state[room], state[room - 3] = state[room - 3], state[room]
            self.child.append(State(state, self))

    def Left(self, room):
        if room % 3 < 2:
            state = copy.deepcopy(self.state)
            state[room], state[room + 1] = state[room + 1], state[room]
            self.child.append(State(state, self))

    def Right(self, room):
        if room % 3 > 0:
            state = copy.deepcopy(self.state)
            state[room], state[room - 1] = state[room - 1], state[room]
            self.child.append(State(state, self))

    def ShowState(self):
        print('step=', self.step, ' cost=', self.cost, end='\n')
        for i in range(9):
            print(self.state[i], end=' ')
            if i % 3 == 2:
                print()
        print()

    def Expand(self):
        State.Up(self, self.room)
        State.Down(self, self.room)
        State.Left(self, self.room)
        State.Right(self, self.room)
