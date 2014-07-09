# ------------------------------------------------------------------
To set the default to UTF-8, you want to add the following to my.cnf

[client]
default-character-set=utf8

[mysql]
default-character-set=utf8


[mysqld]
collation-server = utf8_unicode_ci
init-connect='SET NAMES utf8'
character-set-server = utf8

# ------------------------------------------------------------------
# 检查方法
1. 进入mysql
2. 执行下面命令
	show variables like "char%"
	show variables like "collation_%"
3.然后试图建立列名含有中文的表,查询meta表
	select * from columns_v2;

