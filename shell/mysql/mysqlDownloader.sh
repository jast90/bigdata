#!/bin/bash
host="https://repo.mysql.com/yum/mysql-5.6-community/el/6/x86_64/"
#bench="mysql-community-bench-5.6.48-2.el6.x86_64.rpm"
common="mysql-community-common-5.6.48-2.el6.x86_64.rpm"
libs="mysql-community-libs-5.6.48-2.el6.x86_64.rpm"
client="mysql-community-client-5.6.48-2.el6.x86_64.rpm"
server="mysql-community-server-5.6.48-2.el6.x86_64.rpm"
perlDbI="http://repo.openfusion.net/centos6-x86_64/perl-DBI-1.616-1.of.el6.x86_64.rpm"

cd /opt/software
sudo mkdir mysql
cd /opt/software/mysql

wget -b $host$common
wget -b $host$libs
wget -b $host$client
wget -b $host$server
wget -b $perlDbI
