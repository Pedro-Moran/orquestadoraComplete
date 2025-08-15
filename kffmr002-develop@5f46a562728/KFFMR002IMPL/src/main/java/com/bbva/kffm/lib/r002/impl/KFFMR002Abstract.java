package com.bbva.kffm.lib.r002.impl;

import com.bbva.elara.configuration.manager.application.ApplicationConfigurationService;
import com.bbva.elara.library.AbstractLibrary;
import com.bbva.j6ea.lib.r021.J6EAR021;
import com.bbva.j6ec.lib.r021.J6ECR021;
import com.bbva.kffm.lib.r002.KFFMR002;
import com.bbva.ksan.lib.r101.KSANR101;

/**
 * This class automatically defines the libraries and utilities that it will use.
 */
public abstract class KFFMR002Abstract extends AbstractLibrary implements KFFMR002 {

	protected ApplicationConfigurationService applicationConfigurationService;

	protected J6EAR021 j6eaR021;

	protected KSANR101 ksanR101;

	protected J6ECR021 j6ecR021;


	/**
	* @param applicationConfigurationService the this.applicationConfigurationService to set
	*/
	public void setApplicationConfigurationService(ApplicationConfigurationService applicationConfigurationService) {
		this.applicationConfigurationService = applicationConfigurationService;
	}

	/**
	* @param j6eaR021 the this.j6eaR021 to set
	*/
	public void setJ6eaR021(J6EAR021 j6eaR021) {
		this.j6eaR021 = j6eaR021;
	}

	/**
	* @param ksanR101 the this.ksanR101 to set
	*/
	public void setKsanR101(KSANR101 ksanR101) {
		this.ksanR101 = ksanR101;
	}

	/**
	* @param j6ecR021 the this.j6ecR021 to set
	*/
	public void setJ6ecR021(J6ECR021 j6ecR021) {
		this.j6ecR021 = j6ecR021;
	}

}