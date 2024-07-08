DELIMITER $$
DROP function IF EXISTS movie_web.`func_splitString` $$
CREATE FUNCTION movie_web.`func_splitString`
( f_string varchar(1500),f_delimiter varchar(5),f_order int)
RETURNS varchar(255) CHARSET utf8   NO SQL
BEGIN

declare result varchar(255) default '';
set result = reverse(substring_index(reverse(substring_index(f_string,f_delimiter,f_order)),f_delimiter,1));
return result;
END $$

DROP function IF EXISTS movie_web.`func_similarity`;
 CREATE FUNCTION movie_web.func_similarity ( s1 varchar(1500), s2 varchar(1500) )
        RETURNS double NO SQL
        begin
            declare s, t1, t2, a, b float default 0;
            declare i int default 0;
            while (i < 128) do
                set a = convert(func_splitString(s1, ',', i+1), float);
                set b = convert(func_splitString(s2, ',', i+1), float);
                set s = s + a * a * b * b;
                set t1 = t1 + a * a;
                set t2 = t2 + b * b;
                set i = i + 1;
            end while;
            set s = s / (t1 * t2);
            return s;
        end;

DROP function IF EXISTS movie_web.`func_movieSimilarity`;
 CREATE FUNCTION movie_web.func_movieSimilarity ( mid1 varchar(10), mid2 varchar(10) )
        RETURNS double READS SQL DATA
        begin
            declare f1, f2 varchar(1500);
            declare s float default 0;
            select feature into f1 from movie where mid=mid1;
            select feature into f2 from movie where mid=mid2;
            set s = func_similarity(f1, f2);
            return s;
        end;

create fulltext index director_fulltext
    on movie_web.movie (director) WITH PARSER `ngram`;

create fulltext index title_fulltext
    on movie_web.movie (mname) WITH PARSER `ngram`;