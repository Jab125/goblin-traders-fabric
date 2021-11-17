package net.hat.gt.spawning;

import com.jab125.util.forgehelper.util.Constants;
import net.minecraft.nbt.NbtCompound;

/**
 * Reworked for fabric
 */
public class GoblinData
{
    protected GoblinTraderData data;
    protected int goblinTraderSpawnDelay;
    protected int goblinTraderSpawnChance;

    public GoblinData(GoblinTraderData data)
    {
        this.data = data;
    }

    public void setGoblinTraderSpawnDelay(int goblinTraderSpawnDelay)
    {
        this.goblinTraderSpawnDelay = goblinTraderSpawnDelay;
        this.data.setDirty(true);
    }

    public void setGoblinTraderSpawnChance(int goblinTraderSpawnChance)
    {
        this.goblinTraderSpawnChance = goblinTraderSpawnChance;
        this.data.setDirty(true);
    }

    public int getGoblinTraderSpawnDelay()
    {
        return goblinTraderSpawnDelay;
    }

    public int getGoblinTraderSpawnChance()
    {
        return goblinTraderSpawnChance;
    }

    public void read(NbtCompound compound)
    {
        if(compound.contains("GoblinTraderSpawnDelay", Constants.NBT.TAG_INT))
        {
            this.goblinTraderSpawnDelay = compound.getInt("GoblinTraderSpawnDelay");
        }
        if(compound.contains("GoblinTraderSpawnChance", Constants.NBT.TAG_INT))
        {
            this.goblinTraderSpawnChance = compound.getInt("GoblinTraderSpawnChance");
        }
    }

    public NbtCompound write(NbtCompound compound)
    {
        compound.putInt("GoblinTraderSpawnDelay", this.goblinTraderSpawnDelay);
        compound.putInt("GoblinTraderSpawnChance", this.goblinTraderSpawnChance);
        return compound;
    }
}
