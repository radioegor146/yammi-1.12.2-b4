package ru.yammi;

import ru.yammi.modulesystem.modules.XRayBlockModelRenderer;
import ru.yammi.helpers.ReflectionHelper;
import ru.yammi.gui.ClickGUIScreen;
import ru.yammi.helpers.TrayHelper;
import ru.yammi.helpers.ConfigHelper;
import ru.yammi.modulesystem.modules.ClickGUIModule;
import ru.yammi.modulesystem.ModuleController;
import ru.yammi.modulesystem.ModuleManager;
import ru.yammi.eventsystem.EventBus;
import java.lang.invoke.MethodHandles;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockRendererDispatcher;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {

    private Logger logger = LogManager.getLogger(Main.class);
    private final static Main INSTANCE = new Main();

    public static Main getInstance() {
        return INSTANCE;
    }

    public int getDays() {
        return 1488;
    }

    public void main() throws Throwable {
        ReflectionHelper.lookup = MethodHandles.lookup();
        TrayHelper.displayTray();
        ModuleManager.loadModules();
        ReflectionHelper.hookField(ReflectionHelper.getField(Minecraft.class, "mcProfiler", "field_71424_I", "B"), Minecraft.getMinecraft(), new ProfilerHook());
        EventBus.register(new ModuleController());
        ((ClickGUIModule) ModuleManager.getModule(ClickGUIModule.class)).setClickGUIScreen(new ClickGUIScreen());
        BlockRendererDispatcher blockRendererDispatcher = Minecraft.getMinecraft().getBlockRendererDispatcher();
        ReflectionHelper.hookField(ReflectionHelper.getField(blockRendererDispatcher.getClass(), "blockModelRenderer", "field_175027_c", "b"), blockRendererDispatcher, new XRayBlockModelRenderer(Minecraft.getMinecraft().getBlockColors()));
        ConfigHelper.load();
    }

    public int getVersion() {
        return 4;
    }

    public void log(String string) {
        this.logger.info(String.valueOf(new StringBuilder().append("[Yammi] ").append(string)));
    }
}
