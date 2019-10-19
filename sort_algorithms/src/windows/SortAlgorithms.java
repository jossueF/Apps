package windows;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.border.TitledBorder;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JSpinner;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingWorker;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.Toolkit;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import logic.SortMethods;
import logic.TimeWork;

import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.ParseException;
import java.util.Arrays;
import java.util.concurrent.ExecutionException;
import java.awt.event.ActionEvent;
import javax.swing.JProgressBar;

public class SortAlgorithms extends JFrame implements PropertyChangeListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton btnRandom;
	private JSpinner spinner;
	private JScrollPane spAleatorio;
	private JScrollPane spOrdenado;
	private JTextArea taOrdenado;
	private JTable table;
	private JMenuBar menuBar;
	private JMenu mnHelp;
	private JMenuItem mntmAbout;
	private JTextArea taAleatorio;
	private JMenuItem mntmBubblesort;
	private JMenuItem mntmSelectionsort;
	private JMenuItem mntmInsertionsort;
	private JMenuItem mntmExit;
	private JScrollPane scrollPane;
	private JButton btnClear;
	private JProgressBar pbWork;
	
	// Logic
	private SortMethods sort;
	private TimeWork time;
	private String timeNow;
	private SwingWorker<String, Void> bubbleWorker;
	private SwingWorker<String, Void> selectionWorker;
	private SwingWorker<String, Void> insertionWorker;
	private boolean done;
	private int idTable;

	// Models
	private SpinnerNumberModel spinnerModel;
	private DefaultTableModel tableModel;
	private JButton btnCancelar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e1) {
			e1.printStackTrace();
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SortAlgorithms frame = new SortAlgorithms();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public SortAlgorithms() {
		initComponents();
		createEvents();
		// createThreads();
	}

	private void initComponents() {
		setIconImage(Toolkit.getDefaultToolkit()
				.getImage(SortAlgorithms.class.getResource("/resources/perfilPrincipal.png")));
		setTitle("Sort Algorithms");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 527);
		setLocationRelativeTo(null);

		sort = new SortMethods();
		time = new TimeWork();
		idTable = 0;
		spinnerModel = new SpinnerNumberModel();
		spinnerModel.setValue(10);
		spinnerModel.setMinimum(10);
		spinnerModel.setStepSize(10);
		
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnFile = new JMenu("Inicio");
		menuBar.add(mnFile);

		mntmBubblesort = new JMenuItem("BubbleSort");
		mntmBubblesort.setIcon(new ImageIcon(SortAlgorithms.class.getResource("/resources/start.png")));
		mnFile.add(mntmBubblesort);

		mntmSelectionsort = new JMenuItem("SelectionSort");
		mntmSelectionsort.setIcon(new ImageIcon(SortAlgorithms.class.getResource("/resources/start.png")));
		mnFile.add(mntmSelectionsort);

		mntmInsertionsort = new JMenuItem("InsertionSort");
		mntmInsertionsort.setIcon(new ImageIcon(SortAlgorithms.class.getResource("/resources/start.png")));
		mnFile.add(mntmInsertionsort);

		mnFile.addSeparator();
		mntmExit = new JMenuItem("Exit");
		mntmExit.setIcon(new ImageIcon(SortAlgorithms.class.getResource("/resources/exit.png")));
		mnFile.add(mntmExit);

		mnHelp = new JMenu("Ayuda");
		menuBar.add(mnHelp);

		mntmAbout = new JMenuItem("Acerca");
		mntmAbout.setIcon(new ImageIcon(SortAlgorithms.class.getResource("/resources/perfilPrincipalIcono.png")));
		mnHelp.add(mntmAbout);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JPanel pnAleatorio = new JPanel();
		pnAleatorio.setBorder(
				new TitledBorder(null, "Arreglo aleatorio", TitledBorder.LEADING, TitledBorder.TOP, null, null));

		JPanel pnOrdenado = new JPanel();
		pnOrdenado.setBorder(
				new TitledBorder(null, "Arreglo Ordenado", TitledBorder.LEADING, TitledBorder.TOP, null, null));

		scrollPane = new JScrollPane();
		scrollPane.setBorder(
				new TitledBorder(null, "Informaci\u00F3n", TitledBorder.LEADING, TitledBorder.TOP, null, null));

		btnClear = new JButton("Limpiar");

		btnCancelar = new JButton("Cancelar");
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING).addGroup(gl_contentPane
				.createSequentialGroup()
				.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createSequentialGroup().addGap(262).addComponent(btnCancelar)
								.addPreferredGap(ComponentPlacement.RELATED).addComponent(btnClear))
						.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addComponent(scrollPane, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 422,
										Short.MAX_VALUE)
								.addComponent(pnOrdenado, Alignment.LEADING, GroupLayout.DEFAULT_SIZE,
										GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(pnAleatorio, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE)))
				.addGap(2)));
		gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addGroup(gl_contentPane
				.createSequentialGroup()
				.addComponent(pnAleatorio, GroupLayout.PREFERRED_SIZE, 113, GroupLayout.PREFERRED_SIZE).addGap(18)
				.addComponent(pnOrdenado, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE).addGap(18)
				.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 183, GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(ComponentPlacement.RELATED).addGroup(gl_contentPane
						.createParallelGroup(Alignment.BASELINE).addComponent(btnClear).addComponent(btnCancelar))
				.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));

		table = new JTable();
		tableModel = new DefaultTableModel(new Object[][] {}, new String[] {"Id", "Tipo", "Cantidad", "Tiempo" });
		table.setModel(tableModel);

		scrollPane.setViewportView(table);

		spOrdenado = new JScrollPane();
		GroupLayout gl_pnOrdenado = new GroupLayout(pnOrdenado);
		gl_pnOrdenado.setHorizontalGroup(gl_pnOrdenado.createParallelGroup(Alignment.LEADING).addComponent(spOrdenado,
				GroupLayout.DEFAULT_SIZE, 410, Short.MAX_VALUE));
		gl_pnOrdenado.setVerticalGroup(
				gl_pnOrdenado.createParallelGroup(Alignment.LEADING).addGroup(gl_pnOrdenado.createSequentialGroup()
						.addComponent(spOrdenado, GroupLayout.DEFAULT_SIZE, 47, Short.MAX_VALUE).addContainerGap()));

		taOrdenado = new JTextArea();
		taOrdenado.setEditable(false);
		spOrdenado.setViewportView(taOrdenado);
		pnOrdenado.setLayout(gl_pnOrdenado);

		spAleatorio = new JScrollPane();

		spinner = new JSpinner();
		spinner.setModel(spinnerModel);
		
		btnRandom = new JButton("random");

		pbWork = new JProgressBar(0, 100);
		pbWork.setStringPainted(true);
		// pbWork.setVisible(false);

		GroupLayout gl_pnAleatorio = new GroupLayout(pnAleatorio);
		gl_pnAleatorio.setHorizontalGroup(gl_pnAleatorio.createParallelGroup(Alignment.TRAILING)
				.addComponent(spAleatorio, GroupLayout.DEFAULT_SIZE, 410, Short.MAX_VALUE).addGroup(Alignment.LEADING,
						gl_pnAleatorio.createSequentialGroup().addContainerGap().addComponent(btnRandom)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(spinner, GroupLayout.PREFERRED_SIZE, 84, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(pbWork, GroupLayout.DEFAULT_SIZE, 235, Short.MAX_VALUE)));
		gl_pnAleatorio.setVerticalGroup(gl_pnAleatorio.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnAleatorio.createSequentialGroup()
						.addComponent(spAleatorio, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
						.addGroup(gl_pnAleatorio.createParallelGroup(Alignment.BASELINE).addComponent(btnRandom)
								.addComponent(spinner, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(pbWork, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE))));
		taAleatorio = new JTextArea();
		taAleatorio.setEditable(false);
		spAleatorio.setViewportView(taAleatorio);
		pnAleatorio.setLayout(gl_pnAleatorio);
		contentPane.setLayout(gl_contentPane);
	}

	private void createEvents() {
		mntmAbout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				About about = new About();
				about.setModal(true);
				about.setVisible(true);
			}
		});

		// -------------------------- Random --------------------------------------------
		btnRandom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					spinner.commitEdit();
				} catch (ParseException p) {
				}
				int size = (Integer) spinner.getValue();
				sort.cleanArray();
				sort.randomArray(size);
				pbWork.setValue(pbWork.getMinimum());
				pbWork.setForeground(Color.BLUE);
				taAleatorio.setText(sort.getTheArray().toString());
				taOrdenado.setText("");
			}
		});

		// -------------------------- Bubble --------------------------------------------
		mntmBubblesort.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (sort.getTheArray().size() > 0) {
					mntmBubblesort.setEnabled(false);
					mntmSelectionsort.setEnabled(false);
					mntmInsertionsort.setEnabled(false);
					btnRandom.setEnabled(false);
					btnClear.setEnabled(false);
					spinner.setEnabled(false);
					setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
					done = false;
					pbWork.setVisible(true);
					pbWork.setValue(0);
					pbWork.setForeground(Color.BLUE);
					bubbleStart();
				}
			}
		});

		// -------------------------- Selection --------------------------------------------
		mntmSelectionsort.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (sort.getTheArray().size() > 0) {
					mntmBubblesort.setEnabled(false);
					mntmSelectionsort.setEnabled(false);
					mntmInsertionsort.setEnabled(false);
					btnRandom.setEnabled(false);
					btnClear.setEnabled(false);
					spinner.setEnabled(false);
					setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
					done = false;
					pbWork.setVisible(true);
					pbWork.setValue(0);
					pbWork.setForeground(Color.BLUE);
					selectionStart();
				}
			}
		});

		// -------------------------- Insertion --------------------------------------------
		mntmInsertionsort.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (sort.getTheArray().size() > 0) {
					mntmBubblesort.setEnabled(false);
					mntmSelectionsort.setEnabled(false);
					mntmInsertionsort.setEnabled(false);
					btnRandom.setEnabled(false);
					btnClear.setEnabled(false);
					spinner.setEnabled(false);
					setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
					done = false;
					pbWork.setVisible(true);
					pbWork.setValue(0);
					pbWork.setForeground(Color.BLUE);
					insertionStart();
				}
			}
		});

		// -------------------------- Clean --------------------------------------------
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				taAleatorio.setText("");
				taOrdenado.setText("");
				spinnerModel.setValue(10);
				//tableModel = new DefaultTableModel(new Object[][] {}, new String[] {"Id", "Tipo", "Cantidad", "Tiempo" });
				//table.setModel(tableModel);
				// pbWork.setVisible(false);
				pbWork.setValue(pbWork.getMinimum());
				pbWork.setForeground(Color.BLUE);
				sort.cleanArray();
			}
		});

		// -------------------------- Exit --------------------------------------------
		mntmExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
		// Ir al final de la tabla cada que se añade un elemento nuevo
		scrollPane.getVerticalScrollBar().addAdjustmentListener(new AdjustmentListener() {
			public void adjustmentValueChanged(AdjustmentEvent e) {
				e.getAdjustable().setValue(e.getAdjustable().getMaximum());
			}
		});

	}

	private void bubbleStart() {
		bubbleWorker = new SwingWorker<String, Void>() {

			@Override
			protected String doInBackground() throws Exception {
				//System.out.println("doInBackground() esta en el hilo " + Thread.currentThread().getName());
				int[] bubbleArray = new int[sort.getTheArray().size()];
				for(int i = 0; i < bubbleArray.length; i++) {
					bubbleArray[i] = sort.getTheArray().get(i);
				}
				double progress = 0.0;
				double step = (100.0 / (bubbleArray.length - 2.0));
				setProgress(0);
				long timeBegin = time.getLastTime();
				
				for (int i = bubbleArray.length - 1; i > 1; i--) {
					for (int j = 0; j < i; j++) {
						if (bubbleArray[j] > bubbleArray[j + 1]) {
							sort.swapValues(j, j + 1, bubbleArray);
						}
					}
					progress += step;
					//System.out.println(String.format("%f", progress));
					setProgress((int) Math.round(progress));
					if (Thread.interrupted()) { // En caso de cancelar el hilo.
						return "";
					}
				}
				
				long timeEnd = time.getLastTime();
				timeNow = time.getTimeNow(timeBegin, timeEnd);
				pbWork.setForeground(Color.GREEN);
				return Arrays.toString(bubbleArray);
			}

			@Override
			protected void done() {
				done = true; // Le decimos al progress listener que se detenga de actualizar. Necesario ya
								// que la ultima actualización de progress ocurre despues de que done() es
								// invocado.
				Toolkit.getDefaultToolkit().beep();
				mntmBubblesort.setEnabled(true);
				mntmSelectionsort.setEnabled(true);
				mntmInsertionsort.setEnabled(true);
				btnRandom.setEnabled(true);
				btnClear.setEnabled(true);
				spinner.setEnabled(true);
				setCursor(null);
				// pbWork.setValue(pbWork.getMinimum());
				//System.out.println("Done");
				try {
					String result = get();
					String[] data = { String.format("%d", ++idTable), "Bubble_Sort", String.format("%d", sort.getTheArray().size()), timeNow };

					taOrdenado.setText(result);
					tableModel.addRow(data);
				} catch (InterruptedException | ExecutionException e) {
					e.printStackTrace();
				}
			}
		};
		bubbleWorker.addPropertyChangeListener(this);
		bubbleWorker.execute();

		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bubbleWorker.cancel(true);
			}
		});
	}

	private void selectionStart() {
		selectionWorker = new SwingWorker<String, Void>() {
			@Override
			protected String doInBackground() throws Exception {
				int[] selectionArray = new int[sort.getTheArray().size()];
				for(int i = 0; i < selectionArray.length; i++) {
					selectionArray[i] = sort.getTheArray().get(i);
				}
				double progress = 0.0;
				double step = (100.0 / selectionArray.length);
				setProgress(0);
				long timeBegin = time.getLastTime();
				
				for(int x = 0; x < selectionArray.length; x++) {
					int minimum = x;
					for(int y = x; y < selectionArray.length; y++) {
						if(selectionArray[minimum] > selectionArray[y]) {
							minimum = y;
						}
					}
					progress += step;
					setProgress((int) Math.round(progress));
					sort.swapValues(x, minimum, selectionArray);
					if(Thread.interrupted()) {
						return "";
					}
				}
				
				long timeEnd = time.getLastTime();
				timeNow = time.getTimeNow(timeBegin, timeEnd);
				pbWork.setForeground(Color.GREEN);
				return Arrays.toString(selectionArray);
			}

			@Override
			protected void done() {
				done = true;
				Toolkit.getDefaultToolkit().beep();
				mntmBubblesort.setEnabled(true);
				mntmSelectionsort.setEnabled(true);
				mntmInsertionsort.setEnabled(true);
				btnRandom.setEnabled(true);
				btnClear.setEnabled(true);
				spinner.setEnabled(true);
				setCursor(null);
				
				try {
					String result = get();
					String[] data = { String.format("%d", ++idTable), "Selection_Sort", String.format("%d", sort.getTheArray().size()), timeNow };
					taOrdenado.setText(result);
					tableModel.addRow(data);
				} catch (InterruptedException | ExecutionException e) {
					e.printStackTrace();
				}
			}
		};
		selectionWorker.addPropertyChangeListener(this);
		selectionWorker.execute();
		
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectionWorker.cancel(true);
			}
		});
	}
	
	private void insertionStart() {
		insertionWorker = new SwingWorker<String, Void>() {
			@Override
			protected String doInBackground() throws Exception {
				int[] insertionArray = new int[sort.getTheArray().size()];
				for(int i = 0; i < insertionArray.length; i++) {
					insertionArray[i] = sort.getTheArray().get(i);
				}
				double progress = 0.0;
				double step = (100.0 / (insertionArray.length - 1.0));
				setProgress(0);
				long timeBegin = time.getLastTime();
				
				for(int i = 1; i < insertionArray.length; i++) {
					int j = i;
					int toIn = insertionArray[i];
					
					while((j > 0) && (insertionArray[j - 1] > toIn)) {
						insertionArray[j] = insertionArray[j - 1];
						j--;
					}
					insertionArray[j] = toIn;
					progress += step;
					setProgress((int) Math.round(progress));
					if(Thread.interrupted()) {
						return "";
					}
				}
				
				long timeEnd = time.getLastTime();
				timeNow = time.getTimeNow(timeBegin, timeEnd);
				pbWork.setForeground(Color.GREEN);
				return Arrays.toString(insertionArray);
			}
			
			@Override
			protected void done() {
				done = true;
				Toolkit.getDefaultToolkit().beep();
				mntmBubblesort.setEnabled(true);
				mntmSelectionsort.setEnabled(true);
				mntmInsertionsort.setEnabled(true);
				btnRandom.setEnabled(true);
				btnClear.setEnabled(true);
				spinner.setEnabled(true);
				setCursor(null);
				
				try {
					String result = get();
					String[] data = { String.format("%d", ++idTable), "Insertion_Sort", String.format("%d", sort.getTheArray().size()), timeNow };
					taOrdenado.setText(result);
					tableModel.addRow(data);
				} catch (InterruptedException | ExecutionException e) {
					e.printStackTrace();
				}
			}
		};
		insertionWorker.addPropertyChangeListener(this);
		insertionWorker.execute();
		
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				insertionWorker.cancel(true);
			}
		});
	
	}
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if ("progress" == evt.getPropertyName() && !done) {
			int progress = (Integer) evt.getNewValue();
			pbWork.setValue(progress);
		}
	}
}
