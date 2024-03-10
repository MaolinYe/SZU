clc;
clear;
load ./train_images.mat;
load ./train_labels.mat;
k = 10;
dimension = 2;
Dimension = 28 * 28;
picturesNumber = 100;
sample = train_images(:, :, 1:picturesNumber);
sample = reshape(sample, 28 * 28, picturesNumber);
sample = sample';
class = zeros(1, picturesNumber);
times = [];
ratios = [];
for time = 1:10
    tic;

    % K-Means++ initial center selection
    classCenter = zeros(k, Dimension);
    classCenter(1, :) = sample(randi(picturesNumber), :);
    for j = 2:k
        distances = pdist2(sample, classCenter(1:j-1, :));
        minDistances = min(distances, [], 2); % 为什么挑最近的呢？因为是挑离所有已选中心最远的
        [~, index] = max(minDistances);
        classCenter(j, :) = sample(index, :);
    end

    iterator = 0;
    while (true)
        iterator = iterator + 1;
        nextCenter = zeros(k, Dimension);
        classNumber = zeros(1, k);
        for i = 1:picturesNumber
            distances = pdist2(sample(i, :), classCenter);
            [~, index] = min(distances);
            class(i) = index;
            classNumber(class(i)) = classNumber(class(i)) + 1;
            nextCenter(class(i), :) = nextCenter(class(i), :) + sample(i, :);
        end
        temp = classCenter;
        for i = 1:k
            if classNumber(i) ~= 0
                classCenter(i, :) = nextCenter(i, :) / classNumber(i);
            end
        end
        if isequal(temp, classCenter)
            break;
        end
    end

    map = containers.Map('KeyType', 'int32', 'ValueType', 'int32');
    for i = 1:k
        number = [];
        for j = 1:picturesNumber
            if class(j) == i
                number = [number, train_labels(j)];
            end
        end
        map(i) = mode(number);
    end
    count = 0;
    for i = 1:picturesNumber
        if map(class(i)) == train_labels(i)
            count = count + 1;
        end
    end
    ratio = count / picturesNumber;
    ratios = [ratios, ratio];
    times = [times, toc];
end
