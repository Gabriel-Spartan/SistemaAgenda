@echo off
cd /d "%~dp0\mysql-5.7.30-winx64"
start "" bin\mysqld.exe --defaults-file=config\my.ini --console
