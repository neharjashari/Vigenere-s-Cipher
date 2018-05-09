package VigenereCipher;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;

import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Random;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.TransferHandler;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.ToolItem;

import com.ibm.icu.impl.locale.AsciiUtil;

import javax.swing.JSlider;
import javax.swing.JEditorPane;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;

import javax.swing.JFormattedTextField;
import java.awt.Color;
import javax.swing.UIManager;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.border.EtchedBorder;

public class Vigenere
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
					Vigenere window = new Vigenere();
					window.frmVigenereCipher.setVisible(true);
				} catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}

	char[] alfabeti = { 'A', 'B', 'C', 'Ç', 'D', 'E', 'Ë', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q',
			'R', 'S', 'T', 'U', 'V', 'X', 'Y', 'Z' };

	char[] alfabetiLow = { 'a', 'b', 'c', 'ç', 'd', 'e', 'ë', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p',
			'q', 'r', 's', 't', 'u', 'v', 'x', 'y', 'z' };

	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JTextField txtPlaintexti;
	private JTextField txtCiphertexti;
	private JTextField txtTekstiDekriptuar;
	private JTextField txtCelesi;

	/**
	 * Create the application.
	 */
	public Vigenere()
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

		JLabel lblPlaintexti = new JLabel("Plaintexti:");
		lblPlaintexti.setForeground(Color.WHITE);
		lblPlaintexti.setFont(new Font("Trebuchet MS", Font.PLAIN, 34));
		lblPlaintexti.setBounds(15, 35, 191, 29);
		frmVigenereCipher.getContentPane().add(lblPlaintexti);

		JLabel lblCiphertexti = new JLabel("Ciphertexti:");
		lblCiphertexti.setForeground(Color.WHITE);
		lblCiphertexti.setFont(new Font("Trebuchet MS", Font.PLAIN, 34));
		lblCiphertexti.setBounds(15, 130, 308, 43);
		frmVigenereCipher.getContentPane().add(lblCiphertexti);

		JLabel lblTekstiIDekriptuar = new JLabel("Teksti i dekriptuar:");
		lblTekstiIDekriptuar.setForeground(Color.WHITE);
		lblTekstiIDekriptuar.setFont(new Font("Trebuchet MS", Font.PLAIN, 34));
		lblTekstiIDekriptuar.setBounds(15, 241, 308, 43);
		frmVigenereCipher.getContentPane().add(lblTekstiIDekriptuar);

		JRadioButton rdbtnEnglish = new JRadioButton("English");
		rdbtnEnglish.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		rdbtnEnglish.setForeground(Color.WHITE);
		buttonGroup.add(rdbtnEnglish);
		rdbtnEnglish.setSelected(true);
		rdbtnEnglish.setFont(new Font("Trebuchet MS", Font.PLAIN, 26));
		rdbtnEnglish.setOpaque(false);
		rdbtnEnglish.setBounds(416, 435, 115, 29);
		frmVigenereCipher.getContentPane().add(rdbtnEnglish);

		JRadioButton rdbtnShqip = new JRadioButton("Shqip");
		rdbtnShqip.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		rdbtnShqip.setForeground(Color.WHITE);
		buttonGroup.add(rdbtnShqip);
		rdbtnShqip.setFont(new Font("Trebuchet MS", Font.PLAIN, 26));
		rdbtnShqip.setOpaque(false);
		rdbtnShqip.setBounds(582, 435, 93, 29);
		frmVigenereCipher.getContentPane().add(rdbtnShqip);

		txtPlaintexti = new JTextField();
		txtPlaintexti.addFocusListener(new FocusAdapter()
		{
			@Override
			public void focusGained(FocusEvent arg0)
			{
				txtPlaintexti.setBackground(new Color(200, 225, 242));
			}

			@Override
			public void focusLost(FocusEvent e)
			{
				txtPlaintexti.setBackground(Color.WHITE);
			}
		});
		txtPlaintexti.setFont(new Font("Trebuchet MS", Font.PLAIN, 26));
		txtPlaintexti.setBounds(15, 75, 308, 43);
		frmVigenereCipher.getContentPane().add(txtPlaintexti);
		txtPlaintexti.setColumns(10);

		txtCiphertexti = new JTextField();
		txtCiphertexti.addFocusListener(new FocusAdapter()
		{
			@Override
			public void focusGained(FocusEvent arg0)
			{
				txtCiphertexti.setBackground(new Color(200, 225, 242));
			}

			@Override
			public void focusLost(FocusEvent e)
			{
				txtCiphertexti.setBackground(Color.WHITE);
			}
		});
		txtCiphertexti.setFont(new Font("Trebuchet MS", Font.PLAIN, 26));
		txtCiphertexti.setColumns(10);
		txtCiphertexti.setBounds(15, 184, 308, 43);
		frmVigenereCipher.getContentPane().add(txtCiphertexti);

		txtTekstiDekriptuar = new JTextField();
		txtTekstiDekriptuar.setEditable(false);
		txtTekstiDekriptuar.setFont(new Font("Trebuchet MS", Font.PLAIN, 26));
		txtTekstiDekriptuar.setColumns(10);
		txtTekstiDekriptuar.setBounds(15, 295, 308, 43);
		frmVigenereCipher.getContentPane().add(txtTekstiDekriptuar);

		JLabel lblCelesi = new JLabel("Celesi:");
		lblCelesi.setForeground(Color.WHITE);
		lblCelesi.setFont(new Font("Trebuchet MS", Font.PLAIN, 34));
		lblCelesi.setBounds(721, 130, 105, 43);
		frmVigenereCipher.getContentPane().add(lblCelesi);

		JButton btnGjenero = new JButton("");
		btnGjenero.setIcon(new ImageIcon(Vigenere.class.getResource("/images_files/Webp.net-resizeimage (4).png")));
		btnGjenero.setContentAreaFilled(false);
		btnGjenero.setBorderPainted(false);

		btnGjenero.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnGjenero.setBackground(Color.WHITE);
		
		btnGjenero.setFont(new Font("Trebuchet MS", Font.PLAIN, 26));
		btnGjenero.setBounds(785, 241, 69, 59);
		frmVigenereCipher.getContentPane().add(btnGjenero);

		JButton btnEnkripto = new JButton("");
		btnEnkripto.setBorderPainted(false);
		btnEnkripto.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnEnkripto.setIcon(new ImageIcon(Vigenere.class.getResource("/images_files/ico_22.png")));
		btnEnkripto.setOpaque(false);
		btnEnkripto.setContentAreaFilled(false);
		
		btnEnkripto.setFont(new Font("Trebuchet MS", Font.PLAIN, 26));
		btnEnkripto.setBounds(53, 368, 69, 96);
		frmVigenereCipher.getContentPane().add(btnEnkripto);

		JButton btnDekripto = new JButton("");
		btnDekripto.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		btnDekripto.setIcon(new ImageIcon(Vigenere.class.getResource("/images_files/ico_2.png")));
		btnDekripto.setOpaque(false);
		btnDekripto.setContentAreaFilled(false);
		btnDekripto.setBorderPainted(false);
		btnDekripto.setFont(new Font("Tahoma", Font.PLAIN, 26));
		btnDekripto.setBounds(179, 374, 69, 73);
		frmVigenereCipher.getContentPane().add(btnDekripto);

		txtCelesi = new JTextField();
		

			
		txtCelesi.addFocusListener(new FocusAdapter()
		{
			@Override
			public void focusGained(FocusEvent arg0)
			{
				txtCelesi.setBackground(new Color(200, 225, 242));
			}
			@Override
			public void focusLost(FocusEvent e)
			{
				txtCelesi.setBackground(Color.WHITE);
			}
		});
		txtCelesi.setFont(new Font("Trebuchet MS", Font.PLAIN, 26));
		txtCelesi.setColumns(10);
		txtCelesi.setBounds(721, 184, 204, 43);
		frmVigenereCipher.getContentPane().add(txtCelesi);

		JRadioButton rdbtnAllCharacters = new JRadioButton("All Characters");
		rdbtnAllCharacters.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		rdbtnAllCharacters.setForeground(Color.WHITE);
		buttonGroup.add(rdbtnAllCharacters);
		rdbtnAllCharacters.setFont(new Font("Trebuchet MS", Font.PLAIN, 26));
		rdbtnAllCharacters.setOpaque(false);
		rdbtnAllCharacters.setBounds(729, 435, 198, 29);
		frmVigenereCipher.getContentPane().add(rdbtnAllCharacters);

		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(Vigenere.class.getResource("/images_files/england_ico.png")));
		label.setBounds(526, 435, 50, 29);
		frmVigenereCipher.getContentPane().add(label);

		JLabel label_1 = new JLabel("");
		label_1.setIcon(new ImageIcon(Vigenere.class.getResource("/images_files/albania_ico.png")));
		label_1.setBounds(677, 435, 46, 29);
		frmVigenereCipher.getContentPane().add(label_1);

		JLabel label_2 = new JLabel("");
		label_2.setBackground(Color.WHITE);
		label_2.setIcon(new ImageIcon(Vigenere.class.getResource("/images_files/final_background.png")));
		label_2.setBounds(0, 0, 938, 480);
		frmVigenereCipher.getContentPane().add(label_2);

	}

	
}