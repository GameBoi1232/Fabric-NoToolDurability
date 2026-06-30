package net.tbbtly.notooldurability;

import net.fabricmc.api.ModInitializer;
import net.tbbtly.notooldurability.command.NoToolDurabilityCommand;
import net.tbbtly.notooldurability.config.NoToolDurabilityState;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/*
 *
 * Prevents all durability loss on any item that normally consumes durability
 * (tools, weapons, armor, bows, fishing rods, flint-and-steel, etc.).
 *
 * Server operators can toggle the feature on or off at runtime with:
 *   /tooldurability enable
 *   /tooldurability disable
 *   /tooldurability toggle
 *   /tooldurability status
 *
 */

public class NoToolDurability implements ModInitializer {

	public static final String MOD_ID = "notooldurability";
	public static final Logger LOGGER  = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {

		NoToolDurabilityCommand.register();

		LOGGER.info("NoToolDurability initialised. Durability removal is {}.",
				NoToolDurabilityState.isEnabled() ? "DISABLED (Items are Unbreakable)" : "ENABLED (Vanilla Behaviour)");
	}

}
