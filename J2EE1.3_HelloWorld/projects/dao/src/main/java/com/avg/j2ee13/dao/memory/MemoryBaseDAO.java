package com.avg.j2ee13.dao.memory;

import com.avg.j2ee13.dao.DAOException;
import com.avg.j2ee13.dao.GenericAbstractDAO;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public abstract class MemoryBaseDAO extends GenericAbstractDAO {

    protected static final Log logger = LogFactory.getLog(MemoryBaseDAO.class);

    private List data = new LinkedList();

    protected MemoryBaseDAO(final Map parameters) throws DAOException {
        super(parameters);
    }

    protected void initParameters(Map parameters) {
        // no extra-parameters
    }

    protected List getData() {
        return data;
    }

}
