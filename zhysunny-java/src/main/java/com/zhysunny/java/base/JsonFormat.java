package com.zhysunny.java.base;

/**
 * 美化json字符串
 * @author 章云
 * @date 2019/11/19 14:10
 */
public class JsonFormat {

    /**
     * 单位缩进字符串。
     */
    private static final String SPACE = "    ";

    /**
     * 返回格式化JSON字符串。
     * @param json 未格式化的JSON字符串。
     * @return 格式化的JSON字符串。
     */
    public String formatJson(String json) {
        json = clean(json);
        StringBuffer result = new StringBuffer();
        int length = json.length();
        int number = 0;
        char key;
        //遍历输入字符串。
        for (int i = 0; i < length; i++) {
            //1、获取当前字符。
            key = json.charAt(i);
            //2、如果当前字符是前方括号、前花括号做如下处理：
            if ((key == '[') || (key == '{')) {
                //（1）如果前面还有字符，并且字符为“:”，打印：换行和缩进字符字符串。
                if ((i - 1 > 0) && (json.charAt(i - 1) == ':')) {
                    result.append(' ');
                }
                //（2）打印：当前字符。
                result.append(key);
                //（3）前方括号、前花括号，的后面必须换行。打印：换行。
                result.append('\n');
                //（4）每出现一次前方括号、前花括号；缩进次数增加一次。打印：新行缩进。
                number++;
                result.append(indent(number));
                //（5）进行下一次循环。
                continue;
            }
            //3、如果当前字符是后方括号、后花括号做如下处理：
            if ((key == ']') || (key == '}')) {
                //（1）后方括号、后花括号，的前面必须换行。打印：换行。
                result.append('\n');
                //（2）每出现一次后方括号、后花括号；缩进次数减少一次。打印：缩进。
                number--;
                result.append(indent(number));
                //（3）打印：当前字符。
                result.append(key);
                //（4）如果当前字符后面还有字符，并且字符不为“，”，打印：换行。
                if (((i + 1) < length) && (json.charAt(i + 1) != ',') && (json.charAt(i + 1) != ']') && (json.charAt(i + 1) != '}')) {
                    result.append('\n');
                }
                //（5）继续下一次循环。
                continue;
            }
            //4、如果当前字符是逗号。逗号后面换行，并缩进，不改变缩进次数。
            if ((key == ',')) {
                result.append(key);
                result.append('\n');
                result.append(indent(number));
                continue;
            }
            //5、打印：当前字符。
            result.append(key);
        }

        return result.toString();
    }

    /**
     * 返回指定次数的缩进字符串。每一次缩进两个空格，即SPACE。
     * @param number 缩进次数。
     * @return 指定缩进次数的字符串。
     */
    private String indent(int number) {
        StringBuffer result = new StringBuffer();
        for (int i = 0; i < number; i++) {
            result.append(SPACE);
        }
        return result.toString();
    }

    /**
     * 输入的json可能包含空格或者换行符
     * @param json
     * @return
     */
    private String clean(String json) {
        json = json.replaceAll("\n", "").replaceAll("\r", "");
        StringBuffer result = new StringBuffer();
        int length = json.length();
        char key;
        int number = 0;
        for (int i = 0; i < length; i++) {
            key = json.charAt(i);
            if (key == '\'' || key == '\"') {
                number = number == 0 ? 1 : 0;
            }
            if (key == ' ' && number == 0) {
                continue;
            }
            result.append(key);
        }
        return result.toString();
    }

    public static void main(String[] args) {
        JsonFormat json = new JsonFormat();
        String str = "{\n    'age':23, 'aihao': \r\n['pashan','movies'],'name':{'firstName':'zhang','lastName':'san','aihao':['pashan','movies','name':{'firstName':'zhang','lastName':'san','aihao':['pashan','movies']}]}}";
        String result = json.formatJson(str);
        System.out.println(result);

    }

}
