package com.avg.j2ee13.dao.ldap;

import com.avg.j2ee13.dao.DAOException;
import com.avg.j2ee13.dao.GenericAbstractDAO;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Map;

public abstract class LDAPBaseDAO extends GenericAbstractDAO {

    protected static final Log logger = LogFactory.getLog(LDAPBaseDAO.class);

    protected LDAPBaseDAO(final Map parameters) throws DAOException {
        super(parameters);
    }

}
