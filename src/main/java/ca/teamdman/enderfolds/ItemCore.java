package ca.teamdman.enderfolds;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemCore extends ItemFood {
	public ItemCore() {
		super(0, 0, false);
		setAlwaysEdible();
		setRegistryName("core");
		setTranslationKey("core");
	}

	@Override
	public ItemStack onItemUseFinish(ItemStack stack, World worldIn, EntityLivingBase entityLiving) {
		ItemStack result = super.onItemUseFinish(stack, worldIn, entityLiving);
		stack.grow(1);
		return result;
	}
//
//	@Override
//	public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
//		Block block = worldIn.getBlockState(pos).getBlock();
//		if (!(block instanceof BlockFold))
//			return super.onItemUse(player, worldIn, pos, hand, facing, hitX, hitY, hitZ);
//		if (((BlockFold) block).type == BlockFold.Type.SOURCE)
//			return EnumActionResult.FAIL;
//		if (!(worldIn.getTileEntity(pos) instanceof TileFoldDestination))
//			return EnumActionResult.FAIL;
//
//		ItemStack stack = player.getHeldItem(hand);
//		//noinspection ConstantConditions
//		((TileFoldDestination) worldIn.getTileEntity(pos)).insertCore(stack);
//		return EnumActionResult.SUCCESS;
//	}


}
