import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.GridLayout;

public class KnightsTour extends JFrame {
	private static final long serialVersionUID = 1L;
	
	public static void main(String[] args){
		SwingUtilities.invokeLater(new Runnable(){
			public void run(){
				new KnightsTour();
			}
		});
	}
	
	public class TourButton extends JButton implements Comparable<TourButton>{	
		private static final long serialVersionUID = 1L;
		public TourButton(int x, int y, int accessability, int legalMoves){
			this.x = x;
			this.y = y;
			this.accessability = accessability;
			this.legalMoves = legalMoves;
			setText(x + ", " + y);
		}
		
		public void SetButtonText(String a){
			setText(a);
		}
		
		public int compareTo(TourButton a){
			int compare = a.legalMoves - this.legalMoves;
			return compare;
		}
		
		int x;
		int y;
		int accessability;
		int legalMoves;
	}
	
	KnightsTour(){
		super("Knight's Tour");
		launch();
	}
	
	JMenu createFileMenu(){
		JMenu m = new JMenu("File");
		m.add(createFileNewJMenuItem());
		m.addSeparator();
		m.add(createFileQuitJMenuItem());
		return m;
	}
	
	JMenu createHelpMenu(){
		JMenu m = new JMenu("Help");
		m.add(createHelpAboutMenuItem());
		m.add(createHelpInstructionsMenuItem());
		return m;
	}
	
	JMenuItem createFileNewJMenuItem(){
		JMenuItem mi = new JMenuItem("New");
		mi.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, KeyEvent.CTRL_MASK));
		mi.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				performNewAction();
			}
		});
		return mi;
	}
	
	JMenuItem createFileQuitJMenuItem(){
		JMenuItem mi = new JMenuItem("Quit");
		mi.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, KeyEvent.CTRL_MASK));
		mi.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				performQuitAction();
			}
		});
		return mi;
	}
	
	JMenuItem createHelpInstructionsMenuItem(){
		JMenuItem mi = new JMenuItem("Instructions");
		mi.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I, KeyEvent.CTRL_MASK));
		mi.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				performInstructionsAction();
			}
		});
		return mi;
	}

	JMenuItem createHelpAboutMenuItem(){
		JMenuItem mi = new JMenuItem("About");
		mi.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, KeyEvent.CTRL_MASK));
		mi.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				performAboutAction();
			}
		});
		return mi;
	}
	
	JMenuBar createJMenuBar(){
		JMenuBar mb = new JMenuBar();
		mb.add(createFileMenu());
		mb.add(createHelpMenu());
		return mb;
	}
	
	private void launch(){
		
		int height = Toolkit.getDefaultToolkit().getScreenSize().height;
		int width = Toolkit.getDefaultToolkit().getScreenSize().width;
		setBounds(width / 2 - 350, height / 2 - 350, 700, 700);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setJMenuBar(createJMenuBar());
		setLayout(new GridLayout(8, 8));
		
		for(int i = 0; i < 8; i++){
			for(int j = 0; j < 8; j++){
				int a = 1;
				int m = 0;
				TourButton b = new TourButton(i, j, a, m);
				add(grid[i][j] = b);
				b.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e){
						performClickAction((TourButton) e.getSource());
					}
				});
				if(i == 0 || i == 2 || i == 4 || i == 6){
					if((j % 2) > 0){
						b.setBackground(Color.white);
						b.setForeground(Color.black);
					}
					else{
						b.setBackground(Color.black);
						b.setForeground(Color.white);
					}
				}
				if(i == 1 || i == 3 || i == 5 || i == 7){
					if((j % 2) > 0){
						b.setBackground(Color.black);
						b.setForeground(Color.white);
					}
					else{
						b.setBackground(Color.white);
						b.setForeground(Color.black);
					}
				}
			}	
		}
		
		setVisible(true);
	}
	
	public void validMoves(TourButton b){
		int moves = 0;
		if(b.accessability == 0){
			moves = 0;
		}
		else{
			if(((b.x + 2) <= 7) && (((b.y + 1) <= 7))){
				TourButton o = grid[b.x+2][b.y+1];
				if(o.accessability != 0){
					moves++;
				}
			}
			if(((b.x - 2) >= 0) && (((b.y + 1) <= 7))){
				TourButton o = grid[b.x-2][b.y+1];
				if(o.accessability != 0){
					moves++;
				}
			}
			if(((b.x + 2) <= 7) && (((b.y - 1) >= 0))){
				TourButton o = grid[b.x+2][b.y-1];
				if(o.accessability != 0){
					moves++;
				}
			}
			if(((b.x - 2) >= 0) && (((b.y - 1) >= 0))){
				TourButton o = grid[b.x-2][b.y-1];
				if(o.accessability != 0){
					moves++;
				}
			}
			if(((b.y + 2) <= 7) && (((b.x + 1) <= 7))){
				TourButton o = grid[b.x+1][b.y+2];
				if(o.accessability != 0){
					moves++;
				}
			}
			if(((b.y + 2) <= 7) && (((b.x - 1) >= 0))){
				TourButton o = grid[b.x-1][b.y+2];
				if(o.accessability != 0){
					moves++;
				}
			}
			if(((b.y - 2) >= 0) && (((b.x + 1) <= 7))){
				TourButton o = grid[b.x+1][b.y-2];
				if(o.accessability != 0){
					moves++;
				}
			}
			if(((b.y - 2) >= 0) && (((b.x - 1) >= 0))){
				TourButton o = grid[b.x-1][b.y-2];
				if(o.accessability != 0){
					moves++;
				}
			}
		}
			
	 b.legalMoves = moves;
	}

	public TourButton makeMove(TourButton b){
		TourButton o = null;
		TourButton p = null;
		TourButton q = null;
		TourButton r = null;
		TourButton s = null;
		TourButton t = null;
		TourButton u = null;
		TourButton v = null;
		TourButton w = null;
		if(((b.x + 2) <= 7) && (((b.y + 1) <= 7))){
			o = grid[b.x + 2][b.y + 1];
			if(o.accessability == 1){
				validMoves(o);
			}
			else if(o.accessability == 0){
				o = null;
			}
		}
		
		if(((b.x - 2) >= 0) && (((b.y + 1) <= 7))){
			p = grid[b.x - 2][b.y + 1];
			if(p.accessability == 1){
				validMoves(p);
			}
			else if(p.accessability == 0){
				p = null;
			}
		}
		
		if(((b.x + 2) <= 7) && (((b.y - 1) >= 0))){
			q = grid[b.x + 2][b.y - 1];
			if(q.accessability == 1){
				validMoves(q);
			}
			else if(q.accessability == 0){
				q = null;
			}
		}
		
		if(((b.x - 2) >= 0) && (((b.y - 1) >= 0))){
			r = grid[b.x - 2][b.y - 1];
			if(r.accessability == 1){
				validMoves(r);
			}
			else if(r.accessability == 0){
				r = null;
			}
		}
		
		if(((b.y + 2) <= 7) && (((b.x + 1) <= 7))){
			s = grid[b.x + 1][b.y + 2];
			if(s.accessability == 1){
				validMoves(s);
			}
			else if(s.accessability == 0){
				s = null;
			}
		}
		
		if(((b.y + 2) <= 7) && (((b.x - 1) >= 0))){
			t = grid[b.x - 1][b.y + 2];
			if(t.accessability == 1){
				validMoves(t);
			}
			else if(t.accessability == 0){
				t = null;
			}
		}
		
		if(((b.y - 2) >= 0) && (((b.x + 1) <= 7))){
			u = grid[b.x + 1][b.y - 2];
			if(u.accessability == 1){
				validMoves(u);
			}
			else if(u.accessability == 0){
				u = null;
			}
		}
		
		if(((b.y - 2) >= 0) && (((b.x - 1) >= 0))){
			v = grid[b.x - 1][b.y - 2];
			if(v.accessability == 1){
				validMoves(v);
			}
			else if(v.accessability == 0){
				v = null;
			}
		}
		
		if((w == null) && (o != null)){
			w = o;
		}
		else if((w == null) && (o == null)){}
		else if ((w != null) && (o != null)){
			if((w.compareTo(o) <= 0) && (w.legalMoves > 0) && (o.legalMoves > 0)){
				w = o;
			}
		}
		
		if((w == null) && (p != null)){
			w = p;
		}
		else if((w == null) && (p == null)){}
		else if ((w != null) && (p != null)){
			if((w.compareTo(p) <= 0) && (w.legalMoves > 0) && (p.legalMoves > 0)){
				w = p;
			}
		}
		
		if((w == null) && (q != null)){
			w = q;
		}
		else if((w == null) && (q == null)){}
		else if ((w != null) && (q != null)){
			if((w.compareTo(q) <= 0) && (w.legalMoves > 0) && (q.legalMoves > 0)){
				w = q;
			}
		}
		
		if((w == null) && (r != null)){
			w = r;
		}
		else if((w == null) && (r == null)){}
		else if ((w != null) && (r != null)){
			if((w.compareTo(r) <= 0) && (w.legalMoves > 0) && (r.legalMoves > 0)){
				w = r;
			}
		}
		
		if((w == null) && (s != null)){
			w = s;
		}
		else if((w == null) && (s == null)){}
		else if ((w != null) && (s != null)){
			if((w.compareTo(s) <= 0) && (w.legalMoves > 0) && (s.legalMoves > 0)){
				w = s;
			}
		}
		
		if((w == null) && (t != null)){
			w = t;
		}
		else if((w == null) && (t == null)){}
		else if ((w != null) && (t != null)){
			if((w.compareTo(t) <= 0) && (w.legalMoves > 0) && (t.legalMoves > 0)){
				w = t;
			}
		}
		
		if((w == null) && (u != null)){
			w = u;
		}
		else if((w == null) && (u == null)){}
		else if ((w != null) && (u != null)){
			if((w.compareTo(u) <= 0) && (w.legalMoves > 0) && (u.legalMoves > 0)){
				w = u;
			}
		}
		
		if((w == null) && (v != null)){
			w = v;
		}
		else if((w == null) && (v == null)){}
		else if ((w != null) && (v != null)){
			if((w.compareTo(v) <= 0) && (w.legalMoves > 0) && (v.legalMoves > 0)){
				w = v;
			}
		}
		
	return w;	
	}
	
	int index = 0;
	public void performClickAction(TourButton b){
		index++;
		TourButton c = b;
		validMoves(c);
		String StringIndex = Integer.toString(index);
		if(index <= 64){
			c.setText(StringIndex);
		}
		System.out.println(c.x + ", " + c.y);
		c.accessability = 0;
		ActionListener[] list = c.getActionListeners();
		ActionListener l = list[0];
		c.removeActionListener(l);
		b = makeMove(c);
		if(b == null){
			if(index == 64){
				JOptionPane.showMessageDialog(null, "The Knight's Tour has successfully completed.", "Success!", JOptionPane.PLAIN_MESSAGE);
			}
		}
		else{
			performClickAction(b);
		}
	}
	
	private void performNewAction(){
		dispose();
		new KnightsTour();
	}
	
	private void performQuitAction(){
		int choice = JOptionPane.showConfirmDialog(this, "Are you sure you want to quit?", "Quit?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		if (choice == JOptionPane.YES_OPTION){
			System.exit(0);
		}
	}
	
	private void performAboutAction(){
		JOptionPane.showMessageDialog(null, "Knight's Tour Program Version 1.0.0\nAuthor: Eric Zebrowski\nStudent ID: 21675421\nCS 288-002", "About", JOptionPane.PLAIN_MESSAGE);
	}
	
	private void performInstructionsAction(){
		JOptionPane.showMessageDialog(null, "Click any square on the board to display the solution to the Knight's Tour problem.", "Instructions", JOptionPane.PLAIN_MESSAGE);
	}
	
	public TourButton[][] grid = new TourButton[8][8];
}