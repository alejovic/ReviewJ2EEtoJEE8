package com.avg.j2ee13.dao.memory;

import com.avg.j2ee13.dao.GenericAbstractDAO;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public abstract class MemoryBaseDAO extends GenericAbstractDAO {

    protected static final Log logger = LogFactory.getLog(MemoryBaseDAO.class);

    private List data = new LinkedList();

    protected MemoryBaseDAO(HashMap parameters) {
        super(parameters);
    }

    protected List getData() {
        return data;
    }

}
