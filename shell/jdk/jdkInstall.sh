# 安装jdk
sudo tar -zxvf /opt/software/jdk-8u212-linux-x64.tar.gz -C /opt
sudo sed -i '$ i export JAVA_HOME=/opt/jdk1.8.0_212' /etc/profile
sudo sed -i '$ i export PATH=$JAVA_HOME:$PATH' /etc/profile
sudo source /etc/profile
