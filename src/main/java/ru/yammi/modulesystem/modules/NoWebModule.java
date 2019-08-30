package ru.yammi.modulesystem.modules;

import ru.yammi.modulesystem.Module;
import ru.yammi.modulesystem.ModuleCategory;
import ru.yammi.eventsystem.events.UpdateEvent;
import ru.yammi.eventsystem.EventTarget;
import java.lang.reflect.Field;
import net.minecraft.entity.Entity;
import ru.yammi.helpers.ReflectionHelper;

public class NoWebModule
        extends Module {

    public NoWebModule() {
        super("NoWeb", ModuleCategory.MOVEMENT);
    }

    @EventTarget
    public void onUpdate(UpdateEvent oTTDuKOxgEObFPc2) {
        if (this.getState()) {
            try {
                Field field = ReflectionHelper.getField(Entity.class, "isInWeb", "field_70134_J", "E");
                field.setBoolean(this.mc.player, false);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
    }
}
