#!/bin/sh

cd "$( dirname "$0" )" 
java -Xms512M -Xmx1G -XX:MaxPermSize=128M -XX:+UseConcMarkSweepGC -jar spigot.jar