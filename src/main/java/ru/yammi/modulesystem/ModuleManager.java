package ru.yammi.modulesystem;

import ru.yammi.eventsystem.EventBus;
import java.util.ArrayList;
import java.util.List;
import ru.yammi.modulesystem.modules.AntiKnockbackModule;
import ru.yammi.modulesystem.modules.AutoSprintModule;
import ru.yammi.modulesystem.modules.ChestESPModule;
import ru.yammi.modulesystem.modules.ClickGUIModule;
import ru.yammi.modulesystem.modules.ESPModule;
import ru.yammi.modulesystem.modules.FastBreakModule;
import ru.yammi.modulesystem.modules.FastBridgeModule;
import ru.yammi.modulesystem.modules.FastLadderModule;
import ru.yammi.modulesystem.modules.FlyModule;
import ru.yammi.modulesystem.modules.FullbrightModule;
import ru.yammi.modulesystem.modules.GhostModule;
import ru.yammi.modulesystem.modules.HUDModule;
import ru.yammi.modulesystem.modules.InventoryMoveModule;
import ru.yammi.modulesystem.modules.KillauraModule;
import ru.yammi.modulesystem.modules.LongJumpModule;
import ru.yammi.modulesystem.modules.MultiJumpModule;
import ru.yammi.modulesystem.modules.NameTagsModule;
import ru.yammi.modulesystem.modules.NoFallModule;
import ru.yammi.modulesystem.modules.NoWebModule;
import ru.yammi.modulesystem.modules.NukerModule;
import ru.yammi.modulesystem.modules.SpeedHackModule;
import ru.yammi.modulesystem.modules.SpiderModule;
import ru.yammi.modulesystem.modules.StepModule;
import ru.yammi.modulesystem.modules.StrafeModule;
import ru.yammi.modulesystem.modules.TracersModule;
import ru.yammi.modulesystem.modules.TriggerBotModule;
import ru.yammi.modulesystem.modules.VClipModule;
import ru.yammi.modulesystem.modules.WallHackModule;
import ru.yammi.modulesystem.modules.XRayModule;

public class ModuleManager {

    private static List<Module> modules = new ArrayList();

    public static void disableModule(String moduleName) {
        Module module = ModuleManager.getModule(moduleName);
        if (module != null) {
            module.setState(false);
        }
    }

    public static Module getModule(Class moduleClass) {
        for (Module module : modules) {
            if (module.getClass() != moduleClass) {
                continue;
            }
            return module;
        }
        return null;
    }

    public static Module getModule(String moduleName) {
        for (Module module : modules) {
            if (!module.getName().equals(moduleName)) {
                continue;
            }
            return module;
        }
        return null;
    }

    public static List<Module> getModules() {
        return modules;
    }

    public static boolean isModuleEnabled(String moduleName) {
        Module module = ModuleManager.getModule(moduleName);
        if (module != null) {
            return module.getState();
        }
        return false;
    }

    public static boolean isModuleEnabled(Class moduleClass) {
        Module module = ModuleManager.getModule(moduleClass);
        if (module != null) {
            return module.getState();
        }
        return false;
    }
    
    private static void registerModule(Module module) {
        EventBus.register(module);
        modules.add(module);
    }

    public static void loadModules() {
        registerModule(new AntiKnockbackModule());
        registerModule(new AutoSprintModule());
        registerModule(new ChestESPModule());
        registerModule(new ClickGUIModule());
        registerModule(new ESPModule());
        registerModule(new FastBreakModule());
        registerModule(new FastBridgeModule());
        registerModule(new FastLadderModule());
        registerModule(new FlyModule());
        registerModule(new FullbrightModule());
        registerModule(new GhostModule());
        registerModule(new HUDModule());
        registerModule(new InventoryMoveModule());
        registerModule(new KillauraModule());
        registerModule(new LongJumpModule());
        registerModule(new MultiJumpModule());
        registerModule(new NameTagsModule());
        registerModule(new NoFallModule());
        registerModule(new NoWebModule());
        registerModule(new NukerModule());
        registerModule(new SpeedHackModule());
        registerModule(new SpiderModule());
        registerModule(new StepModule());
        registerModule(new StrafeModule());
        registerModule(new TracersModule());
        registerModule(new TriggerBotModule());
        registerModule(new VClipModule());
        registerModule(new WallHackModule());
        registerModule(new XRayModule());
        
// --- Original Code ---
//        File file = new File("build\\libs\\modid-1.0.jar");
//        Main.getInstance().log(String.valueOf(new StringBuilder().append("Self file ").append(file.getAbsolutePath())));
//        if (!file.getName().endsWith(".jar")) {
//            return;
//        }
//        ZipFile zipFile = new ZipFile(file);
//        Enumeration<? extends ZipEntry> enumeration = zipFile.entries();
//        while (enumeration.hasMoreElements()) {
//            Class<?> class_;
//            String string;
//            ZipEntry zipEntry = enumeration.nextElement();
//            if (zipEntry.isDirectory() || !(string = zipEntry.getName()).endsWith(".class") || zipEntry.isDirectory() || (class_ = ModuleManager.class.getClassLoader().loadClass(string.replace(".class", "").replace("/", "."))).getSuperclass() != Module.class) {
//                continue;
//            }
//            Module asaIBIYOhmSolWc = (Module) class_.newInstance();
//            Main.getInstance().log(String.valueOf(new StringBuilder().append("Loading module ").append(asaIBIYOhmSolWc.getName())));
//            EventBus.register(asaIBIYOhmSolWc);
//            modules.add(asaIBIYOhmSolWc);
//        }
//        try {
//            zipFile.close();
//        } catch (Exception exception) {
//            exception.printStackTrace();
//        }
    }

//    private static void walkDir(File file) {
//        String string;
//        if (file.isDirectory()) {
//            File[] arrfile = file.listFiles();
//            Arrays.asList(arrfile).stream().forEach(ModuleManager::walkDir);
//        } else if (file.getName().endsWith(".class") && (string = file.getAbsolutePath().replace("C:\\Users\\Evgeniy\\Desktop\\dev\\vanilla1.12.2\\eclipse\\Client\\bin\\", "").replace(".class", "").replace("\\", ".")).startsWith("ru.yammi")) {
//            try {
//                Class<?> class_ = ModuleManager.class.getClassLoader().loadClass(string);
//                if (class_.getSuperclass() == Module.class) {
//                    Module asaIBIYOhmSolWc = (Module) class_.newInstance();
//                    Main.getInstance().log(String.valueOf(new StringBuilder().append("Loading module ").append(asaIBIYOhmSolWc.getName())));
//                    EventBus.register(asaIBIYOhmSolWc);
//                    modules.add(asaIBIYOhmSolWc);
//                }
//            } catch (Exception exception) {
//                exception.printStackTrace();
//            }
//        }
//    }
}
