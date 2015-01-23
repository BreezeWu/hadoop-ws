“Windows下Linux虚拟机”使用双操作系统Linux的文件
===============================================

操作步骤
---------
1、在一个物理机上安装两个操作系统，如 Win7 和 Debian操作系统。这里的Debian操作系统称之为physical Debian。
2、在Windows机器上安装VMWare虚拟软件，并创建一个虚拟机，也安装Debian操作系统。这里的Debian操作系统称之为Vitual Debian。
3、让Vitual Debian使用physical Debian的文件
（1）在Windows机器上安装Ext2Fsd，让Windows机可以访问physical Debian的文件系统（注意设置可读写），比如，挂接在Win7的J盘。
（2）在VMWare上设置Vitual Debian虚拟机属性，将Win7的J盘共享给虚拟机。比如，共享名也是J。
（3）启动Vitual Debian，挂接VMWare共享给她的文件。VMWare共享给虚拟机的文件路径为“/mnt/hgfs/J”
ln -s /mnt/hgfs/J/home/hadoop/workspace_github /home/hadoop/workspace_github
4、然后，就可以在Vitual Debian下使用phsical debian的文件和程序。
5、注意事项与问题：
（1）physical Debian和Vitual Debian的主机名要一致。不然hadoop启动会存在问题。
（2）Vitual Debian可以重新创建文件，但似乎不能够修改physical Debian上的文件。（why?）

我个人化的程序
--------------
mv ~/.bashrc ~/bak-.bashrc
ln -s ~/workspace_github/hadoop-ws/linux-ws-debian/hadoop-at-debian.bashrc ~/.bashrc
source ~/.bashrc
然后，可以启动spark-shell

又如：使用physical Debian下的hadoop-2.5.1程序和数据
ln -s /mnt/hgfs/J/opt/hadoop/hadoop-2.5.1 /opt/hadoop/hadoop-2.5.1
ln -s /mnt/hgfs/J/home/hadoop/data-hadoop-2.5.1 /home/hadoop/data-hadoop-2.5.1

这么做的原因
--------------
1.我的笔记本硬件配置有限，Win7下的虚拟机比较慢，若实际工作则效率很低。
2.但我又要做一些培训，需要使用Win7下的会议软件，并要实际演示Vitual Debian上的一些内容。
 
