package ca.teamdman.enderfolds;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.common.capabilities.Capability;

import javax.annotation.Nullable;

public class TileFoldSource extends TileEntity { //implements ISidedInventory {
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
		TileEntity tile = dest.getWorld().getTileEntity(dest.getPos().offset(facing.getOpposite()));
		if (tile != null)
			return tile.hasCapability(capability, facing);
		return false;
	}

	@Nullable
	@Override
	public <T> T getCapability(Capability<T> capability, @Nullable EnumFacing facing) {
		TileFoldDestination dest = getDestination();
		if (dest == null)
			return null;
		TileEntity tile = dest.getWorld().getTileEntity(dest.getPos().offset(facing.getOpposite()));
		if (tile != null)
			return tile.getCapability(capability, facing);
		return null;
	}

	@Override
	public void readFromNBT(NBTTagCompound compound) {
		readDestFromNBT(compound);
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		if (destPos != null) {
			compound.setLong("pos", destPos.toLong());
			compound.setInteger("dim", destDim);
		}
		return super.writeToNBT(compound);
	}

	public void readDestFromNBT(NBTTagCompound compound) {
		if (compound == null || !compound.hasKey("pos") || !compound.hasKey("dim"))
			return;
		this.destPos = BlockPos.fromLong(compound.getLong("pos"));
		this.destDim = compound.getInteger("dim");
	}
//
//	@Override
//	public int[] getSlotsForFace(EnumFacing side) {
//		TileFoldDestination dest = getDestination();
//		if (dest == null)
//			return new int[0];
//		TileEntity tile = dest.getWorld().getTileEntity(dest.getPos().offset(side.getOpposite()));
//		if (tile instanceof ISidedInventory)
//			return ((ISidedInventory) tile).getSlotsForFace(side);
//		return new int[0];
//	}
//
//	@Override
//	public boolean canInsertItem(int index, ItemStack itemStackIn, EnumFacing direction) {
//		TileFoldDestination dest = getDestination();
//		if (dest == null)
//			return false;
//		TileEntity tile = dest.getWorld().getTileEntity(dest.getPos().offset(direction.getOpposite()));
//		if (tile instanceof ISidedInventory)
//			return ((ISidedInventory) tile).canInsertItem(index, itemStackIn, direction.getOpposite());
//		return false;
//	}
//
//	@Override
//	public boolean canExtractItem(int index, ItemStack stack, EnumFacing direction) {
//		TileFoldDestination dest = getDestination();
//		if (dest == null)
//			return false;
//		TileEntity tile = dest.getWorld().getTileEntity(dest.getPos().offset(direction.getOpposite()));
//		if (tile instanceof ISidedInventory)
//			return ((ISidedInventory) tile).canExtractItem(index, stack, direction.getOpposite());
//		return false;
//	}
//
//	@Override
//	public int getSizeInventory() {
//		TileFoldDestination dest = getDestination();
//		if (dest == null)
//			return 0;
//		TileEntity tile = dest.getWorld().getTileEntity(dest.getPos());
//		if (tile instanceof IInventory)
//			return ((IInventory) tile).getSizeInventory();
//		return 0;
//	}
//
//	@Override
//	public boolean isEmpty() {
//		TileFoldDestination dest = getDestination();
//		if (dest == null)
//			return false;
//		TileEntity tile = dest.getWorld().getTileEntity(dest.getPos());
//		if (tile instanceof IInventory)
//			return ((IInventory) tile).isEmpty();
//		return false;
//	}
//
//	@Override
//	public ItemStack getStackInSlot(int index) {
//		TileFoldDestination dest = getDestination();
//		if (dest == null)
//			return ItemStack.EMPTY;
//		TileEntity tile = dest.getWorld().getTileEntity(dest.getPos());
//		if (tile instanceof IInventory)
//			return ((IInventory) tile).getStackInSlot(index);
//		return ItemStack.EMPTY;
//	}
//
//	@Override
//	public ItemStack decrStackSize(int index, int count) {
//		TileFoldDestination dest = getDestination();
//		if (dest == null)
//			return ItemStack.EMPTY;
//		TileEntity tile = dest.getWorld().getTileEntity(dest.getPos());
//		if (tile instanceof IInventory)
//			return ((IInventory) tile).decrStackSize(index, count);
//		return ItemStack.EMPTY;
//	}
//
//	@Override
//	public ItemStack removeStackFromSlot(int index) {
//		TileFoldDestination dest = getDestination();
//		if (dest == null)
//			return ItemStack.EMPTY;
//		TileEntity tile = dest.getWorld().getTileEntity(dest.getPos());
//		if (tile instanceof IInventory)
//			return ((IInventory) tile).removeStackFromSlot(index);
//		return ItemStack.EMPTY;
//	}
//
//	@Override
//	public void setInventorySlotContents(int index, ItemStack stack) {
//		TileFoldDestination dest = getDestination();
//		if (dest == null)
//			return;
//		TileEntity tile = dest.getWorld().getTileEntity(dest.getPos());
//		if (tile instanceof IInventory)
//			((IInventory) tile).setInventorySlotContents(index, stack);
//	}
//
//	@Override
//	public int getInventoryStackLimit() {
//		TileFoldDestination dest = getDestination();
//		if (dest == null)
//			return 0;
//		TileEntity tile = dest.getWorld().getTileEntity(dest.getPos());
//		if (tile instanceof IInventory)
//			return ((IInventory) tile).getSizeInventory();
//		return 0;
//	}
//
//	@Override
//	public boolean isUsableByPlayer(EntityPlayer player) {
//		TileFoldDestination dest = getDestination();
//		if (dest == null)
//			return false;
//		TileEntity tile = dest.getWorld().getTileEntity(dest.getPos());
//		if (tile instanceof IInventory)
//			return ((IInventory) tile).isUsableByPlayer(player);
//		return false;
//	}
//
//	@Override
//	public void openInventory(EntityPlayer player) {
//		TileFoldDestination dest = getDestination();
//		if (dest == null)
//			return;
//		TileEntity tile = dest.getWorld().getTileEntity(dest.getPos());
//		if (tile instanceof IInventory)
//			((IInventory) tile).openInventory(player);
//	}
//
//	@Override
//	public void closeInventory(EntityPlayer player) {
//		TileFoldDestination dest = getDestination();
//		if (dest == null)
//			return;
//		TileEntity tile = dest.getWorld().getTileEntity(dest.getPos());
//		if (tile instanceof IInventory)
//			((IInventory) tile).closeInventory(player);
//	}
//
//	@Override
//	public boolean isItemValidForSlot(int index, ItemStack stack) {
//		TileFoldDestination dest = getDestination();
//		if (dest == null)
//			return false;
//		TileEntity tile = dest.getWorld().getTileEntity(dest.getPos());
//		if (tile instanceof IInventory)
//			return ((IInventory) tile).isItemValidForSlot(index, stack);
//		return false;
//	}
//
//	@Override
//	public int getField(int id) {
//		TileFoldDestination dest = getDestination();
//		if (dest == null)
//			return 0;
//		TileEntity tile = dest.getWorld().getTileEntity(dest.getPos());
//		if (tile instanceof IInventory)
//			return ((IInventory) tile).getField(id);
//		return 0;
//	}
//
//	@Override
//	public void setField(int id, int value) {
//		TileFoldDestination dest = getDestination();
//		if (dest == null)
//			return;
//		TileEntity tile = dest.getWorld().getTileEntity(dest.getPos());
//		if (tile instanceof IInventory)
//			((IInventory) tile).setField(id, value);
//	}
//
//	@Override
//	public int getFieldCount() {
//		TileFoldDestination dest = getDestination();
//		if (dest == null)
//			return 0;
//		TileEntity tile = dest.getWorld().getTileEntity(dest.getPos());
//		if (tile instanceof IInventory)
//			return ((IInventory) tile).getFieldCount();
//		return 0;
//	}
//
//	@Override
//	public void clear() {
//		TileFoldDestination dest = getDestination();
//		if (dest == null)
//			return;
//		TileEntity tile = dest.getWorld().getTileEntity(dest.getPos());
//		if (tile instanceof IInventory)
//			((IInventory) tile).clear();
//	}
//
//	@Override
//	public String getName() {
//		TileFoldDestination dest = getDestination();
//		if (dest == null)
//			return "";
//		TileEntity tile = dest.getWorld().getTileEntity(dest.getPos());
//		if (tile instanceof IInventory)
//			return ((IInventory) tile).getName();
//		return "";
//	}
//
//	@Override
//	public boolean hasCustomName() {
//		TileFoldDestination dest = getDestination();
//		if (dest == null)
//			return false;
//		TileEntity tile = dest.getWorld().getTileEntity(dest.getPos());
//		if (tile instanceof IInventory)
//			return ((IInventory) tile).hasCustomName();
//		return false;
//	}
}
