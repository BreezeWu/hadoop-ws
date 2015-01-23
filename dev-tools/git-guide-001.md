git clone https://github.com/apache/spark.git
git checkout -b my-branch-1.2 origin/branch-1.2

git pull

git push -u origin master   #第一次推送 -u: Git不但会把本地的master分支内容推送的远程新的master分支，还会把本地的master分支和远程的master分支关联起来，在以后的推送或者拉取时就可以简化命令。
git push origin master

git branch -b my-branch-1.2 branch-1.2
git checkout my-branch-1.2
# 或合并为下面一条命令
#git checkout -b my-branch-1.2 branch-1.2

git add ***.**
git commit -m "说明"
git push origin master

#git merge命令用于合并指定分支到当前分支
git merge dev

