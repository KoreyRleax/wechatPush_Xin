package cn.cvzhanshi.wechatpush.config;

import cn.cvzhanshi.wechatpush.entity.Weather;

import java.util.Random;

public class ValueToStringFilter {
    String date;
    String tianqi;
    String city;
    String low;
    String temp;
    String high;
    String wind_class;
    String wind_dir;
    String caiHongPi;
    String lianAi;
    String birthday_xin;
    String birthday_me;
    String endWords;
    String myWord;
    public ValueToStringFilter() {
        strParse();
    }

    public void strParse() {
        Weather weather = WeatherUtils.getWeather();
        date = weather.getDate() + "  " + weather.getWeek() + " 13:14";
        tianqi = "天气 : " + weather.getText_now();
        city = "城市 : 百色";
        low = "最低温度 : " + weather.getLow();
        temp = "当前温度 : " + weather.getTemp();
        high = "最高温度 : " + weather.getHigh();
        wind_class = "风力 : " + weather.getWind_class();
        wind_dir = "风向 : " + weather.getWind_dir();
        caiHongPi = CaiHongPiUtils.getCaiHongPi();
        lianAi = "时至今日，我们已经相识了  " + JiNianRiUtils.getLianAi() + "天";
        birthday_xin = "距离你下次生日还有" + JiNianRiUtils.getBirthday_Xin() + "天";
        birthday_me = "距离我下次生日还有" + JiNianRiUtils.getBirthday_Me() + "天";
        endWords = "每天下午13:14准时推送哦~~啾咪";
        String[] MyWords = {"要不要去散散步，我们也好久没见啦。", "见不到你时 心心念念 与你相见时 依依恋恋。", "在神明面前，我只想念你。", "我们什么时候才能见面呢。", "我想见你 穿过教堂和人海拥抱你", "那我心里的想念攒够了能不能见面", "说不出情话了 只想见你一面", "你是我的「唯一热衷且坚定不移」。", "想你之余还是想你", "你说你也想我，江南的雪就融了。", "日出东方落与于西，朝思暮想念于你。"};
        int randomIndex = random();
        myWord = MyWords[randomIndex];
    }

    public int random() {
        Random random = new Random();
        int anInt = random.nextInt(11);
        return anInt;
    }
}
