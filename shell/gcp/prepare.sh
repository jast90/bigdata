#!/bin/bash

# 临时关闭防火墙
sudo chkconfig iptables off
# 永久关闭防火墙
sudo service iptables stop

#临时管理selinux
sudo setenforce 0
#永久关闭seLinux
sudo sed -i 's/SELINUX=enforcing/SELINUX=disabled/' /etc/selinux/config

# 修改ssh配置使之可以免密登入
sudo sed -i 's/PermitRootLogin no/PermitRootLogin yes/' /etc/ssh/sshd_config
sudo sed -i 's/#PasswordAuthentication yes/PermitRootLogin yes/' /etc/ssh/sshd_config
sudo sed -i 's/PasswordAuthentication no/#PasswordAuthentication no/' /etc/ssh/sshd_config
sudo sed -i 's/#ChallengeResponseAuthentication yes/ChallengeResponseAuthentication yes/' /etc/ssh/sshd_config
sudo sed -i 's/ChallengeResponseAuthentication no/#ChallengeResponseAuthentication no/' /etc/ssh/sshd_config


# 修改root密码(非交互式修改root用户的密码
# sudo echo "root:123456" | chpasswd #无法执行

# 重启
#init 6