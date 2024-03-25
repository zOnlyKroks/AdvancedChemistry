package de.zonlykroks.advancedchemistry.element;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import de.zonlykroks.advancedchemistry.AdvancedChemistry;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.block.Block;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class Element extends Item {
    @JsonProperty(" name")
    public String name;

    @JsonProperty("atomicNumber")
    public int atomicNumber;
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

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(Text.of("Atomic Number: " + this.atomicNumber));
        tooltip.add(Text.of("Name: " + this.name));
        tooltip.add(Text.of("Symbol: " + this.symbol));
        tooltip.add(Text.of("Group Block: " + this.groupBlock));
        tooltip.add(Text.of("Year Discovered: " + this.yearDiscovered));

        stack.getOrCreateNbt().putString("Atomic Number", "Atomic Number: " + this.atomicNumber);
        stack.getOrCreateNbt().putString("Name", "Name: " + this.name);
        stack.getOrCreateNbt().putString("Symbol", "Symbol: " + this.symbol);
        stack.getOrCreateNbt().putString("Group Block", "Group Block: " + this.groupBlock);
        stack.getOrCreateNbt().putString("Year Discovered", "Year Discovered: " + this.yearDiscovered);
        stack.getOrCreateNbt().putString("Mass", "Atomic Mass: " + this.atomicMass);
        stack.getOrCreateNbt().putString("CPK Hex Color", "CPK Hex Color: " + this.cpkHexColor);
        stack.getOrCreateNbt().putString("Electronic Configuration", "Electronic Configuration: " + this.electronicConfiguration);
        stack.getOrCreateNbt().putString("Electronegativity", "Electronegativity: " + this.electronegativity);
        stack.getOrCreateNbt().putString("Atomic Radius", "Atomic Radius: " + this.atomicRadius);
        stack.getOrCreateNbt().putString("Ion Radius", "Ion Radius: " + this.ionRadius);
        stack.getOrCreateNbt().putString("Van Del Waals Radius", "Van Del Waals Radius: " + this.vanDelWaalsRadius);
        stack.getOrCreateNbt().putString("Ionization Energy", "Ionization Energy: " + this.ionizationEnergy);
        stack.getOrCreateNbt().putString("Electron Affinity", "Electron Affinity: " + this.electronAffinity);
        stack.getOrCreateNbt().putString("Oxidation States", "Oxidation States: " + this.oxidationStates);
        stack.getOrCreateNbt().putString("Standard State", "Standard State: " + this.standardState);
        stack.getOrCreateNbt().putString("Bonding Type", "Bonding Type: " + this.bondingType);
        stack.getOrCreateNbt().putString("Melting Point", "Melting Point: " + this.meltingPoint);
        stack.getOrCreateNbt().putString("Boiling Point", "Boiling Point: " + this.boilingPoint);
        stack.getOrCreateNbt().putString("Density", "Density: " + this.density);
    }
}
