#!/bin/sh
##############################################################################
##
##  Maven Start Up Batch script for UNIX
##
##############################################################################

# resolve links - $0 may be a softlink
PRG="$0"

while [ -h "$PRG" ]; do
  ls=`ls -ld "$PRG"`
  link=`expr "$ls" : '.*-> \(.*\)$'`
  if expr "$link" : '/.*' > /dev/null; then
    PRG="$link"
  else
    PRG=`dirname "$PRG"`/"$link"
  fi
done

PRGDIR=`dirname "$PRG"`
[ -z "$MAVEN_WRAPPER_HOME" ] && MAVEN_WRAPPER_HOME="$PRGDIR"

exec "$MAVEN_WRAPPER_HOME/.mvn/wrapper/maven-wrapper.jar" "$@"
