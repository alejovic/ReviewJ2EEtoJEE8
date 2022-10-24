package com.avg.j2ee13.dao.filestore.helloworld;

import com.avg.j2ee13.dao.DAOException;
import com.avg.j2ee13.dao.filestore.FileBaseDAO;
import com.avg.j2ee13.dto.BaseDTO;
import com.avg.j2ee13.dto.HelloDTO;
import com.avg.j2ee13.util.StringUtils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class HelloWorldFileDAOImpl extends FileBaseDAO {

    private static final String FILE = "helloworld.txt";


    public HelloWorldFileDAOImpl(HashMap parameters) {
        super(parameters);
    }

    public String getFileName() {
        return FILE;
    }

    public BaseDTO insert(BaseDTO baseDTO) throws DAOException {
        boolean existing = false;
        List list = findAll();
        for (int index = 0; index < list.size(); index++) {
            HelloDTO dto = (HelloDTO) list.get(index);
            if (dto.getId() == baseDTO.getId()) {
                return dto;
            }
        }
        if (!existing) {
            final long id = new Long(list.size()).longValue() + 1;
            baseDTO.setId(id);
            list.add(baseDTO);
            syncFile(list);
        }
        return baseDTO;
    }

    public void update(BaseDTO baseDTO) throws DAOException {

    }

    public List findAll() throws DAOException {
        List result = new ArrayList();

        BufferedReader bufferedReader = null;
        FileReader fileReader = null;

        try {

            fileReader = new FileReader(dataFile);
            bufferedReader = new BufferedReader(fileReader);

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                final String[] fields = StringUtils.split(line);
                HelloDTO dto = buildDTO(fields);
                result.add(dto);
            }

        } catch (IOException e) {
            throw new DAOException(DAOException.ERROR_DAO_01, e.getMessage(), e);
        } finally {
            closeAll(bufferedReader, fileReader);
        }

        return result;
    }

    public BaseDTO findById(long id) throws DAOException {
        List list = findAll();
        HelloDTO dto = null;
        for (int index = 0; index < list.size(); index++) {
            dto = (HelloDTO) list.get(index);
            if (dto.getId() == id) {
                return dto;
            }
        }
        return dto;
    }

    private HelloDTO buildDTO(final String[] fields) {
        HelloDTO dto = new HelloDTO();
        dto.setId(Long.parseLong(fields[0]));
        dto.setMessage(fields[1]);
        //check dateformat util...
        //helloDTO.setDateOfCreation(fields[2]);
        return dto;

    }

    public String getLine(final BaseDTO baseDTO) {
        final HelloDTO dto = (HelloDTO) baseDTO;
        final String DEFAULT_SEPARATOR = ";";
        return dto.getId() + DEFAULT_SEPARATOR
                + dto.getMessage() + DEFAULT_SEPARATOR
                + dto.getDateOfCreation() + DEFAULT_SEPARATOR;
    }

    public void syncFile(final List list) throws DAOException {
        if (list == null) {
            return;
        }

        try {
            final boolean APPEND_FILE = true;
            final FileWriter out = new FileWriter(dataFile.getAbsoluteFile().getPath(), APPEND_FILE);
            for (int index = 0; index < list.size(); index++) {
                BaseDTO dto = (BaseDTO) list.get(index);
                final String line = getLine(dto);
                out.write(line + "\n");
            }
            out.close();
        } catch (IOException e) {
            throw new DAOException(DAOException.ERROR_DAO_01, e.getMessage(), e);
        }
    }
}
