package com.dsw.iot.constant;

import org.springframework.stereotype.Service;

/**
 * 法度对接配置
 */
@Service
public class FaduConfig {
    //DEBUG
    public static final String appKey = "3c56602233254dd4be6db5d9b7f9a23e";
    public static final String appSecret = "37179a0b0422f14de73120ff5f03915e";
    //生产环境
//    public static final String appKey = "429386a29d2d4171905f364a8da5775f";
//    public static final String appSecret = "48adf69999f6bca473ca9ea22cff2e55";

    public static final String prefix_order = "FDBL://SSO&";

    public static final String method_queryBiluListByTime = "/rest/secret/queryBiluListByTime";

    public static final String method_queryBiluListByRyIdcard = "/rest/secret/queryBiluListByRyIdcard";

    public static final String method_getSigntureFileContent = "/rest/secret/getSigntureFileContent";// getSigntureFileContent

    public static final String method_getLatestSigntureFileContent = "/rest/secret/getLatestSigntureFileContent";

    public static final String method_queryBiluByBiluID = "/rest/secret/queryBiluByBiluID";

    public static final String method_queryBiluListByAjid = "/rest/secret/queryBiluListByAjid";

}
