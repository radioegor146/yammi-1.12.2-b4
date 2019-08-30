package by.radioegor146.interop;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import ru.yammi.Main;

@Mod(modid = "yammi-b4", name = "Yammi b4", version = "b4")
public class ForgeMod {
    
    @Mod.EventHandler
    public void init(FMLInitializationEvent event) throws Throwable {
        Main.getInstance().main();
    }
}
