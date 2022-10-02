package code.shubham.itemstore;

import code.shubham.utils.json.JsonUtils;
import code.shubham.utils.redis.store.RedisStore;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

public class RedisItemDB implements ItemDB {

    private static final Map<String, Class<? extends Item>> ITEM_CLASSES = new HashMap<>();

    static {
        ITEM_CLASSES.put(Animal.class.getSimpleName(), Animal.class);
        ITEM_CLASSES.put(Person.class.getSimpleName(), Person.class);
    }

    private final RedisStore redisStore;

    public RedisItemDB(final RedisStore redisStore) {
        this.redisStore = redisStore;
    }

    @Override
    public String storeObject(Item item) {
        item.setID(UUID.randomUUID().toString());
        var jsonMap = JsonUtils.convert(item, Map.class);
        jsonMap.put(Constants.KIND_KEY, item.getKind());
        this.redisStore.executeTransaction(Arrays.asList(
                transaction -> transaction.set(item.getID(), JsonUtils.convert(jsonMap)),
                transaction -> transaction.set(Constants.ITEM_NAME_KEY_PREFIX + item.getName(), item.getID()),
                transaction -> transaction.sadd(Constants.ITEMS_KIND_KEY_PREFIX + item.getKind(), item.getID())
        ));
        return item.getID();
    }

    @Override
    public Item getObjectByID(String id) {
        String json = this.redisStore.get(id);
        if (json == null)
            return null;
        var jsonMap = JsonUtils.convert(json, Map.class);
        String kind = (String) jsonMap.get(Constants.KIND_KEY);
        return JsonUtils.convert(json, ITEM_CLASSES.get(kind));
    }

    @Override
    public Item getObjectByName(String name) {
        String id = this.redisStore.get("Item:name:" + name);
        if (id == null)
            return null;
        return this.getObjectByID(id);
    }

    @Override
    public List<Item> listObjects(String kind) {
        return this.redisStore.getList("Items:kind:" + kind).
                stream().
                map(String::valueOf).
                map(this::getObjectByID).
                collect(Collectors.toList());
    }

    @Override
    public boolean deleteObject(String id) {
        Item item = this.getObjectByID(id);
        List<Object> result = this.redisStore.executeTransaction(Arrays.asList(
                transaction -> transaction.del(item.getID()),
                transaction -> transaction.del(Constants.ITEM_NAME_KEY_PREFIX + item.getName()),
                transaction -> transaction.del(Constants.ITEMS_KIND_KEY_PREFIX + item.getKind())
        ));
        return true;
    }
}
