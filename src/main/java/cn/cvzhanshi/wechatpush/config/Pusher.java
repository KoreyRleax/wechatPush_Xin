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
        //配置
        WxMpInMemoryConfigStorage wxStorage = new WxMpInMemoryConfigStorage();
        wxStorage.setAppId(appId);
        wxStorage.setSecret(secret);
        WxMpService wxMpService = new WxMpServiceImpl();
        wxMpService.setWxMpConfigStorage(wxStorage);
        //推送消息
        WxMpTemplateMessage templateMessage = WxMpTemplateMessage.builder()
//                .toUser("oIJBI6xTz1UKKgVPioHDsCaCXWQA")
                .toUser("oIJBI6wUxgMa5JMYE5DQa4PNPkVk")
                .templateId("wpSEwmDrtWCsHIIgMhhISAq2EmQ31zqGiTi8JoOj5EI")
                .build();
        Weather weather = WeatherUtils.getWeather();
        ValueToStringFilter filter = new ValueToStringFilter();
        Map<String, String> map = CaiHongPiUtils.getEnsentence();
        templateMessage.addData(new WxMpTemplateData("date", filter.date,"#00BFFF"));
        templateMessage.addData(new WxMpTemplateData("weather", filter.tianqi,"#00FFFF"));
        templateMessage.addData(new WxMpTemplateData("low", filter.low + "","#FF6347"));
        templateMessage.addData(new WxMpTemplateData("high", filter.high+ "","#FF6347" ));
        templateMessage.addData(new WxMpTemplateData("temp", filter.temp+ "","#EE212D"));
        templateMessage.addData(new WxMpTemplateData("windClass", filter.wind_class+ "","#42B857" ));
        templateMessage.addData(new WxMpTemplateData("windDir", filter.wind_dir+ "","#B95EA3" ));
        templateMessage.addData(new WxMpTemplateData("caiHongPi", filter.caiHongPi,"#FF69B4"));
        templateMessage.addData(new WxMpTemplateData("lianAi", filter.lianAi+"","#FF1493"));
        templateMessage.addData(new WxMpTemplateData("birthday_xin", filter.birthday_xin+"","#FFA500"));
        templateMessage.addData(new WxMpTemplateData("birthday_me", filter.birthday_me+"","#FFA500"));
        templateMessage.addData(new WxMpTemplateData("city", filter.city+"","#FFA500"));
        templateMessage.addData(new WxMpTemplateData("endWords", filter.endWords +"","#C71585"));
        templateMessage.addData(new WxMpTemplateData("myWord", filter.myWord +"","#FF69B4"));
        String beizhu = "Dear 欣欣";
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
            tips = "今天天气炎热，最高温度达到了"+highTemp+"℃，出门记得防晒哦！";
        }else if (lowTemp<=20){
            tips = "今天外面可冷，别冻着了！最低温度达到了"+lowTemp+"℃";
        }else{
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
