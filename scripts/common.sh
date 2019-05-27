PRG=hbase-rdd-examples-assembly
VER=0.9.0

. /etc/spark/conf/spark-env.sh

DRIVER_CLASSPATH=$(hbase classpath)

JAR_PKG=${PRG}-${VER}.jar

START="spark-submit \
--conf "spark.executor.extraClassPath=${DRIVER_CLASSPATH}" \
--driver-class-path $DRIVER_CLASSPATH \
--properties-file spark.conf \
--master yarn"