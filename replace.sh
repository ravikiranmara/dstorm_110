#! /bin/sh

sudo svc -d /etc/service/nimbus/
cp /home/ubuntu/git/storm/storm-core/target/storm-core-1.1.0.jar /home/ubuntu/storm/lib/storm-core-1.1.0.jar 
sudo svc -u /etc/service/nimbus/

