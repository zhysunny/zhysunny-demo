package com.zhysunny.java.data;

import com.zhysunny.common.date.DateUtils;
import com.zhysunny.common.random.RandomUtils;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 模拟学生数据
 * @author 章云
 * @date 2019/11/12 22:37
 */
public class CreateStudentData {

    private static int count = 5000;
    private static List<String> sid = new ArrayList<>(count);
    private static List<String> names = new ArrayList<>(count);
    private static String[] courses = { "语文", "数学", "英语", "政治", "历史", "地理", "物理", "化学", "生物" };
    private static List<String> cid = new ArrayList<>(courses.length);
    private static File temp;

    public static void main(String[] args) throws ParseException {
        temp = new File("temp");
        if (!temp.exists()) {
            temp.mkdirs();
        }
        createStudent();
        createCourseAndTeacher();
        createScore();
    }

    private static void createScore() {
        // sid string,cid string,score int
        Random random = new Random();
        List<String> datas = new ArrayList<>();
        for (String s : sid) {
            for (String c : cid) {
                int score = random.nextInt(100);
                datas.add(s + "\t" + c + "\t" + score + "\n");
            }
        }
        write(datas, new File(temp, "score.txt"));
    }

    private static void createCourseAndTeacher() {
        // cid string,cname string,tid string
        // tid string,tname string
        int i = 1;
        List<String> dataCourse = new ArrayList<>();
        List<String> dataTeacher = new ArrayList<>();
        for (String course : courses) {
            // id
            StringBuilder id = new StringBuilder(2);
            id.append(i++);
            while (id.length() < 2) {
                id.insert(0, "0");
            }
            cid.add(id.toString());
            // tname
            String name = RandomUtils.smallCode(5);
            while (names.contains(name)) {
                name = RandomUtils.smallCode(5);
            }
            names.add(name);
            dataCourse.add(id + "\t" + course + "\t" + id + "\n");
            dataTeacher.add(id + "\t" + name + "\n");
        }
        write(dataCourse, new File(temp, "course.txt"));
        write(dataTeacher, new File(temp, "teacher.txt"));
    }

    private static void createStudent() throws ParseException {
        // id string,name string,birth string,gender int
        String start = "1990-01-01";
        Random random = new Random();
        List<String> datas = new ArrayList<>();
        for (int i = 1; i <= count; i++) {
            // id
            StringBuilder id = new StringBuilder(5);
            id.append(i);
            while (id.length() < 5) {
                id.insert(0, "0");
            }
            sid.add(id.toString());
            // name
            String name = RandomUtils.smallCode(5);
            while (names.contains(name)) {
                name = RandomUtils.smallCode(5);
            }
            names.add(name);
            // birth 模拟1990-01-01到2000-01-01
            int num = random.nextInt(10 * 365);
            String birth = DateUtils.getDay("yyyy-MM-dd", start, "yyyy-MM-dd", num);
            int gender = random.nextInt(2);
            datas.add(id + "\t" + name + "\t" + birth + "\t" + gender + "\n");
        }
        write(datas, new File(temp, "student.txt"));
    }

    private static void write(List<String> datas, File file) {
        try (FileOutputStream fos = new FileOutputStream(file);) {
            datas.forEach(str -> {
                try {
                    fos.write(str.getBytes());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
