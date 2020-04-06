package com.black.boy.kafka.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConfigLoader {

    private static ConfigLoader instance = new ConfigLoader();
    private static final String KAFKA_CONFIG_FILE = "kafka-config.properties";
    private static final Logger LOG = LoggerFactory.getLogger(ConfigLoader.class);

    public static ConfigLoader getInstance() {
        return instance;
    }

    /**
     * 由配置文件中加载参数
     */
    public Properties loadConfig() {
        URL fileUrl = ConfigLoader.class.getClassLoader().getResource(KAFKA_CONFIG_FILE);
        String filePath = fileUrl.getFile();
        Reader f;
        Properties p = new Properties();
        try {
            f = new InputStreamReader(new FileInputStream(filePath), "UTF-8");
            p.load(f);
        } catch (IOException e) {
            LOG.error("load config file fail:", e);
        }
        return p;
    }
}
