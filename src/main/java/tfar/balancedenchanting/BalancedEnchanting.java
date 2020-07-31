package tfar.balancedenchanting;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(BalancedEnchanting.MODID)
public class BalancedEnchanting {

	public static final String MODID = "balancedenchanting";

	/**
	 * This method returns the cap amount of experience that the experience bar can hold. With each level, the experience
	 * cap on the player's experience bar is raised by 10.
	 *
	 * @see PlayerEntity#xpBarCap()
	 */
	private static int getRequiredXpToNextLevel(int level) {
		if (level >= 30) {
			return 112 + (level - 30) * 9;
		} else {
			return level >= 15 ? 37 + (level - 15) * 5 : 7 + level * 2;
		}
	}

	public static int convertLevelToTotalxp(int level) {
		int sum = 0;
		for (int i = 0; i < level; i++) {
			sum += getRequiredXpToNextLevel(i);
		}
		return sum;
	}
}
