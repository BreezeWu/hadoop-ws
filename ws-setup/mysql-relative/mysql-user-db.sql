# 设置mysql支持中文
[mysqld]
character-set-server=utf8
init-connect='SET NAMES utf8'
collation-server=utf8_general_ci

[client]
default-character-set=utf8

# linux 命令行中
mysql -u root -p -h master-hadoop

-- mysql命令行中
create user 'wuhz'@'%' identified by 'wuhz';
grant all privileges on *.* to 'wuhz'@'%' with grant option;
flush privileges;

# linux 命令行中
mysql -u wuhz -pwuhz -h master-hadoop

-- mysql命令行中
create database dm2014;
use dm2014;

