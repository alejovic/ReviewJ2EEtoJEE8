package com.avg.j2ee13.ejb.service;

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

    public HelloDTO storeMessage(String message) {
        log.info("HelloWorldSessionLocalBean.storeHello started");
        HelloDTO dto = new HelloDTO(message);
        return dto;
    }

    public HelloDTO getMessage(long id) {
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
            throw new RuntimeException(e);
        } finally {
            closeAll(connection, ps, rs);
            return dto;
        }

    }

}
