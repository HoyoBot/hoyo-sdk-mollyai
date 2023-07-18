package net.catrainbow.moli;

public class Test {

    public static void main(String[] args) {
        String result = "格蕾修香香软软的\n" +
                "-----------------------------\n" +
                " 歌曲名称：Moon Halo（崩坏3《薪炎永燃》动画短片印象曲）\n" +
                " 演唱歌手：茶理理/TetraCalyx/Hanser/HOYO-MiX\n" +
                " 专辑名称：Moon Halo\n" +
                "-----------------------------\n" +
                " 播放地址：https://music.163.com/outchain/player?type=2&id=1859652717&auto=1&height=66\n" +
                "-----------------------------\n" +
                " 热门评论：\n" +
                " 茶理理：录音的时候鼻子就酸了，听到最终版眼泪快掉下来[流泪]….愿所有的美好都能得到祝福\n" +
                " 崩坏三第一偶像爱酱：痛苦曾掩理前行的道路，但灼烁的星火终将燃起\n" +
                "\n" +
                "以烈焰宣告黎明破晓，用希望传承薪火永存          \n" +
                "                               —《薪炎永燃》\n" +
                "（决战的时刻到了！）\n" +
                "（以此烈火、斩无不断！）\n" +
                "（现在轮到我了、没意见吧！）\n" +
                "                          —琪亚娜·卡斯兰娜\n" +
                " 水墨染镜华：希望不熄，薪火永燃\n" +
                "琪亚娜，你是我的骄傲\n" +
                "-----------------------------\n" +
                " 最新评论：\n" +
                " 骷髅汉斯先生：贴吧上有多少你们原神玩家的今天发言截图你是一张不看啊，要不我来发给你？别到时候说这肯定是反串！\n" +
                " 骷髅汉斯先生：不是一开始你们原神玩家到处ky，拒绝接受批判，一系列逆天行为才导致反原人吗？\n" +
                " 芝士猪爬：大伟哥辛苦了\n";
        String[] subResult = result.split("-----------------------------");
        String linkLineStr = subResult[2];
        String link = linkLineStr.split("：")[1];
        int index = subResult[0].length() + 1 + subResult[1].length() + 1;
        int endIndex = index + linkLineStr.length();
        System.out.println(link);
    }

}
