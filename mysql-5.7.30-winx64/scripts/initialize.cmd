@echo off
"\mysql-5.7.30-winx64\bin\mysqld" --defaults-file="\mysql-5.7.30-winx64\config\my.ini" --standalone --explicit_defaults_for_timestamp --initialize
echo See %computername%.err for your temporary root password