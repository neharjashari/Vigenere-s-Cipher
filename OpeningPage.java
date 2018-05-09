package VigenereCipher;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.Cursor;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import VigenereCipher.Vigenere;
import VigenereCipher.VigenereFile;

public class OpeningPage
{

	private JFrame frmVigenereCipher;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				try
				{
					OpeningPage window = new OpeningPage();
					window.frmVigenereCipher.setVisible(true);
				} catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public OpeningPage()
	{
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize()
	{
		frmVigenereCipher = new JFrame();
		frmVigenereCipher.setIconImage(
				Toolkit.getDefaultToolkit().getImage(Vigenere.class.getResource("/images_files/lock-icon.png")));
		frmVigenereCipher.setResizable(false);
		frmVigenereCipher.setTitle("Vigenere Cipher");
		frmVigenereCipher.setBounds(100, 100, 944, 520);
		frmVigenereCipher.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmVigenereCipher.getContentPane().setLayout(null);
		frmVigenereCipher.setLocationRelativeTo(null);

		JLabel label = new JLabel("Vigenere's Cipher");
		label.setForeground(Color.WHITE);
		label.setFont(new Font("Trebuchet MS", Font.PLAIN, 40));
		label.setBounds(27, 129, 317, 85);
		frmVigenereCipher.getContentPane().add(label);

		JButton button = new JButton("Encrypt File");
		button.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				VigenereFile.main(null);
				frmVigenereCipher.dispose();
			}
		});
		button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		button.setFont(new Font("Verdana", Font.PLAIN, 15));
		button.setBounds(39, 254, 133, 38);
		frmVigenereCipher.getContentPane().add(button);

		JButton button_1 = new JButton("Encrypt Text");
		button_1.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				Vigenere.main(null);
				frmVigenereCipher.dispose();
			}
		});
		button_1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		button_1.setFont(new Font("Verdana", Font.PLAIN, 15));
		button_1.setBounds(187, 254, 133, 38);
		frmVigenereCipher.getContentPane().add(button_1);

		JLabel label_2 = new JLabel("");
		label_2.setBackground(Color.WHITE);
		label_2.setIcon(new ImageIcon(Vigenere.class.getResource("/images_files/final_background.png")));
		label_2.setBounds(0, 0, 938, 480);
		frmVigenereCipher.getContentPane().add(label_2);
	}
}
