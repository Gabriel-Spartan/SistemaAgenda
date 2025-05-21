@echo off
cd /d "%~dp0"

:: Iniciar MySQL (en segundo plano) con permisos elevados
echo Iniciando MySQL...
start /min "" ".\mysql-5.7.30-winx64\bin\mysqld.exe" --defaults-file=.\config\my.ini

:: Esperar 2 segundos para que MySQL se inicie
timeout /t 2 >nul

:: Iniciar el sistema Java (si MySQL está listo)
echo Iniciando Java...
start /min "" ".\jre\bin\java.exe" -cp "SistemaAgenda.jar;NotificadorTray.jar;lib/*" view.LoginUsuario

exit