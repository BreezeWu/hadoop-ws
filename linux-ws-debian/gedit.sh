# -----------------------------------------------------------------------------
# 配置gedit 比如,支持列编辑
#	https://github.com/ltoth/gedit-conf

cd ~/.gnome2
git clone git@github.com:ltoth/gedit-conf.git

# * Install gedit-plugins, csstidy, git-core, gitk, pyqt4-dev-tools through apt-get
sudo apt-get install gedit-plugins csstidy git-core gitk pyqt4-dev-tools

# * Install git-cola from http://cola.tuxfamily.org/releases/release/
sudo apt-get install git-cola

gconftool-2 --type int --set /apps/gedit-2/plugins/elastictabstops/minimumwidth 15
gconftool-2 --type int --set /apps/gedit-2/plugins/elastictabstops/paddingwidth 10
