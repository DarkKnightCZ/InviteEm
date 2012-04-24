package darkknightcz.InviteEm;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

public class Settings extends YamlConfiguration {
	public static final String CONFIG_FILE = "config.yml";

	public static String host;
	public static String username;
	public static String password;
	public static String database;

	public static Integer MaxInvitations; /* default invitations per user */
	public static List<String> deniedIps = new ArrayList<String>();

	public static Integer inviteMoney;
	public static Integer registerMoney;
	
	public static List<String> banOverride;

	/* LOCALES */
	public static String kickMessage;
	public static String cannotInvite;
	public static String alreadyInvited;
	public static String alreadyRegistered;
	public static String successfullyInvited;
	public static String somethingWentWrong;
	public static String youHaveToBePlayer;
	public static String ipIsOnList;
	public static String playerDoesNotExist;
	public static String playerOffset;
	public static String somethingWentWrongOffset;
	public static String offsetChanged;
	public static String registerMoneyMessage;
	public static String inviteMoneyMessage;
	public static String rewardCanceled;
	public static String youHaveBeenWarned;
	public static String IpAddedOnList;
	public static String IpNotAdded;

	public static String ipConflict;

	public final Plugin plugin;
	public FileConfiguration configFile = null;

	public Settings(Plugin plugin) {
		this.plugin = plugin;
		this.configFile = plugin.getConfig();
		this.plugin.getConfig().options().copyDefaults(true);
		this.plugin.saveConfig();
	}

	public void load() {
		host = configFile.getString("settings.database.host", "localhost");
		username = configFile.getString("settings.database.username",
				"username");
		password = configFile.getString("settings.database.password",
				"password");
		database = configFile.getString("settings.database.database",
				"minecraft");

		inviteMoney = configFile.getInt("economy.InviteMoney");
		registerMoney = configFile.getInt("economy.RegisterMoney");
		
		
		banOverride = configFile.getStringList("ban.override");
		

		kickMessage = configFile.getString("locale.KickMessage",
				"You are not invited nor registered!");
		cannotInvite = configFile.getString("locale.CannotInvite",
				"You cannot invite another player!");
		alreadyInvited = configFile.getString("locale.AlreadyInvited",
				"User USER is already invited!");
		alreadyRegistered = configFile.getString("locale.AlreadyRegistered",
				"User USER is already registered!");
		successfullyInvited = configFile.getString("locale.SuccesfullyInvited",
				"User USER has been successfully invited!");
		somethingWentWrong = configFile.getString("locale.SomethingWentWrong",
				"Something went wrong and the user hasn't been invited!");
		youHaveToBePlayer = configFile.getString("locale.YouHaveToBePlayer",
				"You have to be player in order to invite another player!");
		ipIsOnList = configFile
				.getString("locale.IpIsOnList",
						"We are sorry, but new registrations from your IP address are banned!");
		playerDoesNotExist = configFile.getString("locale.PlayerDoesNotExist",
				"That player does not play on our server!");
		playerOffset = configFile.getString("locale.PlayerOffset",
				"Player PLAYER can invite OFFSET more players up to MAX!");
		somethingWentWrong = configFile
				.getString(
						"locale.SomethingWentWrong",
						"Something went wrong and the offset couldn't be changed. Maybe that player does not play on this server!");
		offsetChanged = configFile
				.getString(
						"locale.offsetChanged",
						"Invitation offset for player PLAYER has been changed, it is now OFFSET. Player can invite up to MAX player!");

		inviteMoneyMessage = configFile.getString("locale.InviteMoney",
				"You have been awarded MONEY for inviting player PLAYER!");
		registerMoneyMessage = configFile.getString("locale.RegisterMoney",
				"You have been awarde MONEY for joining our server!");
		rewardCanceled = configFile.getString("locale.RewardCanceled",
				"Reward for player PLAYER has been canceled due to REASON!");
		ipConflict = configFile.getString("locale.IPConflict",
				"same IP address");

		youHaveBeenWarned = configFile.getString("locale.YouHaveBeenWarned",
				"You have been warned for REASON");


		IpAddedOnList = configFile.getString("locale.IpAddedOnList",
				"IP has been added to the denied list!");
		IpNotAdded = configFile.getString("locale.IpAddedOnList",
				"IP has NOT been added to the denied list! There is probably same ip in the database!");

		MaxInvitations = configFile.getInt("settings.MaxInvitations");
	}

	public static boolean isOnList(String ip) {
		return deniedIps.contains(ip);

	}
}
