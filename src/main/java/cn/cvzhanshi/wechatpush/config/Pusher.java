package cn.cvzhanshi.wechatpush.config;


import cn.cvzhanshi.wechatpush.entity.Weather;
import me.chanjar.weixin.mp.api.WxMpInMemoryConfigStorage;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateData;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateMessage;

import java.util.Map;

/**
 * @author cVzhanshi
 * @create 2022-08-04 21:09
 */
public class Pusher {

    public static void main(String[] args) {
        push();
    }
    private static String appId = "wxa80b52b343d73a52";
    private static String secret = "f44e14f477f731c1eed16c394be75c32";



    public static void push(){
        //1，配置
        WxMpInMemoryConfigStorage wxStorage = new WxMpInMemoryConfigStorage();
        wxStorage.setAppId(appId);
        wxStorage.setSecret(secret);
        WxMpService wxMpService = new WxMpServiceImpl();
        wxMpService.setWxMpConfigStorage(wxStorage);
        //2,推送消息
        WxMpTemplateMessage templateMessage = WxMpTemplateMessage.builder()
                .toUser("oIJBI6xTz1UKKgVPioHDsCaCXWQA")
                .toUser("oIJBI6wUxgMa5JMYE5DQa4PNPkVk")
                .templateId("H3rZKrB6fBRbs5xW6Hl3O5ZoBdHI69SQlVwkFWFkQi8")
                .build();
        //3,如果是正式版发送模版消息，这里需要配置你的信息
        Weather weather = WeatherUtils.getWeather();
        Map<String, String> map = CaiHongPiUtils.getEnsentence();
        templateMessage.addData(new WxMpTemplateData("riqi",weather.getDate() + "  "+ weather.getWeek(),"#00BFFF"));
        templateMessage.addData(new WxMpTemplateData("tianqi",weather.getText_now(),"#00FFFF"));
        templateMessage.addData(new WxMpTemplateData("low",weather.getLow() + "","#173177"));
        templateMessage.addData(new WxMpTemplateData("temp",weather.getTemp() + "","#EE212D"));
        templateMessage.addData(new WxMpTemplateData("high",weather.getHigh()+ "","#FF6347" ));
        templateMessage.addData(new WxMpTemplateData("windclass",weather.getWind_class()+ "","#42B857" ));
        templateMessage.addData(new WxMpTemplateData("winddir",weather.getWind_dir()+ "","#B95EA3" ));
        templateMessage.addData(new WxMpTemplateData("caihongpi",CaiHongPiUtils.getCaiHongPi(),"#FF69B4"));
        templateMessage.addData(new WxMpTemplateData("lianai",JiNianRiUtils.getLianAi()+"","#FF1493"));
        templateMessage.addData(new WxMpTemplateData("shengri1",JiNianRiUtils.getBirthday_Xin()+"","#FFA500"));
        templateMessage.addData(new WxMpTemplateData("shengri2",JiNianRiUtils.getBirthday_Me()+"","#FFA500"));
        templateMessage.addData(new WxMpTemplateData("en",map.get("en") +"","#C71585"));
        templateMessage.addData(new WxMpTemplateData("zh",map.get("zh") +"","#C71585"));
        String beizhu = "欣欣子早上好啊！";
        if(JiNianRiUtils.getLianAi() % 365 == 0){
            beizhu = "今天是相识" + (JiNianRiUtils.getLianAi() / 365) + "周年纪念日！";
        }
        if(JiNianRiUtils.getBirthday_Xin()  == 0){
            beizhu = "今天是欣欣生日，生日快乐呀！";
        }
        if(JiNianRiUtils.getBirthday_Me()  == 0){
            beizhu = "今天是我的生日，生日快乐呀！";
        }
        templateMessage.addData(new WxMpTemplateData("beizhu",beizhu,"#FF0000"));

        String tips = "";
        int highTemp= Integer.parseInt(weather.getHigh());
        int lowTemp= Integer.parseInt(weather.getLow());
        if (highTemp>=34){
            tips = "今天天气炎热，出门记得防晒哦！";
        }else if (lowTemp<=20){
            tips = "今天很凉爽，我带你出门兜风吧！";
        }
        templateMessage.addData(new WxMpTemplateData("tips",tips,"#FF69B4"));

        try {
            System.out.println(templateMessage.toJson());
            System.out.println(wxMpService.getTemplateMsgService().sendTemplateMsg(templateMessage));
        } catch (Exception e) {
            System.out.println("推送失败：" + e.getMessage());
            e.printStackTrace();
        }
    }
}
