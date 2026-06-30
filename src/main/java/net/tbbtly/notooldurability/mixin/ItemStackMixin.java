package net.tbbtly.notooldurability.mixin;

import net.tbbtly.notooldurability.config.NoToolDurabilityState;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ItemStack.class)
public abstract class ItemStackMixin {

	@Shadow public abstract int getDamageValue();

	@Inject(
			method = "setDamageValue(I)V",
			at = @At("HEAD"),
			cancellable = true
	)
	private void notooldurability$cancelDamageIncrease(int newDamage, CallbackInfo ci) {
		if (NoToolDurabilityState.isEnabled() && newDamage > getDamageValue()) {
			ci.cancel();
		}
	}
}