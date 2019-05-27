package ca.teamdman.enderfolds;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
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
		if (entityLiving instanceof EntityPlayer) {
			ItemStack shard = new ItemStack(EnderFolds.Items.SHARD);
			shard.setTagCompound(stack.getTagCompound());
			((EntityPlayer) entityLiving).addItemStackToInventory(shard);
		}
		return result;
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
		playerIn.setActiveHand(handIn);
		return new ActionResult<>(EnumActionResult.SUCCESS, playerIn.getHeldItem(handIn));
	}
}
