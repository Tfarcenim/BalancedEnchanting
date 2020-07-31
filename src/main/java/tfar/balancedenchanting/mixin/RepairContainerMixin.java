package tfar.balancedenchanting.mixin;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.container.RepairContainer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import tfar.balancedenchanting.BalancedEnchanting;

@Mixin(RepairContainer.class)
public class RepairContainerMixin {
	@Redirect(at = @At(value = "INVOKE",target = "Lnet/minecraft/entity/player/PlayerEntity;addExperienceLevel(I)V"), method = "func_230301_a_")
	private void balancedAnvil(PlayerEntity playerEntity, int levels) {
		playerEntity.giveExperiencePoints(-BalancedEnchanting.convertLevelToTotalxp(-levels));
	}
}
