PRG=hbase-rdd-examples
VER=0.5.3.1

. /etc/spark/conf/spark-env.sh

DRIVER_CLASSPATH=$(hbase classpath)

JAR_PKG=${PRG}-${VER}.jar

START="spark-submit \
--conf "spark.executor.extraClassPath=${DRIVER_CLASSPATH}" \
--driver-class-path $DRIVER_CLASSPATH \
--properties-file spark.conf \
--master yarn"