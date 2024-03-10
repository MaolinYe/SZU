from torcheeg.datasets import DREAMERDataset
from torcheeg import transforms

from torcheeg.model_selection import KFoldGroupbyTrial

from torch.utils.data import DataLoader
from torcheeg.models import TSCeption
from torcheeg.trainers import ClassifierTrainer
import pytorch_lightning as pl

# Step 1: Initialize the Dataset
dataset = DREAMERDataset(io_path='dreamer', mat_path='DREAMER.mat',
                         offline_transform=transforms.Compose([
                             transforms.BaselineRemoval(),
                             transforms.MeanStdNormalize(),
                             transforms.To2d()
                         ]),
                         online_transform=transforms.ToTensor(),
                         label_transform=transforms.Compose([
                             transforms.Select('valence'),
                             transforms.Binary(3.0)
                         ]),
                         chunk_size=128, baseline_chunk_size=128, num_baseline=61, num_worker=4)
# Step 2: Divide the Training and Test Samples in the Dataset
k_fold = KFoldGroupbyTrial(n_splits=2, split_path=f'split')
# Step 3: Define the Model and Initiate Training
for i, (train_dataset, val_dataset) in enumerate(k_fold.split(dataset)):
    train_loader = DataLoader(train_dataset, batch_size=64, shuffle=True)
    val_loader = DataLoader(val_dataset, batch_size=64, shuffle=False)
    model = TSCeption(num_electrodes=14, num_classes=2, num_T=15, num_S=15, in_channels=1,
                      hid_channels=32, sampling_rate=128, dropout=0.5)
    trainer = ClassifierTrainer(model=model, num_classes=2, lr=1e-4, weight_decay=1e-4,
                                accelerator="cpu")
    trainer.fit(train_loader, val_loader, max_epochs=128, default_root_dir=f'model/{i}',
                callbacks=[pl.callbacks.ModelCheckpoint(save_last=True)],
                enable_progress_bar=True, enable_model_summary=True, limit_val_batches=0.0)
    score = trainer.test(val_loader, enable_progress_bar=True, enable_model_summary=True)[0]
    print(f'Fold {i} test accuracy: {score["test_accuracy"]:.4f}')
