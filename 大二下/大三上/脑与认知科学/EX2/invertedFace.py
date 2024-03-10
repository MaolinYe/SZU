import os
import random
import pandas
from psychopy import visual, event, core


# 展示提示文字
def showCue(text):
    cue = visual.TextStim(win, text=text, pos=(0, 0))
    cue.draw()
    win.flip()
    event.waitKeys()


facesPath = 'face'
faces = os.listdir(facesPath)
showFaces = random.sample(faces, 15)
win = visual.Window(size=(1000, 618))
showCue('将展示10张照片，每张照片展示2秒\n请尝试记住它们，按任意键开始')
for face in showFaces[:10]:
    facePath = os.path.join(facesPath, face)
    faceImage = visual.ImageStim(win, image=facePath)
    faceImage.ori = 180
    faceImage.draw()
    win.flip()
    core.wait(2)
showedFaces = random.sample(showFaces[:10], 5)  # 随机取出5张展示过的照片
showFaces = showedFaces + showFaces[10:]  # 取出未展示过的5张照片和展示过的照片组合在一起
random.shuffle(showFaces)  # 重新打乱图片顺序
showCue('接下来将展示10张照片\n你需要判断该照片是否出现过\n如果出现过请按Y，否则请按N\n按任意键开始')
data = {'结果': [], '反应时间': []}
for face in showFaces[:10]:
    Time = core.Clock()
    facePath = os.path.join(facesPath, face)
    faceImage = visual.ImageStim(win, image=facePath)
    faceImage.ori = 180
    faceImage.draw()
    win.flip()
    key = event.waitKeys(keyList=['y', 'n'])
    time = Time.getTime()
    if key[0] == 'y' and face in showedFaces or key[0] == 'n' and face not in showedFaces:
        result = '正确'
    else:
        result = '错误'
    showCue(result + '！反应时间为' + str(time) + '秒！\n' + '按任意键继续')
    data['结果'].append(result)
    data['反应时间'].append(time)
win.close()
# 将数据写入excel
excel = 'invertData.xlsx'
df = pandas.DataFrame(data)
try:
    DF = pandas.read_excel(excel)  # 该execl文件已经存在则追加数据
    DF = pandas.concat([DF, df], ignore_index=True)
    DF.to_excel(excel, index=False)
except FileNotFoundError:  # 没有该excel文件将创建一个新的
    df.to_excel(excel, index=False)
