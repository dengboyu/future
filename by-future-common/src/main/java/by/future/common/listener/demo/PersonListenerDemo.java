package by.future.common.listener.demo;

/**
 * 设计一个事件源被别人监听
 * 一个人吃和跑被别人监听
 *
 * @author by@Deng
 * @create 2017-10-13 07:43
 */
public class PersonListenerDemo{
    public static void main(String[] args) {
        Person person = new Person();
        person.registerListener(new PersonListener() {
            @Override
            public void doeat(Event e) {
                Person p = e.getPerson();
                System.out.println(p+"好好吃，天天向上");
            }

            @Override
            public void dorun(Event e) {
                Person p = e.getPerson();
                System.out.println(p+"跑的瘦下来了");
            }
        });
        person.eat();
        person.run();
    }
}

/**
 * 设计一个事件源
 * @author by@Deng
 * @date 2017/10/13 上午7:50
 */
class Person {

    private PersonListener listener;

    public void eat(){
        if(listener!=null){
            listener.doeat(new Event(this));
        }
    }


    public void run(){
        if(listener!=null){
            listener.dorun(new Event(this));
        }
    }

    public void registerListener(PersonListener listener){
        this.listener = listener;
    }
}

/**
 * 监听器
 * @author by@Deng
 * @date 2017/10/13 上午7:45
 */
interface PersonListener{

    void doeat(Event event);

    void dorun(Event event);

}

/**
 * 事件对象，封装事件源
 * @author by@Deng
 * @date 2017/10/13 上午7:54
 */
class Event{
    private Person person;

    public Event(){}

    public Event(Person person) {
        this.person = person;
    }

    public Person getPerson() {
        return person;
    }

    public void getPerson(Person person) {
        this.person = person;
    }
}
