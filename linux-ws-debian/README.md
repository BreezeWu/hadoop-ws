��Windows��Linux�������ʹ��˫����ϵͳLinux���ļ�
===============================================

��������
---------
1����һ��������ϰ�װ��������ϵͳ���� Win7 �� Debian����ϵͳ�������Debian����ϵͳ��֮Ϊphysical Debian��
2����Windows�����ϰ�װVMWare���������������һ���������Ҳ��װDebian����ϵͳ�������Debian����ϵͳ��֮ΪVitual Debian��
3����Vitual Debianʹ��physical Debian���ļ�
��1����Windows�����ϰ�װExt2Fsd����Windows�����Է���physical Debian���ļ�ϵͳ��ע�����ÿɶ�д�������磬�ҽ���Win7��J�̡�
��2����VMWare������Vitual Debian��������ԣ���Win7��J�̹��������������磬������Ҳ��J��
��3������Vitual Debian���ҽ�VMWare����������ļ���VMWare�������������ļ�·��Ϊ��/mnt/hgfs/J��
ln -s /mnt/hgfs/J/home/hadoop/workspace_github /home/hadoop/workspace_github
4��Ȼ�󣬾Ϳ�����Vitual Debian��ʹ��phsical debian���ļ��ͳ���
5��ע�����������⣺
��1��physical Debian��Vitual Debian��������Ҫһ�¡���Ȼhadoop������������⡣
��2��Vitual Debian�������´����ļ������ƺ����ܹ��޸�physical Debian�ϵ��ļ�����why?��

�Ҹ��˻��ĳ���
--------------
mv ~/.bashrc ~/bak-.bashrc
ln -s ~/workspace_github/hadoop-ws/linux-ws-debian/hadoop-at-debian.bashrc ~/.bashrc
source ~/.bashrc
Ȼ�󣬿�������spark-shell

���磺ʹ��physical Debian�µ�hadoop-2.5.1���������
ln -s /mnt/hgfs/J/opt/hadoop/hadoop-2.5.1 /opt/hadoop/hadoop-2.5.1
ln -s /mnt/hgfs/J/home/hadoop/data-hadoop-2.5.1 /home/hadoop/data-hadoop-2.5.1

��ô����ԭ��
--------------
1.�ҵıʼǱ�Ӳ���������ޣ�Win7�µ�������Ƚ�������ʵ�ʹ�����Ч�ʺܵ͡�
2.������Ҫ��һЩ��ѵ����Ҫʹ��Win7�µĻ����������Ҫʵ����ʾVitual Debian�ϵ�һЩ���ݡ�
 
