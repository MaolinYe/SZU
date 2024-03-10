x = linspace(0,2*pi,100);
y1 = sin(x);
y2 = cos(x);
diff = y1 - y2;
plot(x,y1,x,y2);
hold on;
bar(x,diff);
legend('sin(x)','cos(x)','difference');