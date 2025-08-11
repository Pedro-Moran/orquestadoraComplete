package com.bbva.pfmh.lib.r010.impl.validation;

import com.bbva.pfmh.dto.jcisconnector.ffmm.commons.InputListInvestmentFundsDTO;
import com.bbva.pfmh.dto.jcisconnector.ffmm.commons.OutputInvestmentFundsDTO;
import com.bbva.pfmh.dto.jcisconnector.ffmm.investmen.InvestmentFund;
import com.bbva.pfmh.lib.r010.impl.utils.ValidationUtils;
import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

public class ValidationUtilsTest {

    @Test
    public void testValidationInputIsNullOrEmpty_NullInput() {
        assertTrue(ValidationUtils.validationInputIsNullOrEmpty(null));
    }

    @Test
    public void testPrivateConstructor() throws Exception {
        Constructor<ValidationUtils> constructor = ValidationUtils.class.getDeclaredConstructor();
        constructor.setAccessible(true);
        assertThrows(InvocationTargetException.class, constructor::newInstance);
    }

    @Test
    public void testValidationInputIsNullOrEmpty_EmptyCustomerId() {
        InputListInvestmentFundsDTO input = new InputListInvestmentFundsDTO();
        input.setCustomerId("");
        assertTrue(ValidationUtils.validationInputIsNullOrEmpty(input));
    }

    @Test
    public void testValidationInputIsNullOrEmpty_ValidInput() {
        InputListInvestmentFundsDTO input = new InputListInvestmentFundsDTO();
        input.setCustomerId("00001171");
        assertFalse(ValidationUtils.validationInputIsNullOrEmpty(input));
    }

    @Test
    public void testValidationResponseIsNullOrEmpty_NullResponse() {
        assertFalse(ValidationUtils.validationResponseIsNullOrEmpty(null));
    }

    @Test
    public void testValidationResponseIsNullOrEmpty_EmptyData() {
        OutputInvestmentFundsDTO response = new OutputInvestmentFundsDTO();
        response.setData(null);
        assertFalse(ValidationUtils.validationResponseIsNullOrEmpty(response));
    }

    @Test
    public void testValidationResponseIsNullOrEmpty_ValidResponse() {
        OutputInvestmentFundsDTO response = new OutputInvestmentFundsDTO();
        InvestmentFund fund1 = new InvestmentFund();
        InvestmentFund fund2 = new InvestmentFund();
        response.setData(List.of(fund1, fund2));
        assertTrue(ValidationUtils.validationResponseIsNullOrEmpty(response));
    }
}
