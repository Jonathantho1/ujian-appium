package com.juaracoding;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) throws MalformedURLException {
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
        AndroidDriver driver = new AndroidDriver(url,desiredCapabilities);

        //Pengeluaran

        //delay
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

        if(totalSaldoKeluar.equals("-25.000.000.000")){
            System.out.println("Pengeluaran system true");
        }else {
            System.out.println("Pengeluaran system false");
        }

        //Pemasukan
        add.click();
        driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);

        MobileElement pemasukan = (MobileElement) driver.findElementById("com.chad.financialrecord:id/btnIncome");
        pemasukan.click();

        driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);

        MobileElement tanggalJual = (MobileElement) driver.findElementById("com.chad.financialrecord:id/tvDate");
        tanggalJual.click();

        MobileElement tanggalJualKategori = (MobileElement) driver.findElementByXPath("//android.view.View[@content-desc=\"19 Oktober 2023\"]");
        tanggalJualKategori.click();

        MobileElement tanggalButton = (MobileElement) driver.findElementById("android:id/button1");
        tanggalButton.click();

        MobileElement kategoriMasuk = (MobileElement) driver.findElementById("com.chad.financialrecord:id/tvName");
        kategoriMasuk.click();

        MobileElement pilihKategoriMasuk = (MobileElement) driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.ListView/android.widget.LinearLayout[10]/android.widget.LinearLayout/android.widget.TextView");
        pilihKategoriMasuk.click();

        MobileElement jumlahMasuk = (MobileElement) driver.findElementById("com.chad.financialrecord:id/etAmount");
        jumlahMasuk.sendKeys("30000000000");

        MobileElement keteranganMasuk = (MobileElement) driver.findElementById("com.chad.financialrecord:id/etNote");
        keteranganMasuk.sendKeys("BMW Sold");

        MobileElement simpanMasuk = (MobileElement) driver.findElementById("com.chad.financialrecord:id/btSave");
        simpanMasuk.click();

        MobileElement saldoMasuk = (MobileElement) driver.findElementById("com.chad.financialrecord:id/tvBalance");
        String totalSaldoMasuk = saldoMasuk.getText();

        if (totalSaldoMasuk.equals("5.000.000.000")) {
            System.out.println("Pemasukkan system true");
        }else {
            System.out.println("Pemasukkan system false");
        }

        delay(3);
        driver.quit();
    }

    public static void delay(long sec){

        try {
            Thread.sleep(sec*1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}