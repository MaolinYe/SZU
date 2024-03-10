import pysaliency

dataset_location = 'datasets'
mit_stimuli, mit_fixations = pysaliency.external_datasets.get_mit1003(location=dataset_location)
my_model = pysaliency.SaliencyMapModelFromDirectory(mit_stimuli, 'EX4/Saliency')
auc = my_model.AUC(mit_stimuli, mit_fixations)
nss = my_model.NSS(mit_stimuli, mit_fixations)
print('AUC: ', auc)
print('NSS: ', nss)
