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
import java.util.Scanner;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.TransferHandler;
import javax.swing.UIManager;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.ToolItem;

import com.ibm.icu.impl.locale.AsciiUtil;

import javax.swing.JSlider;
import javax.swing.JEditorPane;
import javax.swing.JFileChooser;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JFormattedTextField;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;

import javax.swing.DropMode;
import javax.swing.ImageIcon;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JLayeredPane;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class VigenereFile
{

	private JFrame frmVigenereCipher;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args)
	{
		// per ta bere me moderne dritaren per file
		try
		{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} 
		catch (Exception ex)
		{

		}
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				try
				{
					VigenereFile window = new VigenereFile();
					window.frmVigenereCipher.setVisible(true);
				} catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}

	char[] alfabeti = { 'A', 'B', 'C', '�', 'D', 'E', '�', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q',
			'R', 'S', 'T', 'U', 'V', 'X', 'Y', 'Z' };

	char[] alfabetiLow = { 'a', 'b', 'c', '�', 'd', 'e', '�', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p',
			'q', 'r', 's', 't', 'u', 'v', 'x', 'y', 'z' };

	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JTextField txtCelesi;
	private final ButtonGroup buttonGroup_1 = new ButtonGroup();
	private final ButtonGroup buttonGroup_2 = new ButtonGroup();

	/**
	 * Create the application.
	 */
	public VigenereFile()
	{
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize()
	{
		frmVigenereCipher = new JFrame();
		frmVigenereCipher.setResizable(false);
		frmVigenereCipher.setIconImage(
				Toolkit.getDefaultToolkit().getImage(VigenereFile.class.getResource("/images_files/lock-icon.png")));
		frmVigenereCipher.setTitle("Vigenere Cipher");
		frmVigenereCipher.setBounds(100, 100, 1673, 941);
		frmVigenereCipher.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmVigenereCipher.getContentPane().setLayout(null);
		frmVigenereCipher.setSize(1540, 826);
		frmVigenereCipher.setLocationRelativeTo(null);

		// deklarimi i file per pune te mevonshme
		JFileChooser file = new JFileChooser();

		JTextArea txtTekstiDekriptuar = new JTextArea();

		txtTekstiDekriptuar.setEditable(false);
		txtTekstiDekriptuar.setLineWrap(true);
		txtTekstiDekriptuar.setFont(new Font("Courier New", Font.PLAIN, 26));

		JLabel lblPlaintexti = new JLabel("Plaintexti:");
		lblPlaintexti.setForeground(new Color(255, 255, 255));
		lblPlaintexti.setFont(new Font("Trebuchet MS", Font.PLAIN, 34));
		lblPlaintexti.setBounds(25, 16, 225, 43);
		frmVigenereCipher.getContentPane().add(lblPlaintexti);

		JLabel lblCiphertexti = new JLabel("Ciphertexti:");
		lblCiphertexti.setForeground(new Color(255, 255, 255));
		lblCiphertexti.setFont(new Font("Trebuchet MS", Font.PLAIN, 34));
		lblCiphertexti.setBounds(437, 16, 242, 43);
		frmVigenereCipher.getContentPane().add(lblCiphertexti);

		JLabel lblTekstiIDekriptuar = new JLabel("Teksti i dekriptuar:");
		lblTekstiIDekriptuar.setForeground(new Color(255, 255, 255));
		lblTekstiIDekriptuar.setFont(new Font("Trebuchet MS", Font.PLAIN, 34));
		lblTekstiIDekriptuar.setBounds(849, 16, 341, 43);
		frmVigenereCipher.getContentPane().add(lblTekstiIDekriptuar);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(437, 75, 397, 515);
		frmVigenereCipher.getContentPane().add(scrollPane_1);

		JTextArea txtCiphertexti = new JTextArea();

		scrollPane_1.setViewportView(txtCiphertexti);
		txtCiphertexti.setLineWrap(true);
		txtCiphertexti.setFont(new Font("Courier New", Font.PLAIN, 26));

		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(849, 76, 397, 514);
		scrollPane_2.setViewportView(txtTekstiDekriptuar);
		frmVigenereCipher.getContentPane().add(scrollPane_2);

		JRadioButton rdbtnEnglish = new JRadioButton("English");
		rdbtnEnglish.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		rdbtnEnglish.setForeground(new Color(255, 255, 255));
		rdbtnEnglish.setOpaque(false);
		buttonGroup.add(rdbtnEnglish);
		rdbtnEnglish.setSelected(true);
		rdbtnEnglish.setFont(new Font("Trebuchet MS", Font.PLAIN, 26));
		rdbtnEnglish.setBounds(1007, 730, 122, 29);
		frmVigenereCipher.getContentPane().add(rdbtnEnglish);

		JRadioButton rdbtnShqip = new JRadioButton("Shqip");
		rdbtnShqip.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		rdbtnShqip.setForeground(new Color(255, 255, 255));
		rdbtnShqip.setOpaque(false);
		buttonGroup.add(rdbtnShqip);
		rdbtnShqip.setFont(new Font("Trebuchet MS", Font.PLAIN, 26));
		rdbtnShqip.setBounds(1171, 730, 101, 29);
		frmVigenereCipher.getContentPane().add(rdbtnShqip);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(25, 75, 397, 515);
		frmVigenereCipher.getContentPane().add(scrollPane);

		JTextArea txtPlaintexti = new JTextArea();

		scrollPane.setViewportView(txtPlaintexti);
		txtPlaintexti.setLineWrap(true);
		txtPlaintexti.setFont(new Font("Courier New", Font.PLAIN, 26));

		JRadioButton rdbtnPlaintextImport = new JRadioButton("Plaintext");
		rdbtnPlaintextImport.setForeground(new Color(255, 255, 255));
		rdbtnPlaintextImport.setOpaque(false);
		rdbtnPlaintextImport.setSelected(true);
		buttonGroup_2.add(rdbtnPlaintextImport);
		rdbtnPlaintextImport.setFont(new Font("Trebuchet MS", Font.PLAIN, 26));
		rdbtnPlaintextImport.setBounds(147, 679, 188, 43);
		frmVigenereCipher.getContentPane().add(rdbtnPlaintextImport);

		JRadioButton rdbtnCiphertextImport = new JRadioButton("Ciphertext");
		rdbtnCiphertextImport.setForeground(new Color(255, 255, 255));
		rdbtnCiphertextImport.setOpaque(false);
		buttonGroup_2.add(rdbtnCiphertextImport);
		rdbtnCiphertextImport.setFont(new Font("Trebuchet MS", Font.PLAIN, 26));
		rdbtnCiphertextImport.setBounds(147, 723, 188, 43);
		frmVigenereCipher.getContentPane().add(rdbtnCiphertextImport);

		JRadioButton rdbtnCiphertextExport = new JRadioButton("Ciphertext");
		rdbtnCiphertextExport.setForeground(new Color(255, 255, 255));
		rdbtnCiphertextExport.setOpaque(false);
		rdbtnCiphertextExport.setSelected(true);
		buttonGroup_1.add(rdbtnCiphertextExport);
		rdbtnCiphertextExport.setFont(new Font("Trebuchet MS", Font.PLAIN, 26));
		rdbtnCiphertextExport.setBounds(552, 679, 164, 43);
		frmVigenereCipher.getContentPane().add(rdbtnCiphertextExport);

		JRadioButton rdbtnDecryptedTextExport = new JRadioButton("Teksti i dekriptuar");
		rdbtnDecryptedTextExport.setForeground(new Color(255, 255, 255));
		rdbtnDecryptedTextExport.setOpaque(false);
		buttonGroup_1.add(rdbtnDecryptedTextExport);
		rdbtnDecryptedTextExport.setFont(new Font("Trebuchet MS", Font.PLAIN, 26));
		rdbtnDecryptedTextExport.setBounds(552, 730, 250, 43);
		frmVigenereCipher.getContentPane().add(rdbtnDecryptedTextExport);

		JLabel lblCelesi = new JLabel("Celesi:");
		lblCelesi.setForeground(new Color(255, 255, 255));
		lblCelesi.setFont(new Font("Trebuchet MS", Font.PLAIN, 34));
		lblCelesi.setBounds(1308, 16, 143, 43);
		frmVigenereCipher.getContentPane().add(lblCelesi);
		JButton btnGjenero = new JButton("");
		btnGjenero.setIcon(new ImageIcon(VigenereFile.class.getResource("/images_files/Webp.net-resizeimage (4).png")));
		btnGjenero.setBorderPainted(false);
		btnGjenero.setContentAreaFilled(false);
		btnGjenero.setBackground(new Color(255, 255, 255));
		btnGjenero.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
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
						if (rand.nextInt(28) % 27 == 0)
						{
							if (rand.nextInt(10) % 2 == 0)
								celesiA.append('�');
							else
								celesiA.append('�');
						} else
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
		btnGjenero.setFont(new Font("Trebuchet MS", Font.PLAIN, 26));
		btnGjenero.setBounds(1377, 133, 72, 66);
		frmVigenereCipher.getContentPane().add(btnGjenero);

		JButton btnEnkripto = new JButton("");
		btnEnkripto.setBackground(new Color(255, 255, 255));
		btnEnkripto.setIcon(new ImageIcon(Vigenere.class.getResource("/images_files/ico_22.png")));
		btnEnkripto.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnEnkripto.setOpaque(false);
		btnEnkripto.setContentAreaFilled(false);
		btnEnkripto.setBorderPainted(false);
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
		btnEnkripto.setFont(new Font("Trebuchet MS", Font.PLAIN, 26));
		btnEnkripto.setBounds(1377, 258, 72, 66);
		frmVigenereCipher.getContentPane().add(btnEnkripto);

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
		btnDekripto.setBounds(1377, 340, 72, 66);
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
					} catch (HeadlessException | UnsupportedFlavorException | IOException e)
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
							|| (!data.matches("^[ABCDEFGHIJKLMNOPQRSTUVXYZ����]*$") && rdbtnShqip.isSelected()))
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

		txtCelesi.setFont(new Font("Trebuchet MS", Font.PLAIN, 26));
		txtCelesi.setColumns(10);
		txtCelesi.setBounds(1308, 75, 204, 43);
		frmVigenereCipher.getContentPane().add(txtCelesi);

		JRadioButton rdbtnAllCharacters = new JRadioButton("All Characters");
		rdbtnAllCharacters.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		rdbtnAllCharacters.setForeground(new Color(255, 255, 255));
		rdbtnAllCharacters.setOpaque(false);
		buttonGroup.add(rdbtnAllCharacters);
		rdbtnAllCharacters.setFont(new Font("Trebuchet MS", Font.PLAIN, 26));
		rdbtnAllCharacters.setBounds(1325, 730, 198, 29);
		frmVigenereCipher.getContentPane().add(rdbtnAllCharacters);

		JButton btnImport = new JButton("");
		btnImport.setContentAreaFilled(false);
		btnImport.setBorderPainted(false);
		btnImport.setIcon(new ImageIcon(VigenereFile.class.getResource("/images_files/Webp.net-resizeimage (1).png")));
		btnImport.setOpaque(false);
		btnImport.setSelectedIcon(null);
		btnImport.setBackground(new Color(255, 255, 255));
		btnImport.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnImport.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				// lexojme kontekstin e files me ane te skanerit
				file.showOpenDialog(null);
				Scanner sc = null;
				try
				{
					sc = new Scanner(file.getSelectedFile());
				} 
				catch (FileNotFoundException | NullPointerException e)
				{
					return;
				}
				String teksti = "";
				while (sc.hasNextLine())
				{
					teksti += sc.nextLine();
				}
				if (rdbtnPlaintextImport.isSelected())
					txtPlaintexti.setText(teksti);
				else
					txtCiphertexti.setText(teksti);
			}
		});
		btnImport.setFont(new Font("Trebuchet MS", Font.PLAIN, 26));
		btnImport.setBounds(190, 606, 72, 61);
		frmVigenereCipher.getContentPane().add(btnImport);

		JButton btnExport = new JButton("");
		btnExport.setIcon(new ImageIcon(VigenereFile.class.getResource("/images_files/Webp.net-resizeimage (3).png")));
		btnExport.setBorderPainted(false);
		btnExport.setContentAreaFilled(false);
		btnExport.setBackground(new Color(255, 255, 255));
		btnExport.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnExport.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				String teksti = "";
				file.showSaveDialog(null);

				if (rdbtnCiphertextExport.isSelected())
					teksti = txtCiphertexti.getText();
				else
					teksti = txtTekstiDekriptuar.getText();

				BufferedWriter bw = null;
				FileWriter fw = null;
				try
				{
					// shkruajme ne filen qe eshte selektuar
					fw = new FileWriter(file.getSelectedFile());
					bw = new BufferedWriter(fw);
					bw.write(teksti);
				} 
				catch (IOException | NullPointerException ex)
				{
					return;
				} 
				finally
				{
					try
					{
						if (bw != null)
							bw.close();
						if (fw != null)
							fw.close();
					} 
					catch (IOException ex)
					{
						return;
					}
				}
			}
		});
		btnExport.setFont(new Font("Trebuchet MS", Font.PLAIN, 26));
		btnExport.setBounds(597, 606, 72, 61);
		frmVigenereCipher.getContentPane().add(btnExport);

		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(Vigenere.class.getResource("/images_files/england_ico.png")));
		label.setBounds(1121, 730, 50, 29);
		frmVigenereCipher.getContentPane().add(label);

		JLabel label_1 = new JLabel("");
		label_1.setIcon(new ImageIcon(Vigenere.class.getResource("/images_files/albania_ico.png")));
		label_1.setBounds(1268, 730, 46, 29);
		frmVigenereCipher.getContentPane().add(label_1);

		JLabel label_2 = new JLabel("");
		label_2.setBackground(Color.WHITE);
		label_2.setIcon(new ImageIcon(Vigenere.class.getResource("/images_files/2.png")));
		label_2.setBounds(0, 0, 1534, 786);
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
			celesi = celesi.replace('�', 'E');
			celesi = celesi.replace('�', 'C');
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
				} else if (plaintexti.charAt(i) >= 'A' && plaintexti.charAt(i) <= 'Z')
				{
					int pozita = plaintexti.charAt(i) - 'A';
					int pozitaRe = (pozita + (celesi.charAt(ii % celesi.length()) - 'A')) % 26;
					char encCharacter = (char) (pozitaRe + 'A');
					rez.setCharAt(i, encCharacter);
					ii++;
				} else
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
				if (((plaintexti.charAt(i) >= 'a' && plaintexti.charAt(i) <= 'z') || plaintexti.charAt(i) == '�'
						|| plaintexti.charAt(i) == '�') && (plaintexti.charAt(i) != 'w'))
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
				} else if (((plaintexti.charAt(i) >= 'A' && plaintexti.charAt(i) <= 'Z') || plaintexti.charAt(i) == '�'
						|| plaintexti.charAt(i) == '�') && (plaintexti.charAt(i) != 'W'))
				{
					int pozita;
					int pozitaCel;
					// gjejme poziten e shkronjes se plaintext ne vargun alfabetiLow
					// dhe poziten e shkronjes se celesit ne vargun alfabeti
					pozita = pozitaNeVargChar(plaintexti.charAt(i), alfabeti);
					pozitaCel = pozitaNeVargChar(celesi.charAt(ii % celesi.length()), alfabeti);
					int pozitaRe = (pozita + pozitaCel) % alfabetiLow.length;
					char encCharacter = alfabeti[pozitaRe];
					rez.setCharAt(i, encCharacter);
					ii++;
				} else
				{
					continue;
				}
			}
		}
		// Te gjitha karakteret
		else
		{
			celesi = celesi.replace('�', 'E');
			celesi = celesi.replace('�', 'C');
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
			celesi = celesi.replace('�', 'E');
			celesi = celesi.replace('�', 'C');
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
				} else if (ciphertexti.charAt(i) >= 'A' && ciphertexti.charAt(i) <= 'Z')
				{
					int pozita = ciphertexti.charAt(i) - 'A';
					int pozitaRe = ((pozita - (celesi.charAt(ii % celesi.length()) - 'A')) + 26) % 26;
					char decCharacter = (char) (pozitaRe + 'A');
					rez.setCharAt(i, decCharacter);
					ii++;
				} else
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
				if (((ciphertexti.charAt(i) >= 'a' && ciphertexti.charAt(i) <= 'z') || ciphertexti.charAt(i) == '�'
						|| ciphertexti.charAt(i) == '�') && (ciphertexti.charAt(i) != 'w'))
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
				} else if (((ciphertexti.charAt(i) >= 'A' && ciphertexti.charAt(i) <= 'Z')
						|| ciphertexti.charAt(i) == '�' || ciphertexti.charAt(i) == '�')
						&& (ciphertexti.charAt(i) != 'W'))
				{
					int pozita;
					int pozitaCel;
					// gjejme poziten e shkronjes se plaintext ne vargun alfabetiLow
					// dhe poziten e shkronjes se celesit ne vargun alfabeti
					pozita = pozitaNeVargChar(ciphertexti.charAt(i), alfabeti);
					pozitaCel = pozitaNeVargChar(celesi.charAt(ii % celesi.length()), alfabeti);
					int pozitaRe = ((pozita - pozitaCel) + alfabetiLow.length) % alfabetiLow.length;
					char decCharacter = alfabeti[pozitaRe];
					rez.setCharAt(i, decCharacter);
					ii++;
				} else
				{
					continue;
				}

			}
		}
		// te gjitha karakteret
		else
		{
			celesi = celesi.replace('�', 'E');
			celesi = celesi.replace('�', 'C');
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
