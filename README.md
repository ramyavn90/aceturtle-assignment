### Prerequisites
- Eclipse 
- JRE 1.8 or later
- TestNG

### Running Test
- Create a folder named `libs` at project root
- Download and copy the following libraries to `libs`
  - [Selenium Server](https://selenium-release.storage.googleapis.com/3.7/selenium-server-standalone-3.7.0.jar)
  - Extract ChromeDriver [Mac](https://chromedriver.storage.googleapis.com/2.33/chromedriver_mac64.zip) / [Win 32](https://chromedriver.storage.googleapis.com/2.33/chromedriver_win32.zip)
  - Extract FirefoxDriver [Mac](https://github.com/mozilla/geckodriver/releases/download/v0.19.1/geckodriver-v0.19.1-macos.tar.gz) / [Win 32](https://github.com/mozilla/geckodriver/releases/download/v0.19.1/geckodriver-v0.19.1-win32.zip) / [Win 64](https://github.com/mozilla/geckodriver/releases/download/v0.19.1/geckodriver-v0.19.1-win64.zip)
- Right click on `testng.xml` from Eclipse and select `Run As` - `1 TestNG Suite`
