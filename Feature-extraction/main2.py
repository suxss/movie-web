from tqdm import tqdm
import DBC


def updateSimilar():
    conn = DBC.Connection()
    id_content = conn.getContents()
    pbar = tqdm(range(260, len(id_content)))
    for i in pbar:
        select_result = conn.getMostSimilar(id_content[i][0])
        conn.updateSimilar(id_content[i][0], select_result[0][0], select_result[1][0], select_result[2][0],
                           select_result[3][0])


# 1115
if __name__ == '__main__':
    updateSimilar()
