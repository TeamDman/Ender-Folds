package ca.teamdman.enderfolds;

import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@net.minecraftforge.common.config.Config(modid= EnderFolds.MOD_ID, name= EnderFolds.MOD_NAME, category="")
@Mod.EventBusSubscriber(modid = EnderFolds.MOD_ID)
public class Config {
	@net.minecraftforge.common.config.Config.Comment("General Options")
	@net.minecraftforge.common.config.Config.RequiresMcRestart
	public static General general = new General();
	public static class General {
		@net.minecraftforge.common.config.Config.Comment("Forces destinations to always be indestructible.")
		public boolean indestructibleDestinations = false;
		@net.minecraftforge.common.config.Config.Comment("Only allows one core to be extracted from a destination.")
		public boolean unqiueCores = true;
	}

	@SubscribeEvent
	public static void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event) {
		if (event.getModID().equals(EnderFolds.MOD_ID))
			ConfigManager.sync(event.getModID(), net.minecraftforge.common.config.Config.Type.INSTANCE);
	}
}
