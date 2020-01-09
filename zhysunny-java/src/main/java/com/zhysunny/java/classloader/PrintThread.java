package com.zhysunny.java.classloader;

import java.net.URISyntaxException;

/**
 * @author 章云
 * @date 2020/1/9 10:50
 */
public class PrintThread extends Thread {

    @Override
    public void run() {
        PrintService thermalLoading = null;
        while (true) {
            try {
                thermalLoading = (PrintService)ClassFactory.getInstance("com.zhysunny.java.classloader.PrintServiceImpl");
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
            thermalLoading.print();
            try {
                sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
