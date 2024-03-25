package de.zonlykroks.advancedchemistry.config;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import de.zonlykroks.advancedchemistry.AdvancedChemistry;
import de.zonlykroks.advancedchemistry.element.Element;
import de.zonlykroks.advancedchemistry.util.CSV;
import de.zonlykroks.advancedchemistry.util.FileDownloader;
import net.fabricmc.loader.impl.FabricLoaderImpl;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class SimpleChemConfig {

    public File configFileCSV, configFileJson;
    public String jsonFileContent;

    public void loadOrCreateConfig() {
        String configDir = FabricLoaderImpl.INSTANCE.getConfigDir() + "/advancedchemistry";
        File configDirFile = new File(configDir);

        if(!configDirFile.exists()) {
            configDirFile.mkdirs();
        }

        File configFile = new File(configDir + "/chem_table.csv");

        if(!configFile.exists()) {
            try {
                FileDownloader.downloadAndWrite(
                        "https://raw.githubusercontent.com/andrejewski/periodic-table/master/data.csv",
                        configFile);
                this.configFileCSV = configFile;
            }catch (IOException e) {
                e.printStackTrace();
            }
        }else {
            this.configFileCSV = configFile;
        }
    }

    public void convertCSVToJSON(File csvFile) {
        try {
            File jsonFile = new File(csvFile.getPath().replace(".csv", ".json"));
            if(!jsonFile.exists()) {
                jsonFile.createNewFile();
            }else {
                this.configFileJson = jsonFile;
                return;
            }

            List<Map<String, String>> list = getMaps(csvFile);
            ObjectMapper mapper = new ObjectMapper();
            mapper.enable(SerializationFeature.INDENT_OUTPUT);
            mapper.writeValue(new FileWriter(jsonFile), list);

            this.configFileJson = jsonFile;
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void convertJSONToString() {
       try {
           BufferedReader reader = new BufferedReader(new FileReader(this.configFileJson));
           StringBuilder stringBuilder = new StringBuilder();
           String line = null;
           String ls = System.lineSeparator();
           while ((line = reader.readLine()) != null) {
               stringBuilder.append(line);
               stringBuilder.append(ls);
           }
           // delete the last new line separator
           stringBuilder.deleteCharAt(stringBuilder.length() - 1);
           reader.close();

           this.jsonFileContent = stringBuilder.toString();
           return;
       }catch (Exception e) {
           e.printStackTrace();
       }
       this.jsonFileContent = "";
    }

    @NotNull
    private static List<Map<String, String>> getMaps(File csvFile) throws IOException {
        InputStream in = new FileInputStream(csvFile);

        CSV csv = new CSV(true, ',', in );
        List< String > fieldNames = null;
        if (csv.hasNext()) fieldNames = new ArrayList< >(csv.next());
        List <Map< String, String >> list = new ArrayList < > ();
        while (csv.hasNext()) {
            List < String > x = csv.next();
            Map < String, String > obj = new LinkedHashMap< >();
            for (int i = 0; i < fieldNames.size(); i++) {
                obj.put(fieldNames.get(i), x.get(i));
            }
            list.add(obj);
        }
        return list;
    }
}
