package com.avg.j2ee13.util.localization;

import java.io.File;
import java.io.FileInputStream;
import java.util.*;

public class PropertiesConfiguration {

    private static Map instances = new HashMap();
    private ResourceBundle bundle;
    private Set keys;

    protected PropertiesConfiguration(String fileName) throws LocalizationException {
        try {
            bundle = ResourceBundle.getBundle(fileName);
        } catch (MissingResourceException e) {
            java.io.InputStream is = null;
            try {
                File file = new File(fileName);
                if (!file.exists()) {
                    file = new File(fileName + ".properties");
                    if (!file.exists()) {
                        throw new LocalizationException("Properties files not found. -> " + fileName);
                    }
                }
                is = new FileInputStream(file);
                bundle = new PropertyResourceBundle(is);
                is.close();
            } catch (Exception e0) {
                e0.printStackTrace();
                if (is != null) {
                    try {
                        is.close();
                    } catch (Exception er) {
                        throw new LocalizationException(e);
                    }
                }
                throw new LocalizationException(e);
            }
        }
    }

    public static PropertiesConfiguration getInstance(String fileName) {
        PropertiesConfiguration instance;
        if (!instances.containsKey(fileName)) {
            try {
                instance = new PropertiesConfiguration(fileName);
            } catch (Exception e) {
                return null;
            }
            instances.put(fileName, instance);
        } else {
            instance = (PropertiesConfiguration) instances.get(fileName);
        }

        return instance;
    }

    public String getProperty(String key) {
        if (containsKey(key)) {
            return bundle.getString(key);
        }
        return null;
    }

    public String getProperty(String key, String defaultValue) {
        if (containsKey(key)) {
            return bundle.getString(key);
        }
        return defaultValue;
    }

    public boolean containsKey(String key) {
        for (Enumeration keys = bundle.getKeys(); keys.hasMoreElements(); ) {
            String strKey = (String) keys.nextElement();
            if (strKey.equals(key)) {
                return true;
            }
        }
        return false;
    }

    public boolean containsValue(String key, String expected) {
        for (Enumeration keys = bundle.getKeys(); keys.hasMoreElements(); ) {
            String strKey = (String) keys.nextElement();
            if (strKey.equals(key) && getProperty(key).equals(expected)) {
                return true;
            }
        }
        return false;
    }

    public Set getKeys() {
        if (keys == null) {
            keys = new HashSet();
            Enumeration enumerationClaves = bundle.getKeys();
            for (; enumerationClaves.hasMoreElements(); ) {
                String clave = (String) enumerationClaves.nextElement();
                keys.add(clave);
            }
        }
        return keys;
    }

}
