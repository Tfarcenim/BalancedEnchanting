package tfar.balancedenchanting.mixin;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AnvilMenu;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import tfar.balancedenchanting.BalancedEnchanting;

@Mixin(AnvilMenu.class)
public class RepairContainerMixin {
	@Redirect(at = @At(value = "INVOKE",target = "Lnet/minecraft/world/entity/player/Player;giveExperienceLevels(I)V"), method = "onTake")
	private void balancedAnvil(Player playerEntity, int levels) {
		playerEntity.giveExperiencePoints(-BalancedEnchanting.convertLevelToTotalxp(-levels));
	}
}
