package com.avg.j2ee13.bo.strategy;

import com.avg.j2ee13.bo.BOException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * HelloWorldTransferBO class Doesn't know the concrete transfer method (strategy) user has
 * picked. It uses common strategy interface to delegate collecting payment data
 * to strategy object.
 */
public class HelloWorldTransferBO {

    protected static final Log logger = LogFactory.getLog(HelloWorldTransferBO.class);

    public void transferData(IHelloWorldTransferStrategyBO transferStrategy) throws BOException {
        logger.debug("HelloWorldTransferBO.transferData strategy -> " + transferStrategy);
        transferStrategy.transferMessage();
    }


}
