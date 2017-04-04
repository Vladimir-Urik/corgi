package cz.wake.corgibot.commands.user;

import cz.wake.corgibot.commands.Command;
import cz.wake.corgibot.commands.CommandType;
import cz.wake.corgibot.utils.MessageUtils;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.entities.User;

import java.awt.*;
import java.util.concurrent.ThreadLocalRandom;

public class EightBallCommand implements Command {

    static String outcomes[] = {"Ano.", "Ne.", "S největší pravděpodobností ANO!", "Archanděl Wakošuk mi sdělil, že ANO!", "Možná.", "Počkej zamyslím se, ANO!", "Pravděpodobně ne!", "Nepravděpodobně...", "Když se nad tím zamyšlíš, je to možné!", "Pokuď budou padat eura ze stromu, tak ano."};

    @Override
    public void onCommand(User sender, TextChannel channel, Message message, String[] args, Member member) {
        try {
            if(args.length < 1){
                channel.sendMessage(sender.getAsMention() + " Musíš položit otázku, neumím číst myšlenky!").queue();
            } else {
                //channel.sendMessage(outcomes[ThreadLocalRandom.current().nextInt(0, outcomes.length)]).queue();
                channel.sendMessage(MessageUtils.getEmbed(sender, new Color(230,100,150)).addField(sender.getName() + " se ptá:", message.getRawContent().replace(".8ball ", ""), false).addField("Corgi odpovídá:", outcomes[ThreadLocalRandom.current().nextInt(0, outcomes.length)], false).build()).queue();
            }
        } catch (Exception e){
            MessageUtils.sendErrorMessage("Chyba při provádění příkazu!", channel);
        }
    }

    @Override
    public String getCommand() {
        return "8ball";
    }

    @Override
    public String getDescription() {
        return "Generátor pravy!";
    }

    @Override
    public CommandType getType() {
        return CommandType.GENERAL;
    }
}