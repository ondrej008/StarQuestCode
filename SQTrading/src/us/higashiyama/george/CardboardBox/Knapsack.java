
package us.higashiyama.george.CardboardBox;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.entity.Damageable;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import us.higashiyama.george.CardboardBox.Utils.CardboardPotionEffect;
import us.higashiyama.george.Currencies.Crate;

public class Knapsack implements Serializable {

	// Knapsack represents a player inventory. It holds two crates as well as
	// health, xp, potion effects, and hunger
	private static final long serialVersionUID = 6764493511554613048L;

	private final int hunger;
	private final double health;
	private final float xp;
	private final int xpLevel;
	private List<CardboardPotionEffect> potions = new ArrayList<CardboardPotionEffect>();
	private final Crate inventory;
	private final Crate armor;

	public Knapsack(Player p) {

		this.hunger = p.getFoodLevel();
		this.health = ((Damageable) p).getHealth();
		this.xp = p.getExp();
		this.xpLevel = p.getLevel();
		this.potions = CardboardPotionEffect.boxPotions(p.getActivePotionEffects());
		this.inventory = new Crate(p.getInventory());
		this.armor = new Crate(p.getInventory().getArmorContents());

	}

	public void unpack(Player p) {

		p.setFoodLevel(this.hunger);
		p.setHealth(this.health);
		p.setExp(this.xp);
		p.setLevel(this.xpLevel);
		p.addPotionEffects(CardboardPotionEffect.unboxPotions(this.potions));
		this.inventory.unpack(p.getInventory());
		ItemStack[] armor = this.armor.unpackItemArray();
		PlayerInventory inv = p.getInventory();
		inv.setHelmet(armor[3]);
		inv.setChestplate(armor[2]);
		inv.setLeggings(armor[1]);
		inv.setBoots(armor[0]);

	}
}
