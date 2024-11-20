import java.awt.*;

public class Jugador {
    private int x;
    private int y;
    private final Laberinto laberinto;

    public Jugador(int startX, int startY, Laberinto laberinto) {
        this.x = startX;
        this.y = startY;
        this.laberinto = laberinto;
    }

    public void dibujar(Graphics g) { //PONER IMAGEN PATO
        int tileSize = laberinto.getTileSize();
        g.setColor(Color.RED);
        g.fillOval(x * tileSize + 5, y * tileSize + 5, tileSize - 10, tileSize - 10);
    }

    public void mover(int dx, int dy) {
        int nuevoX = x + dx;
        int nuevoY = y + dy;

        if (laberinto.esTransitable(nuevoX, nuevoY)) {
            x = nuevoX;
            y = nuevoY;
        }
    }

    public boolean haLlegadoALaSalida() {
        return x == Laberinto.COLUMNAS - 2 && y == Laberinto.FILAS - 1;
    }
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}