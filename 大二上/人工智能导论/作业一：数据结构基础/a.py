gift=input()
fox=0
for it in gift:
    if(it=='B'):
        break
    elif it=='(':
        fox=fox+1
    else:
        fox=fox-1
print(fox)