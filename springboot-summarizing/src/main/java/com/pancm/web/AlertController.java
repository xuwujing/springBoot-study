package com.pancm.web;

import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;


/**
 * @Author pancm
 * @Description 飞书告警中转服务
 * https://open.feishu.cn/document/client-docs/bot-v3/add-custom-bot#756b882f
 * https://cloud.tencent.com/developer/article/2209466
 * @Date 2023/9/11
 * @Param
 * @return
 **/
@RestController
@RequestMapping("alert")
@Slf4j
public class AlertController {

    //    @Value("${feishu.webhook-url:https://open.feishu.cn/open-apis/bot/v2/hook/d4329724-9e06-453c-9078-a2d52cac7585}")
    @Value("${feishu.webhook-url:https://open.feishu.cn/open-apis/bot/v2/hook/07f75330-f57b-40dd-8236-30092b0f4cc2}")
    private String webhookUrl;

    @Autowired
    private RestTemplate restTemplate;


    private String msg = "告警状态: %s\n告警标题：%s\n告警描述：%s\n面板URL：%s\n发生时间：%s\n负责人： %s";


    @RequestMapping("/webhook/receive")
    public void receive(@RequestBody String message) {
        log.info("AlertController.receive 获取的Grafana的告警数据:{}", message);
        Map<String, Object> m = createMessage(getText(message));
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        log.info("AlertController.receive 发送飞书的告警内容:{}", m);
        restTemplate.postForEntity(webhookUrl, new HttpEntity<>(m, headers), Void.class);
    }

    /**
     * 构建通知报文
     *
     * @param content
     * @return
     */
    protected Map<String, Object> createMessage(String content) {
        Map<String, Object> messageJson = new HashMap<>();
        messageJson.put("msg_type", "text");

        Map<String, Object> text = new HashMap<>();
        text.put("text", content);
        messageJson.put("content", text);
        Long timestamp = System.currentTimeMillis() / 1000;
        messageJson.put("timestamp", timestamp);
        return messageJson;
    }

    /**
     * 构建文本内容
     *
     * @param body
     * @return
     */
    private String getText(String body) {
        JSONObject json = JSON.parseObject(body);
        Map<String, Object> root = new HashMap<>();
        root.put("title", json.getString("title"));
        root.put("description", json.getJSONObject("commonAnnotations").getString("description"));
        JSONObject alertObject = json.getJSONArray("alerts").getJSONObject(0);
        root.put("panelUrl", alertObject.getString("panelURL"));
        root.put("startTime", DateUtil.now());
        String senMsg = String.format(msg,
                getStatusMsg(json.getString("status")),
                json.getString("title"),
                json.getJSONObject("commonAnnotations").getString("description"),
                alertObject.getString("panelURL"),
                DateUtil.now(),
                json.getJSONObject("commonAnnotations").getString("handler")
        );
        return senMsg;
    }

    private String getStatusMsg(String msg) {
        if ("firing".equals(msg)) {
            return "告警产生";
        }
        return "告警恢复";
    }
}
