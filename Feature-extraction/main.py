from tqdm import tqdm
import DBC


def updateFeature():
    import feature
    conn = DBC.Connection()
    id_content = conn.getContents()
    pbar = tqdm(range(len(id_content)))
    for i in pbar:
        f = feature.sentence_vector(id_content[i][1])
        f_str = ','.join(map(lambda y: "{0:.6f}".format(y), f))
        conn.updateFeature(mid=id_content[i][0], feature=f_str)


def updateSimilar():
    conn = DBC.Connection()
    id_content = conn.getContents()
    pbar = tqdm(range(4000, len(id_content)))
    for i in pbar:
        select_result = conn.getMostSimilar(id_content[i][0])
        conn.updateSimilar(id_content[i][0], select_result[0][0], select_result[1][0], select_result[2][0],
                           select_result[3][0])

# 2826
if __name__ == '__main__':
    updateSimilar()
