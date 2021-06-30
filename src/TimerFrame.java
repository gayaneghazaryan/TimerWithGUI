import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TimerFrame extends JFrame implements ActionListener {
    JLabel counterLabel;
    JLabel title;
    JLabel minutes;
    JLabel seconds;
    JPanel textPanel;
    JTextField minuteTextField;
    JTextField secondTextField;
    JButton startButton;

    public TimerFrame() {
        setSize(500,400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLayout(new GridLayout(4,1,0,50));

        title = new JLabel();
        title.setText("Timer");
        title.setHorizontalAlignment(JLabel.CENTER);
        title.setPreferredSize(new Dimension(20,20));
        add(title);

        textPanel = new JPanel();
        textPanel.setBorder(new EmptyBorder(0,100,0,100));
        textPanel.setLayout(new GridLayout(2,2,0,0));

        minutes = new JLabel();
        minutes.setText("Minutes:");
        minutes.setHorizontalAlignment(JLabel.LEFT);

        seconds = new JLabel();
        seconds.setText("Seconds:");
        seconds.setHorizontalAlignment(JLabel.LEFT);

        minuteTextField = new JTextField("00");
        minuteTextField.setPreferredSize(new Dimension(20,20));


        secondTextField = new JTextField("00");
        secondTextField.setPreferredSize(new Dimension(20,20));

        textPanel.add(minutes);
        textPanel.add(minuteTextField);
        textPanel.add(seconds);
        textPanel.add(secondTextField);


        counterLabel = new JLabel("00:00");
        counterLabel.setBounds(300,230,100,50);
        counterLabel.setHorizontalAlignment(JLabel.CENTER);
        counterLabel.setFont(new Font("Arial",Font.PLAIN,70));
        add(counterLabel);

        add(textPanel);

        startButton = new JButton("START");
        startButton.addActionListener(this);
        add(startButton);
        setVisible(true);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        int minutes = Integer.parseInt(minuteTextField.getText());
        int seconds = Integer.parseInt(secondTextField.getText());
        if(e.getActionCommand().equals(startButton.getActionCommand())) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    counterLabel.setEnabled(false);
                    int time = (minutes * 60) + seconds;
                    int changing_minutes = minutes;
                    int changing_seconds = seconds;

                    for (int i = 0; i <= time ; i++) {

                        //printing format
                        if(changing_minutes <= 9) {
                            if(changing_seconds <= 9)
                                counterLabel.setText(String.format("0%d:0%d\n",changing_minutes,changing_seconds));
                            else
                                counterLabel.setText(String.format("0%d:%d\n",changing_minutes,changing_seconds));
                        } else {
                            if(changing_seconds <= 9)
                                counterLabel.setText(String.format("%d:0%d\n",changing_minutes,changing_seconds));
                            else
                                counterLabel.setText(String.format("%d:%d\n",changing_minutes,changing_seconds));
                        }

                        //decrement
                        if(changing_seconds == 0) {
                            changing_minutes--;
                            changing_seconds = 59;
                        } else
                            changing_seconds--;

                        //time
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        counterLabel.setEnabled(true);
                    }
                }
            });

            thread.start();
        }

    }
}
