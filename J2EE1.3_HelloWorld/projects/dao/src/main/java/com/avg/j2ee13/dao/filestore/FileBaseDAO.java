package com.avg.j2ee13.dao.filestore;

import com.avg.j2ee13.dao.DAOException;
import com.avg.j2ee13.dao.GenericAbstractDAO;
import com.avg.j2ee13.dto.BaseDTO;
import com.avg.j2ee13.util.DateUtils;
import com.avg.j2ee13.util.localization.ServiceLocator;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public abstract class FileBaseDAO extends GenericAbstractDAO {

    protected static final String DEFAULT_LINE_SEPARATOR = ";";
    protected File dataFile;

    protected FileBaseDAO(ServiceLocator locator) {
        super(locator);
    }

    protected void initParameters(Map parameters) throws DAOException {
        dataFile = new File(locator.getDAOFileDataSource() + File.separator + getFileName());
        try {
            if (dataFile.createNewFile()) {
                logger.info("FileBaseDAO A new file has been created -> " + dataFile.getAbsolutePath());
            }
        } catch (IOException e) {
            throw new DAOException(DAOException.DAO_NO_FILE, e.getMessage(), e);
        }
    }

    public abstract String getFileName();

    public abstract String[] getFields();

    public abstract Class getDTOClass();

    private String getLine(final BaseDTO baseDTO, Class dtoClass, String[] orderFields) throws InvocationTargetException, IllegalAccessException {
        final StringBuffer stringBuffer = new StringBuffer(100);

        final Method[] methods = dtoClass.getDeclaredMethods();
        for (int field_index = 0; field_index < orderFields.length; field_index++) {
            final String field = orderFields[field_index];
            if (field.equalsIgnoreCase("id")) {
                stringBuffer.append(baseDTO.getId());
                stringBuffer.append(DEFAULT_LINE_SEPARATOR);
            }
            for (int method_index = 0; method_index < methods.length; method_index++) {
                final Method method = methods[method_index];
                String methodName = method.getName();
                if (methodName.startsWith("get")) {
                    methodName = method.getName().substring(3);
                } else if (methodName.startsWith("is")) {
                    methodName = method.getName().substring(2);
                }

                if (field.equalsIgnoreCase(methodName)) {
                    if (method.getReturnType().equals(java.util.Date.class)) {
                        stringBuffer.append(DateUtils.getDateTime((Date) method.invoke(baseDTO, null), Locale.getDefault()));
                    } else {
                        stringBuffer.append(method.invoke(baseDTO, null));
                        stringBuffer.append(DEFAULT_LINE_SEPARATOR);
                    }
                }
            }
        }
        return stringBuffer.toString();
    }

    protected void syncFile(final List list) throws DAOException {
        if (list == null) {
            return;
        }

        try {
            final boolean APPEND_FILE = false;
            final FileWriter out = new FileWriter(dataFile.getAbsoluteFile().getPath(), APPEND_FILE);
            for (int index = 0; index < list.size(); index++) {
                BaseDTO dto = (BaseDTO) list.get(index);
                final String line = getLine(dto, getDTOClass(), getFields());
                out.write(line + "\n");
            }
            out.close();
        } catch (IOException e) {
            throw new DAOException(DAOException.DAO_ERROR_FILE_SYNC, e.getMessage(), e);
        } catch (InvocationTargetException e) {
            throw new DAOException(DAOException.DAO_ERROR_FILE_SYNC, e.getMessage(), e);
        } catch (IllegalAccessException e) {
            throw new DAOException(DAOException.DAO_ERROR_FILE_SYNC, e.getMessage(), e);
        }
    }

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
