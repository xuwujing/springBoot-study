APPDIR=`pwd`
PIDFILE=$APPDIR/springboot-excel.pid
if [ -f "$PIDFILE" ] && kill -0 $(cat "$PIDFILE"); then
echo "springboot-excel is already running..."
exit 1
fi
nohup java -jar $APPDIR/springboot-excel.jar >/dev/null 2>&1 &
echo $! > $PIDFILE
echo "start springboot-excel..."


