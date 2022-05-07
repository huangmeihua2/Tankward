package BuildModel;

public class test {
    public static void main(String[] args) {
        Person person = new Person.PersonBuilder().basicInfo(011, 26, "黄")
                .score(98)
                .weight(123).build();
    }
}
