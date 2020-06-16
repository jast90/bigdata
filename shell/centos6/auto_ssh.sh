#!/bin/bash
pcount=$#
if((pcount==0)); then
echo "auto_ssh.sh usage: auto_ssh.sh sshname hostName hostIndexStart hostIndexEnd username"
exit;
fi

sshName=$1
hostName=$2
hostIndexStart=$3
hostIndexEnd=$4
username=$5

# 无交互生成ssh key
ssh-keygen -t rsa -f ~/.ssh/$sshName  -P ""

# ssh-copy-id发送到多台主机上
for((host=hostIndexStart; host<=hostIndexEnd; host++)); do
    echo ------------------- hadoop$host --------------
    ssh-copy-id -i ~/.ssh/$sshName.pub $username@$hostName$host
done
