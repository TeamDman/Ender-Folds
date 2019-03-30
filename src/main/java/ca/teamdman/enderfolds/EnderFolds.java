package ca.teamdman.enderfolds;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.registry.GameRegistry;

@Mod(
		modid = EnderFolds.MOD_ID,
		name = EnderFolds.MOD_NAME,
		version = EnderFolds.VERSION
)
public class EnderFolds {

	public static final String MOD_ID   = "enderfolds";
	public static final String MOD_NAME = "Ender Folds";
	public static final String VERSION  = "0.1.0";

	@Mod.Instance(MOD_ID)
	public static EnderFolds INSTANCE;

	/**
	 * This is the first initialization event. Register tile entities here.
	 * The registry events below will have fired prior to entry to this method.
	 */
	@Mod.EventHandler
	public void preinit(FMLPreInitializationEvent event) {
		GameRegistry.registerTileEntity(TileFoldSource.class, new ResourceLocation(MOD_ID,"fold_source"));
		GameRegistry.registerTileEntity(TileFoldDestination.class, new ResourceLocation(MOD_ID,"fold_destination"));

	}

	/**
	 * This is the second initialization event. Register custom recipes
	 */
	@Mod.EventHandler
	public void init(FMLInitializationEvent event) {

	}

	/**
	 * This is the final initialization event. Register actions from other mods here
	 */
	@Mod.EventHandler
	public void postinit(FMLPostInitializationEvent event) {

	}

	@GameRegistry.ObjectHolder(MOD_ID)
	public static class Blocks {
		public static final BlockFold SOURCE      = null;
		public static final BlockFold DESTINATION = null;
	}

	/**
	 * Forge will automatically look up and bind items to the fields in this class
	 * based on their registry name.
	 */
	@GameRegistry.ObjectHolder(MOD_ID)
	public static class Items {
		public static final Item LINKER = null;
	}

	/**
	 * This is a special class that listens to registry events, to allow creation of mod blocks and items at the proper time.
	 */
	@Mod.EventBusSubscriber
	public static class ObjectRegistryHandler {
		/**
		 * Listen for the register event for creating custom items
		 */
		@SubscribeEvent
		public static void addItems(RegistryEvent.Register<Item> event) {
			event.getRegistry().register(new ItemLinker());
			event.getRegistry().register(new ItemBlock(Blocks.SOURCE).setRegistryName(Blocks.SOURCE.getRegistryName()).setCreativeTab(Blocks.SOURCE.getCreativeTab()));
			event.getRegistry().register(new ItemBlock(Blocks.DESTINATION).setRegistryName(Blocks.DESTINATION.getRegistryName()).setCreativeTab(Blocks.DESTINATION.getCreativeTab()));
		}

		/**
		 * Listen for the register event for creating custom blocks
		 */
		@SubscribeEvent
		public static void addBlocks(RegistryEvent.Register<Block> event) {
			event.getRegistry().register(new BlockFold(BlockFold.Type.DESTINATION));
			event.getRegistry().register(new BlockFold(BlockFold.Type.SOURCE));
		}
	}
}
