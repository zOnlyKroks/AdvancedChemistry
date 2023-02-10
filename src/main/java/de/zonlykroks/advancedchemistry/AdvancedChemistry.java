package de.zonlykroks.advancedchemistry;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import de.zonlykroks.advancedchemistry.config.AdvancedChemistryConfigModel;
import de.zonlykroks.advancedchemistry.config.AdvancedChemistryWrapper;
import de.zonlykroks.advancedchemistry.items.ElementItem;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.io.*;
import java.net.URL;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class AdvancedChemistry implements ModInitializer {

    public static final AdvancedChemistryWrapper CONFIG = AdvancedChemistryWrapper.createAndLoad();

    public static final Map<Integer,ElementItem> elementItemMap = new HashMap<>();
    public static final String MODID = "advancedchemistry";

    public static final ItemGroup customItemGroup = FabricItemGroupBuilder.create(new Identifier(MODID,"tab")).build();

    public static URL elementURL;
    private static File elementFile;
    private static File configDir;

    @Override
    public void onInitialize() {
        try {
            elementURL = new URL("https://raw.githubusercontent.com/Bowserinator/Periodic-Table-JSON/master/PeriodicTableJSON.json");
            configDir = new File(FabricLoader.getInstance().getConfigDir() + "/AdvancedChemistry/");
            elementFile = new File(configDir + "/elements.json");

            downloadFile(elementFile);
            registerItems();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void downloadFile(File file) throws Exception {
        if(!configDir.exists())
            configDir.mkdirs();

        if(!file.exists())
            file.createNewFile();

        InputStream input = elementURL.openStream();
        FileOutputStream outputStream = new FileOutputStream(file);

        byte[] buffer = new byte[4096];

        int n = 0;

        while(-1 != (n = input.read(buffer))) {
            outputStream.write(buffer,0,n);
        }

        input.close();
        outputStream.close();
    }

    public void registerItems() throws Exception {
        JsonObject object = JsonParser.parseReader(new FileReader(elementFile)).getAsJsonObject();

        JsonArray array = object.getAsJsonArray("elements");

        for(JsonElement jsonElement : array) {
            JsonObject subObject = jsonElement.getAsJsonObject();

            String name = subObject.get("name").getAsString();
            int elementNumber = subObject.get("number").getAsInt();

            ElementItem elementItem = new ElementItem(name,elementNumber);
            Registry.register(Registry.ITEM,new Identifier(AdvancedChemistry.MODID,name.toLowerCase()),elementItem);
            elementItemMap.putIfAbsent(elementNumber,elementItem);
        }
    }
}
