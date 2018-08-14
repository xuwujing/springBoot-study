#!/bin/bash
APPDIR=`pwd`
PIDFILE=$APPDIR/springboot-package.pid
if [ ! -f "$PIDFILE" ] || ! kill -0 "$(cat "$PIDFILE")"; then
echo "springboot-package not running..."
else
echo "stopping springboot-package..."
PID="$(cat "$PIDFILE")"
kill -9 $PID
rm "$PIDFILE"
echo "...springboot-package stopped"
fi