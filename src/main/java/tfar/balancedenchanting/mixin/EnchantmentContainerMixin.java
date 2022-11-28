package tfar.balancedenchanting.mixin;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.EnchantmentMenu;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import tfar.balancedenchanting.BalancedEnchanting;

@Mixin(EnchantmentMenu.class)
public class EnchantmentContainerMixin {

    @Inject(method = "clickMenuButton",at = @At("HEAD"))
    private void captureIndex(Player playerIn, int id, CallbackInfoReturnable<Boolean> cir) {
        BalancedEnchanting.id.set(id);
    }
}
