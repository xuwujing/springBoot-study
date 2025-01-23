APPDIR=`pwd`
PIDFILE=$APPDIR/springboot-excel.pid
if [ ! -f "$PIDFILE" ] || ! kill -0 "$(cat "$PIDFILE")"; then
echo "springboot-excel not running..."
else
echo "stopping springboot-excel..."
PID="$(cat "$PIDFILE")"
kill -9 $PID
rm "$PIDFILE"
echo "...springboot-excel stopped"
fi


