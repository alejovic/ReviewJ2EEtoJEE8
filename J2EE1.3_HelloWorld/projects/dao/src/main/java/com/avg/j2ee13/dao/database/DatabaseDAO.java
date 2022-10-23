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
import java.util.HashMap;

public abstract class DatabaseDAO extends GenericAbstractDAO {

    protected static final Log logger = LogFactory.getLog(DatabaseDAO.class);

    public DatabaseDAO(HashMap parameters) {
        //podria iniciar aqui
        super(parameters);
    }

    protected Connection getConnection() throws DAOException {
        try {
            String dsName = (String) getParameters().get("DS_NAME");
            Connection connection = ServiceLocator.getInstance().getConnection(dsName);

            return connection;
        } catch (Exception e) {
            logger.error(e);
            throw new DAOException(DAOException.ERROR_DAO_01, e.getMessage(), e);
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
