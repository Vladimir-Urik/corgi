package cz.wake.corgibot.commands.admin;

import cz.wake.corgibot.commands.Command;
import cz.wake.corgibot.commands.CommandType;
import cz.wake.corgibot.commands.CommandUse;
import cz.wake.corgibot.commands.Rank;
import cz.wake.corgibot.utils.CPUDaemon;
import me.jagrosh.jdautilities.waiter.EventWaiter;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.JDAInfo;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.User;

import java.lang.management.ManagementFactory;

public class BotStatsCommand implements Command {

    @Override
    public void onCommand(User sender, MessageChannel channel, Message message, String[] args, Member member, EventWaiter w) {
        if (args.length < 1) {
            long totalMb = Runtime.getRuntime().totalMemory() / (1024 * 1024);
            long usedMb = (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) / (1024 * 1024);
            EmbedBuilder embed = new EmbedBuilder();
            embed.addField("Uptime", getUptime(), true);
            embed.addField("Počet uživatelů", String.valueOf(channel.getJDA().getUsers().size()), true);
            embed.addField("Text channelů", String.valueOf(channel.getJDA().getTextChannels().size()), true);
            embed.addField("Private channelů", String.valueOf(channel.getJDA().getPrivateChannels().size()), true);
            embed.addField("Voice channelů", String.valueOf(channel.getJDA().getVoiceChannels().size()), true);
            embed.addField("Guilds", String.valueOf(channel.getJDA().getGuilds().size()), true);
            embed.addField("Paměť", usedMb + "MB / " + totalMb + "MB", true);
            embed.addField("Zatížení", ((int) (CPUDaemon.get() * 10000)) / 100d + "%", true);
            embed.addField("Threads", String.valueOf(Thread.getAllStackTraces().size()), true);
            embed.addField("Počet odpovědí", String.valueOf(channel.getJDA().getResponseTotal()), true);
            embed.addField("JDA verze", JDAInfo.VERSION, true);
            embed.addField("Majitel", channel.getJDA().getUserById("177516608778928129").getAsMention(), true);
            channel.sendMessage(embed.build()).queue();
        }

    }

    @Override
    public String getCommand() {
        return "bstats";
    }

    @Override
    public String getHelp() {
        return null;
    }

    @Override
    public CommandType getType() {
        return CommandType.BOT_OWNER;
    }

    @Override
    public CommandUse getUse() {
        return CommandUse.ALL;
    }

    @Override
    public Rank getRank() {
        return Rank.BOT_OWNER;
    }

    private String getUptime(){
        long millis = ManagementFactory.getRuntimeMXBean().getUptime();
        long seconds = millis / 1000;
        long minutes = seconds / 60;
        long hours = minutes / 60;
        long days = hours / 24;
        return String.format("%d dní, %02d hodin, %02d minut", days, hours % 24, minutes % 60);
    }
}
