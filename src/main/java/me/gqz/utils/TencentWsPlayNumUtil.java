package me.gqz.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>Title: TencentWsPlayNumUtil. </p>
 * <p>Description 腾讯微视接口工具 </p>
 * @author dragon
 * @date 2018/8/2 下午3:57
 */
public class TencentWsPlayNumUtil {

    private static final String PLAYNUM_URL = "https://h5.qzone.qq.com/webapp/json/weishi/ReportFeedPlay";

    /**
     * <p>Title: getTencentWsPlayNum. </p>
     * <p>获取腾讯微视播放量数据 </p>
     * @param feedId
     * @author dragon
     * @date 2018/8/2 下午3:56
     * @return Integer
     */
    public static Integer getTencentWsPlayNum(String feedId) {
        Map<String, String> map = new HashMap<>();
        map.put("feed_id", feedId);
        String responseJson = HttpClientUtil.doPost(PLAYNUM_URL, map);
        JSONObject json = JSON.parseObject(responseJson);
        Object data = json.get("data");
        Integer playNum = (Integer) JSON.parseObject(data.toString()).get("playnum");
        return playNum;
    }
}
