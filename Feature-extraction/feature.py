import gensim
import jieba
import numpy as np

model_file = './model/baike_26g_news_13g_novel_229g.bin'
model = gensim.models.KeyedVectors.load_word2vec_format(model_file, binary=True)


def sentence_vector(s):
    words = jieba.lcut(s)
    v = np.zeros(128)
    count = 0
    for word in words:
        try:
            v += model[word]
            count += 1
        except KeyError:
            pass
    if count:
        v /= count
    return v
