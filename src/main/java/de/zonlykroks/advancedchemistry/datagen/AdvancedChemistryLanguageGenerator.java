package de.zonlykroks.advancedchemistry.datagen;

import de.zonlykroks.advancedchemistry.AdvancedChemistry;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;

import java.nio.file.Path;

public class AdvancedChemistryLanguageGenerator extends FabricLanguageProvider {
    protected AdvancedChemistryLanguageGenerator(FabricDataOutput dataOutput) {
        super(dataOutput);
    }

    @Override
    public void generateTranslations(TranslationBuilder translationBuilder) {
        AdvancedChemistry.registryNameElementToElement.forEach((identifier, element) -> {
            translationBuilder.add(element,element.name.replace(" ", ""));
        });

        try {
            Path existingFilePath = dataOutput.getModContainer().findPath("assets/advancedchemistry/lang/en_us_manual.json").get();
            translationBuilder.add(existingFilePath);
        } catch (Exception e) {
            throw new RuntimeException("Failed to add existing language file!", e);
        }
    }
}
