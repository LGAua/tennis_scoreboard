package com.lga.tennisscoreboard.listener;

import com.lga.tennisscoreboard.util.DataImporter;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class DataImportListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        DataImporter.importData();
    }
}