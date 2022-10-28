package com.avg.j2ee13.dao.database.helloworld;

import com.avg.j2ee13.dao.DAOException;
import com.avg.j2ee13.dao.database.DatabaseDAO;
import com.avg.j2ee13.dto.BaseDTO;
import com.avg.j2ee13.dto.HelloDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class HelloWorldDatabaseDAO extends DatabaseDAO {

    public HelloWorldDatabaseDAO(final Map parameters) throws DAOException {
        super(parameters);
    }

    public BaseDTO insert(BaseDTO baseDTO) throws DAOException {
        HelloDTO dto = (HelloDTO) baseDTO;

        StringBuffer sql = new StringBuffer(100);
        Connection conn;
        PreparedStatement ps = null;

        conn = getConnection();

        try {

            sql.append(" INSERT INTO ");
            sql.append(" 	AVG_ORDER ");
            sql.append(" 	( ");
            sql.append(" 		ID, ");
            sql.append(" 		DATEOFORDER, ");
            sql.append("		FK_ID_CUSTOMER ");
            sql.append("	 ) ");
            sql.append(" VALUES ( ?, ?, ? ) ");
            logger.debug("SQL ->  " + sql);

            ps = conn.prepareStatement(sql.toString());
            ps.setString(1, dto.getMessage());
            ps.setDate(2, new java.sql.Date(dto.getDateOfCreation().getTime()));
            ps.execute();

        } catch (SQLException e) {
            throw new DAOException(DAOException.DAO_ERROR_INSERT, e.getMessage(), e);
        } finally {
            this.closeAll(conn, ps, null);
        }

        return baseDTO;
    }

    public void update(BaseDTO baseDTO) throws DAOException {
        HelloDTO dto = (HelloDTO) baseDTO;

        StringBuffer sql = new StringBuffer(100);
        Connection conn;
        PreparedStatement ps = null;

        conn = getConnection();

        try {

            sql.append(" UPDATE ");
            sql.append(" 	AVG_ORDER ");
            sql.append(" SET ");
            sql.append(" 	 ");
            sql.append(" WHERE ");
            sql.append(" ");

            ps = conn.prepareStatement(sql.toString());
            ps.setLong(1, dto.getId());
            ps.setString(2, dto.getMessage());
            ps.setDate(2, new java.sql.Date(dto.getDateOfCreation().getTime()));
            ps.execute();

            logger.debug("SQL ejecutado: " + sql);

        } catch (SQLException e) {
            throw new DAOException(DAOException.DAO_ERROR_UPDATE, e.getMessage(), e);
        } finally {
            this.closeAll(conn, ps, null);
        }
    }

    public void delete(BaseDTO baseDTO) throws DAOException {

    }

    public List findAll() throws DAOException {
        List result = new ArrayList();

        StringBuffer sql = new StringBuffer(100);
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        conn = getConnection();

        try {

            sql.append(" SELECT ");
            sql.append(" 		ID, ");
            sql.append(" 		DATEOFORDER, ");
            sql.append("		FK_ID_CUSTOMER ");
            sql.append(" FROM ");
            sql.append(" 	AVG_ORDER ");

            ps = conn.prepareStatement(sql.toString());
            rs = ps.executeQuery();

            while (rs.next()) {
                HelloDTO dto = buildDTO(rs);
                result.add(dto);
            }

        } catch (SQLException e) {
            throw new DAOException(DAOException.DAO_ERROR_FIND_ALL, e.getMessage(), e);
        } finally {
            this.closeAll(conn, ps, rs);
        }

        return result;
    }

    public BaseDTO findById(long id) throws DAOException {
        HelloDTO dto = new HelloDTO();
        dto.setId(id);
        find(dto);
        return dto;
    }

    private void find(BaseDTO baseDTO) throws DAOException {

        StringBuffer sql = new StringBuffer(100);
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        conn = getConnection();

        try {

            HelloDTO dto = (HelloDTO) baseDTO;
            boolean isAnd = false;

            sql.append(" SELECT ");
            sql.append(" 		ID, ");
            sql.append(" 		DATEOFORDER, ");
            sql.append("		FK_ID_CUSTOMER ");
            sql.append(" FROM ");
            sql.append(" 	AVG_ORDER ");
            sql.append(" WHERE ");

            if (dto.getId() > 0) {
                sql.append(" ID = ? ");
                isAnd = true;
            }
            if (dto.getDateOfCreation() != null) {
                if (isAnd) {
                    sql.append(" AND ");
                }
                sql.append(" DATEOFORDER = ? ");
            }

            ps = conn.prepareStatement(sql.toString());

            int parameter = 1;
            if (dto.getId() > 0) {
                ps.setLong(parameter, dto.getId());
                parameter++;
            }
            if (dto.getDateOfCreation() != null) {
                ps.setDate(parameter, new java.sql.Date(dto.getDateOfCreation()
                        .getTime()));
            }

            rs = ps.executeQuery();

            while (rs.next()) {
                buildDTO(rs);
            }

        } catch (SQLException e) {
            throw new DAOException(DAOException.DAO_ERROR_FIND, e.getMessage(), e);
        } finally {
            this.closeAll(conn, ps, rs);
        }
    }

    private HelloDTO buildDTO(ResultSet rs) throws SQLException {
        HelloDTO dto = new HelloDTO();
        dto.setId(rs.getLong("ID"));
        dto.setMessage(rs.getString("MESSAGE"));
        dto.setDateOfCreation(rs.getDate("CREATED_DATE"));
        return dto;

    }
}
