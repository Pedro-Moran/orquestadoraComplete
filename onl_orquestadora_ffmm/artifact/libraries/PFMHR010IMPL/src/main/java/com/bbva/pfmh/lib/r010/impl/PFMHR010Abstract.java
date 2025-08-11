package com.bbva.pfmh.lib.r010.impl;

import com.bbva.elara.configuration.manager.application.ApplicationConfigurationService;
import com.bbva.elara.library.AbstractLibrary;
import com.bbva.pfmh.lib.r010.PFMHR010;
import com.bbva.pfmh.lib.r010.impl.cics.FMC7Connection;
import com.bbva.pfmh.lib.r015.PFMHR015;

/**
 * This class automatically defines the libraries and utilities that it will use.
 */
public abstract class PFMHR010Abstract extends AbstractLibrary implements PFMHR010 {

	protected ApplicationConfigurationService applicationConfigurationService;

	protected PFMHR015 pfmhR015;

	protected FMC7Connection fmc7Connection;


	/**
	* @param applicationConfigurationService the this.applicationConfigurationService to set
	*/
	public void setApplicationConfigurationService(ApplicationConfigurationService applicationConfigurationService) {
		this.applicationConfigurationService = applicationConfigurationService;
	}

	/**
	 * @param pfmhR015 the this.pfmhR015 to set
	 */
	public void setPfmhR015(PFMHR015 pfmhR015) {
		this.pfmhR015 = pfmhR015;
	}

	public void setFmc7Connection(FMC7Connection fmc7Connection) {
		this.fmc7Connection = fmc7Connection;
	}

}