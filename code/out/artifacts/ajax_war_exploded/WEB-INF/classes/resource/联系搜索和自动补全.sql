CREATE TABLE t_questions(
	id INT PRIMARY KEY AUTO_INCREMENT,
	question VARCHAR(32) NOT NULL DEFAULT ""
);
DROP TABLE t_questions;
SELECT * FROM t_questions;
INSERT INTO t_questions VALUES(NULL, "北京时间");
INSERT INTO t_questions VALUES(NULL, "北京天气");
INSERT INTO t_questions VALUES(NULL, "北京尾号限行");
INSERT INTO t_questions VALUES(NULL, "北京交通大学");
INSERT INTO t_questions VALUES(NULL, "北京旅游攻略");
INSERT INTO t_questions VALUES(NULL, "北京大学");
INSERT INTO t_questions VALUES(NULL, "北京地铁时刻表");

INSERT INTO t_questions VALUES(NULL, "潮州天气");
INSERT INTO t_questions VALUES(NULL, "潮州马桶厂家直销");
INSERT INTO t_questions VALUES(NULL, "潮州旅游必去十大景点");
INSERT INTO t_questions VALUES(NULL, "潮州是哪个省");
INSERT INTO t_questions VALUES(NULL, "潮州人才网");
INSERT INTO t_questions VALUES(NULL, "潮州天气预报");
INSERT INTO t_questions VALUES(NULL, "潮州旅游攻略");
INSERT INTO t_questions VALUES(NULL, "潮州旅游自助游");
INSERT INTO t_questions VALUES(NULL, "潮州和潮汕的区别");

INSERT INTO t_questions VALUES(NULL, "饶平县属于哪个市");
INSERT INTO t_questions VALUES(NULL, "饶平天气");
INSERT INTO t_questions VALUES(NULL, "饶平县");
INSERT INTO t_questions VALUES(NULL, "饶平邮政编码");
INSERT INTO t_questions VALUES(NULL, "饶平是汕头还是潮州");
INSERT INTO t_questions VALUES(NULL, "饶平县人民政府");

SELECT id,question FROM t_questions WHERE question LIKE "%大学%";