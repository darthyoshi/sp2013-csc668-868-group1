package org.openmrs.module.dssmodule.extension.html;

import java.util.HashMap;
import java.util.Map;
import org.openmrs.module.Extension;
import org.openmrs.module.web.extension.AdministrationSectionExt;

/**
 *
 * @author bia
 */

public class AdminDSS extends AdministrationSectionExt
{	
	/**
	 * @see org.openmrs.module.web.extension.AdministrationSectionExt#getMediaType()
	 */
	public Extension.MEDIA_TYPE getMediaType() {
		return Extension.MEDIA_TYPE.html;  
	}
	
	/**
	 * @see org.openmrs.module.web.extension.AdministrationSectionExt#getTitle()
	 */
	public String getTitle() {
		return "DSS Compiler";
	}
	
	/**
	 * @see org.openmrs.module.web.extension.AdministrationSectionExt#getLinks()
	 */
	public Map<String, String> getLinks() {
		
		Map<String, String> map = new HashMap<String, String>();
		
		map.put("module/dssmodule/dssRules.form", "Create/Modify DSS Rule");
                map.put("module/dssmodule/dssUpload.form", "Upload a DSS File");
                
		return map;
	}
    
}
