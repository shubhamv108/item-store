package code.shubham;

import code.shubham.itemstore.Animal;
import code.shubham.itemstore.Person;
import code.shubham.itemstore.RedisItemDB;
import code.shubham.utils.redis.store.impl.RedisStoreImpl;

public class Main {
    public static void main(String[] args) {
        final var redisStore = new RedisStoreImpl();
        final var itemDB = new RedisItemDB(redisStore);

        final var animal1 = new Animal();
        animal1.setName("Animal1");
        final var animal2 = new Animal();
        animal2.setName("Animal2");
        final var animal3 = new Animal();
        animal3.setName("Animal3");

        final var person1 = new Person();
        person1.setName("Person1");
        final var person2 = new Person();
        person2.setName("Person2");
        final var person3 = new Person();
        person3.setName("Person3");

        itemDB.storeObject(animal1);
        itemDB.storeObject(animal2);
        itemDB.storeObject(animal3);
        itemDB.storeObject(person1);
        itemDB.storeObject(person2);
        itemDB.storeObject(person3);

        itemDB.listObjects("Animal").forEach(item -> System.out.println(item.getName()));
        System.out.println(itemDB.getObjectByName("Person1").getName());
    }
}
