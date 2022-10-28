package com.avg.j2ee13.dao.filestore.helloworld;

import com.avg.j2ee13.dao.DAOException;
import com.avg.j2ee13.dao.filestore.FileBaseDAO;
import com.avg.j2ee13.dto.BaseDTO;
import com.avg.j2ee13.dto.HelloDTO;
import com.avg.j2ee13.util.DateUtils;
import com.avg.j2ee13.util.StringUtils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class HelloWorldFileDAOImpl extends FileBaseDAO {

    private static final String FILE = "helloworld.txt";

    public HelloWorldFileDAOImpl(final Map parameters) throws DAOException {
        super(parameters);
    }

    public String getFileName() {
        return FILE;
    }

    public String[] getFields() {
        return new String[]{"id", "message", "dateOfCreation"};
    }

    public Class getDTOClass() {
        return HelloDTO.class;
    }

    public BaseDTO insert(BaseDTO baseDTO) throws DAOException {
        try {
            boolean existing = false;
            final List list = findAll();
            for (int index = 0; index < list.size(); index++) {
                final HelloDTO dto = (HelloDTO) list.get(index);
                if (dto.getId() == baseDTO.getId()) {
                    existing = true;
                }
            }
            if (!existing) {
                final long id = Long.parseLong(list.size() + "") + 1;
                baseDTO.setId(id);
                list.add(baseDTO);
                syncFile(list);
            }
            return baseDTO;
        } catch (Exception e) {
            throw new DAOException(DAOException.DAO_ERROR_INSERT, e.getMessage(), e);
        }
    }

    public void update(BaseDTO baseDTO) throws DAOException {
        try {
            final List list = findAll();
            for (int index = 0; index < list.size(); index++) {
                final HelloDTO dto = (HelloDTO) list.get(index);
                if (dto.getId() == baseDTO.getId()) {
                    list.set(index, baseDTO);
                }
            }
            syncFile(list);
        } catch (Exception e) {
            throw new DAOException(DAOException.DAO_ERROR_UPDATE, e.getMessage(), e);
        }
    }

    public void delete(BaseDTO baseDTO) throws DAOException {
        try {
            boolean existing = false;
            final List list = findAll();
            int index = 0;
            for (; index < list.size(); index++) {
                HelloDTO dto = (HelloDTO) list.get(index);
                if (dto.getId() == baseDTO.getId()) {
                    existing = true;
                    break;
                }
            }
            if (existing) {
                list.remove(index);
                syncFile(list);
            }
        } catch (Exception e) {
            throw new DAOException(DAOException.DAO_ERROR_DELETE, e.getMessage(), e);
        }
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
            throw new DAOException(DAOException.DAO_ERROR_FIND_ALL, e.getMessage(), e);
        } catch (ParseException e) {
            throw new DAOException(DAOException.DAO_ERROR_FIND_ALL, e.getMessage(), e);
        } finally {
            closeAll(bufferedReader, fileReader);
        }

        return result;
    }

    public BaseDTO findById(long id) throws DAOException {
        try {
            List list = findAll();
            HelloDTO dto = null;
            for (int index = 0; index < list.size(); index++) {
                dto = (HelloDTO) list.get(index);
                if (dto.getId() == id) {
                    return dto;
                }
            }
            return dto;
        } catch (Exception e) {
            throw new DAOException(DAOException.DAO_ERROR_FIND, e.getMessage(), e);
        }
    }

    private HelloDTO buildDTO(final String[] fields) throws ParseException {
        HelloDTO dto = new HelloDTO();
        dto.setId(Long.parseLong(fields[0]));
        dto.setMessage(fields[1]);
        dto.setDateOfCreation(DateUtils.toDate(fields[2], Locale.getDefault()));
        return dto;

    }

}
