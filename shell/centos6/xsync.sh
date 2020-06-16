#!/bin/bash
#1 获取输入参数个数，如果没有参数，直接退出
pcount=$#
if((pcount==0)); then
echo no args;
exit;
fi

hostName=$2
hostIndexStart=$3
hostIndexEnd=$4

#2 获取文件名称
p1=$1
fname=`basename $p1`
echo fname=$fname

#3 获取上级目录到绝对路径
pdir=`cd -P $(dirname $p1); pwd`
echo pdir=$pdir

#4 获取当前用户名称
user=`whoami`

#5 循环
for((host=hostIndexStart; host<hostIndexEnd; host++)); do
    echo ------------------- hadoop$host --------------
    rsync -rvl $pdir/$fname $user@$hostName$host:$pdir
done