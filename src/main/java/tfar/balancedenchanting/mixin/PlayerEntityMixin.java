package tfar.balancedenchanting.mixin;

import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import tfar.balancedenchanting.BalancedEnchanting;

@Mixin(Player.class)
public class PlayerEntityMixin {
	@Redirect(at = @At(value = "INVOKE",target = "Lnet/minecraft/world/entity/player/Player;giveExperienceLevels(I)V"), method = "onEnchantmentPerformed")
	private void balancedEnchantingTable(Player playerEntity, int levels) {
		BalancedEnchanting.handleEnchantXp(playerEntity, levels);
	}
}
