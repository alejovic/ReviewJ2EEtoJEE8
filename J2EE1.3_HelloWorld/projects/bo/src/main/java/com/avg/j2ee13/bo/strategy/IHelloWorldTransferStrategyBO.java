package com.avg.j2ee13.bo.strategy;

import com.avg.j2ee13.bo.BOException;

/**
 * Common interface for all strategy
 */
public interface IHelloWorldTransferStrategyBO {

    void transferMessage() throws BOException;
}
