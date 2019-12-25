package by.future.common.utils;


import by.future.entity.config.OpenCommonConfig;
import by.future.entity.constant.SysConst;
import org.apache.commons.lang3.StringUtils;

/**
 * @Author：by@Deng
 * @Date：2019/12/20 16:28
 */
public class OpenUtils {


    /**
     * 根据type类型进行限流检查
     * @return true:被限流 false:不被限流
     *
     * @Author：by@Deng
     * @Date：2019/2/25 17:00
     */
    public static <T> boolean checkIsLimit(T t, String checkValue) {

        try {

            if(t instanceof OpenCommonConfig && StringUtils.isNotEmpty(checkValue)){

                OpenCommonConfig openCommonConfig = (OpenCommonConfig) t;
                if(!openCommonConfig.isOpenFlag()) return false;

                //0 1与limitNums配合使用限制开关
                int type = openCommonConfig.getType();
                String limitNums =openCommonConfig.getLimitNums();

                if(type ==1){

                    if(StringUtils.isNotEmpty(limitNums) && executeCheckLimit(limitNums,checkValue)) return true;

                }else if(type ==0){

                    if(StringUtils.isEmpty(limitNums)) return true;

                    if(StringUtils.isNotEmpty(limitNums) && !executeCheckLimit(limitNums,checkValue)) return true;
                }
            }

        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        return false;
    }


    /**
     * 执行逻辑
     *
     * @Author：by@Deng
     * @Date：2019/2/25 19:51
     */
    private static boolean executeCheckLimit(String limitNums,String checkValue){

        if(StringUtils.isEmpty(limitNums) || StringUtils.isEmpty(checkValue)) return false;

        boolean checkResult =false;

        String[] nums = limitNums.split(SysConst.COMMA_SPLIT);
        for (String num : nums) {
            if (num.equals(checkValue)) {
                checkResult = true;
                break;
            }else if (checkValue.length() >= num.length() && num.equals(checkValue.substring(checkValue.length() - num.length()))) {
                checkResult = true;
                break;
            }
        }
        return checkResult;
    }





}
