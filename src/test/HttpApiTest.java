import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import me.gqz.model.json.TencentWsPlaynum;
import me.gqz.utils.HttpClientUtil;
import me.gqz.utils.TencentWsPlayNumUtil;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class HttpApiTest {
    @Test
    public void sendApi() {
        System.out.println(TencentWsPlayNumUtil.getTencentWsPlayNum("Bpu1afbSUxm8ftTmYHRpnL"));
    }
}
