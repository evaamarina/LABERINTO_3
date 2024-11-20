import java.awt.*;

public class Objeto {
    private final int x;
    private final int y;
    private boolean recogido;

    public Objeto(int x, int y) {
        this.x = x;
        this.y = y;
        this.recogido = false;
    }

    public void dibujar(Graphics g, int tileSize) {
        if (!recogido) {
            g.setColor(Color.BLUE);
            g.fillRect(x * tileSize + 10, y * tileSize + 10, tileSize - 20, tileSize - 20);
        }
    }

    public boolean estaEn(int jugadorX, int jugadorY) {
        return x == jugadorX && y == jugadorY;
    }

    public void recoger() {
        this.recogido = true;
    }

    public boolean estaRecogido() {
        return recogido;
    }
}