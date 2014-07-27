sudo ntfs-3g /dev/sda7 /mnt/win-f
cd ~/link-goagent/local
#python ~/link-goagent/local/proxy.py &
python ~/link-goagent/local/proxy.py &

# -----------------------------------------------------------------
# 手动设置以太网
# 	sudo ifconfig eth0 inet up 192.0.2.1 netmask 255.255.255.0
# 手动设置无线网络
#	sudo ifconfig wlan0 inet up 192.168.0.8 netmask 255.255.255.0
# 禁用eth0
# sudo ifconfig eth0 inet down

# -----------------------------------------------------------------
# 在linux下面访问魅族3手机


# -----------------------------------------------------------------
# 初始化脚本,便于编辑后可通过git直接同步到github
# ln -s ~/workspace_github/hadoop-ws/linux-ws-debian/initial-user-env-of-hadoop.sh ~/initial-user-env-of-hadoop.sh
# hadoop用户的.bashrc
# ln -s ~/workspace_github/hadoop-ws/hadoop@debian.bashrc ~/.bashrc
# R语言的 .Rprofile
# ln -s ~/workspace_github/hadoop-ws/r-ws/env-R.Rprofile ~/.Rprofile
