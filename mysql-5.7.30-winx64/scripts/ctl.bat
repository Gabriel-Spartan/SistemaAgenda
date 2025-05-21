@echo off
rem START or STOP Services
rem ----------------------------------
rem Check if argument is STOP or START

if not ""%1"" == ""START"" goto stop


"\mysql-5.7.30-winx64\bin\mysqld" --defaults-file="\mysql-5.7.30-winx64\config\my.ini" --standalone --explicit_defaults_for_timestamp
if errorlevel 1 goto error
goto finish

:stop
cmd.exe /C start "" /MIN call "\mysql-5.7.30-winx64\scripts\killprocess.bat" "mysqld.exe"

if not exist "\mysql-5.7.30-winx64\data\%computername%.pid" goto finish
echo Delete %computername%.pid ...
del "\mysql-5.7.30-winx64\data\%computername%.pid"
goto finish


:error
echo MySQL could not be started

:finish
exit
