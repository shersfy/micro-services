#!/bin/bash
#author py
#date 2018-07-05

jar_name=$1
pid=`ps aux | grep $jar_name | grep -v grep | grep -v stop | awk '{print $2}'`
if [ -n "$pid" ];then
  kill -9 $pid
  echo "${jar_name} stopped success"
else
  echo "No ${jar_name} process exists"
fi
