package cz.wake.corgibot.commands.user;

import com.jagrosh.jdautilities.commons.waiter.EventWaiter;
import cz.wake.corgibot.annotations.SinceCorgi;
import cz.wake.corgibot.commands.Command;
import cz.wake.corgibot.commands.CommandCategory;
import cz.wake.corgibot.objects.GuildWrapper;
import cz.wake.corgibot.utils.Constants;
import cz.wake.corgibot.utils.EmoteList;
import cz.wake.corgibot.utils.MessageUtils;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

@SinceCorgi(version = "1.2.2")
public class Cat implements Command {

    @Override
    public void onCommand(MessageChannel channel, Message message, String[] args, Member member, EventWaiter w, GuildWrapper gw) {
        String url = "";
        OkHttpClient caller = new OkHttpClient();
        Request request = new Request.Builder().url("http://thecatapi.com/api/images/get").build();
        try {
            Response response = caller.newCall(request).execute();
            url = response.request().url().toString();
        } catch (Exception e) {
            MessageUtils.sendErrorMessage("Something went wrong! Try again later..", channel);
        }
        channel.sendMessage(MessageUtils.getEmbed(Constants.ORANGE).setTitle(EmoteList.CAT + " | " + "Random cat image:").setImage(url).build()).queue();
    }

    @Override
    public String getCommand() {
        return "cat";
    }

    @Override
    public String getDescription() {
        return "Random cat images";
    }

    @Override
    public String getHelp() {
        return "%cat - Generate some cat image";
    }

    @Override
    public CommandCategory getCategory() {
        return CommandCategory.FUN;
    }

    @Override
    public String[] getAliases() {
        return new String[]{"rcat"};
    }
}
