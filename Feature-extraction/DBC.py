import pymysql
import db_settings


class Connection:
    mysql_conn = pymysql.connect(host=db_settings.host, port=db_settings.port, user=db_settings.user,
                                 password=db_settings.password, db=db_settings.db)

    def getContents(self):
        sql = "select * from movie_web.movie where s1_mid is null  or  s2_mid is null  or s3_mid is null  or s4_mid is null;"
        try:
            with self.mysql_conn.cursor() as cursor:
                cursor.execute(sql)
                select_result = cursor.fetchall()
                return select_result
        except Exception as e:
            print(e)
            return None

    def updateFeature(self, mid: str, feature: str):
        sql = f"UPDATE 综合项目.movie t SET t.feature = '{feature}' WHERE t.mid LIKE '{mid}'"
        try:
            with self.mysql_conn.cursor() as cursor:
                cursor.execute(sql)
            self.mysql_conn.commit()
            return 0
        except Exception as e:
            self.mysql_conn.rollback()
            return -1

    def getMostSimilar(self, mid, n=4):
        sql = f"select mid from movie where mid != {mid} order by func_movieSimilarity({mid}, mid) desc limit {n};"
        try:
            with self.mysql_conn.cursor() as cursor:
                cursor.execute(sql)
                select_result = cursor.fetchall()
                return select_result
        except Exception as e:
            print(e)
            return None

    def updateSimilar(self, mid: str, smid1: str, smid2: str, smid3: str, smid4: str):
        sql = f"UPDATE 综合项目.movie t SET t.s1_mid = '{smid1}', t.s2_mid = '{smid2}', t.s3_mid = '{smid3}', t.s4_mid = '{smid4}' WHERE t.mid LIKE '{mid}'"
        try:
            with self.mysql_conn.cursor() as cursor:
                cursor.execute(sql)
            self.mysql_conn.commit()
            return 0
        except Exception as e:
            self.mysql_conn.rollback()
            return -1

# if __name__ == '__main__':
#     conn = Connection()
#     conn.updateFeature(mid='7065187', feature='1243,34')
