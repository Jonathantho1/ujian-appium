package com.juaracoding;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class FinancialTest {

    private AndroidDriver driver;

    @BeforeClass
    public void setUp() throws MalformedURLException {
        //Desired Capabilities
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("deviceName","Pixel 2 API 30");
        desiredCapabilities.setCapability("UUID","emulator-5554");
        desiredCapabilities.setCapability("platformName","android");
        desiredCapabilities.setCapability("appPackage","com.chad.financialrecord");
        desiredCapabilities.setCapability("appActivity","com.rookie.catatankeuangan.feature.splash.SplashActivity");
        desiredCapabilities.setCapability("noReset",true);

        //URL
        URL url = new URL("http://127.0.0.1:4723/wd/hub");

        //instance Android Driver
        driver = new AndroidDriver(url,desiredCapabilities);
    }

    @AfterClass
    public void finish(){
        driver.quit();
    }

    @Test (priority = 1)
    public void pengeluaran(){
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        MobileElement add = (MobileElement) driver.findElementById("com.chad.financialrecord:id/fabMenu");
        add.click();

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        MobileElement tanggal = (MobileElement) driver.findElementById("com.chad.financialrecord:id/tvDate");
        tanggal.click();

        MobileElement pilihTanggal = (MobileElement) driver.findElementByXPath("//android.view.View[@content-desc=\"17 Oktober 2023\"]");
        MobileElement okButton = (MobileElement) driver.findElementById("android:id/button1");
        pilihTanggal.click();
        okButton.click();

        MobileElement kategori = (MobileElement) driver.findElementById("com.chad.financialrecord:id/tvName");
        kategori.click();

        MobileElement pilihKategori = (MobileElement) driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.ListView/android.widget.LinearLayout[2]/android.widget.LinearLayout");
        pilihKategori.click();

        MobileElement jumlah = (MobileElement) driver.findElementById("com.chad.financialrecord:id/etAmount");
        jumlah.sendKeys("25000000000");

        MobileElement keterangan = (MobileElement) driver.findElementById("com.chad.financialrecord:id/etNote");
        keterangan.sendKeys("BMW");

        MobileElement simpan = (MobileElement) driver.findElementById("com.chad.financialrecord:id/btSave");
        simpan.click();

        driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);

        MobileElement saldoKeluar = (MobileElement) driver.findElementById("com.chad.financialrecord:id/tvBalance");
        String totalSaldoKeluar = saldoKeluar.getText();
        System.out.println("Total Saldo: "+totalSaldoKeluar);

        Assert.assertEquals(totalSaldoKeluar,"-25.000.000.000");
    }

    @Test (priority = 2)
    public void pemasukan(){
        driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);

        MobileElement add = (MobileElement) driver.findElementById("com.chad.financialrecord:id/fabMenu");
        add.click();

        driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);

        MobileElement pemasukan = (MobileElement) driver.findElementById("com.chad.financialrecord:id/btnIncome");
        pemasukan.click();

        MobileElement tanggal = (MobileElement) driver.findElementById("com.chad.financialrecord:id/tvDate");
        tanggal.click();

        MobileElement pilihTanggal = (MobileElement) driver.findElementByXPath("//android.view.View[@content-desc=\"19 Oktober 2023\"]");
        MobileElement okButton = (MobileElement) driver.findElementById("android:id/button1");
        pilihTanggal.click();
        okButton.click();

        MobileElement kategori = (MobileElement) driver.findElementById("com.chad.financialrecord:id/tvName");
        kategori.click();

        MobileElement pilihKategori = (MobileElement) driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.ListView/android.widget.LinearLayout[10]/android.widget.LinearLayout/android.widget.TextView");
        pilihKategori.click();

        MobileElement jumlah = (MobileElement) driver.findElementById("com.chad.financialrecord:id/etAmount");
        jumlah.sendKeys("30000000000");

        MobileElement keteranganMasuk = (MobileElement) driver.findElementById("com.chad.financialrecord:id/etNote");
        keteranganMasuk.sendKeys("BMW Sold");

        MobileElement simpan = (MobileElement) driver.findElementById("com.chad.financialrecord:id/btSave");
        simpan.click();

        MobileElement saldo = (MobileElement) driver.findElementById("com.chad.financialrecord:id/tvBalance");
        String totalSaldo = saldo.getText();
        System.out.println("Total Saldo: "+totalSaldo);

        Assert.assertEquals(totalSaldo,"5.000.000.000");
    }
}
