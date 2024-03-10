class queue:
    def __init__(self):
        self.length = 0
        self.volume = []

    def push(self, temp):
        self.volume.append(temp)
        self.length = self.length + 1

    def pop(self):
        if (self.empty()):
            print('Sorry,this is a empty queue!')
            return
        self.volume.pop(0)
        self.length = self.length - 1

    def front(self):
        if (self.empty()):
            print('Sorry,this is a empty queue!')
            return
        return self.volume[0]

    def empty(self):
        if (self.length == 0):
            return True
        return False

    def size(self):
        return self.length


if __name__ == "__main__":
    pass
