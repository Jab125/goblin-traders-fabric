package net.hat.gt.spawning;

import com.jab125.util.forgehelper.util.Constants;
import net.hat.gt.GobT;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtList;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.PersistentState;
import net.minecraft.world.World;

import java.util.HashMap;
import java.util.Map;

/**
 * Reworked for fabric
 */
public class GoblinTraderData extends PersistentState
{
    protected static final String DATA_NAME = GobT.MODID + "_goblin_trader";

    protected final Map<String, GoblinData> data = new HashMap<>();

    public GoblinTraderData() {
        super(DATA_NAME);
    }

    public GoblinData getGoblinData(String key)
    {
        return this.data.computeIfAbsent(key, s -> new GoblinData(this));
    }

    public GoblinTraderData read(NbtCompound tag)
    {
        if(tag.contains("GoblinTraderSpawnDelay", Constants.NBT.TAG_INT))
        {
            this.getGoblinData("GoblinTrader").setGoblinTraderSpawnDelay(tag.getInt("GoblinTraderSpawnDelay"));
        }
        if(tag.contains("GoblinTraderSpawnChance", Constants.NBT.TAG_INT))
        {
            this.getGoblinData("GoblinTrader").setGoblinTraderSpawnChance(tag.getInt("GoblinTraderSpawnChance"));
        }
        if(tag.contains("Data", Constants.NBT.TAG_LIST))
        {
            this.data.clear();
            NbtList list = tag.getList("Data", Constants.NBT.TAG_COMPOUND);
            list.forEach(nbt -> {
                NbtCompound goblinTag = (NbtCompound) nbt;
                String key = goblinTag.getString("Key");
                GoblinData data = new GoblinData(this);
                data.read(goblinTag);
                this.data.put(key, data);
            });
        }
        return this;
    }

    @Override
    public void fromTag(NbtCompound tag) {
        read(tag);
    }

    @Override
    public NbtCompound writeNbt(NbtCompound compound)
    {
        NbtList list = new NbtList();
        this.data.forEach((s, goblinData) -> {
            NbtCompound goblinTag = new NbtCompound();
            goblinData.write(goblinTag);
            goblinTag.putString("Key", s);
            list.add(goblinTag);
        });
        compound.put("Data", list);
        return compound;
    }

    public static GoblinTraderData get(MinecraftServer server)
    {
        ServerWorld level = server.getWorld(World.OVERWORLD);
        assert level != null;
        return level.getPersistentStateManager().getOrCreate(GoblinTraderData::new, DATA_NAME);
    }
}
