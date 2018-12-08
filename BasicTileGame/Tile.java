import java.awt.image.*;

public class Tile {

    private BufferedImage image;
    private boolean blocked;
    private boolean door;

    public Tile(BufferedImage image, boolean blocked) {
        this.image = image;
        this.blocked = blocked;

    }

    public BufferedImage getImage() { return image; }
    public boolean isBlocked() { return blocked; }
    public boolean isDoor() {return door; }


}
