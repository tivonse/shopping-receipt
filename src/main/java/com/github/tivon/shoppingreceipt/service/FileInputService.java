package com.github.tivon.shoppingreceipt.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tivon.shoppingreceipt.model.source.DataSource;

import java.io.*;
import java.util.Optional;
import java.util.Properties;

public class FileInputService {

    private static final String DEFAULT_PROPERTIES_FILE_Path = "src/main/resources/config/config.properties";

    private ObjectMapper mapper;

    private Properties prop;

    public Properties getValueFromPropertiesFileSource() {
        try (InputStream input = new FileInputStream(DEFAULT_PROPERTIES_FILE_Path)){
            prop = new Properties();
            prop.load(input);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return prop;
    }

    @SuppressWarnings("Provide optional argument to get from specified file path")
    public Properties getValueFromPropertiesFileSource(String filePath) {
        try (InputStream input = new FileInputStream(filePath)){
            Properties prop = new Properties();
            prop.load(input);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return prop;
    }

    public DataSource getProductListFromJsonSource(String filePath) {
        mapper =  new ObjectMapper();
        DataSource dataSource = null;
        try {
            dataSource = mapper.readValue(new File(filePath), DataSource.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return dataSource;
    }

}
