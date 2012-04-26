package darkknightcz.InviteEm.economy;

import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import darkknightcz.InviteEm.InviteEm;
import darkknightcz.InviteEm.Settings;

public class RegisterMoney implements Runnable {
	private Player player;
	private InviteEm plugin;
	private int type;

	public RegisterMoney(InviteEm plugin, Player player, int type) {
		this.plugin = plugin;
		this.player = player;
		this.type = type;
	}

	@Override
	public void run() {
		if (player.isOnline()) {
			if (type == 0) { //at invite
				if (plugin.getDb().tryReward(player)) {
					plugin.economy.depositPlayer(player.getName(),
							Settings.registerMoney);
					player.sendMessage(ChatColor.GREEN
							+ Settings.registerMoneyMessage.replaceAll(
									"MONEY",
									Settings.registerMoney
											+ " "
											+ plugin.economy
													.currencyNamePlural()));
				} else {
					player.sendMessage(ChatColor.RED
							+ Settings.rewardCanceled.replaceAll("PLAYER",
									player.getName()).replaceAll("REASON",
									Settings.ipConflict));
				}
			} else {
				List<String> usernames = plugin.getDb().loadRewards(player);
				if (usernames != null) {
					for (String nick : usernames) {
						if (plugin.getDb().isRegistered(nick)) {
							plugin.economy.depositPlayer(player.getName(),
									Settings.inviteMoney);
							plugin.setRewarded(nick);
							player.sendMessage(ChatColor.GREEN
									+ Settings.inviteMoneyMessage
											.replaceAll(
													"MONEY",
													Settings.inviteMoney
															+ " "
															+ plugin.economy
																	.currencyNamePlural())
											.replaceAll("PLAYER", nick));
						}
					}
				}

			}
		}
	}

}