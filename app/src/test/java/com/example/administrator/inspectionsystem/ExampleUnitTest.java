package com.example.administrator.inspectionsystem;

import com.example.administrator.inspectionsystem.inspectionsystem.bean.Register;
import com.example.administrator.inspectionsystem.inspectionsystem.utils.DataUtil;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        Register register = new Register();
        register.setId(1000);
        register.setPressure("30度");
        register.setTemperature("40度高温");
        DataUtil.getListByExample(register,null);
//        System.out.println(Integer.class.isAssignableFrom(Number.class));
//        System.out.println(Number.class.isAssignableFrom(Integer.class));
    }
}