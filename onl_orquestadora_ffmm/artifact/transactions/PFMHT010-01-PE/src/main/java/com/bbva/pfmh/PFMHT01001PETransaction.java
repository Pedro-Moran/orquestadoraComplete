package com.bbva.pfmh;

import com.bbva.pfmh.dto.jcisconnector.ffmm.commons.InputListInvestmentFundsDTO;
import com.bbva.pfmh.dto.jcisconnector.ffmm.commons.OutputInvestmentFundsDTO;
import com.bbva.pfmh.lib.r010.PFMHR010;
import com.bbva.elara.domain.transaction.Severity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestClientException;

import java.util.List;


public class PFMHT01001PETransaction extends AbstractPFMHT01001PETransaction {


    private static final Logger LOGGER = LoggerFactory.getLogger(PFMHT01001PETransaction.class);

    @Override
    public void execute() {
        LOGGER.info("[PFMHT010] execute:: START");
        PFMHR010 pfmhR010 = this.getServiceLibrary(PFMHR010.class);
        InputListInvestmentFundsDTO input = this.getInputlistinvestmentfundsdto();

        String customerId = input.getCustomerId();
        String paginationKey = input.getPaginationKey();
        Integer pageSize = input.getPageSize();

        LOGGER.info("customerId: {}", customerId);
        LOGGER.info("paginationKey: {}", paginationKey);
        LOGGER.info("pageSize: {}", pageSize);

        LOGGER.info("RBVDT30301PETransaction - START");

        try {
            List<OutputInvestmentFundsDTO> responseOut = pfmhR010.executeGetFFMMStatements(input);
            LOGGER.info("responseOut -> {}", responseOut);
            if (responseOut != null) {
                this.setResponseOut(responseOut);
                this.setSeverity(Severity.OK);
            } else {
                this.setResponseOut(List.of());
                this.setSeverity(Severity.ENR);
            }
        } catch (RestClientException e) {
            LOGGER.error("Error executing PFMHR010 service: {}", e.getMessage());
            this.setResponseOut(List.of());
            this.setSeverity(Severity.ENR);
        }
    }
}