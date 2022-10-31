package com.avg.j2ee13.dao.database;

import com.avg.j2ee13.dao.DAOException;
import com.avg.j2ee13.dao.GenericAbstractDAO;
import com.avg.j2ee13.util.localization.ServiceLocator;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class DatabaseDAO extends GenericAbstractDAO {

    protected static final Log logger = LogFactory.getLog(DatabaseDAO.class);

    protected DatabaseDAO(ServiceLocator locator) {
        super(locator);
    }

    protected Connection getConnection() throws DAOException {
        try {
            return locator.getConnection();
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
