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
	public int      destDim;

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
		if (dest == null)
			return false;
		if (facing != null) {
			TileEntity tile = dest.getWorld().getTileEntity(dest.getPos().offset(facing.getOpposite()));
			if (tile != null)
				return tile.hasCapability(capability, facing);
		} else {
			for (EnumFacing face : EnumFacing.VALUES) {
				TileEntity tile = dest.getWorld().getTileEntity(dest.getPos().offset(face));
				if (tile != null && tile.hasCapability(capability, face))
					return true;
			}
		}
		return false;
	}

	@Nullable
	@Override
	public <T> T getCapability(Capability<T> capability, @Nullable EnumFacing facing) {
		TileFoldDestination dest = getDestination();
		if (dest == null)
			return null;
		if (facing != null) {
			TileEntity tile = dest.getWorld().getTileEntity(dest.getPos().offset(facing.getOpposite()));
			if (tile != null)
				return tile.getCapability(capability, facing);
		} else {
			for (EnumFacing face : EnumFacing.VALUES) {
				TileEntity tile = dest.getWorld().getTileEntity(dest.getPos().offset(face));
				if (tile != null && tile.getCapability(capability, face) != null)
					return tile.getCapability(capability, face);
			}
		}
		return null;
	}

	@Override
	public void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);
		readDestFromNBT(compound);
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		if (destPos != null) {
			compound.setLong("pos", destPos.toLong());
			compound.setInteger("dim", destDim);
			System.out.println("Wrote to nbt");
		}
		return super.writeToNBT(compound);
	}

	public void readDestFromNBT(NBTTagCompound compound) {
		if (compound == null || !compound.hasKey("pos") || !compound.hasKey("dim"))
			return;
		this.destPos = BlockPos.fromLong(compound.getLong("pos"));
		this.destDim = compound.getInteger("dim");
	}
}
