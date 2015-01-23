#debian所在磁盘路径
export MAJOR_USER_AT_DEBIAN_HOME=/media/hadoop/0145ddb8-58dd-4eb8-badc-c418e8d647c9/home/hadoop
export MAJOR_USER_AT_DEBIAN_OPT=/media/hadoop/0145ddb8-58dd-4eb8-badc-c418e8d647c9/opt

#查看当前mount
mount fdisk -l

#1.挂接debian下的opt
#将/dev/sda10卸载，重新挂在到/opt_ubuntu
sudo unmount /dev/sda10
sudo mkdir /opt_ubuntu
sudo mount /dev/sda10 /opt_ubuntu
#将debian下/opt链接到ubuntu的/opt
sudo ln -s ${MAJOR_USER_AT_DEBIAN_OPT} /

#2.挂接debian下hadoop用户的目录
sudo ln -s ${MAJOR_USER_AT_DEBIAN_HOME}/workspace_github /home/hadoop/workspace_github
sudo ln -s ${MAJOR_USER_AT_DEBIAN_HOME}/workspace_data /home/hadoop/workspace_data
sudo ln -s ${MAJOR_USER_AT_DEBIAN_HOME}/workspace_idea /home/hadoop/workspace_idea
sudo ln -s ${MAJOR_USER_AT_DEBIAN_HOME}/ldoce5 /home/hadoop/ldoce5

#3.使用debian下的用户配置文件
mv ~/.bashrc ~/.bashrc_ubuntu
ln -s ~/workspace_github/hadoop-ws/linux-ws-debian/hadoop-at-debian.bashrc ~/.bashrc
source ~/.bashrc

#4.git配置文件
ln -s ~/workspace_github/hadoop-ws/linux-ws-debian/git-conf.gitignore ~/.gitignore
ln -s ~/workspace_github/hadoop-ws/linux-ws-debian/git-conf.gitignore_global ~/.gitignore_global

#5.其他
ln -s ~/workspace_github/hadoop-ws/r-ws/env-R.Rprofile ~/.Rprofile
ln -s ~/workspace_github/hadoop-ws/linux-ws-debian/initial-user-env-of-hadoop.sh ~/initial-user-env-of-hadoop.sh
ln -s ~/workspace_github/hadoop-ws/linux-ws-debian/git-conf.gitconfig ~/.gitconfig



