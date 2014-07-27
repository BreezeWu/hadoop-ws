# -----------------------------------------------------------------------------
# linux下字体配置

图形界面下, 选择 advanced setting, 然后选择 fonts
然后配置为文泉驿自体.即可解决chrome某些字发虚的问题.

# -----------------------------------------------------------------------------
# linux下安装微软自体
# -----------------------------------------------------------------------------
# 官方安装方法

sudo apt-get install ttf-mscorefonts-installer
# license?
sudo echo ttf-mscorefonts-installer msttcorefonts/accepted-mscorefonts-eula select true | sudo debconf-set-selections

# -----------------------------------------------------------------------------
# 安装雅黑字体

# linux安装微软雅黑字体
#	http://www.ojpal.com/home.php?mod=space&uid=32&do=blog&id=1557
	
#linux下的中文字体实在糟糕的很，尤其是用chrome，默认的字体怎能用难看来形容。说到中文字体还是微软雅黑漂亮点～
#	下载字体文件：http://dl.dbank.com/c04q4bhp8b
 
# 雅黑字体文件在Vista中放在$WINDOWS/fonts/msyh.ttf，网上也可以很容易获得。

# a.得到字体文件，修改文件权限为777以便所有用户使用，并把字体文件移动到/usr/share/fonts/zh_CN文件夹中：
#chmod 777 msyh.ttf

mkdir /usr/share/fonts/zh_CN
cd msyh
sudo cp msyh* /usr/share/fonts/zh_CN
sudo chmod 777 /usr/share/fonts/zh_CN/msyh*

# b.为系统构建字体文件并刷新字体缓存
cd /usr/share/fonts/zh_CN
sudo mkfontscale
sudo mkfontdir
sudo fc-cache -fv

# -----------------------------------------------------------------------------
# 安装文泉驿字体
#	http://wenq.org/wqy2/index.cgi?HalfTeal_INSTALL_zh




