package ca.teamdman.enderfolds;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemLinker extends Item {
	public ItemLinker() {
		setRegistryName("linker");
		setTranslationKey("linker");
	}

	@Override
	public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		Block block = worldIn.getBlockState(pos).getBlock();
		if (!(block instanceof BlockFold))
			return super.onItemUse(player, worldIn, pos, hand, facing, hitX, hitY, hitZ);
		if (((BlockFold) block).type == BlockFold.Type.DESTINATION)
			return EnumActionResult.FAIL;
		if (!(worldIn.getTileEntity(pos) instanceof TileFoldSource))
			return EnumActionResult.FAIL;
		ItemStack stack = player.getHeldItem(hand);

		((TileFoldSource) worldIn.getTileEntity(pos)).readDestFromNBT(stack.getTagCompound());
		stack.setCount(stack.getCount()-1);

		return EnumActionResult.SUCCESS;
	}


}
