package com.zhysunny.java.date;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 〈将日期转换为干支历法的年月日时，并计算生肖、阴历、星期几〉
 *
 * @author wgn
 * @create 2018/6/29
 */
public class Birth {

    private int[] lunarInfo = new int[] {
            0x04bd8, 0x04ae0, 0x0a570, 0x054d5, 0x0d260, 0x0d950, 0x16554, 0x056a0, 0x09ad0, 0x055d2, 0x04ae0, 0x0a5b6,
            0x0a4d0, 0x0d250, 0x1d255, 0x0b540, 0x0d6a0, 0x0ada2, 0x095b0, 0x14977, 0x04970, 0x0a4b0, 0x0b4b5, 0x06a50,
            0x06d40, 0x1ab54, 0x02b60, 0x09570, 0x052f2, 0x04970, 0x06566, 0x0d4a0, 0x0ea50, 0x06e95, 0x05ad0, 0x02b60,
            0x186e3, 0x092e0, 0x1c8d7, 0x0c950, 0x0d4a0, 0x1d8a6, 0x0b550, 0x056a0, 0x1a5b4, 0x025d0, 0x092d0, 0x0d2b2,
            0x0a950, 0x0b557, 0x06ca0, 0x0b550, 0x15355, 0x04da0, 0x0a5b0, 0x14573, 0x052b0, 0x0a9a8, 0x0e950, 0x06aa0,
            0x0aea6, 0x0ab50, 0x04b60, 0x0aae4, 0x0a570, 0x05260, 0x0f263, 0x0d950, 0x05b57, 0x056a0, 0x096d0, 0x04dd5,
            0x04ad0, 0x0a4d0, 0x0d4d4, 0x0d250, 0x0d558, 0x0b540, 0x0b6a0, 0x195a6, 0x095b0, 0x049b0, 0x0a974, 0x0a4b0,
            0x0b27a, 0x06a50, 0x06d40, 0x0af46, 0x0ab60, 0x09570, 0x04af5, 0x04970, 0x064b0, 0x074a3, 0x0ea50, 0x06b58,
            0x055c0, 0x0ab60, 0x096d5, 0x092e0, 0x0c960, 0x0d954, 0x0d4a0, 0x0da50, 0x07552, 0x056a0, 0x0abb7, 0x025d0,
            0x092d0, 0x0cab5, 0x0a950, 0x0b4a0, 0x0baa4, 0x0ad50, 0x055d9, 0x04ba0, 0x0a5b0, 0x15176, 0x052b0, 0x0a930,
            0x07954, 0x06aa0, 0x0ad50, 0x05b52, 0x04b60, 0x0a6e6, 0x0a4e0, 0x0d260, 0x0ea65, 0x0d530, 0x05aa0, 0x076a3,
            0x096d0, 0x04bd7, 0x04ad0, 0x0a4d0, 0x1d0b6, 0x0d250, 0x0d520, 0x0dd45, 0x0b5a0, 0x056d0, 0x055b2, 0x049b0,
            0x0a577, 0x0a4b0, 0x0aa50, 0x1b255, 0x06d20, 0x0ada0, 0x14b63
    };

    private static final String[] GAN = new String[] {
            "甲", "乙", "丙", "丁", "戊", "己", "庚", "辛", "壬", "癸"
    };

    private static final String[] ZHI = new String[] {
            "子", "丑", "寅", "卯", "辰", "巳", "午", "未", "申", "酉", "戌", "亥"
    };

    private static final String[] WEEK = new String[] {
            "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"
    };

    long[] sTermInfo = new long[] {
            0, 21208, 42467, 63836, 85337, 107014, 128867, 150921, 173149, 195551, 218072, 240693, 263343, 285989,
            308563, 331033, 353350, 375494, 397447, 419210, 440795, 462224, 483532, 504758
    };

    private int year;

    private int month;

    private int day;

    private boolean leap;

    final static String chineseNumber[] = {
            "正", "二", "三", "四", "五", "六", "七", "八", "九", "十", "十一", "腊"
    };

    final static String chineseNumber1[] = {
            "一", "二", "三", "四", "五", "六", "七", "八", "九", "十", "十一", "十二"
    };

    private static final SimpleDateFormat BASE_DATE_FORMAT = new SimpleDateFormat("yyyy年MM月dd日HH时");

    private static final String BASE_DATE = "1900年1月31日0时";

    public Birth(Calendar cal) throws ParseException {
        Date baseDate = BASE_DATE_FORMAT.parse(BASE_DATE);
        int yearCyl, monCyl, dayCyl;
        int leapMonth = 0;

        // 求出和1900年1月31日相差的天数
        int offset = (int) ((cal.getTime().getTime() - baseDate.getTime()) / 86400000L);
        //        dayCyl = offset + 40;
        monCyl = 14;

        // 用offset减去每农历年的天数
        // 计算当天是农历第几天
        // i最终结果是农历的年份
        // offset是当年的第几天
        int iYear;
        int daysOfYear = 0;
        for (iYear = 1900; iYear < 2050 && offset > 0; iYear++) {
            daysOfYear = lunarDaysByYear(iYear);
            offset -= daysOfYear;
            monCyl += 12;
        }
        if (offset < 0) {
            offset += daysOfYear;
            iYear--;
            monCyl -= 12;
        }
        // 农历年份
        year = iYear;
        //        yearCyl = iYear - 1864;
        leapMonth = leapMonth(iYear); // 闰哪个月,1-12
        leap = false;
        // 用当年的天数offset,逐个减去每月（农历）的天数，求出当天是本月的第几天
        int iMonth, daysOfMonth = 0;
        for (iMonth = 1; iMonth < 13 && offset > 0; iMonth++) {
            // 闰月
            if (leapMonth > 0 && iMonth == (leapMonth + 1) && !leap) {
                --iMonth;
                leap = true;
                daysOfMonth = leapDays(year);
            } else {
                daysOfMonth = monthDays(year, iMonth);
            }

            offset -= daysOfMonth;
            // 解除闰月
            if (leap && iMonth == (leapMonth + 1)) {
                leap = false;
            }
            if (!leap) {
                monCyl++;
            }
        }
        // offset为0时，并且刚才计算的月份是闰月，要校正
        if (offset == 0 && leapMonth > 0 && iMonth == leapMonth + 1) {
            if (leap) {
                leap = false;
            } else {
                leap = true;
                --iMonth;
                --monCyl;
            }
        }
        // offset小于0时，也要校正
        if (offset < 0) {
            offset += daysOfMonth;
            --iMonth;
            --monCyl;
        }
        month = iMonth;
        day = offset + 1;
    }

    /**
     * 返回农历 y年的总天数
     *
     * @param y
     */
    private int lunarDaysByYear(int y) {
        int i, sum = 348;
        for (i = 0x8000; i > 0x8; i >>= 1) {
            sum += (lunarInfo[y - 1900] & i) > 0 ? 1 : 0;
        }
        return (sum + leapDays(y));
    }

    /**
     * 返回农历日
     *
     * @param day
     * @return
     */
    private String getLunarDay(int day) {
        String[] chinese = {"初", "十", "廿", "卅"};
        int n = day % 10 == 0 ? 9 : day % 10 - 1;
        if (day > 30) {
            return "";
        }
        if (day == 10) {
            return "初十";
        }
        if (day == 20) {
            return "二十";
        }
        if (day == 30) {
            return "三十";
        }
        return chinese[day / 10] + chineseNumber1[n];
    }

    private String getLunarMonth() {
        return leap ? "闰" + chineseNumber[month - 1] + "月" : chineseNumber[month - 1] + "月";
    }

    private String getWeek(Calendar calendar) {
        return WEEK[calendar.get(Calendar.DAY_OF_WEEK) - 1];
    }

    private String animalsYear() {
        final String[] Animals = new String[] {
                "鼠", "牛", "虎", "兔", "龙", "蛇", "马", "羊", "猴", "鸡", "狗", "猪"
        };
        return Animals[(year - 4) % 12] + "年";
    }

    /**
     * 返回农历 y年闰月的天数
     *
     * @param y
     * @return
     */
    private int leapDays(int y) {
        if (leapMonth(y) > 0) {
            long day = lunarInfo[y - 1900] & 0x10000;
            return day > 0 ? 30 : 29;
        } else {
            return 0;
        }
    }

    /**
     * 返回农历 y年闰哪个月 1-12 , 没闰返回 0
     *
     * @param y
     * @return
     */
    private int leapMonth(int y) {
        return (lunarInfo[y - 1900] & 0xf);
    }

    /**
     * 返回农历 y年m月的总天数
     *
     * @param y
     * @param m
     * @return
     */
    private int monthDays(int y, int m) {
        return ((lunarInfo[y - 1900] & (0x10000 >> m)) > 0 ? 30 : 29);
    }

    // ===== 某年的第n个节气为几日(从0小寒起算) Date.UTC(1900, 0, 6, 2, 5)

    /**
     * 正确的立春时间应该是以小时来进行计算的
     *
     * @param y
     * @param n
     * @return
     */
    private int spring(int y, int n) {
        long times = 31556925974L * (y - 1900) + sTermInfo[n] * 60000L + (long) 0.7 * (y - 1900);
        Date offDate = new Date(times - 2208549300000L);
        // 1、取得本地时间：
        Calendar cal = Calendar.getInstance();
        cal.setTime(offDate);
        // 2、取得时间偏移量：
        int zoneOffset = cal.get(Calendar.ZONE_OFFSET);
        // 3、取得夏令时差：
        int dstOffset = cal.get(Calendar.DST_OFFSET);
        // 4、从本地时间里扣除这些差量，即可以取得UTC时间：
        cal.add(Calendar.MILLISECOND, -(zoneOffset + dstOffset));
        // 之后调用cal.get(int x)或cal.getTimeInMillis()方法所取得的时间即是UTC标准时间。
        return (cal.get(Calendar.DATE));
    }

    /**
     * 传入 offset 返回干支, 0=甲子
     *
     * @param num
     * @return
     */
    private String cyclical(int num) {
        return (GAN[num % 10] + ZHI[num % 12]);
    }

    /**
     * 计算年柱
     *
     * @param calendar
     * @return
     */
    private String surnameYear(Calendar calendar) {
        int y = calendar.get(Calendar.YEAR);
        int m = calendar.get(Calendar.MONTH);
        int i = calendar.get(Calendar.DATE) - 1;
        String surnameYear = null;
        // 年柱 1900年立春后为庚子年(60进制36)
        if (m < 2) {
            surnameYear = cyclical(y - 1900 + 36 - 1);
        } else {
            surnameYear = cyclical(y - 1900 + 36);
        }
        int term2 = spring(y, 2); // 立春日期
        // 月柱 1900年1月小寒以前为 丙子月(60进制12)
        // 依节气调整二月分的年柱, 以立春为界
        if (m == 1 && (i + 1) >= term2) {
            surnameYear = cyclical(y - 1900 + 36);
        }
        return surnameYear + "年";
    }

    /**
     * 计算月柱
     *
     * @param calendar
     * @return
     */
    private String surnameMonth(Calendar calendar) {
        int y = calendar.get(Calendar.YEAR);
        int m = calendar.get(Calendar.MONTH);
        int i = calendar.get(Calendar.DATE) - 1;
        // 依节气月柱, 以「节」为界
        int firstNode = spring(y, m * 2); // 返回当月「节」为几日开始
        String surnameMonth = cyclical((y - 1900) * 12 + m + 12);
        if ((i + 1) >= firstNode) {
            surnameMonth = cyclical((y - 1900) * 12 + m + 13);
        }
        return surnameMonth + "月";
    }

    private String surnameDay(Calendar calendar) {
        int y = calendar.get(Calendar.YEAR);
        int m = calendar.get(Calendar.MONTH);
        int i = calendar.get(Calendar.DATE) - 1;
        int dayCyclical = jlday(y, m);
        String surnameDay = cyclical(dayCyclical + i);
        return surnameDay + "日";
    }

    /**
     * 计算时柱
     *
     * @param calendar
     */
    public String surnameHour(Calendar calendar) {
        String surnameDay = surnameDay(calendar);
        // 时柱
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        String surnameHour = GAN[hourG(surnameDay.substring(0, 1), hour)] + ZHI[hourZ(hour)];
        return surnameHour + "时";
    }

    /**
     * 根据 日干 推算 时柱 根据提供的推算图来计算
     *
     * @param dG
     * @param hour
     * @return
     */
    private int hourG(String dG, int hour) {
        int ind = 1;
        for (String s : GAN) {
            if (s.equals(dG)) {
                break;
            }
            ind++;
        }
        ind = ind % 5; // 五个为一周期
        int hourind = hourZ(hour);
        hourind = hourind + (ind - 1) * 2;
        return hourind < 0 ? hourind + 10 : hourind % 10;
    }

    /**
     * 返回 小时对应的 支的索引
     *
     * @param hour
     * @return
     */
    private int hourZ(int hour) {
        if (hour >= 23 || hour < 1) {
            return 0;
        } else if (hour >= 1 && hour < 3) {
            return 1;
        } else if (hour >= 3 && hour < 5) {
            return 2;
        } else if (hour >= 5 && hour < 7) {
            return 3;
        } else if (hour >= 7 && hour < 9) {
            return 4;
        } else if (hour >= 9 && hour < 11) {
            return 5;
        } else if (hour >= 11 && hour < 13) {
            return 6;
        } else if (hour >= 13 && hour < 15) {
            return 7;
        } else if (hour >= 15 && hour < 17) {
            return 8;
        } else if (hour >= 17 && hour < 19) {
            return 9;
        } else if (hour >= 19 && hour < 21) {
            return 10;
        } else if (hour >= 21 && hour < 23) {
            return 11;
        }
        return 0;
    }

    /**
     * 间隔天数
     *
     * @param y
     * @param m
     * @return
     */
    public int jlday(int y, int m) {
        ScriptEngineManager sem = new ScriptEngineManager(); /* script引擎管理 */
        ScriptEngine se = sem.getEngineByName("javascript"); /* script引擎 */
        try {
            se.eval(" var y =" + y + ",m=" + m + " ;"); /* 执行一段script */
            se.eval("function jlday(   ) { " + "return Date.UTC(y,m,1,0,0,0,0)/86400000+25567+10;" + "}"); /* 添加一个方法 */
            Invocable invocableEngine = (Invocable) se;
            Object callbackvalue = invocableEngine.invokeFunction("jlday"); /* 调用方法中的函数 */
            if (callbackvalue != null) {
                double numdb = Double.valueOf(callbackvalue.toString());
                return (int) numdb;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static void main(String[] args) throws ParseException {
        int year = 1994;
        int month = 1;
        int day = 9;
        int hour = 11;
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month - 1, day, hour, 0);

        Birth birth = new Birth(calendar);
        String weekDay = birth.getWeek(calendar);//计算输出星期几
        Date date = calendar.getTime();
        String dayTime = BASE_DATE_FORMAT.format(date);//输出阳历日期

        String surnameYear = birth.surnameYear(calendar);
        String surnameMonth = birth.surnameMonth(calendar);
        String surnameDay = birth.surnameDay(calendar);
        String surnameHour = birth.surnameHour(calendar);
        System.out.println("阳历：" + dayTime + " " + weekDay);
        System.out.println("农历：" + surnameYear + "(" + birth.animalsYear() + ")" + birth.getLunarMonth() + birth
                .getLunarDay(birth.day));
        System.out.println("八字：" + surnameYear + surnameMonth + surnameDay + surnameHour);
    }
}