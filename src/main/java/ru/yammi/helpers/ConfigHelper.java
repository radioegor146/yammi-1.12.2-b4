package ru.yammi.helpers;

import ru.yammi.modulesystem.Module;
import ru.yammi.modulesystem.ModuleManager;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import ru.yammi.config.BooleanValue;
import ru.yammi.config.SliderValue;
import ru.yammi.modulesystem.modules.XRayBlockModelRenderer;

public class ConfigHelper {

    private static JsonObject jsonObject;
    private static String path;

    static {
        path = String.valueOf(new StringBuilder().append(System.getProperty("user.home")).append(File.separator).append("yammi-1.12.2-b4.json"));
    }

    public static BooleanValue getBoolValue(String moduleName, String valueName) {
        for (BooleanValue value : ModuleManager.getModule(moduleName).getBooleanValues()) {
            if (!value.getName().equals(valueName)) {
                continue;
            }
            return value;
        }
        return null;
    }

    private static boolean isBoolValueExists(String moduleName, String valueName) {
        for (BooleanValue value : ModuleManager.getModule(moduleName).getBooleanValues()) {
            if (!value.getName().equals(valueName)) {
                continue;
            }
            return true;
        }
        return false;
    }

    public static void load() {
        try {
            File file = new File(path);
            if (!file.exists()) {
                file.createNewFile();
                ConfigHelper.store();
            }
            try (FileReader fileReader = new FileReader(file)) {
                JsonParser jsonParser = new JsonParser();
                JsonElement jsonElement = jsonParser.parse(fileReader);
                jsonObject = jsonElement.getAsJsonObject();
                JsonArray modulesArray = jsonObject.getAsJsonArray("modules");
                for (int i = 0; i < modulesArray.size(); ++i) {
                    JsonObject moduleInfoObject = modulesArray.get(i).getAsJsonObject();
                    String moduleName = moduleInfoObject.get("name").getAsString();
                    Module module = ModuleManager.getModule(moduleName);
                    if (module == null) {
                        continue;
                    }
                    module.setKeybind(moduleInfoObject.get("bind").getAsInt());
                    module.setState(moduleInfoObject.get("state").getAsBoolean());
                }
                JsonArray xrayBlocksArray = jsonObject.getAsJsonArray("xrayblocks");
                if (xrayBlocksArray != null) {
                    for (int i = 0; i < xrayBlocksArray.size(); ++i) {
                        XRayBlockModelRenderer.xrayBlocks.add(xrayBlocksArray.get(i).getAsJsonObject().get("id").getAsInt());
                    }
                }
                JsonArray settingsJsonArray = jsonObject.getAsJsonArray("settings");
                if (settingsJsonArray != null) {
                    for (int i = 0; i < settingsJsonArray.size(); ++i) {
                        settingsJsonArray.get(i).getAsJsonObject().entrySet().stream().forEach(entry -> {
                            String moduleName = entry.getKey();
                            JsonArray moduleSettingsArray = entry.getValue().getAsJsonArray();
                            for (int j = 0; j < moduleSettingsArray.size(); ++j) {
                                JsonObject settingValueObject = moduleSettingsArray.get(j).getAsJsonObject();
                                int sliderX = settingValueObject.get("slider").getAsInt();
                                String valueTag = settingValueObject.get("tag").getAsString();
                                ConfigHelper.setSliderX(ModuleManager.getModule(moduleName), valueTag, sliderX);
                                float floatValue = settingValueObject.get("value").getAsFloat();
                                ConfigHelper.setValue(ModuleManager.getModule(moduleName), valueTag, floatValue);
                            }
                        });
                    }
                }
                JsonArray boolSettingsJsonArray = jsonObject.getAsJsonArray("boolSettings");
                if (boolSettingsJsonArray != null) {
                    for (int i = 0; i < boolSettingsJsonArray.size(); ++i) {
                        boolSettingsJsonArray.get(i).getAsJsonObject().entrySet().stream().forEach(entry -> {
                            String moduleName = entry.getKey();
                            JsonArray moduleBoolSettingsArray = entry.getValue().getAsJsonArray();
                            for (int j = 0; j < moduleBoolSettingsArray.size(); ++j) {
                                JsonObject boolSettingValueObject = moduleBoolSettingsArray.get(j).getAsJsonObject();
                                String valueTag = boolSettingValueObject.get("tag").getAsString();
                                boolean boolValue = boolSettingValueObject.get("state").getAsBoolean();
                                if (!ConfigHelper.isBoolValueExists(moduleName, valueTag)) {
                                    continue;
                                }
                                ConfigHelper.getBoolValue(moduleName, valueTag).setState(boolValue);
                            }
                        });
                    }
                }
            }
        } catch (Exception exception) {
            exception.printStackTrace(System.err);
        }
    }

    private static void setSliderX(Module module, String valueTag, int n) {
        if (module.getValues().size() > 0) {
            module.getValues().stream().filter((sliderValue) -> sliderValue.getName().equals(valueTag)).forEachOrdered((sliderValue) -> {
                sliderValue.setSliderX(n);
            });
        }
    }

    private static void setValue(Module module, String valueTag, float f) {
        if (module.getValues().size() > 0) {
            module.getValues().stream().filter((sliderValue) -> sliderValue.getName().equals(valueTag)).forEachOrdered((sliderValue) -> {
                sliderValue.setValue(f);
            });
        }
    }

    public static void store() {
        JsonArray jsonArray;
        JsonObject object;
        JsonArray jsonArray2 = new JsonArray();
        ModuleManager.getModules().stream().forEach(asaIBIYOhmSolWc -> {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("name", asaIBIYOhmSolWc.getName());
            jsonObject.addProperty("bind", asaIBIYOhmSolWc.getKeybind());
            jsonObject.addProperty("state", asaIBIYOhmSolWc.getState());
            jsonArray2.add(jsonObject);
        });
        JsonArray jsonArray3 = new JsonArray();
        JsonArray jsonArray4 = new JsonArray();
        for (Module object2 : ModuleManager.getModules()) {
            JsonObject jsonObject;
            String string;
            if (object2.getValues().size() > 0) {
                object = new JsonObject();
                jsonArray = new JsonArray();
                string = object2.getName();
                object.add(string, jsonArray);
                for (SliderValue object3 : object2.getValues()) {
                    jsonObject = new JsonObject();
                    jsonObject.addProperty("tag", object3.getName());
                    jsonObject.addProperty("value", object3.getValue());
                    jsonObject.addProperty("slider", object3.getSliderX());
                    jsonArray.add(jsonObject);
                }
                jsonArray3.add(object);
            }
            if (object2.getBooleanValues().size() <= 0) {
                continue;
            }
            object = new JsonObject();
            jsonArray = new JsonArray();
            string = object2.getName();
            object.add(string, jsonArray);
            for (BooleanValue object3 : object2.getBooleanValues()) {
                jsonObject = new JsonObject();
                jsonObject.addProperty("tag", object3.getName());
                jsonObject.addProperty("state", object3.isState());
                jsonArray.add(jsonObject);
            }
            jsonArray4.add(object);
        }
        try {
            JsonArray jsonArray5 = new JsonArray();
            XRayBlockModelRenderer.xrayBlocks.stream().forEach(n -> {
                JsonObject jsonObject = new JsonObject();
                jsonObject.addProperty("id", n);
                jsonArray5.add(jsonObject);
            });
            jsonObject = new JsonObject();
            jsonObject.add("modules", jsonArray2);
            jsonObject.add("xrayblocks", jsonArray5);
            jsonObject.add("settings", jsonArray3);
            jsonObject.add("boolSettings", jsonArray4);
            File object2 = new File(path);
            if (!object2.exists()) {
                object2.createNewFile();
            }
            FileWriter writer = new FileWriter(object2);
            new GsonBuilder().setPrettyPrinting().create().toJson(jsonObject, writer);
            writer.close();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
