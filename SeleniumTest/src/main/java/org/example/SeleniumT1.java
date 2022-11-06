package org.example;

import io.github.bonigarcia.wdm.config.DriverManagerType;
import io.github.bonigarcia.wdm.managers.ChromeDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class SeleniumT1 {
    WebDriver driver =null;

    @BeforeTest
    public  void getHeroku(){
        ChromeDriverManager.getInstance(DriverManagerType.CHROME).setup();
        driver=new ChromeDriver();
        driver.get("https://the-internet.herokuapp.com/");
    }
    @BeforeMethod
    public void getHomepage(){
        driver.get("https://the-internet.herokuapp.com/");
    }
    @Test
    public void add_remove_elements(){

        driver.findElement(By.xpath("//*[@id=\"content\"]/ul/li[2]/a")).click();
        driver.findElement(By.xpath("//*[@id=\"content\"]/div/button")).click();
        WebElement del = driver.findElement(By.xpath("//*[@id=\"elements\"]/button"));
        del.click();

    }
    @Test
    public void checkBoxes(){
        driver.findElement(By.xpath("//*[@id=\"content\"]/ul/li[6]/a")).click();
        WebElement checkb1 = driver.findElement(By.xpath("//*[@id=\"checkboxes\"]/input[1]"));
        WebElement checkb2 = driver.findElement(By.xpath("//*[@id=\"checkboxes\"]/input[2]"));
        if (!checkb1.isSelected()){
            checkb1.click();
        }
        if (!checkb2.isSelected()){
            checkb2.click();
        }
        System.out.println("button 1 is clicked " + checkb1.isSelected());
        System.out.println("button 2 is clicked " + checkb2.isSelected());

    }
    @Test
    public void contextMenu(){
        Actions action = new Actions(driver);
        driver.findElement(By.linkText("Context Menu")).click();
        WebElement box = driver.findElement(By.xpath("//*[@id=\"hot-spot\"]"));
        action.contextClick(box).perform();
        System.out.println("alert text : " + driver.switchTo().alert().getText());
        driver.switchTo().alert().accept();

    }
    @Test
    public void disapearingElements(){

        driver.findElement(By.linkText("Disappearing Elements")).click();
        int c=0;
        do {
            try {
                driver.findElement(By.xpath("//*[@id=\"content\"]/div/ul/li[5]/a")).click();
                c++;
            }catch (NoSuchElementException e){
                driver.navigate().refresh();
            }
        }while (c==0);

        System.out.println("u gjet");
    }

    @AfterTest
    public  void testerQ(){
        driver.close();
        driver.quit();
        }
    }
