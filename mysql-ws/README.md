# install mysql
sudo apt-get install mysql-server mysql-client

# enter mysql
mysql -uroot -p 
# ERROR 1045 (28000): Access denied for user 'root'@'localhost' (using password: YES)


# 在mysql中创建hive用户
#create user 'hive'@'%' identified by 'hive';
#grant all privileges on *.* to 'hive'@'%' with grant option;
#grant all privileges on *.* to 'root'@'%' with grant option;
create user 'hive'@'hadoop-master' identified by 'hive';
grant all privileges on *.* to 'hive'@'hadoop-master' with grant option;
grant all privileges on *.* to 'root'@'hadoop-master' with grant option;
flush privileges;

#修改/etc/mysql/my.cnf，调整其绑定的ip
#注释掉 # bind-address = 127.0.0.1

#创建hive数据库
#使用hive用户登录
mysql -u hive -phive -h hadoop-master
# 创建hive数据库---也可以由hive自动创建
create database hive_db;
show databases;

#在mysql中创建sqoop用户
#create user 'sqoop'@'%' identified by 'sqoop';
#grant all privileges on *.* to 'sqoop'@'%' with grant option;
#grant all privileges on *.* to 'root'@'%' with grant option;
create user 'sqoop'@'hadoop-master' identified by 'sqoop';
grant all privileges on *.* to 'sqoop'@'hadoop-master' with grant option;
grant all privileges on *.* to 'root'@'hadoop-master' with grant option;
flush privileges;

#使用sqoop用户登录
mysql -u sqoop -psqoop -h hadoop-master
#创建sqoop数据库
create database sqoop_db;
show databases;



