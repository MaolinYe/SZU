import com.youdao.aicloud.translate.utils.AuthV3Util;
import com.youdao.aicloud.translate.utils.HttpUtil;

import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

/**
 * 网易有道智云翻译服务api调用demo
 * api接口: https://openapi.youdao.com/api
 */
public class YouDaoAPI {

    private static String APP_KEY = "";     // 您的应用ID
    private static String APP_SECRET = "";  // 您的应用密钥

    public YouDaoAPI(String appid, String securityKey) {
        APP_KEY = appid;
        APP_SECRET = securityKey;
    }

    public String getTransResult(String query, String from, String to) throws NoSuchAlgorithmException {
        // 添加请求参数
        Map<String, String[]> params = createRequestParams(query, from, to);
        // 添加鉴权相关参数
        AuthV3Util.addAuthParams(APP_KEY, APP_SECRET, params);
        // 请求api服务
        byte[] result = HttpUtil.doPost("https://openapi.youdao.com/api", null, params, "application/json");
        return new String(result, StandardCharsets.UTF_8);
    }

    private static Map<String, String[]> createRequestParams(String query, String from, String to) {
        return new HashMap<String, String[]>() {{
            put("q", new String[]{query});
            put("from", new String[]{from});
            put("to", new String[]{to});
        }};
    }
}
