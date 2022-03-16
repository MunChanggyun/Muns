import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class eyeAccountManager implements Job {
    private WebDriver driver;
    private WebElement element;
    private String password = "";

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println("JOB START ::::::::::::: ");
        setConfig();
    }

    public void setConfig() {
        String CHROME_DRIVER = "webdriver.chrome.driver";
        String WEB_DRIVER_PATH = "C:\\projects\\selenium\\lib\\chromedriver.exe";

        System.setProperty(CHROME_DRIVER, WEB_DRIVER_PATH);

        driver = new ChromeDriver();

       setEyeAccountManagerLogin();
    }

    public void setEyeAccountManagerLogin() {
        driver.get("http://localhost:8080/login.do");

        element = driver.findElement(By.id("id"));  // 아이디 태그(input)
        element.click();
        element.sendKeys("input id");   // 아이디 입력
        element = driver.findElement(By.id("password"));    // 비밀번호 태그(input)
        element.click();
        element.sendKeys(password); // 비밀번호 입력
        element = driver.findElement(By.id("button-submit"));   // 확인 (button)
        element.click();

        String alert = driver.switchTo().alert().getText(); // 비밀번호 만료일을 확인하기위한 경고창 내용 확인
        String[] alertStrings = alert.split( " ");

        driver.switchTo().alert().accept();

        if (alertStrings.length >= 5) { // 경고창 내용이 비밀번호 관련된 내용인경우
            int passwordDay = Integer.parseInt(alertStrings[0].substring(0, alertStrings[0].length() -1));  // 비밀번호 만료일

            if (passwordDay < 10) {
                element = driver.findElement(By.id("id-button-changePwd"));
                element.click();

                element = driver.findElement(By.name("oldaupwd"));
                element.click();
                element.sendKeys(password);

                changePassword ();

                element = driver.findElement(By.name("aupwd"));
                element.click();
                element.sendKeys(password);
                element = driver.findElement(By.name("aupwd2"));
                element.click();
                element.sendKeys(password);

                element = driver.findElement(By.id("id-button-confirm"));
                element.click();
            }

        } else {
            //driver.close();   // 브라우져 닫기
        }
    }

    /**
     * 비밀번호 변경
     */
    private void changePassword () {
        if (password.contains("!!")) {
            password = password.substring(0, password.length() - 1);
        } else {
            password += "!";
        }
    }
}
