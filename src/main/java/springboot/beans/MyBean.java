package springboot.beans;
import springboot.util.BeanInitLogger;


public class MyBean extends BeanInitLogger {

    private int age =30;
    private String name = "issac";

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
