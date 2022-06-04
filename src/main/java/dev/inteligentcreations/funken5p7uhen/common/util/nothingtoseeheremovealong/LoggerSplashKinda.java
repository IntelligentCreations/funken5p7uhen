package dev.inteligentcreations.funken5p7uhen.common.util.nothingtoseeheremovealong;

import dev.inteligentcreations.funken5p7uhen.funken5p7uhen;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class LoggerSplashKinda
{
    public static List<String> splashes = new ArrayList<>();

    public static void generateRandomSplash()
    {
        Random random = new Random();
        addSplash("TeaCon is a kind of wild tea house you can find naturally...");
        addSplash("Actually this mod does nothing... probably.");
        addSplash("I LOVE MUDROCK AAAAAAAAAAAAAAA");
        addSplash("I'm actually not a logger");
        addSplash("Initialized... maybe");
        addSplash("jvav.lang.NullPointerExceptionButThisIsNothingHahaye");
        addSplash("Please stop...");
        addSplash("YES INTELLIGENTCREATIONS");
        addSplash("YES MIKHAILTAPIO");
        addSplash("87e8275ccb0908633b8b3d639ed07c44");
        addSplash("                             i probably said nothing i guess");
        funken5p7uhen.LOGGER.info(splashes.get(random.nextInt(splashes.size())));
    }

    protected LoggerSplashKinda()
    {
    }

    public static LoggerSplashKinda getInstance()
    {
        return new LoggerSplashKinda();
    }

    public List<String> getListOfSplashes()
    {
        return splashes;
    }

    public static void addSplash(String splash)
    {
        LoggerSplashKinda.getInstance().getListOfSplashes().add(splash);
    }
}
