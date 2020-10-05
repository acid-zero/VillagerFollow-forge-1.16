package com.acidzero.villagerfollow;

import net.minecraft.block.Blocks;
import net.minecraft.entity.ai.goal.TemptGoal;
import net.minecraft.entity.merchant.villager.VillagerEntity;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;


// The value here should match an entry in the META-INF/mods.toml file
@Mod("villagerfollow")
public class VillagerFollow
{
    public VillagerFollow() {
        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void addVillagerFollowTask(final EntityJoinWorldEvent event)
    {
        if ((!event.getWorld().isRemote) && (event.getEntity() instanceof VillagerEntity))
        {
            VillagerEntity villager = (VillagerEntity)event.getEntity();

            // Villagers will follow players holding an Emerald Block at 0.5 speed
            TemptGoal followPlayerTask1 = new TemptGoal(villager, 0.5D, Ingredient.fromItems(Blocks.EMERALD_BLOCK.asItem()), false);
            villager.goalSelector.addGoal(4, followPlayerTask1);

            // Villagers will follow players holding an Emerald at 0.25 speed
            TemptGoal followPlayerTask2 = new TemptGoal(villager, 0.25D, Ingredient.fromItems(Items.EMERALD), false);
            villager.goalSelector.addGoal(4, followPlayerTask2);
        }
    }
}
