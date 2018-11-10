package demo;

import net.sf.oval.ConstraintViolation;
import net.sf.oval.Validator;
import net.sf.oval.constraint.*;

import java.util.List;

/**
 * @Author: Sunshine
 * @Description:
 * @Date: Create in 2018/11/8 14:30
 */
public class OvalTest {

    @NotBlank(message = "姓名不能为空", profiles = "name")
    private String name;

    @NotBlank(message = "密码不能为空",profiles = "password")
    @MinLength(value = 6, message = "密码最小长度为6", profiles = "password")
    @MaxLength(value = 18, message = "密码最大长度为18", profiles = "password")
    private String password;

    @Max(value = 19,message = "年龄最大不能超过19", profiles = "age")
    private int age;

    public OvalTest(String name, String password, int age) {
        this.name = name;
        this.password = password;
        this.age = age;
    }

    public static void main(String[] args) {
        test1();
        test2();
    }

    private static void test1(){
        OvalTest ovalTest = new OvalTest("","123456", 20);
        Validator validator = new Validator();
        List<ConstraintViolation> ret = validator.validate(ovalTest);
        System.out.println(ret);
    }

    private static void test2(){
        OvalTest ovalTest = new OvalTest("hehehe","12345", 30);
        Validator validator = new Validator();
        String[] profiles = new String[]{"password", "age"};
        List<ConstraintViolation> ret = validator.validate(ovalTest, profiles);
        for (ConstraintViolation item: ret){
            System.out.println(item.getMessage());
        }
        System.out.println(ret);
    }
}
