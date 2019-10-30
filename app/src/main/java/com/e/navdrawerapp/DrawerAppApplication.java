package com.e.navdrawerapp;

import android.app.Application;

import java.util.ArrayList;
import java.util.List;

public class DrawerAppApplication extends Application {

    public static List<Student> studentList = new ArrayList<>();

    private static DrawerAppApplication INSTANCE;

    public static DrawerAppApplication getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new DrawerAppApplication();
        }
        return INSTANCE;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        populateList();

    }

    private void populateList() {
        studentList.add(new Student("Adrian Lipici", "Politehnica"));
        studentList.add(new Student("Iulian Ganea", "Politehnica"));
        studentList.add(new Student("Cristian Popescu", "Politehnica"));
        studentList.add(new Student("Asfasf agda", "Politehnica"));
        studentList.add(new Student("afsadgb asfasg", "Politehnica"));
        studentList.add(new Student("asgsga", "Politehnica"));
        studentList.add(new Student("asgdsbhfdh", "Politehnica"));
        studentList.add(new Student("asfdsbfdbn", "Politehnica"));
        studentList.add(new Student("asgdsbhfdbn", "Politehnica"));
        studentList.add(new Student("avfbgvn", "Politehnica"));
        studentList.add(new Student("fbbsbnsdb", "Politehnica"));
        studentList.add(new Student("sdbgshtjy", "Politehnica"));
        studentList.add(new Student("bgjyjtdvs", "Politehnica"));
        studentList.add(new Student("isijkvndnb", "Politehnica"));
        studentList.add(new Student("Adrian Lipici", "Politehnica"));
        studentList.add(new Student("Iulian Ganea", "Politehnica"));
        studentList.add(new Student("Cristian Popescu", "Politehnica"));
        studentList.add(new Student("Asfasf agda", "Politehnica"));
        studentList.add(new Student("afsadgb asfasg", "Politehnica"));
        studentList.add(new Student("asgsga", "Politehnica"));
        studentList.add(new Student("asgdsbhfdh", "Politehnica"));
        studentList.add(new Student("asfdsbfdbn", "Politehnica"));
        studentList.add(new Student("asgdsbhfdbn", "Politehnica"));
        studentList.add(new Student("avfbgvn", "Politehnica"));
        studentList.add(new Student("fbbsbnsdb", "Politehnica"));
        studentList.add(new Student("sdbgshtjy", "Politehnica"));
        studentList.add(new Student("bgjyjtdvs", "Politehnica"));
        studentList.add(new Student("isijkvndnb", "Politehnica"));
        studentList.add(new Student("Adrian Lipici", "Politehnica"));
        studentList.add(new Student("Iulian Ganea", "Politehnica"));
        studentList.add(new Student("Cristian Popescu", "Politehnica"));
        studentList.add(new Student("Asfasf agda", "Politehnica"));
        studentList.add(new Student("afsadgb asfasg", "Politehnica"));
        studentList.add(new Student("asgsga", "Politehnica"));
        studentList.add(new Student("asgdsbhfdh", "Politehnica"));
        studentList.add(new Student("asfdsbfdbn", "Politehnica"));
        studentList.add(new Student("asgdsbhfdbn", "Politehnica"));
        studentList.add(new Student("avfbgvn", "Politehnica"));
        studentList.add(new Student("fbbsbnsdb", "Politehnica"));
        studentList.add(new Student("sdbgshtjy", "Politehnica"));
        studentList.add(new Student("bgjyjtdvs", "Politehnica"));
        studentList.add(new Student("isijkvndnb", "Politehnica"));
    }
}
