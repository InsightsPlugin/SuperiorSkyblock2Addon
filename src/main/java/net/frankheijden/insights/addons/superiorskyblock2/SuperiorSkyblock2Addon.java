package net.frankheijden.insights.addons.superiorskyblock2;

import com.bgsoftware.superiorskyblock.api.SuperiorSkyblockAPI;
import com.bgsoftware.superiorskyblock.api.island.Island;
import net.frankheijden.insights.entities.Area;
import net.frankheijden.insights.entities.CacheAssistant;
import net.frankheijden.insights.entities.CuboidSelection;
import org.bukkit.Location;

import java.util.Collections;

public class SuperiorSkyblock2Addon extends CacheAssistant {

    public SuperiorSkyblock2Addon() {
        super("SuperiorSkyblock2", "SuperiorSkyblock2", "island", "1.0.0");
    }

    public String getId(Island island, Location loc) {
        return getPluginName() + "@" + island.getUniqueId() + "-" + loc.getWorld().getName();
    }

    public Area adapt(Island island, Location loc) {
        if (island == null) return null;
        return Area.from(this, getId(island, loc), Collections.singletonList(new CuboidSelection(
                island.getMinimum(),
                island.getMaximum()
        )));
    }

    @Override
    public Area getArea(Location location) {
        return adapt(SuperiorSkyblockAPI.getIslandAt(location), location);
    }
}
