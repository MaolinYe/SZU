expression = input()
stack = []
result = 0
for it in expression:
    if it == '{' or it == '[' or it == '(':
        stack.append(it)
    elif stack == [] and (it == '}' or it == ']' or it == ')'):
        result = -2
        break
    elif stack and (it == '}' and stack.pop() != '{' or it == ']' and stack.pop() != '[' or it == ')' and stack.pop() != '('):
        result = -1
        break
if stack and result!=-1:
    result = -3
print(result)
