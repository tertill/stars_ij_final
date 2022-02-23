public class Main {
    public static void main(String[] args) {
        // setup
        /*
        ImageIcon image = new ImageIcon("oceanstar.png");

        JLabel text = new JLabel();
        text.setText("this will soon be used to house the components of the game!");
        text.setIcon(image);

        text.setForeground(Color.white);

        text.setFont(new Font("Comic Sans MS", Font.BOLD, 15));

        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 800);
        frame.setTitle("stars");
        frame.setResizable(false);
        frame.setVisible(true);

        frame.getContentPane().setBackground(Color.black);

        frame.getContentPane().add(text);

        ImageIcon pic = new ImageIcon("pictures\\oceanstar.png");
        frame.setIconImage(pic.getImage());
        */

        Game engine = new Game();

        engine.rungame();


    }
}
