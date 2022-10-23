package com.avg.j2ee13.dao.filestore;

import com.avg.j2ee13.dao.GenericAbstractDAO;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public abstract class FileBaseDAO extends GenericAbstractDAO {

    protected File dataFile;

    public FileBaseDAO(HashMap parameters) {
        super(parameters);
        dataFile = new File(locator.getFileDataSource() + File.separator + getFileName());
    }

    public abstract String getFileName();

    protected void closeAll(BufferedReader bufferedReader, FileReader fileReader) {
        try {
            if (fileReader != null) {
                fileReader.close();
            }
        } catch (IOException e) {
            logger.warn(e);
        }

        try {
            if (bufferedReader != null) {
                bufferedReader.close();
            }
        } catch (IOException e) {
            logger.warn(e);
        }

    }
}
