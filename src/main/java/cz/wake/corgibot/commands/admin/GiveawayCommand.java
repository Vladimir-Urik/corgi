package cz.wake.corgibot.commands.admin;

import cz.wake.corgibot.commands.Command;
import cz.wake.corgibot.commands.CommandType;
import cz.wake.corgibot.commands.CommandUse;
import cz.wake.corgibot.commands.Rank;
import cz.wake.corgibot.managers.Giveaway;
import cz.wake.corgibot.utils.Constants;
import cz.wake.corgibot.utils.MessageUtils;
import me.jagrosh.jdautilities.waiter.EventWaiter;
import net.dv8tion.jda.core.entities.*;

public class GiveawayCommand implements Command {

    @Override
    public void onCommand(User sender, MessageChannel channel, Message message, String[] args, Member member, EventWaiter w) {
        String str = message.getRawContent().substring(9).trim();
        String[] parts = str.split("\\s+", 2);
        try {
            int sec = Integer.parseInt(parts[0]);
            channel.sendMessage(MessageUtils.getEmbed(Constants.GRAY).setDescription("Generuji...").build()).queue(m -> {
                m.addReaction("\uD83C\uDF89").queue();
                new Giveaway(sec, m, parts.length > 1 ? parts[1] : null).start();
            });
            message.delete().queue();
        } catch (NumberFormatException ex) {
            MessageUtils.sendErrorMessage("Nelze zadat vteřiny v tomto tvaru `" + parts[0] + "`", channel);
        }
    }

    @Override
    public String getCommand() {
        return "giveaway";
    }

    @Override
    public String getDescription() {
        return "Giveawaye na rozdávání!";
    }

    @Override
    public String getHelp() {
        return null;
    }

    @Override
    public CommandType getType() {
        return CommandType.MODERATION;
    }

    @Override
    public CommandUse getUse() {
        return CommandUse.GUILD;
    }

    @Override
    public Rank getRank() {
        return Rank.MODERATOR;
    }
}

