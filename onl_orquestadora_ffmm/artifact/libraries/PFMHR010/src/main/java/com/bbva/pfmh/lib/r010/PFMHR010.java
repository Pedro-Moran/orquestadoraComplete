package com.bbva.pfmh.lib.r010;


import com.bbva.pfmh.dto.jcisconnector.ffmm.commons.InputListInvestmentFundsDTO;
import com.bbva.pfmh.dto.jcisconnector.ffmm.commons.OutputInvestmentFundsDTO;

import java.util.List;


public interface PFMHR010 {

     List<OutputInvestmentFundsDTO> executeGetFFMMStatements(InputListInvestmentFundsDTO input);
}