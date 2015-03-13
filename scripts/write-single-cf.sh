#! /bin/sh

SDIR=`dirname $(readlink -f $0)`
cd "$SDIR"

. "$SDIR/common.sh"

MAIN=unicredit.example.WriteSingleCf

MEMORY=4g
CORES=2
EXECUTORS=20

$START \
--num-executors $EXECUTORS \
--executor-memory $MEMORY \
--executor-cores $CORES \
--class $MAIN \
$JAR_PKG \
$@