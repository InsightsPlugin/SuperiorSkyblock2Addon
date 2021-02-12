package dev.frankheijden.insights.addons.superiorskyblock2;

import com.bgsoftware.superiorskyblock.api.SuperiorSkyblockAPI;
import com.bgsoftware.superiorskyblock.api.island.Island;
import dev.frankheijden.insights.api.addons.InsightsAddon;
import dev.frankheijden.insights.api.addons.Region;
import dev.frankheijden.insights.api.addons.SimpleCuboidRegion;
import dev.frankheijden.insights.api.objects.math.Vector3;
import org.bukkit.Location;
import org.bukkit.World;
import java.util.Optional;

public class SuperiorSkyblock2Addon implements InsightsAddon {

    public String getId(Island island, World world) {
        return getPluginName() + "@" + island.getUniqueId() + "-" + world.getName();
    }

    public Optional<Region> adapt(Island island, World world) {
        if (island == null) return Optional.empty();
        Location min = island.getMinimum();
        Location max = island.getMaximum();
        return Optional.of(new SimpleCuboidRegion(
                world,
                new Vector3(min.getBlockX(), min.getBlockY(), min.getBlockZ()),
                new Vector3(max.getBlockX(), max.getBlockY(), max.getBlockZ()),
                getPluginName(),
                getId(island, world)
        ));
    }

    @Override
    public String getPluginName() {
        return "SuperiorSkyblock2";
    }

    @Override
    public String getAreaName() {
        return "island";
    }

    @Override
    public String getVersion() {
        return "{version}";
    }

    @Override
    public Optional<Region> getRegion(Location location) {
        return adapt(SuperiorSkyblockAPI.getIslandAt(location), location.getWorld());
    }
}
