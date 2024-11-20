import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class LaberintoJuego extends JFrame {
    private final Laberinto laberinto;
    private final Jugador jugador;
    private final List<Objeto> objetos;
    private boolean todosObjetosRecogidos;

    public LaberintoJuego() {
        setTitle("LABERINTO 20x20");
        setSize(610, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        laberinto = new Laberinto();
        jugador = new Jugador(1, 1, laberinto);
        objetos = generarObjetos(3);
        todosObjetosRecogidos = false;

        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                laberinto.dibujar(g);
                jugador.dibujar(g);

                for (Objeto objeto : objetos) {
                    objeto.dibujar(g, laberinto.getTileSize());
                }

                if (todosObjetosRecogidos && jugador.haLlegadoALaSalida()) {
                    g.setColor(Color.GREEN);
                    g.setFont(new Font("Arial", Font.BOLD, 30));
                    g.drawString("YOU WIN!", 200, 200);
                }
            }
        };

        add(panel);

        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}

            @Override
            public void keyPressed(KeyEvent e) {
                if (!todosObjetosRecogidos || !jugador.haLlegadoALaSalida()) {
                    switch (e.getKeyCode()) {
                        case KeyEvent.VK_UP -> jugador.mover(0, -1);
                        case KeyEvent.VK_DOWN -> jugador.mover(0, 1);
                        case KeyEvent.VK_LEFT -> jugador.mover(-1, 0);
                        case KeyEvent.VK_RIGHT -> jugador.mover(1, 0);
                    }
                    verificarRecoleccion();
                    panel.repaint();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {}
        });

        setFocusable(true);
        setVisible(true);
    }

    private List<Objeto> generarObjetos(int cantidad) {
        List<Objeto> objetos = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < cantidad; i++) {
            int x, y;
            do {
                x = random.nextInt(Laberinto.COLUMNAS);
                y = random.nextInt(Laberinto.FILAS);
            } while (!laberinto.esTransitable(x, y) || hayObjetoEn(x, y, objetos));

            objetos.add(new Objeto(x, y));
        }

        return objetos;
    }

    private boolean hayObjetoEn(int x, int y, List<Objeto> objetos) {
        for (Objeto objeto : objetos) {
            if (objeto.estaEn(x, y)) {
                return true;
            }
        }
        return false;
    }

    private void verificarRecoleccion() {
        for (Objeto objeto : objetos) {
            if (!objeto.estaRecogido() && objeto.estaEn(jugador.getX(), jugador.getY())) {
                objeto.recoger();
            }
        }

        todosObjetosRecogidos = objetos.stream().allMatch(Objeto::estaRecogido);
    }

    public static void main(String[] args) {
        new LaberintoJuego();
    }
}