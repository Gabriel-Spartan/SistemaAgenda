[Setup]
AppName=Sistema Agenda
AppVersion=1.0
DefaultDirName={pf}\SistemaAgenda
DefaultGroupName=SistemaAgenda
OutputDir=.
OutputBaseFilename=Instalador_SistemaAgenda
Compression=lzma
SolidCompression=yes
PrivilegesRequired=admin
DisableWelcomePage=no
DisableDirPage=no
DisableProgramGroupPage=no
DisableReadyPage=no
DisableFinishedPage=no

[Files]
Source: "SistemaAgenda.jar"; DestDir: "{app}"; Flags: ignoreversion
Source: "NotificadorTray.jar"; DestDir: "{app}"; Flags: ignoreversion
Source: "lanzar.bat"; DestDir: "{app}"; Flags: ignoreversion
Source: "session.txt"; DestDir: "{app}"; Flags: ignoreversion
Source: "lib\*"; DestDir: "{app}\lib"; Flags: ignoreversion recursesubdirs createallsubdirs
Source: "src\resources\*"; DestDir: "{app}\resources"; Flags: ignoreversion recursesubdirs createallsubdirs
Source: "src\reportes\*"; DestDir: "{app}\reportes"; Flags: ignoreversion recursesubdirs createallsubdirs
Source: "jre\*"; DestDir: "{app}\jre"; Flags: ignoreversion recursesubdirs createallsubdirs
Source: "mysql-5.7.30-winx64\*"; DestDir: "{app}\mysql-5.7.30-winx64"; Flags: ignoreversion recursesubdirs
Source: "config\*"; DestDir: "{app}\config"; Flags: ignoreversion recursesubdirs

[Icons]
Name: "{group}\Sistema Agenda"; Filename: "{app}\lanzar.bat"
Name: "{commondesktop}\Sistema Agenda"; Filename: "{app}\lanzar.bat"; Tasks: desktopicon

[Tasks]
Name: "desktopicon"; Description: "Crear un icono en el escritorio"; GroupDescription: "Iconos adicionales:"

[Run]
; Ejecuta el bat con privilegios elevados
Filename: "{app}\lanzar.bat"; Parameters: ""; Flags: runhidden runascurrentuser