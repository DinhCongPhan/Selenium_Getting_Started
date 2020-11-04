import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class FindDeadLink {

    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/ChromeDriver/chromedriver");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();




        List<String> urls = new ArrayList<String>();
        urls.add("http://vnu.edu.vn");
        urls.add("http://fit.uet.vnu.edu.vn");
        urls.add("http://fet.uet.vnu.edu.vn");
        urls.add("http://hust.edu.vn");
        urls.add("http://usth.edu.vn");

        System.out.println("Check all links ... ");

        for(int i=0; i<urls.size(); i++)
        {
            verifyLinkActive(urls.get(i));

        }

        System.out.println("Check all link done ...!");
        System.out.println("Start checking all in image links in " + urls.get(1));
        driver.get("http://vnu.edu.vn");
        List<WebElement> allImages = driver.findElements(By.tagName("img"));
        for(WebElement imageFromList:allImages){
            String ImageUrl=imageFromList.getAttribute("src");
            verifyLinkActive(ImageUrl);
        }


    }
    public static void verifyLinkActive(String linkUrl)
    {
        try
        {
            URL url = new URL(linkUrl);

            HttpURLConnection httpURLConnect=(HttpURLConnection)url.openConnection();

            httpURLConnect.setConnectTimeout(3000);

            httpURLConnect.connect();

            if(httpURLConnect.getResponseCode()==200)
            {
                System.out.println(linkUrl+" - "+httpURLConnect.getResponseMessage());
            }
            else{
                System.out.println(linkUrl+" - "+HttpURLConnection.HTTP_NOT_FOUND);
            }
            if(httpURLConnect.getResponseCode()==HttpURLConnection.HTTP_NOT_FOUND)
            {
                System.out.println(linkUrl+" - "+httpURLConnect.getResponseMessage() + " - "+ HttpURLConnection.HTTP_NOT_FOUND);
            }
        } catch (Exception e) {

        }
    }


}