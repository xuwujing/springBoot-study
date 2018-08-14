#!/bin/bash
APPDIR=`pwd`
PIDFILE=$APPDIR/springboot-package.pid
if [ -f "$PIDFILE" ] && kill -0 $(cat "$PIDFILE"); then
echo "springboot-package is already running..."
exit 1
fi
nohup java -jar $APPDIR/springboot-package.jar >/dev/null 2>&1 &
echo $! > $PIDFILE
echo "start springboot-package..."
