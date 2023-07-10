<h1 id="HoyoBot">hoyo-bot-plugin-molly</h1>

<div>

为 HoyoBot 调用茉莉机器人 API

</div>

## 响应条件

在以下条件下，插件会调用茉莉云机器人 API 并返回回复：

1. 非机器人消息
2. 提及了机器人名称或 `@` 了机器人

## 配置参考

|参数|说明|取值|备注|
|:---:|:---:|:---:|:---:|
|apiKey|ApiKey|`string`| |
|apiSecret|ApiSecret |`string`| |
|botName|机器人名称|`string`| 聊天中提及此名称会触发回复 |