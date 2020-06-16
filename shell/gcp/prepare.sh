#!/bin/bash

# 切换到root用户
sudo -i

# 临时关闭防火墙
chkconfig iptables off
# 永久关闭防火墙
servie iptables stop

#临时管理selinux
setenforce 0
#永久关闭seLinux
sed -i 's/SELINUX=enforcing/SELINUX=disabled/' /etc/selinux/config

# 修改ssh配置使之可以免密登入
sed -i 's/PermitRootLogin no/PermitRootLogin yes/' /etc/ssh/sshd_config
sed -i 's/#PasswordAuthentication yes/PermitRootLogin yes/' /etc/ssh/sshd_config
sed -i 's/PasswordAuthentication no/#PasswordAuthentication no/' /etc/ssh/sshd_config
sed -i 's/#ChallengeResponseAuthentication yes/ChallengeResponseAuthentication yes/' /etc/ssh/sshd_config
sed -i 's/ChallengeResponseAuthentication no/#ChallengeResponseAuthentication no/' /etc/ssh/sshd_config

# 修改root密码
echo "root:123456" | chpasswd

# 重启
init 6