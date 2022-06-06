package me.char321.nexcavate.research.progress;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import me.char321.nexcavate.Nexcavate;
import me.char321.nexcavate.research.Research;
import org.bukkit.NamespacedKey;

import java.lang.reflect.Type;

public class ResearchAdapter implements JsonSerializer<Research>, JsonDeserializer<Research> {
    @Override
    public Research deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        return Nexcavate.instance().getRegistry().getResearch(NamespacedKey.fromString(json.getAsString()));
    }

    @Override
    public JsonElement serialize(Research src, Type typeOfSrc, JsonSerializationContext context) {
        return new JsonPrimitive(src.getKey().toString());
    }
}
