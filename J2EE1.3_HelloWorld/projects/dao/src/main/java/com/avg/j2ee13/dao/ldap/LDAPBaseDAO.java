package com.avg.j2ee13.dao.ldap;

import com.avg.j2ee13.dao.GenericAbstractDAO;
import com.avg.j2ee13.util.localization.ServiceLocator;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public abstract class LDAPBaseDAO extends GenericAbstractDAO {

    protected static final Log logger = LogFactory.getLog(LDAPBaseDAO.class);

    protected LDAPBaseDAO(ServiceLocator locator) {
        super(locator);
    }

//https://hub.docker.com/r/openmicroscopy/apacheds
}
