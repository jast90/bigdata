# 解压tar包
tar -xvf /opt/software/mysql/MySQL-5.6.48-1.el6.x86_64.rpm-bundle.tar -C .

# 解压java 驱动
unzip /opt/software/mysql/mysql-connector-java-5.1.48.zip

# 卸除自带的mysql


# 安装mysql
cd /opt/software/mysql/
rpm -ivh MySQL-server**.rpm