import json
import os
import os.path as osp


def main():
    # json_file是标注完之后生成的json文件的目录。out_yolo_path是yolo格式输出目录，即数据处理完之后文件保存的路径
    # TODO 1:修改读取与保存文件路径
    json_file = r"C:\Users\Yezi\Desktop\人工智能实训\data"  # json文件读取路径
    out_yolo_path = r"C:\Users\Yezi\Desktop\人工智能实训\yoloData"  # 输出yolo文件格式

    # 如果输出的路径不存在，则自动创建这个路径
    if not osp.exists(out_yolo_path):
        os.mkdir(out_yolo_path)

    for file_name in os.listdir(json_file):
        # 遍历json_file里面所有的文件，并判断这个文件是不是以.json结尾
        if file_name.endswith(".json"):
            path = os.path.join(json_file, file_name)
            if os.path.isfile(path):
                data = json.load(open(path))  # 读取json文件
                out_file = open(out_yolo_path + '/' + file_name[:-5] + ".txt", 'w+')
                # TODO 2：读取json后找到需要的数据，然后得到id,x,y,w,h,后四个需要归一化，保存在对应的txt文件里，具体查看文档。
                width, height = data["info"]["width"], data["info"]["height"]
                for obj in data["objects"]:  # 读取json中为objects的内容，其中bbox的内容是你标注的对象的对应方框两个的坐标，可以自己检查是图片对应的哪个像素坐标
                    xmin, ymin, xmax, ymax = obj["bbox"]
                    id = 0
                    x = (xmin + xmax) / 2
                    y = (ymin + ymax) / 2
                    w = xmax - xmin
                    h = ymax - ymin
                    out_file.write(
                        str(id) + ' ' + str(x / width) + ' ' + str(y / height) + ' ' + str(w / width) + ' ' + str(
                            h / height) + '\n')  # 保存
                out_file.close()
    print("Done")


if __name__ == "__main__":
    main()
