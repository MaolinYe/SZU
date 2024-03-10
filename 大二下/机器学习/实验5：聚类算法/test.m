% 创建散点图
x = rand(10,1);
y = rand(10,1);
s = scatter(x,y,'m','o');

% 在每个点旁边添加图片
for i = 1:numel(x)
    % 读取图片
    img = picture;
    img=imrotate(img,180);
    colormap(gray(256));
    % 将图片缩放到适当的大小


    % 在每个点旁边添加图片
%     annotation('textbox', [x(i)-0.025 y(i) 0.1 0.1], 'string', '', 'edgecolor','none', 'margin',0);
    image('CData',img,'XData',[x(i)-0.1 x(i)+0.1],'YData',[y(i)-0.1+1 y(i)+0.1+1]);
end
