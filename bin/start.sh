#!/bin/bash
#author py
#date 2018-07-05

cd "$(dirname "$0")"
cd ..

javahome=${JAVA_HOME}
JAVA_OPTS="-server -Xms64m -Xmx64m -Xmn20m"
if [ -z $javahome ]; then
    echo error: JAVA_HOME not configured
    exit 1
fi
echo "JAVA_HOME=$javahome"

jar_name=$1
pid=`ps aux | grep $jar_name | grep -v grep | grep -v start | awk '{print $2}'`
if [ -n "$pid" ];then
  echo error: $jar_name already exists
  exit 1
fi
if [ ! -d "./logs" ]; then
  mkdir ./logs
fi
# 后台执行输出空
nohup $javahome/bin/java -jar $jar_name 1>/dev/null 2>&1 &
echo "`date '+%Y-%m-%d %H:%M:%S'` ${jar_name} is running..."
