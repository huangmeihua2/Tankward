package BuildModel;

public class Person {
    int id;
    int age;
    double weight;
    int score;
    String name;
    Location location;
    private Person() {
    }
    public static class PersonBuilder {
        Person person = new Person();

        public PersonBuilder basicInfo(int id, int age, String name) {
            person.age = age;
            person.id = id;
            person.name = name;
            return this;
        }
        public PersonBuilder weight(double weight) {
            person.weight = weight;
            return this;
        }

        public PersonBuilder score(int score) {
            person.score = score;
            return this;
        }
        public PersonBuilder builderLocation(Location location) {
            person.location = location;
            return this;
        }
        public Person build() {
            return person;
        }
    }
}
