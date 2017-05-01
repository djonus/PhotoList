package lt.danjon.task.data;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import lt.danjon.task.data.FlickrService;
import lt.danjon.task.data.model.PhotosResponse;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Query;

public class FlickrServiceMock implements FlickrService {

    private static final String JSON_PHOTOS = "{ \"photos\": { \"page\": 1, \"pages\": 50, \"perpage\": 20, \"total\": \"1000\", \n" +
            "    \"photo\": [\n" +
            "      { \"id\": \"33508346234\", \"owner\": \"148893934@N08\", \"secret\": \"8cc01f48e4\", \"server\": \"4168\", \"farm\": 5, \"title\": \"This spinner with cool design will be interesting for you, #fidgetspinners #fidgetspinner #handspinnertoy #fidgets #handspinner #handspinnertoy #fidgetedc#diyfidgetspinner\", \"ispublic\": 1, \"isfriend\": 0, \"isfamily\": 0 },\n" +
            "      { \"id\": \"33508347194\", \"owner\": \"25706803@N04\", \"secret\": \"2b25ee7a59\", \"server\": \"2824\", \"farm\": 3, \"title\": \"@baaprado matando a sdd \uD83C\uDFC4\", \"ispublic\": 1, \"isfriend\": 0, \"isfamily\": 0 },\n" +
            "      { \"id\": \"33966188550\", \"owner\": \"137305978@N06\", \"secret\": \"8fdd6a6265\", \"server\": \"2873\", \"farm\": 3, \"title\": \"IMG_6035\", \"ispublic\": 1, \"isfriend\": 0, \"isfamily\": 0 },\n" +
            "      { \"id\": \"33966188690\", \"owner\": \"98261045@N04\", \"secret\": \"f537d9659d\", \"server\": \"2814\", \"farm\": 3, \"title\": \"ALL Dカール  12-13-14  まりたんいつもありがとう\uD83C\uDF3C  初回5,900円+TAX セーブル+1,000円  ERIKO  @eriko919 #amaranth #マツエクはアマランス #eyelashstylist  #名古屋  #マツエク #まつげエクステ #eyelashes #eyelashsalon #eyelash #eyelashes #名古屋 #栄 #錦\", \"ispublic\": 1, \"isfriend\": 0, \"isfamily\": 0 },\n" +
            "      { \"id\": \"33966189120\", \"owner\": \"149573830@N04\", \"secret\": \"cd4c78cb30\", \"server\": \"2889\", \"farm\": 3, \"title\": \"Fotos recentes\", \"ispublic\": 1, \"isfriend\": 0, \"isfamily\": 0 },\n" +
            "      { \"id\": \"33966189770\", \"owner\": \"87216456@N06\", \"secret\": \"0b899dd75f\", \"server\": \"4170\", \"farm\": 5, \"title\": \"#AllahBless #Grateful #FamilyBond #Friendship #IkangFawzi #MarissaHaque #IsabellaFawzi #ChikitaFawzi #MarissaHaqueBooks #MarissaHaqueFoodie \uD83D\uDE4F\uD83D\uDE0D Bismillah, good morning everyone... I am heading to Medan in this blessed Subuh. Fabiayyi ala'i\", \"ispublic\": 1, \"isfriend\": 0, \"isfamily\": 0 },\n" +
            "      { \"id\": \"33966189960\", \"owner\": \"46056562@N07\", \"secret\": \"9113b10fe1\", \"server\": \"2860\", \"farm\": 3, \"title\": \"\", \"ispublic\": 1, \"isfriend\": 0, \"isfamily\": 0 },\n" +
            "      { \"id\": \"33966190240\", \"owner\": \"144218953@N07\", \"secret\": \"4b59bcf608\", \"server\": \"2856\", \"farm\": 3, \"title\": \"\uD83D\uDEA8 RESTOCK ALERT \uD83D\uDEA8  Unleash the ANML in you  Shop us at: Www.VapeShop.Sale Please note: some products may only be available in store.  FREE Juice Tasting & JuiceDripping at @TheVaporShop stores (members only) .  Open LATE - 't\", \"ispublic\": 1, \"isfriend\": 0, \"isfamily\": 0 },\n" +
            "      { \"id\": \"34192065162\", \"owner\": \"143654790@N07\", \"secret\": \"33245e159d\", \"server\": \"2916\", \"farm\": 3, \"title\": \"A church in Hong Kong？ When I walked street in Hong Kong, I found a church at alley.   香港の教会？ 路地を歩いていたら、小道で教会を見つけましたw #church #hongkong #alley #asian #street #instagram #instagood #instalike #instapic #instaphoto #travel #likes #igers\", \"ispublic\": 1, \"isfriend\": 0, \"isfamily\": 0 },\n" +
            "      { \"id\": \"34192065432\", \"owner\": \"96605961@N06\", \"secret\": \"99fa85e581\", \"server\": \"2835\", \"farm\": 3, \"title\": \"NYIAS2017-7.jpg\", \"ispublic\": 1, \"isfriend\": 0, \"isfamily\": 0 },\n" +
            "      { \"id\": \"34192065742\", \"owner\": \"133876568@N08\", \"secret\": \"9ffdb921cf\", \"server\": \"2836\", \"farm\": 3, \"title\": \"IMG_1386\", \"ispublic\": 1, \"isfriend\": 0, \"isfamily\": 0 },\n" +
            "      { \"id\": \"34309184876\", \"owner\": \"131065771@N06\", \"secret\": \"62ff08b842\", \"server\": \"4178\", \"farm\": 5, \"title\": \"Catedral de Berlim (Berliner Dom). É possível visitar a parte alta da Catedral, a cúpula, onde subindo 270 degraus tem-se uma vista incrível da cidade de Berlin A entrada principal da Catedral de Berlim fica de frente para a Lustgarten,  #Berlin\", \"ispublic\": 1, \"isfriend\": 0, \"isfamily\": 0 },\n" +
            "      { \"id\": \"34309185246\", \"owner\": \"47130635@N07\", \"secret\": \"161516472d\", \"server\": \"2828\", \"farm\": 3, \"title\": \"dreamland-32.jpg\", \"ispublic\": 1, \"isfriend\": 0, \"isfamily\": 0 },\n" +
            "      { \"id\": \"34309185336\", \"owner\": \"22069531@N00\", \"secret\": \"ea16442241\", \"server\": \"2824\", \"farm\": 3, \"title\": \"IMG_9385--\", \"ispublic\": 1, \"isfriend\": 0, \"isfamily\": 0 },\n" +
            "      { \"id\": \"34309185596\", \"owner\": \"94814536@N03\", \"secret\": \"17d76ba0ef\", \"server\": \"2805\", \"farm\": 3, \"title\": \"The Pledge\", \"ispublic\": 1, \"isfriend\": 0, \"isfamily\": 0 },\n" +
            "      { \"id\": \"34350279205\", \"owner\": \"85450469@N04\", \"secret\": \"e950dcf42b\", \"server\": \"2844\", \"farm\": 3, \"title\": \"A Head Full of Dreams\", \"ispublic\": 1, \"isfriend\": 0, \"isfamily\": 0 },\n" +
            "      { \"id\": \"34350279235\", \"owner\": \"48265380@N07\", \"secret\": \"32452f5276\", \"server\": \"2867\", \"farm\": 3, \"title\": \"SAYYES婚禮攝影-新莊典華-盛典廳\", \"ispublic\": 1, \"isfriend\": 0, \"isfamily\": 0 },\n" +
            "      { \"id\": \"34350279605\", \"owner\": \"8722673@N05\", \"secret\": \"80684b1f0a\", \"server\": \"2890\", \"farm\": 3, \"title\": \"20170429-121525 9Q2A5255.jpg\", \"ispublic\": 1, \"isfriend\": 0, \"isfamily\": 0 },\n" +
            "      { \"id\": \"34350279635\", \"owner\": \"146060797@N03\", \"secret\": \"1ec32a731c\", \"server\": \"4177\", \"farm\": 5, \"title\": \"四季咲バレリーナが開花^_^毎年チェルシーフラワーショーの時期に咲くので、３週間ほど早いですね〜。#rose #rosegarden #garden #gardening #gardendesign #gardendesigner #広島 #広島の外構 #広島のガーデン #広島の小さなガーデン屋さん #廿日市 #大竹市 #バレリーナ #ばら #バラ #バラのある暮し #薔薇 #かわいい\", \"ispublic\": 1, \"isfriend\": 0, \"isfamily\": 0 },\n" +
            "      { \"id\": \"34350279695\", \"owner\": \"51794880@N07\", \"secret\": \"e4182f9437\", \"server\": \"2849\", \"farm\": 3, \"title\": \"American Coot\", \"ispublic\": 1, \"isfriend\": 0, \"isfamily\": 0 }\n" +
            "    ] }, \"stat\": \"ok\" }";

    // TODO: 29/04/2017 use common gson
    private Gson gson = new Gson();

    @Override
    public Call<PhotosResponse> recent(@Query("api_key") String apiKey, @Query("page") int page, @Query("per_page") int perPage) {
        return new CallMock<PhotosResponse>() {
            @Override
            public Response<PhotosResponse> execute() throws IOException {
                try {
                    Thread.sleep(ThreadLocalRandom.current().nextLong(100, 4000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return Response.success(gson.fromJson(JSON_PHOTOS, PhotosResponse.class));
            }
        };
    }
}
