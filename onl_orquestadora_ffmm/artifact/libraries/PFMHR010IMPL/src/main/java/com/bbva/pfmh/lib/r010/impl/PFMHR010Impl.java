package com.bbva.pfmh.lib.r010.impl;

import com.bbva.pfmh.dto.jcisconnector.ffmm.commons.InputListInvestmentFundsDTO;
import com.bbva.pfmh.dto.jcisconnector.ffmm.commons.OutputInvestmentFundsDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.List;

public class PFMHR010Impl extends PFMHR010Abstract {

    private static final Logger LOGGER = LoggerFactory.getLogger(PFMHR010Impl.class);

    @Override
    public List<OutputInvestmentFundsDTO> executeGetFFMMStatements(InputListInvestmentFundsDTO input) {
        LOGGER.info("***** PFMHR010Impl - executeGetFFMMStatements - Start || input: {} *****", input);

        List<OutputInvestmentFundsDTO> outputResponse = this.fmc7Connection.executeFMC7Transaction(input);
        if (outputResponse == null || outputResponse.isEmpty()) {
            return Collections.emptyList();
        }

        return outputResponse;
    }
}