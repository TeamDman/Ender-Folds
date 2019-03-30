package ca.teamdman.enderfolds;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.common.capabilities.Capability;

import javax.annotation.Nullable;

public class TileFoldSource extends TileEntity {
	public BlockPos destPos;
	public int destDim;

	public TileFoldDestination getDestination() {
		if (destPos == null)
			return null;
		World world = DimensionManager.getWorld(destDim);
		if (world == null)
			return null;
		TileEntity tile = world.getTileEntity(destPos);
		return tile instanceof TileFoldDestination ? (TileFoldDestination) tile : null;
	}

	@Override
	public boolean hasCapability(Capability<?> capability, @Nullable EnumFacing facing) {
		TileFoldDestination dest = getDestination();
		return dest != null && dest.hasCapability(capability, facing);
	}

	@Nullable
	@Override
	public <T> T getCapability(Capability<T> capability, @Nullable EnumFacing facing) {
		TileFoldDestination dest = getDestination();
		return dest != null ? dest.getCapability(capability, facing) : null;
	}

	public void readDestFromNBT(NBTTagCompound compound) {
		if (compound == null || !compound.hasKey("pos") || !compound.hasKey("dim"))
			return;
		this.destPos = BlockPos.fromLong(compound.getLong("pos"));
		this.destDim = compound.getInteger("dim");
	}
}
