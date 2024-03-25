package de.zonlykroks.advancedchemistry.element;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import de.zonlykroks.advancedchemistry.AdvancedChemistry;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;

public class Element extends Item {
    @JsonProperty(" name")
    public String name;

    @JsonProperty("atomicNumber")
    public String atomicNumber;
    @JsonProperty(" symbol")
    public String symbol;
    @JsonProperty(" atomicMass")
    public String atomicMass;

    @JsonProperty(" cpkHexColor")
    public String cpkHexColor;

    @JsonProperty(" electronicConfiguration")
    public String electronicConfiguration;

    @JsonProperty(" electronegativity")
    public float electronegativity;

    @JsonProperty(" atomicRadius")
    public float atomicRadius;

    @JsonProperty(" ionRadius")
    public String ionRadius;

    @JsonProperty(" vanDelWaalsRadius")
    public float vanDelWaalsRadius;

    @JsonProperty(" ionizationEnergy")
    public float ionizationEnergy;

    @JsonProperty(" electronAffinity")
    public float electronAffinity;

    @JsonProperty(" oxidationStates")
    public String oxidationStates;

    @JsonProperty(" standardState")
    public String standardState;

    @JsonProperty(" bondingType")
    public String bondingType;

    @JsonProperty(" meltingPoint")
    public float meltingPoint;

    @JsonProperty(" boilingPoint")
    public float boilingPoint;

    @JsonProperty("density")
    public float density;

    @JsonProperty(" groupBlock")
    public String groupBlock;

    @JsonProperty(" yearDiscovered")
    public String yearDiscovered;

    @JsonIgnore
    public boolean explodesOnWaterContact;

    @JsonIgnore
    public boolean radioactive;

    public Element() {
        super(new FabricItemSettings());
    }

    public void convertAndRegisterToItem() {
        Identifier key = new Identifier("advancedchemistry", this.name.toLowerCase().replace(" ", ""));

        Element registeredElement = Registry.register(
                Registries.ITEM,
                key,
                this);

        AdvancedChemistry.registryNameElementToElement.put(key,registeredElement);
    }
}
