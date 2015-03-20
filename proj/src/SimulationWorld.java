import org.newdawn.slick.Color;
import org.newdawn.slick.Image;

import java.util.Arrays;

public class SimulationWorld {

    private int width;
    private int height;

    private boolean[][] cellData;
    private boolean[][] bufferedCellData;

    public SimulationWorld(PixelConverter pixelConverter, Image img) {
        width = img.getWidth();
        height = img.getHeight();

        cellData = new boolean[width][height];
        bufferedCellData = new boolean[width][height];

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                Color currentColor = img.getColor(x, y);
                cellData[x][y] = pixelConverter.convertPixel(currentColor);
            }
        }

    }

    public void step() {
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                bufferedCellData[x][y] = checkCellAlive(x, y);
            }
        }

        boolean[][] temp = cellData;
        cellData = bufferedCellData;
        bufferedCellData = temp;
    }

    private boolean checkCellAlive(int x, int y) {
        int neighborCount = 0;

        for (int deltaX = -1; deltaX <= 1; deltaX++) {
            for (int deltaY = -1; deltaY <= 1; deltaY++) {
                if (deltaX != 0 || deltaY != 0) {
                    int currentX = x + deltaX;
                    int currentY = y + deltaY;
                    if (currentX >= 0 && currentX < width && currentY >= 0 && currentY < height) {
                        if (cellData[currentX][currentY]) {
                            neighborCount++;
                        }
                    }
                }
            }
        }

        return neighborCount == 2 || neighborCount == 3;
    }

    public boolean isCellAlive(int x, int y) {
        return cellData[x][y];
    }

    public void printWorldState() {
        for (int i = 0; i < cellData.length; i++) {
            System.out.println(Arrays.toString(cellData[i]) + "\n");
        }
    }
}
