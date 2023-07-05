package cn.starry.hub.database;

import cn.starry.hub.Main;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bukkit.entity.Player;

import java.util.UUID;

import static com.mongodb.client.model.Filters.eq;

public class MongoDB {

    private MongoClient mongoClient;
    private MongoDatabase database;
    private MongoCollection<Document> playerCollection;

    public MongoDB() {
        mongoClient = new MongoClient(Main.getPlugin(Main.class).getConfig().getString("database.host"), Main.getPlugin(Main.class).getConfig().getInt("database.port"));
        database = mongoClient.getDatabase(Main.getPlugin(Main.class).getConfig().getString("database.database"));
        playerCollection = database.getCollection("settings");
    }

    public void savePlayerData(UUID playerUUID, String player, String show,String receive,String time) {
        Document playerData = new Document("uuid", playerUUID.toString())
                .append("name", player).append("SHOW", show).append("RECEIVE", receive).append("TIME", time);
        if (playerCollection.find(new Document("uuid", playerUUID.toString())).first() != null) {
            return;
        }
        playerCollection.insertOne(playerData);
    }

    public void updatePlayerData(UUID playerUUID, String type, String data) {
        if (playerCollection.find(new Document("uuid", playerUUID.toString())).first() == null) {
            return;
        }
        Bson filter = new Document("uuid", playerUUID.toString());
        Bson updateStatus = new Document("$set", new Document(type, data));
        playerCollection.updateOne(filter, updateStatus);
    }

    public String getPlayerData(UUID playerUUID, String type) {
        Document playerData = playerCollection.find(eq("uuid", playerUUID.toString())).first();
        if (playerData != null) {
            return playerData.getString(type);
        }
        return null;
    }

}
