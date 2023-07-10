package net.catrainbow.moli;

import cn.hoyobot.sdk.HoyoBot;
import cn.hoyobot.sdk.event.villa.VillaSendMessageEvent;
import cn.hoyobot.sdk.network.protocol.mihoyo.MsgContentInfo;
import cn.hoyobot.sdk.network.protocol.type.TextType;
import cn.hoyobot.sdk.plugin.Plugin;
import cn.hoyobot.sdk.utils.Config;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;

import java.io.File;
import java.util.HashMap;
import java.util.Locale;

public class Moli extends Plugin {

    private final static String api_url = "https://api.mlyai.com/reply";
    private final static String asset_url = "https://files.molicloud.com/";
    private String key = "";
    private String secret = "";
    private String botName = "";
    private Long lastSend = System.currentTimeMillis();

    @Override
    public void onEnable() {
        this.getLogger().info("茉莉云API插件加载中...");
        this.saveResource("config.yml");
        Config config = new Config(this.getBotProxy().getPluginPath() + File.separator + this.getName().toLowerCase(Locale.ROOT) + File.separator + "config.yml", 2);
        this.key = config.getString("api-key");
        this.secret = config.getString("api-secret");
        this.botName = config.getString("bot-name");
        this.getBotProxy().getEventManager().subscribe(VillaSendMessageEvent.class, this::onMessage);
    }

    public void onMessage(VillaSendMessageEvent event) {
        if (System.currentTimeMillis() - lastSend < 1000) return;
        MsgContentInfo msgContentInfo = event.getMsgContentInfo();
        String msg = msgContentInfo.getValue();
        String command = msg.split("\\s+")[1];
        if (!command.startsWith("/")) return;
        String subCommand = command.replaceFirst("/", "");
        this.apply(event, subCommand);
        this.lastSend = System.currentTimeMillis();
    }

    private void apply(VillaSendMessageEvent event, String message) {
        HashMap<String, String> headers = new HashMap<>();
        headers.put("Api-Key", this.key);
        headers.put("Api-Secret", this.secret);
        headers.put("Content-Type", "application/json;charset=UTF-8");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("content", message);
        jsonObject.put("type", 2);
        jsonObject.put("from", event.getSenderByMember().getUid());
        jsonObject.put("fromName", event.getSenderName());
        jsonObject.put("to", this.getBotProxy().getBot().getBotID());
        jsonObject.put("toName", this.botName);
        HttpResponse response = HttpUtil.createPost(api_url).body(jsonObject).addHeaders(headers).executeAsync();
        //更新大别野ID
        HoyoBot.instance.getBot().setVillaID(String.valueOf(event.getVilla().getId()));
        if (response.body() != null) {
            if (response.body().length() > 1) {
                this.getLogger().info("收到 api 响应:\n" + response.body());

                JSONObject responseJson = new JSONObject(response.body());
                if (responseJson.getStr("code").equals("00000")) {
                    String result = ((JSONObject) (((JSONArray) responseJson.getByPath("data")).get(0))).getStr("content");
                    this.getBotProxy().getBot().sendMessage(event.getRoomID(), new MsgContentInfo(result), TextType.MESSAGE);
                } else
                    this.getBotProxy().getBot().sendMessage(event.getRoomID(), new MsgContentInfo("今天累了呢，明天再聊吧\uD83E\uDD71"), TextType.MESSAGE);

            } else
                this.getBotProxy().getBot().sendMessage(event.getRoomID(), new MsgContentInfo("今天累了呢，明天再聊吧\uD83E\uDD71"), TextType.MESSAGE);
        } else
            this.getBotProxy().getBot().sendMessage(event.getRoomID(), new MsgContentInfo("今天累了呢，明天再聊吧\uD83E\uDD71"), TextType.MESSAGE);
    }

}
