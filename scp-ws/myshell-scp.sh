#! /bin/bash

# export VAR_SCP=hadoop
cd /opt; tar -zcf ${VAR_SCP}.tar.gz ${VAR_SCP}
scp /opt/${VAR_SCP}.tar.gz hadoop@slave-hadoop-01:/opt
ssh hadoop@slave-hadoop-01 "cd /opt; tar -zxf ${VAR_SCP}.tar.gz"
ssh hadoop@slave-hadoop-01 "rm /opt/${VAR_SCP}.tar.gz"
cd /opt; rm ${VAR_SCP}.tar.gz
export VAR_SCP=不存在的VAR_SCP变量
