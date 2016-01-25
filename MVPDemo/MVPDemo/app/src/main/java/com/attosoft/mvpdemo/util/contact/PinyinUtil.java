package com.attosoft.mvpdemo.util.contact;

/**
 * Created by andy on 15/9/30.
 */
public class PinyinUtil {

    private static PinyinUtil mInstance;

    private PinyinUtil() {
    }

    public static synchronized PinyinUtil getInstance() {
        if (mInstance == null) {
            mInstance = new PinyinUtil();
        }
        return mInstance;
    }

    /**
     * 将中文字符串转换为拼音
     * @param displayString
     * @param tone 附带声调
     * @return
     */
    public String convertToPinyin(String displayString, boolean tone){
        char[] chars = displayString.toCharArray();
        StringBuffer sb = new StringBuffer();
        for (int i= 0;i<chars.length;i++){
            String str = pinyin(chars[i]);
            if(str != null) {
                sb.append(tone?str:str.substring(0,str.length()-2));
            }else {
                sb.append(chars[i]);
            }
        }
        return sb.toString();
    }
    /**
     * 字符的拼音，多音字就得到第一个拼音。不是汉字，就return null。
     */
    public static String pinyin(char c) {
//        String[] pinyins = PinyinHelper.toHanyuPinyinStringArray(c);
//        if (pinyins == null) {
//            return null;
//        }
//        return pinyins[0];
        return c+"";
    }
}
