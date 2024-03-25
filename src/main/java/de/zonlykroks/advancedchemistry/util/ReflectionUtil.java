package de.zonlykroks.advancedchemistry.util;

import de.zonlykroks.advancedchemistry.element.Element;
import net.minecraft.block.Block;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ReflectionUtil {

    public static List<Block> collectBlockFieldsFromClass(Class<?> c) {
        List<Block> list  = new ArrayList<>();
        Field[] fields = c.getDeclaredFields();
        for (Field field : fields) {
            try {
                if (field.getType().equals(Block.class) && Modifier.isStatic(field.getModifiers())) {
                    list.add( (Block) field.get(null));
                }
            }
            catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        return list;
    }

}
