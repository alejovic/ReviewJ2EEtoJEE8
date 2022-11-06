package com.avg.j2ee13.bo.strategy;

import com.avg.j2ee13.bo.BOException;
import com.avg.j2ee13.dao.*;
import com.avg.j2ee13.dto.HelloDTO;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Date;
import java.util.List;

/**
 * Concrete strategy.
 * Implements transfer from Memory to DB
 */
public class HelloWorldTransferFromMemoryToFileBO implements IHelloWorldTransferStrategyBO {

    protected static final Log logger = LogFactory.getLog(HelloWorldTransferFromMemoryToFileBO.class);
    private GenericDAOFactory daoSourceFactory;
    private GenericDAOFactory daoTargetFactory;

    public HelloWorldTransferFromMemoryToFileBO() throws BOException {
        try {
            this.daoSourceFactory = DAOFactoryMaker.getInstance().createDAOFactory(DAOParameters.FACTORY_MEMORY);
            this.daoTargetFactory = DAOFactoryMaker.getInstance().createDAOFactory(DAOParameters.FACTORY_FILE_STORE);
            logger.debug("HelloWorldTransferFromMemoryToFileBO.daoSourceFactory -> " + daoSourceFactory);
            logger.debug("HelloWorldTransferFromMemoryToFileBO.daoTargetFactory -> " + daoTargetFactory);
        } catch (DAOException e) {
            throw new BOException(BOException.BO_TRANSFER_ERROR, e.getMessage(), e);
        }
    }


    public void transferMessage() throws BOException {
        try {
            List data = getDataSource();
            writeData(data);
        } catch (DAOException e) {
            throw new BOException(BOException.BO_TRANSFER_ERROR, e.getMessage(), e);
        }
    }

    private void writeData(List data) throws DAOException {
        IGenericDAO dao = daoTargetFactory.createDAO(DAOClassList.HelloWorld);
        for (int index = 0; index < data.size(); index++) {
            HelloDTO dto = (HelloDTO) data.get(index);
            dao.insert(dto);
        }
    }

    private List getDataSource() throws DAOException {
        IGenericDAO dao = daoSourceFactory.createDAO(DAOClassList.HelloWorld);
        dao.insert(new HelloDTO(1000L, "HelloWorldTransferFromMemoryToFileBO 1", new Date()));
        dao.insert(new HelloDTO(1001L, "HelloWorldTransferFromMemoryToFileBO 2", new Date()));
        return dao.findAll();
    }
}
