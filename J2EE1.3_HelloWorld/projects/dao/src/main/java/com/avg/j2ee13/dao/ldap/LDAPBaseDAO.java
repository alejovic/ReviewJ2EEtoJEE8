package com.avg.j2ee13.dao.ldap;

import com.avg.j2ee13.dao.GenericAbstractDAO;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.HashMap;

public abstract class LDAPBaseDAO extends GenericAbstractDAO {

    protected static final Log log = LogFactory.getLog(LDAPBaseDAO.class);

    public LDAPBaseDAO(HashMap parameters) {
        super(parameters);
    }

}
