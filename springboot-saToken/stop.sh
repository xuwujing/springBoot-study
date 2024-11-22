APPDIR=`pwd`
PIDFILE=$APPDIR/springboot-demo.pid
if [ ! -f "$PIDFILE" ] || ! kill -0 "$(cat "$PIDFILE")"; then
echo "springboot-demo not running..."
else
echo "stopping springboot-demo..."
PID="$(cat "$PIDFILE")"
kill -9 $PID
rm "$PIDFILE"
echo "...springboot-demo stopped"
fi


