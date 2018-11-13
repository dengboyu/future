package by.future.servicebiz.wx.service;


import org.springframework.stereotype.Component;


/**
 * 通用方法的service接口
 * Created by Zeal on 2017/4/26.
 */
@Component
public class WeiXinService {

//    @Resource
//    private WXSecretDao wxSecretDao;

    /**
     * 根据当前时间判断accessToken是否失效
     * type: 0公众号  1小程序  accessToken的标识符
     * @return WeiXinEntity
     */
    /*public WeiXinEntity findWeiXinEntity(String userId, Integer type){

        WeiXinEntity weiXinEntity = new WeiXinEntity();

        //根据当前时间来判断是否需要重新生成jsapi_ticket、access_token
        WXSecretEntity wxSecretEntity = wxSecretDao.findWXSecretEntityByTime(userId,type);
        if(wxSecretEntity != null ){
            weiXinEntity.setAccessToken(wxSecretEntity.getAccessToken());
            weiXinEntity.setWxJsapiTicket(wxSecretEntity.getWxJsapiTicket());
        }else {
            //accessToken需要重新生成
            wxSecretEntity = wxSecretDao.findWXSecretEntity(userId,type);

            //1.重新获取access_token值和失效时间
            WXSecretEntity newWxSecretEntity = WeiXinUtils.getAccessToken(wxSecretEntity.getAppId(),wxSecretEntity.getAppSecret());
            weiXinEntity.setAccessToken(newWxSecretEntity.getAccessToken());

            wxSecretEntity.setLoseTime(newWxSecretEntity.getLoseTime());
            wxSecretEntity.setAccessToken(newWxSecretEntity.getAccessToken());

            //2.公众号要需要更新jsapi_ticket
            if(type == 0 ){
                String jsapi_ticket = WeiXinUtils.getTicket(newWxSecretEntity.getAccessToken());
                wxSecretEntity.setWxJsapiTicket(jsapi_ticket);
                weiXinEntity.setWxJsapiTicket(jsapi_ticket);
            }

            //更新access_Token
            wxSecretDao.updateAccessToken(wxSecretEntity);
        }
        return weiXinEntity;
    }*/


    /**
     * 创建一个新的WeiXinEntity
     *
     * @Author: by@Deng
     * @Date: 2018/4/28 上午10:36
     */
    /*public WeiXinEntity createWeiXinEntity(String userId,Integer type){

        WeiXinEntity weiXinEntity = new WeiXinEntity();
        weiXinEntity.setType(type);   //公众号或小程序
        weiXinEntity.setUserId(userId); //用户的userId

        //获取appid,secret
        WXSecretEntity wxSecretEntity = wxSecretDao.findWXSecretEntity(userId,type);

        //重新获取access_token值和失效时间
        wxSecretEntity = WeiXinUtils.getAccessToken(wxSecretEntity.getAppId(),wxSecretEntity.getAppSecret());
        wxSecretEntity.setType(type);
        wxSecretEntity.setUserId(userId);

        //更新access_Token
        wxSecretDao.updateAccessToken(wxSecretEntity);

        weiXinEntity.setAccessToken(wxSecretEntity.getAccessToken());
        return weiXinEntity;
    }*/


    /**
     * 根据userId和type查找商户信息
     * type: 0 公众号   1 小程序的标识
     * @author by@Deng
     * @date 2017/11/14 上午11:32
     */
    /*public WXSecretEntity findWXSecretEntity(String userId,Integer type){
         return wxSecretDao.findWXSecretEntity(userId,type);
    }*/

}
