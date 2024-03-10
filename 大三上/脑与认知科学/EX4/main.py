import numpy as np
from scipy.ndimage import zoom
from scipy.special import logsumexp
import torch
import deepgaze_pytorch
import cv2
import os
import matplotlib.pyplot as plt

DEVICE = 'cpu'
model = deepgaze_pytorch.DeepGazeIIE(pretrained=True).to(DEVICE)
centerbias_template = np.load('centerbias_mit1003.npy')
Origin_path = "EX4/Origin"
Saliency_Path = "EX4/Saliency"
for file_name in os.listdir(Origin_path):
    if file_name.endswith('.jpg') or file_name.endswith('.png'):
        image_path = Origin_path + '/' + file_name
        image = cv2.imread(image_path)
        centerbias = zoom(centerbias_template, (
            image.shape[0] / centerbias_template.shape[0],
            image.shape[1] / centerbias_template.shape[1]),
                          order=0, mode='nearest')
        centerbias -= logsumexp(centerbias)
        image_tensor = torch.tensor([image.transpose(2, 0, 1)]).to(DEVICE)
        centerbias_tensor = torch.tensor([centerbias]).to(DEVICE)
        log_density_prediction = model(image_tensor, centerbias_tensor)
        Saliency = log_density_prediction.detach().cpu().numpy()[0, 0]
        plt.imshow(Saliency)
        plt.axis('off')
        plt.savefig(Saliency_Path + '/' + file_name, bbox_inches='tight', pad_inches=0)
