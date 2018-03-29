package com.dsw.iot.constant;

import org.springframework.stereotype.Service;

/**
 * 法度对接配置
 */
@Service
public class FaduConfig {

    public static final String prefix_order = "FDBL://SSO&";

    public static final String method_queryBiluListByTime = "/rest/secret/queryBiluListByTime";

    public static final String method_queryBiluListByRyIdcard = "/rest/secret/queryBiluListByRyIdcard";

    public static final String method_getSigntureFileContent = "/rest/secret/getSigntureFileContent";// getSigntureFileContent

    public static final String method_getLatestSigntureFileContent = "/rest/secret/getLatestSigntureFileContent";

    public static final String method_queryBiluByBiluID = "/rest/secret/queryBiluByBiluID";

    public static final String method_queryBiluListByAjid = "/rest/secret/queryBiluListByAjid";

}
