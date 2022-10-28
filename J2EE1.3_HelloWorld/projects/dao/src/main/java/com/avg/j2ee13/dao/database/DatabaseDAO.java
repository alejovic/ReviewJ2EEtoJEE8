package com.avg.j2ee13.dao.database;

import com.avg.j2ee13.dao.DAOException;
import com.avg.j2ee13.dao.DAOParameters;
import com.avg.j2ee13.dao.GenericAbstractDAO;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public abstract class DatabaseDAO extends GenericAbstractDAO {

    protected static final Log logger = LogFactory.getLog(DatabaseDAO.class);

    private String dsName;

    protected DatabaseDAO(final Map parameters) throws DAOException {
        super(parameters);
    }

    protected void initParameters(Map parameters) throws DAOException {
        if (parameters.get(DAOParameters.PARAM_DAO_DATASOURCE) == null) {
            throw new DAOException(DAOException.DAO_MISSING_PARAMETER, "Parameter missing -> " + DAOParameters.PARAM_DAO_DATASOURCE);
        }
        this.dsName = (String) parameters.get(DAOParameters.PARAM_DAO_DATASOURCE);
    }

    protected Connection getConnection() throws DAOException {
        try {
            return locator.getConnection(this.dsName);
        } catch (Exception e) {
            logger.error(e);
            throw new DAOException(DAOException.DAO_ERROR_CONNECTION, e.getMessage(), e);
        }
    }

    protected void closeAll(Connection pConnection,
                            PreparedStatement pStatement, ResultSet pResultSet) {
        if (pResultSet != null) {
            try {
                pResultSet.close();
            } catch (SQLException e) {
                logger.warn(e);
            }
        }

        if (pStatement != null) {
            try {
                pStatement.close();
            } catch (SQLException e) {
                logger.warn(e);
            }
        }

        if (pConnection != null) {
            try {
                pConnection.close();
            } catch (SQLException e) {
                logger.warn(e);
            }
        }
    }
}
