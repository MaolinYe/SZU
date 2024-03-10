class stack:
    def __init__(self):
        self.length = 0
        self.volume = []
        self.toppointer = -1

    def push(self, temp):
        self.volume.append(temp)
        self.length, self.toppointer = self.length + 1, self.toppointer + 1

    def pop(self):
        if (self.empty()):
            print('Sorry,this is a empty stack!')
            return
        self.volume.pop()
        self.length, self.toppointer = self.length - 1, self.toppointer - 1

    def top(self):
        if (self.empty()):
            print('Sorry,this is a empty stack!')
            return
        return self.volume[self.toppointer]

    def empty(self):
        if (self.length == 0):
            return True
        return False

    def size(self):
        return self.length


if __name__ == "__main__":
    pass
