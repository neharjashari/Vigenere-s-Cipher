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
		btnGjenero.addActionListener(new ActionListener()
		{
			// clickGjenero
			public void actionPerformed(ActionEvent arg0)
			{
				// gjenerimi i gjatesise se celesit
				Random rand = new Random();
				int randomNr = 3;
				while (true)
				{
					randomNr = rand.nextInt(1025);
					if (randomNr <= 1024 && randomNr >= 5)
						break;
				}
				StringBuilder celesiA = new StringBuilder();
				// Gjenerojme celesin kur nuk eshte selektuar butoni per gjuhen shqipe
				if (!rdbtnShqip.isSelected())
				{
					for (int i = 0; i < randomNr; i++)
					{
						celesiA.append((char) (rand.nextInt(26) + 'A'));
					}
				}
				// Gjenerojme celesin per implementimin e gjuhes shqipe
				else
				{
					for (int i = 0; i < randomNr; i++)
					{
						if(rand.nextInt(28) % 27 == 0)
						{
							if (rand.nextInt(10) % 2 == 0)
								celesiA.append('Ç');
							else
								celesiA.append('Ë');
						} 
						else
						{
							celesiA.append((char) (rand.nextInt(26) + 'A'));
							if (celesiA.charAt(celesiA.length() - 1) == 'W')
							{
								i--;
								celesiA.deleteCharAt(celesiA.length() - 1);
							}
						}
					}
				}
				txtCelesi.setText(celesiA.toString());
			}
		});
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
		btnEnkripto.addActionListener(new ActionListener()
		{
			// clickEnkripto
			public void actionPerformed(ActionEvent arg0)
			{
				// nese nuk ka as celes as plaintext
				if (txtCelesi.getText().isEmpty() && txtPlaintexti.getText().isEmpty())
				{
					JOptionPane.showMessageDialog(null, "Ju lutem shenoni plaintextin");
					return;
				}
				// gjenerojme nje celes te rendomte nese shfrytezuesi nuk ka dhene nje te tille
				else if (txtCelesi.getText().isEmpty())
					btnGjenero.doClick();

				// gjatesia minimale e celesit 5, maksimale 1024
				if (txtCelesi.getText().length() < 5 || txtCelesi.getText().length() > 1024)
				{
					JOptionPane.showMessageDialog(null,
							"Ju lutem jepeni nje celes valid (5 <= Gjatesia e celesit <= 1024)!");
					return;
				}

				// varesisht njera nga modet e zgjedhura
				int opsioni;
				if (rdbtnEnglish.isSelected())
					opsioni = 1;
				else if (rdbtnShqip.isSelected())
					opsioni = 2;
				else
					opsioni = 3;
				String cipherteksti = Enkripto(txtPlaintexti.getText(), txtCelesi.getText(), opsioni);
				txtCiphertexti.setText(cipherteksti);
			}
		});

		JButton btnDekripto = new JButton("");
		btnDekripto.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnDekripto.addActionListener(new ActionListener()
		{
			// clickDekripto
			public void actionPerformed(ActionEvent arg0)
			{
				// nese nuk ka as ciphertext as celes te shenuar
				if (txtCelesi.getText().isEmpty() && txtCiphertexti.getText().isEmpty())
				{
					JOptionPane.showMessageDialog(null, "Ju lutem shenoni ciphertextin");
					return;
				}
				// nese nuk ka celes per dekriptim lajmerojme userin
				else if (txtCelesi.getText().isEmpty())
				{
					JOptionPane.showMessageDialog(null, "Ju lutem shenoni celesin per dekriptim");
					return;
				}
				int opsioni;
				if (rdbtnEnglish.isSelected())
					opsioni = 1;
				else if (rdbtnShqip.isSelected())
					opsioni = 2;
				else
					opsioni = 3;
				String tekstiDekriptuar = Dekripto(txtCiphertexti.getText(), txtCelesi.getText(), opsioni);
				txtTekstiDekriptuar.setText(tekstiDekriptuar);
			}
		});
		btnDekripto.setIcon(new ImageIcon(Vigenere.class.getResource("/images_files/ico_2.png")));
		btnDekripto.setOpaque(false);
		btnDekripto.setContentAreaFilled(false);
		btnDekripto.setBorderPainted(false);
		btnDekripto.setFont(new Font("Tahoma", Font.PLAIN, 26));
		btnDekripto.setBounds(179, 374, 69, 73);
		frmVigenereCipher.getContentPane().add(btnDekripto);

		txtCelesi = new JTextField();
		txtCelesi.addKeyListener(new KeyAdapter()
		{
			// KEYPRESS tek celesi
			@Override
			public void keyPressed(KeyEvent arg0)
			{
				// nese ben paste ne textfield
				if ((arg0.getKeyCode() == KeyEvent.VK_V) && ((arg0.getModifiers() & KeyEvent.CTRL_MASK) != 0))
				{
					String data = "";
					// tentojme te marrim si string ate se cfare permban clipboard
					try
					{
						data = (String) Toolkit.getDefaultToolkit().getSystemClipboard()
								.getData(DataFlavor.stringFlavor);
					} 
					catch (HeadlessException | UnsupportedFlavorException | IOException e)
					{
						System.out.println(e.getMessage());
					}
					data = data.toUpperCase();
					// ndryshojme permbajtjen e clipboard-it
					StringSelection selection = new StringSelection(data);
					Toolkit.getDefaultToolkit().getSystemClipboard().setContents(selection, selection);
					// paste kur eshte shqip ose jo
					// me regex i themi qe fillon me cka ka brenda [], perseritet 0 ose me shume
					// here
					// dhe posahtu mbaron me ate se cfare ka brenda []
					if ((!data.matches("^[A-Z]*$") && !rdbtnShqip.isSelected())
							|| (!data.matches("^[ABCDEFGHIJKLMNOPQRSTUVXYZËÇëç]*$") && rdbtnShqip.isSelected()))
					{
						arg0.consume();
					}
				}
			}
			// KEYTYPE tek celesi
			@Override
			public void keyTyped(KeyEvent arg0)
			{
				char keyChar = arg0.getKeyChar();
				if ((arg0.getKeyChar() == 'w' || arg0.getKeyChar() == 'W') && (rdbtnShqip.isSelected()))
				{
					arg0.consume();
					Toolkit.getDefaultToolkit().beep();
				}
				// nese eshte shkronje e kthen ne uppercase
				if (Character.isAlphabetic(keyChar))
				{
					if (Character.isLowerCase(keyChar))
					{
						arg0.setKeyChar(Character.toUpperCase(keyChar));
					}
				}
				// nese nuk eshte shkronje nuk lihet te shenohet fare
				else
				{
					arg0.consume();
					Toolkit.getDefaultToolkit().beep();
				}
			}
		});

			
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
	
	int pozitaNeVargChar(char karakteri, char[] vargu)
	{
		int pozita = -1;
		for (int i = 0; i < vargu.length; i++)
		{
			if (karakteri == vargu[i])
			{
				pozita = i;
				break;
			}
		}
		return pozita;
	}

	// ---------------------------------------------------------------------------------
	// ---------------------------------METODA ENKRIPTO---------------------------------
	// ---------------------------------------------------------------------------------
	String Enkripto(String plaintexti, String celesi, int opsioni)
	{
		StringBuilder rez = new StringBuilder(plaintexti);
		// Anglisht
		if (opsioni == 1)
		{
			// ne rast se eshte nderruar butoni pas gjenerimit ose pas shenimit
			celesi = celesi.replace('Ë', 'E');
			celesi = celesi.replace('Ç', 'C');
			txtCelesi.setText(celesi);
			int ii = 0;
			for (int i = 0; i < rez.length(); i++)
			{
				if (plaintexti.charAt(i) >= 'a' && plaintexti.charAt(i) <= 'z')
				{
					int pozita = plaintexti.charAt(i) - 'a';
					int pozitaRe = (pozita + (celesi.charAt(ii % celesi.length()) - 'A')) % 26;
					char encCharacter = (char) (pozitaRe + 'a');
					rez.setCharAt(i, encCharacter);
					ii++;
				} 
				else if (plaintexti.charAt(i) >= 'A' && plaintexti.charAt(i) <= 'Z')
				{
					int pozita = plaintexti.charAt(i) - 'A';
					int pozitaRe = (pozita + (celesi.charAt(ii % celesi.length()) - 'A')) % 26;
					char encCharacter = (char) (pozitaRe + 'A');
					rez.setCharAt(i, encCharacter);
					ii++;
				} 
				else
				{
					continue;
				}
			}
		}
		// Shqip
		else if (opsioni == 2)
		{
			celesi = celesi.replace('W', 'V');
			txtCelesi.setText(celesi);
			int ii = 0;
			for (int i = 0; i < rez.length(); i++)
			{
				if (((plaintexti.charAt(i) >= 'a' && plaintexti.charAt(i) <= 'z') || plaintexti.charAt(i) == 'ë'
						|| plaintexti.charAt(i) == 'ç') && (plaintexti.charAt(i) != 'w'))
				{
					int pozita;
					int pozitaCel;
					// gjejme poziten e shkronjes se plaintext ne vargun alfabetiLow
					// dhe poziten e shkronjes se celesit ne vargun alfabeti
					pozita = pozitaNeVargChar(plaintexti.charAt(i), alfabetiLow);
					pozitaCel = pozitaNeVargChar(celesi.charAt(ii % celesi.length()), alfabeti);
					int pozitaRe = (pozita + pozitaCel) % alfabetiLow.length;
					char encCharacter = alfabetiLow[pozitaRe];
					rez.setCharAt(i, encCharacter);
					ii++;
				} 
				else if (((plaintexti.charAt(i) >= 'A' && plaintexti.charAt(i) <= 'Z') || plaintexti.charAt(i) == 'Ë'
						|| plaintexti.charAt(i) == 'Ç') && (plaintexti.charAt(i) != 'W'))
				{
					int pozita;
					int pozitaCel;
					// gjejme poziten e shkronjes se plaintext ne vargun alfabetiLow
					// dhe poziten e shkronjes se celesit ne vargun alfabeti
					pozita = pozitaNeVargChar(plaintexti.charAt(i), alfabeti);
					pozitaCel = pozitaNeVargChar(celesi.charAt(ii % celesi.length()), alfabeti);
					int pozitaRe = (pozita + pozitaCel) % alfabeti.length;
					char encCharacter = alfabeti[pozitaRe];
					rez.setCharAt(i, encCharacter);
					ii++;
				} 
				else
				{
					continue;
				}
			}
		}
		// Te gjitha karakteret
		else
		{
			celesi = celesi.replace('Ë', 'E');
			celesi = celesi.replace('Ç', 'C');
			txtCelesi.setText(celesi);
			for (int i = 0; i < rez.length(); i++)
			{
				int pozita = plaintexti.charAt(i);
				int pozitaRe = ((pozita + (celesi.charAt(i % celesi.length()) - 'A'))) % 256;
				char encCharacter = (char) (pozitaRe);
				rez.setCharAt(i, encCharacter);
			}
		}
		return rez.toString();
	}
	
	// ---------------------------------------------------------------------------------
	// ---------------------------------METODA DEKRIPTO---------------------------------
	// ---------------------------------------------------------------------------------
	String Dekripto(String ciphertexti, String celesi, int opsioni)
	{

		StringBuilder rez = new StringBuilder(ciphertexti);
		// Anglisht
		if (opsioni == 1)
		{
			// ne rast se eshte nderruar butoni pas gjenerimit ose pas shenimit
			celesi = celesi.replace('Ë', 'E');
			celesi = celesi.replace('Ç', 'C');
			txtCelesi.setText(celesi);
			int ii = 0;
			for (int i = 0; i < rez.length(); i++)
			{
				if (ciphertexti.charAt(i) >= 'a' && ciphertexti.charAt(i) <= 'z')
				{
					int pozita = ciphertexti.charAt(i) - 'a';
					int pozitaRe = ((pozita - (celesi.charAt(ii % celesi.length()) - 'A')) + 26) % 26;
					char decCharacter = (char) (pozitaRe + 'a');
					rez.setCharAt(i, decCharacter);
					ii++;
				} 
				else if (ciphertexti.charAt(i) >= 'A' && ciphertexti.charAt(i) <= 'Z')
				{
					int pozita = ciphertexti.charAt(i) - 'A';
					int pozitaRe = ((pozita - (celesi.charAt(ii % celesi.length()) - 'A')) + 26) % 26;
					char decCharacter = (char) (pozitaRe + 'A');
					rez.setCharAt(i, decCharacter);
					ii++;
				} 
				else
				{
					continue;
				}
			}
		}
		// Shqip
		else if (opsioni == 2)
		{
			celesi = celesi.replace('W', 'V');
			txtCelesi.setText(celesi);
			int ii = 0;
			for (int i = 0; i < rez.length(); i++)
			{
				if (((ciphertexti.charAt(i) >= 'a' && ciphertexti.charAt(i) <= 'z') || ciphertexti.charAt(i) == 'ë'
						|| ciphertexti.charAt(i) == 'ç') && (ciphertexti.charAt(i) != 'w'))
				{
					int pozita;
					int pozitaCel;
					// gjejme poziten e shkronjes se plaintext ne vargun alfabetiLow
					// dhe poziten e shkronjes se celesit ne vargun alfabeti
					pozita = pozitaNeVargChar(ciphertexti.charAt(i), alfabetiLow);
					pozitaCel = pozitaNeVargChar(celesi.charAt(ii % celesi.length()), alfabeti);
					int pozitaRe = ((pozita - pozitaCel) + alfabetiLow.length) % alfabetiLow.length;
					char decCharacter = alfabetiLow[pozitaRe];
					rez.setCharAt(i, decCharacter);
					ii++;
				} 
				else if (((ciphertexti.charAt(i) >= 'A' && ciphertexti.charAt(i) <= 'Z')
						|| ciphertexti.charAt(i) == 'Ë' || ciphertexti.charAt(i) == 'Ë')
						&& (ciphertexti.charAt(i) != 'W'))
				{
					int pozita;
					int pozitaCel;
					// gjejme poziten e shkronjes se plaintext ne vargun alfabetiLow
					// dhe poziten e shkronjes se celesit ne vargun alfabeti
					pozita = pozitaNeVargChar(ciphertexti.charAt(i), alfabeti);
					pozitaCel = pozitaNeVargChar(celesi.charAt(ii % celesi.length()), alfabeti);
					int pozitaRe = ((pozita - pozitaCel) + alfabeti.length) % alfabeti.length;
					char decCharacter = alfabeti[pozitaRe];
					rez.setCharAt(i, decCharacter);
					ii++;
				} 
				else
				{
					continue;
				}

			}
		}
		// te gjitha karakteret
		else
		{
			celesi = celesi.replace('Ë', 'E');
			celesi = celesi.replace('Ç', 'C');
			txtCelesi.setText(celesi);
			for (int i = 0; i < rez.length(); i++)
			{
				int pozita = ciphertexti.charAt(i);
				int pozitaRe = ((pozita - (celesi.charAt(i % celesi.length()) - 'A')) + 256) % 256;
				char encCharacter = (char) (pozitaRe);
				rez.setCharAt(i, encCharacter);
			}
		}
		return rez.toString();
	}


	
}
