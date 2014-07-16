sudo ntfs-3g /dev/sda7 /mnt/win-f
cd ~/link-goagent/local
python ~/link-goagent/local/proxy.py

# 设置网络
# sudo ifconfig eth0 inet up 192.0.2.1 netmask 255.255.255.0

# 初始化脚本,便于编辑后可通过git直接同步到github
# ln -s /home/hadoop/workspace_github/hadoop-ws/linux-ws-debian/initial-user-env-of-hadoop.sh /home/hadoop/initial-user-env-of-hadoop.sh
# hadoop用户的.bashrc
# ln -s /home/hadoop/workspace_github/hadoop-ws/hadoop@debian.bashrc /home/hadoop/.bashrc
