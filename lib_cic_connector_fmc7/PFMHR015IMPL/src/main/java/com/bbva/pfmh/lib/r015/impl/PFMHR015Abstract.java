package com.bbva.pfmh.lib.r015.impl;

import com.bbva.elara.configuration.manager.application.ApplicationConfigurationService;
import com.bbva.elara.library.AbstractLibrary;
import com.bbva.elara.utility.interbackend.cics.InterBackendCicsUtils;
import com.bbva.pfmh.lib.r015.PFMHR015;

/**
 * This class automatically defines the libraries and utilities that it will use.
 */
public abstract class PFMHR015Abstract extends AbstractLibrary implements PFMHR015 {

	protected ApplicationConfigurationService applicationConfigurationService;

	protected InterBackendCicsUtils interBackendCicsUtils;


	/**
	* @param applicationConfigurationService the this.applicationConfigurationService to set
	*/
	public void setApplicationConfigurationService(ApplicationConfigurationService applicationConfigurationService) {
		this.applicationConfigurationService = applicationConfigurationService;
	}

	/**
	* @param interBackendCicsUtils the this.interBackendCicsUtils to set
	*/
	public void setInterBackendCicsUtils(InterBackendCicsUtils interBackendCicsUtils) {
		this.interBackendCicsUtils = interBackendCicsUtils;
	}
}