import random
import pandas
from psychopy import visual, event, core


# 展示提示文字，并根据参数实现不同的文字展示
def showCue(text, x=0, y=0, wait=True, title=False, flip=True):
    if title:
        cue = visual.TextStim(win, text=text, pos=(x, y), color='black', height=50, bold=True)
    else:
        cue = visual.TextStim(win, text=text, pos=(x, y), color='black', height=20, bold=True)
    cue.draw()
    if flip:
        win.flip()
    if wait:
        event.waitKeys()


# 构建绿色方块的随机位置
places = []  # 创建空列表
while len(places) < 30:
    num = random.randint(0, 8)  # 生成一个0到8的随机整数
    if not places or num != places[-1]:  # 如果列表为空或者新生成的数与前一个数不相同
        places.append(num)  # 将新生成的数添加到列表中
size = 145   # 方块大小
positions = [
    (-150, 150), (0, 150), (150, 150),
    (-150, 0), (0, 0), (150, 0),
    (-150, -150), (0, -150), (150, -150)
]
win = visual.Window(size=(1000, 618), color='white', units='pix')
showCue('工作记忆测试实验', wait=False, title=True, y=150, flip=False)
showCue('判断前n次绿色方块出现的位置\n按下对应位置的数字,共测试5次\n按任意键查看方块位置对应的数字')
for i in range(9):
    cube = visual.Rect(win, width=size, height=size, pos=positions[i], fillColor='#afafaf')
    cube.draw()
    number = visual.TextStim(win, text=str(i + 1), pos=positions[i], height=size / 2)
    number.draw()
showCue('数字代表方块的位置，按任意键开始测试', y=250)
data = {'结果': [], '反应时间': [], 'n': []}
for index in range(30):
    for i in range(9):
        if i == places[index]:
            color = 'green'
        else:
            color = '#afafaf'
        cube = visual.Rect(win, width=size, height=size, pos=positions[i], fillColor=color)
        cube.draw()
    core.wait(1)  # 展示一秒
    if (index + 1) % 6 == 0:
        n = random.randint(3, 5)
        showCue('前 ' + str(n) + ' 次绿色方块出现的位置是', y=250, wait=False)
        Time = core.Clock()
        key = event.waitKeys(keyList=[str(i) for i in range(10)])
        time = Time.getTime()
        if key[0] == str(places[index - n]+1):
            result = '正确'
        else:
            result = '错误'
        showCue(result + '！反应时间为' + str(time) + '秒！\n' + '按任意键继续')
        data['结果'].append(result)
        data['反应时间'].append(time)
        data['n'].append(n)
    win.flip()
showCue('实验结束，按任意键退出')
win.close()
# 将数据写入excel
excel = 'personDData.xlsx'
df = pandas.DataFrame(data)
try:
    DF = pandas.read_excel(excel)  # 该execl文件已经存在则追加数据
    DF = pandas.concat([DF, df], ignore_index=True)
    DF.to_excel(excel, index=False)
except FileNotFoundError:  # 没有该excel文件将创建一个新的
    df.to_excel(excel, index=False)
