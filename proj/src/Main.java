import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
    public static void main(String[] args) {
        int maxWindowWidth = 0;
        int maxWindowHeight = 0;
        int updatesPerSecond = 0;

        if (args.length != 4) {
            printArguments();
            System.exit(1);
        }

        try {
            updatesPerSecond = Integer.parseInt(args[1]);
            maxWindowWidth = Integer.parseInt(args[2]);
            maxWindowHeight = Integer.parseInt(args[3]);
        } catch (NumberFormatException e) {
            printArguments();
            System.exit(1);
        }

        try {
            AppGameContainer appGC;
            appGC = new AppGameContainer(new Application("Game of Life with Images", args[0],
                    maxWindowWidth, maxWindowHeight));
            appGC.setShowFPS(true);
            appGC.setTargetFrameRate(updatesPerSecond);

            // Set the window to be invisibly small until texture is loaded
            appGC.setDisplayMode(0, 0, false);
            appGC.start();
        } catch (SlickException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static void printArguments() {
        System.out.println("Arguments are <filename> <updatesPerSecond> <max window width>" +
                " <max window height>");
    }
}