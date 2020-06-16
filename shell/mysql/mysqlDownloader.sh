#!/bin/bash
host="https://repo.mysql.com/yum/mysql-5.6-community/el/6/x86_64/"
#bench="mysql-community-bench-5.6.48-2.el6.x86_64.rpm"
common="mysql-community-common-5.6.48-2.el6.x86_64.rpm"
libs="mysql-community-libs-5.6.48-2.el6.x86_64.rpm"
client="mysql-community-client-5.6.48-2.el6.x86_64.rpm"
server="mysql-community-server-5.6.48-2.el6.x86_64.rpm"
perlDbI="http://mirror.centos.org/centos/6/os/x86_64/Packages/perl-DBI-1.609-4.el6.x86_64.rpm"
connectorJava="https://mirrors.sohu.com/mysql/Connector-J/mysql-connector-java-5.1.48.zip"
rpmBundle="http://iso.mirrors.ustc.edu.cn/mysql-ftp/Downloads/MySQL-5.6/MySQL-5.6.48-1.el6.x86_64.rpm-bundle.tar"

cd /opt/software
sudo mkdir mysql
cd /opt/software/mysql


#sudo wget -b $host$common
#sudo wget -b $host$libs
#sudo wget -b $host$client
#sudo wget -b $host$server
sudo wget -b $rpmBundle
sudo wget -b $perlDbI
sudo wget -b $connectorJava
