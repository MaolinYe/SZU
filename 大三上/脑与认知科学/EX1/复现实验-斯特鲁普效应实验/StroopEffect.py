from psychopy import visual, core, event
import pandas
import random

# 创建窗口
win = visual.Window(size=(1000, 618), monitor='testMonitor', units='pix')
# 定义颜色和相应的文字
colors = ['red', 'blue', 'green', 'yellow']
words = ['红色', '蓝色', '绿色', '黄色']
# 创建文本和颜色的显示对象
text_stim = visual.TextStim(win)
color_stim = visual.TextStim(win)
data = {'干扰': [], '反应时间': [], '结果': []}
# 定义实验循环次数
trials = 10
cue = visual.TextStim(win, text='根据你所看到的颜色进行输入\n红色输入r  蓝色输入b  绿色输入g  黄色输入y\n实验进行' + str(
    trials) + '次', pos=(0, 100))
# 实验循环
for _ in range(trials):
    # 随机选择文字和颜色
    color = random.randint(0, 3)
    word = random.randint(0, 3)
    if color == word:
        reference = '无干扰'
    else:
        reference = '有干扰'
    text = visual.TextStim(win, text=words[word], color=colors[color])
    cue.draw()
    text.draw()
    win.flip()
    Time = core.Clock()
    # 等待参与者响应
    keys = event.waitKeys(keyList=['r', 'b', 'g', 'y'])
    # 判断参与者的响应是否正确
    if keys[0] == colors[color][0]:
        result = '正确'
    else:
        result = '错误'
    # 显示反馈信息
    time = Time.getTime()
    feedback = result + '！反应时间为' + str(time) + '秒！'
    feedback_stim = visual.TextStim(win, text=feedback)
    feedback_stim.draw()
    win.flip()
    core.wait(1)
    data['干扰'].append(reference)
    data['反应时间'].append(time)
    data['结果'].append(result)
# 关闭窗口
win.close()
df = pandas.DataFrame(data)
try:
    DF = pandas.read_excel('data.xlsx')
    DF = pandas.concat([DF, df], ignore_index=True)
    DF.to_excel('data.xlsx', index=False)
except FileNotFoundError:
    df.to_excel('data.xlsx', index=False)
