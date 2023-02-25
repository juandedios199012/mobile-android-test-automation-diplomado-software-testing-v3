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

### Tools Mobile

- Appium Server
- Appium Inspector
- Cucumber

### Tools Web

- Selenium WebDriver
- Gson for Test Data
- Extentreports
- TestNG for Run Test

### Devices Cloud Services

- BrowserStack

### Continuous Integration

- Azure Pipeline
- GitHub Action (por revisar)
- Jenkins Cloud or Local (por revisar)

### Agile Planning

- Azure Board

### Test Project Architecture Mobile

- ScreenPlay pattern
- Factory pattern for devices
- Singleton pattern for Session Manage
- Run Local and Remote
- Specification Scenario with Cucumber
- Test Data into .feature files

### Test Project Architecture Web

- ScreenPlay pattern
- Factory pattern for Web drivers
- Singleton pattern for Json Test Data
- Test Data Using Model Classes
- Support Chrome and Firefox
- Use Selenium Grid
- Run Local and Remote
- BaseTest Class
- Parallel execution

### Locators XPATH

- `//android.widget.TextView)[6]`

### Run Cucumber with Gradle from Command Line (Using Task into build.gradle)

- gradle clean cucumber -Psuite=@order
-  ./gradlew cucumber

### Referencias

https://www.youtube.com/watch?v=HDR-fm32Lnk

### Desafios 
- In AndroidCloud be set a capability to get Lima - Perú time zone: `caps.setCapability("browserstack.timezone", "Lima");`
- In AndroidCloud and Android classes be set a capability to acept the Permissions `caps.setCapability("autoGrantPermissions", true);`
- 

  