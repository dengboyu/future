package by.future.common.utils;

import org.apache.commons.lang3.StringUtils;

/**
 * @author by@Deng
 * @create 2020-03-22 22:30
 */
public class ValidUtils {

    private static final int[] factorArr = new int[]{7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2};
    private static final char[] verifyArr = new char[]{'1', '0', 'X', '9', '8', '7', '6', '5', '4', '3', '2'};

    /**
     * 校验18位身份证信息
     *
     * 1.先对前17位数字的权求和，加权因子一共17位 [7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2];
     * 加权求和公式：S=（身份证上第1位*加权因子第1位）+...+(身份证上第17位*加权因子第17位)
     *
     * 2.计算模：M=mod(S, 11)，M为校验码的索引
     * 通过模得到对应的校验码数组：['1', '0', 'X', '9', '8', '7', '6', '5', '4', '3', '2']
     *
     * @Author: by@Deng
     * @Date: 2020/3/22 10:33 下午
     */
    public static boolean verifyIdentity(String identityCode){

        if(StringUtils.isEmpty(identityCode)) return false;

        String pre = StringUtils.substring(identityCode,0,17);
        System.out.println(pre);

        String lastOne = StringUtils.substring(identityCode,17);

        int factorSum = 0;
        for(int i=0;i<factorArr.length;i++){
            factorSum += (Integer.parseInt(String.valueOf(pre.charAt(i)))) * factorArr[i];
        }

        int modVerify = factorSum % 11;
        String verifyCode =  String.valueOf(verifyArr[modVerify]);
        System.out.println("计算出校验码："+verifyCode);

        return StringUtils.equalsIgnoreCase(lastOne,verifyCode);
    }

}
