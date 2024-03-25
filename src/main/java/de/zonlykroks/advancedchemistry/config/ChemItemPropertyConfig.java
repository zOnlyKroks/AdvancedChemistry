package de.zonlykroks.advancedchemistry.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import de.zonlykroks.advancedchemistry.AdvancedChemistry;
import de.zonlykroks.advancedchemistry.element.Element;
import de.zonlykroks.advancedchemistry.mixin.ItemPropertiesMixin;
import net.fabricmc.loader.impl.FabricLoaderImpl;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ChemItemPropertyConfig {

    public File rarityConfigFileJson;
    public String jsonFileContent;

    public void loadOrCreateConfig() {
        String configDir = FabricLoaderImpl.INSTANCE.getConfigDir() + "/advancedchemistry";

        File rarityConfigFileJson = new File(configDir + "/chem_rarity_table.json");

        if(!rarityConfigFileJson.exists()) {
            try {
                rarityConfigFileJson.createNewFile();

                this.rarityConfigFileJson = rarityConfigFileJson;

                fillEmptyConfigFile();
            }catch (Exception e) {
                e.printStackTrace();
            }
        }else {
            this.rarityConfigFileJson = rarityConfigFileJson;
        }
    }

    private void fillEmptyConfigFile() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonArray jsonArray = new JsonArray();

        AdvancedChemistry.registryNameElementToElement.forEach((identifier, element) -> {
            String name = identifier.getPath();

            JsonObject entry = new JsonObject();

            entry.addProperty("identifier", identifier.toString());
            entry.addProperty("maxCount", 16);
            entry.addProperty("maxDamage", 1);
            entry.addProperty("rarity", "COMMON");
            entry.addProperty("fireproof", false);
            entry.addProperty("explodesOnWaterContact", false);
            entry.addProperty("radioactive", false);

            jsonArray.add(entry);
        });

        try (FileWriter file = new FileWriter(rarityConfigFileJson)) {
            file.write(gson.toJson(jsonArray));
            file.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadAndChangeItemProperties() {
        try{
            String jsonString = new String(Files.readAllBytes(rarityConfigFileJson.toPath()));
            JSONArray jsonArray = new JSONArray(jsonString);

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject object = jsonArray.getJSONObject(i);

                String identifierString = object.getString("identifier");
                Identifier identifier = new Identifier(identifierString);

                Element element = AdvancedChemistry.registryNameElementToElement.get(identifier);

                int maxCount = object.getInt("maxCount");
                int maxDamage = object.getInt("maxDamage");
                Rarity rarity = Rarity.valueOf(object.getString("rarity"));
                boolean fireproof = object.getBoolean("fireproof");
                boolean explodesOnWaterContact = object.getBoolean("explodesOnWaterContact");
                boolean radioactive = object.getBoolean("radioactive");

                ((ItemPropertiesMixin) element).setItemMaxCount(maxCount);
                ((ItemPropertiesMixin) element).setItemMaxDamage(maxDamage);
                ((ItemPropertiesMixin) element).setItemRarity(rarity);
                ((ItemPropertiesMixin) element).setItemFireproof(fireproof);
                element.explodesOnWaterContact = explodesOnWaterContact;
                element.radioactive = radioactive;
            }

        }catch (Exception e) {
            e.printStackTrace();
        }
    }

}
