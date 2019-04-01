package ca.teamdman.enderfolds;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

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
	public void preInit(FMLPreInitializationEvent event) {
		GameRegistry.registerTileEntity(TileFoldSource.class, new ResourceLocation(MOD_ID,"source"));
		GameRegistry.registerTileEntity(TileFoldDestination.class, new ResourceLocation(MOD_ID,"destination"));

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
	public void postInit(FMLPostInitializationEvent event) {

	}

	@GameRegistry.ObjectHolder(MOD_ID)
	public static class Blocks {
		public static final Block SOURCE      = net.minecraft.init.Blocks.AIR;
		public static final Block DESTINATION = net.minecraft.init.Blocks.AIR;
	}

	/**
	 * Forge will automatically look up and bind items to the fields in this class
	 * based on their registry name.
	 */
	@GameRegistry.ObjectHolder(MOD_ID)
	public static class Items {
		public static final Item SHARD = net.minecraft.init.Items.AIR;
	}

	/**
	 * This is a special class that listens to registry events, to allow creation of mod blocks and items at the proper time.
	 */
	@SuppressWarnings("ConstantConditions")
	@Mod.EventBusSubscriber
	public static class ObjectRegistryHandler {
		/**
		 * Listen for the register event for creating custom items
		 */
		@SubscribeEvent
		public static void addItems(RegistryEvent.Register<Item> event) {
			event.getRegistry().register(new ItemShard());
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


		private static void registerBlockModel(Block block) {
			ResourceLocation resourceLocation = Block.REGISTRY.getNameForObject(block);
			registerBlockModel(block, 0, resourceLocation.toString());
		}

		private static void registerBlockModel(Block block, int meta, String modelName) {
			registerItemModel(Item.getItemFromBlock(block), meta, modelName);
		}

		private static void registerItemModel(Item item, int meta, String resourcePath) {
			ModelResourceLocation modelResourceLocation = new ModelResourceLocation(resourcePath, "inventory");
			net.minecraftforge.client.model.ModelLoader.setCustomModelResourceLocation(item, meta, modelResourceLocation);
		}

		@SideOnly(Side.CLIENT)
		@SubscribeEvent
		public static void registerRenderers(ModelRegistryEvent event) {
			registerBlockModel(Blocks.DESTINATION);
			registerBlockModel(Blocks.SOURCE);
			registerItemModel(Items.SHARD,0,Items.SHARD.getRegistryName().toString());
		}
	}
}
