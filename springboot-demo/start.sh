APPDIR=`pwd`
PIDFILE=$APPDIR/springboot-demo.pid
if [ -f "$PIDFILE" ] && kill -0 $(cat "$PIDFILE"); then
echo "springboot-demo is already running..."
exit 1
fi
nohup java -jar $APPDIR/springboot-demo.jar >/dev/null 2>&1 &
echo $! > $PIDFILE
echo "start springboot-demo..."


