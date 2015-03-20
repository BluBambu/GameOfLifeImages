import org.newdawn.slick.*;

public class Application extends BasicGame {

    private SimulationWorld world;
    private String imgPath;
    private Image img;
    private float imageScale;
    private int desiredWidth;
    private int desiredHeight;

    public Application(String gameName, String imgPath, int desiredWidth, int desiredHeight) {
        super(gameName);
        this.imgPath = imgPath;
        this.desiredWidth = desiredWidth;
        this.desiredHeight = desiredHeight;
    }

    @Override
    public void init(GameContainer gc) throws SlickException {
        img = new Image(imgPath);
        world = new SimulationWorld(new PixelConverter(), img);
        AppGameContainer container = (AppGameContainer) gc;

        imageScale = getImageScale(img);
        System.out.println(imageScale);

        int windowWidth = (int) Math.ceil(img.getWidth() * imageScale);
        int windowHeight = (int) Math.ceil(img.getHeight() * imageScale);

        container.setDisplayMode(windowWidth, windowHeight, false);
        world.step(); // step it once before rendering so the original image isn't flashed
    }

    private float getImageScale(Image img) {
        boolean isWidthMax = img.getWidth() > img.getHeight();
        return isWidthMax ? (float) desiredWidth / img.getWidth() :
                (float) desiredHeight / img.getHeight();
    }

    @Override
    public void update(GameContainer gc, int i) throws SlickException {
        world.step();
    }

    @Override
    public void render(GameContainer gc, Graphics g) throws SlickException {
        for (int x = 0; x < img.getWidth(); x++) {
            for (int y = 0; y < img.getHeight(); y++) {
                if (world.isCellAlive(x, y)) {
                    g.setColor(img.getColor(x, y));
                    g.fillRect(x * imageScale, y * imageScale, imageScale, imageScale);
                }
            }
        }
    }
}