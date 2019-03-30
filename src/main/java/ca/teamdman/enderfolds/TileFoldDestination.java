package ca.teamdman.enderfolds;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.items.CapabilityItemHandler;

import javax.annotation.Nullable;
import java.util.ArrayList;

public class TileFoldDestination extends TileEntity {
	public final ArrayList<TileFoldSource> sources = new ArrayList<>();

}
