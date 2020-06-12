#!/bin/bash
host="https://repo.mysql.com/yum/mysql-5.6-community/el/6/x86_64/"
#bench="mysql-community-bench-5.6.48-2.el6.x86_64.rpm"
common="mysql-community-common-5.6.48-2.el6.x86_64.rpm"
libs="mysql-community-libs-5.6.48-2.el6.x86_64.rpm"
client="mysql-community-client-5.6.48-2.el6.x86_64.rpm"
server="mysql-community-server-5.6.48-2.el6.x86_64.rpm"
perlDbI="http://repo.openfusion.net/centos6-x86_64/perl-DBI-1.616-1.of.el6.x86_64.rpm"
connectorJava="https://mirrors.sohu.com/mysql/Connector-J/mysql-connector-java-5.1.48.zip"

cd /opt/software
sudo mkdir mysql
cd /opt/software/mysql

sudo wget -b $host$common
sudo wget -b $host$libs
sudo wget -b $host$client
sudo wget -b $host$server
sudo wget -b $perlDbI
sudo wget -b $connectorJava
