from psychopy import visual, event, core
import pandas as pd

# 初始化窗口和图像
win = visual.Window(size=(1000, 618), monitor='testMonitor', units='pix')
left_image = visual.ImageStim(win, image="C++.jfif", pos=(-300, 0), size=(400, 400))
right_image = visual.ImageStim(win, image="Java.jfif", pos=(300, 0), size=(400, 400))
prompt_text = visual.TextStim(win, text="请选择一张图像\n按N键选择左图像\n按M键选择右图像", pos=(0, 0))

# 绘制图像和提示词
left_image.draw()
right_image.draw()
prompt_text.draw()
win.flip()

# 等待用户按键
response = None
rt_clock = core.Clock()
while True:
    keys = event.getKeys()
    if 'n' in keys:
        response = "左图像"
        break
    elif 'm' in keys:
        response = "右图像"
        break

# 输出结果
rt = rt_clock.getTime()
print("用户选择了：", response)
print("反应时间：", rt)

# 保存数据到表格
data = {'选择': [response], '反应时间': [rt]}
df = pd.DataFrame(data)

try:
    existing_df = pd.read_excel('data.xlsx')  # 读取已存在的表格数据
    newDF = pd.concat([df, existing_df], ignore_index=True)
    newDF.to_excel('data.xlsx', index=False)
except FileNotFoundError:
    df.to_excel('data.xlsx', index=False)

# 关闭窗口
win.close()
