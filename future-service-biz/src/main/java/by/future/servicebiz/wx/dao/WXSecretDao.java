/** 
 * Copyright ® 2016-2017 tongyouhui Technology Co., Ltd.
 * All right reserved. 
 */
package by.future.servicebiz.wx.dao;


import by.future.servicebiz.wx.entity.WXSecretEntity;

/**
 * wx_secret数据访问接口
 * 
 * @author by@Deng
 * @date 2017-11-14 12:20:23
 */
public interface WXSecretDao  {


    /**
     * 根据userId和type查询信息判断accessToken是否过期
     * @author by@Deng
     * @date 2017/11/14 下午12:24
     */
    WXSecretEntity findWXSecretEntityByTime(String userId, Integer type);


    /**
     * 根据userId和type查询信息判断accessToken是否过期
     * @author by@Deng
     * @date 2017/11/14 下午12:24
     */
    WXSecretEntity findWXSecretEntity(String userId, Integer type);


    /**
     * 将access_token、jsapi_ticket、失效时间更新到数据库
     * @author by@Deng
     * @date 2017/11/8 下午4:53
     */
    Integer updateAccessToken(WXSecretEntity wxSecretEntity);

}