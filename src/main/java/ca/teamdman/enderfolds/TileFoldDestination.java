package ca.teamdman.enderfolds;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.items.CapabilityItemHandler;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class TileFoldDestination extends TileEntity {
	public boolean hasCore = true;

	@Override
	public void readFromNBT(NBTTagCompound compound) {
		if (compound.hasKey("hasCore")) {
			this.hasCore = compound.getBoolean("hasCore");
		}

		super.readFromNBT(compound);
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		NBTTagList list = new NBTTagList();
		compound.setTag("owners", list);
		compound.setBoolean("hasCore", this.hasCore);
		return super.writeToNBT(compound);
	}

	public ItemStack removeCore() {
		ItemStack stack = new ItemStack(EnderFolds.Items.CORE);
		NBTTagCompound compound = new NBTTagCompound();
		compound.setLong("pos", pos.toLong());
		compound.setInteger("dim", world.provider.getDimension());
		stack.setTagCompound(compound);
		this.hasCore = false;
		markDirty();
		return stack;
	}

	public boolean insertCore(ItemStack stack) {
		if (stack == null || stack.isEmpty() || stack.getItem() != EnderFolds.Items.CORE)
			return false;
		NBTTagCompound compound = stack.getTagCompound();
		if (compound == null || !compound.hasKey("pos") || !compound.hasKey("dim"))
			return false;
		if (!pos.equals(BlockPos.fromLong(compound.getLong("pos"))))
			return false;
		if (world.provider.getDimension() != compound.getInteger("dim"))
			return false;
		stack.shrink(1);
		this.hasCore = true;
		markDirty();
		return true;
	}
}
