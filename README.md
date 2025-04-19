# Android Native Aplication  Test Automation Framework

### Content index

- [Description](#descripcion)
- [Team](#integrantes)
- [Teacher](#teacher)
- [Tools](#tools)
- [Test architecture](#test-architecture)

### Descripción

Proyecto del curso **Android Native Aplication Test Automation Framework** del **Diplomado Testing de Software**
por la [Universidad Cátolica Boliviana "San Pablo"](https://lpz.ucb.edu.bo/) de Bolivia.

### Team

- [ARIZACA CALLEJAS GUISELA LIBERTAD](https://www.linkedin.com/in/guisela-arizaca/)
- [BAUDAZIO SANCHEZ JUAN DE DIOS](https://www.linkedin.com/in/juandediosbaudaziosanchez/)

### Teacher

- [RUBEN ABC ](https://www.linkedin.com/in/juan-david-lopez/)
- [EYNAR ](https://www.linkedin.com/in/juan-david-lopez/)
- [JUAN DAVID LOPEZ FUENTES](https://www.linkedin.com/in/juan-david-lopez/)

### Tools General

- Java 11
- Intellij IDEA (optional)
- Log4j2
- Gradle

### Tools Mobile Test

- Appium Server
- Appium Inspector
- Cucumber

### Devices Cloud Services

- BrowserStack

### Continuous Integration

- Azure Pipeline

### Agile Planning

- Azure Board

### Platform for version control

- GitHub

### Test Project Architecture Mobile

- ScreenPlay pattern
- Factory pattern for devices
- Singleton pattern for Session Manage
- Run Local and Remote
- Specification Scenario with Cucumber
- Test Data into .feature files

### Locators XPATH

- `//android.widget.TextView)[6]`
- `(//*[contains(@text,'290302')])[1]`

### Run Cucumber with Gradle from Command Line (Using Task into build.gradle)

- gradle clean cucumber -Psuite=@order
- ./gradlew clean cucumber
- ./gradlew clean cloud
- ./gradlew report

### Referencias

https://www.youtube.com/watch?v=HDR-fm32Lnk

### Desafios

- In AndroidCloud be set a capability to get Lima - Perú time
  zone: `caps.setCapability("browserstack.timezone", "Lima");`
- In AndroidCloud and Android classes be set a capability to acept the
  Permissions `caps.setCapability("autoGrantPermissions", true);`

### Continuous Integration - Azure Pipeline

- Delete .jar in the field Contents Copy files task

### Eliminar los archivos .html del reporte de cucumber ubicados en el contendor
- script: 'az storage blob delete-batch --source $web --account-name hubtestinglab --connection-string "DefaultEndpointsProtocol=https;AccountName=hubtestinglab;AccountKey=Me5PyyzkfepXLoDzG4gzeNYaiji+oF5D7K+/Vs0r19pZ5g3P+MlDeQc/nqJ0SttVGRQ0GlJPxBK/+AStmaLB/g==;EndpointSuffix=core.windows.net"'
  displayName: 'Delete Reporte'

### Instalación de Python 13.3 desde Pipeline, no funciona
# ── JOB · INSTALL PYTHON ────────────────────────────────────────────────
- job: InstallPython
  displayName: "Ensure Python 3.13 is present"
  dependsOn: Collector
  condition: always()
  pool: { name: SelfHostedAgentPools }

  steps:
  - powershell: |
    # 1) ¿Python 3.13+ ya está en PATH?
    $pythonExe = (& where.exe python.exe 2>$null) -split "`r?`n" | Select-Object -First 1
    if ($pythonExe) {
    $ver = & $pythonExe --version
    if ($ver -match 'Python 3\.(1[1-9]|[2-9]\d)') {
    Write-Host "Python ya instalado: $ver – omitimos instalación."
    exit 0
    }
    }

    Write-Host "Python 3.13 no encontrado. Procediendo a instalar…"

    # 2) Descargar el instalador
    $url       = "https://www.python.org/ftp/python/3.13.3/python-3.13.3-amd64.exe"
    $installer = "$env:TEMP\python-3.13.3-amd64.exe"
    Invoke-WebRequest -Uri $url -OutFile $installer

    # 3) Instalar en modo silencioso
    Start-Process -FilePath $installer `
    -ArgumentList "/quiet InstallAllUsers=1 PrependPath=1" `
    -Wait

    # 4) Localizar python.exe tras la instalación
    Write-Host "Buscando python.exe en PATH…"
    $pythonExe = (& where.exe python.exe 2>$null) -split "`r?`n" | Select-Object -First 1

    if (-not $pythonExe) {
    Write-Host "No hallado en PATH, buscando en Program Files…"
    $candidate = Get-ChildItem "$Env:ProgramFiles" -Directory -Filter "Python313*" |
    Sort-Object LastWriteTime -Descending |
    Select-Object -First 1
    if ($candidate) { $pythonExe = "$($candidate.FullName)\python.exe" }
    }

    if ($pythonExe -and (Test-Path $pythonExe)) {
    # 5) Actualizar PATH de esta sesión
    $pythonDir = Split-Path $pythonExe -Parent
    $env:PATH = "$pythonDir;$pythonDir\Scripts;"+$env:PATH
    Write-Host "python.exe localizado en $pythonExe"
    Write-Host "Versión instalada: $(& $pythonExe --version)"
    exit 0
    } else {
    Write-Warning "python.exe NO encontrado tras la instalación."
    exit 1
    }
    displayName: "Check, Install & Locate Python 3.13+"

