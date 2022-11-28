package tfar.balancedenchanting;


import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.EnchantmentMenu;
import net.minecraftforge.fml.common.Mod;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(BalancedEnchanting.MODID)
public class BalancedEnchanting {

	public static final String MODID = "balancedenchanting";

	/**
	 * This method returns the cap amount of experience that the experience bar can hold. With each level, the experience
	 * cap on the player's experience bar is raised by 10.
	 *
	 * @see Player#getXpNeededForNextLevel()
	 * level to level + 1
	 */
	private static int getRequiredXpToNextLevel(int level) {
		if (level >= 30) {
			return 112 + (level - 30) * 9;
		} else {
			return level >= 15 ? 37 + (level - 15) * 5 : 7 + level * 2;
		}
	}

	//xp required to got from 0 to level
	public static int convertLevelToTotalxp(int level) {
		int sum = 0;
		for (int i = 0; i < level; i++) {
			sum += getRequiredXpToNextLevel(i);
		}
		return sum;
	}

	public static final ThreadLocal<Integer> id = ThreadLocal.withInitial(() -> 0);

	public static void handleEnchantXp(Player player, int cost) {
		AbstractContainerMenu menu = player.containerMenu;

		if (menu instanceof EnchantmentMenu enchantmentMenu) {
			int levelsRequired = enchantmentMenu.costs[id.get()];

			//simply subtracting the cost of the levels would be FAR too cheap as it would go from 0-3
			//when we need 27 - 30

			//so we need to take 0 - 30 and subtract 0 - 27 to get the actual levels that should be removed

			int level30Cost = convertLevelToTotalxp(levelsRequired);

			//remember, cost is a negative number
			int level27Cost = convertLevelToTotalxp(levelsRequired + cost);

			player.giveExperiencePoints(level27Cost - level30Cost);
		}
	}
}
