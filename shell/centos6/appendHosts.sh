#!/bin/bash
# 更新host
# 将$1参数值追加到1.txt
function appendToFile(){
    sudo echo $1 >> $2
}

function appendToHosts(){
    appendToFile $1 /etc/hosts
}

appendToHosts $1