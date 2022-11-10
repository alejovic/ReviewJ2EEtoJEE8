package com.avg.j2ee13.ejb.service;

import com.avg.j2ee13.bo.BOException;
import com.avg.j2ee13.bo.implementations.HelloWorldBO;
import com.avg.j2ee13.dao.DAOException;
import com.avg.j2ee13.dao.DAOFactoryMaker;
import com.avg.j2ee13.dao.GenericDAOFactory;
import com.avg.j2ee13.dto.HelloDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @see ejb-jar.xml
 * Bean Name="HelloWorldLocalBean"
 * description="An Session Local Service EJB named HelloWorldLocalBean]"
 * display-name="HelloWorldLocalBean"
 * jndi-name="ejbs-1.0_HelloWorldLocalBeanLocal"
 * type="Stateless"
 * transaction-type="Container"
 */
public class HelloWorldServiceLocalBean extends GenericServiceLocalBean {

    private static final long serialVersionUID = -1L;
    private GenericDAOFactory daoFactory;

    public void ejbCreate() throws BOException {
        super.ejbCreate();
        try {
            daoFactory = DAOFactoryMaker.getInstance().createDAOFactory(DAOFactoryMaker.FACTORY_FILE_STORE);
        } catch (DAOException e) {
            throw new BOException(BOException.BO_ERROR_01, e.getMessage(), e);
        }
    }

    public HelloDTO storeMessage(String message) throws BOException {
        logger.info("HelloWorldSessionLocalBean.storeMessage started");
        HelloWorldBO bo = new HelloWorldBO(daoFactory);
        return bo.storeMessage(message);
    }

    public HelloDTO getMessage(long id) throws BOException {
        return getMessageWithBO(id);
    }

    public HelloDTO getMessageWithBO(long id) throws BOException {
        logger.info("HelloWorldSessionLocalBean.getMessageBO started");
        HelloWorldBO bo = new HelloWorldBO(daoFactory);
        return bo.getMessage(id);
    }

    public HelloDTO getMessageWithDataSource(long id) {
        final String QUERY = "select * from HELLOWORLD where id = ?";
        HelloDTO dto = null;
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            connection = dataSource.getConnection();
            ps = connection.prepareStatement(QUERY);
            ps.setInt(1, (int) id);

            rs = ps.executeQuery();
            while (rs.next()) {
                dto = new HelloDTO();
                dto.setId(rs.getInt("ID"));
                dto.setMessage(rs.getString("MESSAGE"));
            }
        } catch (SQLException e) {
            throw new RuntimeException();
        } finally {
            closeAll(connection, ps, rs);

        }
        return dto;
    }

}
