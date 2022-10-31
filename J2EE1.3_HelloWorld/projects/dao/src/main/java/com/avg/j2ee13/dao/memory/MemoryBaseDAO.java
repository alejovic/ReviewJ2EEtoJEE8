package com.avg.j2ee13.dao.memory;

import com.avg.j2ee13.dao.GenericAbstractDAO;
import com.avg.j2ee13.util.localization.ServiceLocator;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.LinkedList;
import java.util.List;

public abstract class MemoryBaseDAO extends GenericAbstractDAO {

    protected static final Log logger = LogFactory.getLog(MemoryBaseDAO.class);

    private List data = new LinkedList();

    protected MemoryBaseDAO(ServiceLocator locator) {
        super(locator);
    }

    protected List getData() {
        return data;
    }

}
