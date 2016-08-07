package com.kasim.fsearch.view;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Stream;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class MySearchFrame extends JPanel  implements ListSelectionListener {
    
	private static final long serialVersionUID = 1L;
	private JList<String> list;
    private DefaultListModel<String> listModel;

    private static final String search = "Search";

    private JButton searchButton;

    private JTextField nameField;
    private JLabel status ;


    public MySearchFrame() {
        super(new BorderLayout());

        //Create and populate the list model.
        listModel = new DefaultListModel<String>();
        listModel.addElement("abc.txt");

        status = new JLabel("label");
        //Create the list and put it in a scroll pane.
        list = new JList<String>(listModel);
        list.setSelectionMode(
            ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        list.setSelectedIndex(0);
        list.addListSelectionListener(this);
        JScrollPane listScrollPane = new JScrollPane(list);

        //Create the list-modifying buttons.
        searchButton = new JButton(search);
        searchButton.setActionCommand(search);
        searchButton.addActionListener(new SearchButtonListener());


        //Create the text field for entering new names.
        nameField = new JTextField(15);
        nameField.addActionListener(new SearchButtonListener());
        String name = listModel.getElementAt(list.getSelectedIndex())
                               .toString();
        nameField.setText(name);

        //Create a panel that uses BoxLayout.
        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new BoxLayout(buttonPane,
                                           BoxLayout.LINE_AXIS));
        buttonPane.add(Box.createHorizontalStrut(5));
        buttonPane.add(nameField);
        buttonPane.add(searchButton);
        buttonPane.add(status);
        buttonPane.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
        

        //Put everything together.
        add(buttonPane,BorderLayout.PAGE_START);
        add(listScrollPane, BorderLayout.CENTER);
    }

    /** A listener shared by the text field and add button. */
    class SearchButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            int index = list.getSelectedIndex();
            int size = listModel.getSize();

            if (index == -1 || (index+1 == size)) {
                status.setText("file is searching...");
                list.setSelectedIndex(size);
                try {
                	long t1 = System.currentTimeMillis();
                	JobExecutor submitter = new JobExecutor();
                	submitter.submitJob(Paths.get("/Users/kasimsert/"));
                	submitter.join();
                	System.err.println("# of files count = " + submitter.getCount() + "in ms" +(System.currentTimeMillis() -t1));
                	for(Path p : submitter.getPaths()){
                		listModel.addElement(p.toString());
                	}
                	System.err.println("# size of array list= " + submitter.getPaths().size());
           	} catch (Exception e2) {
					System.err.println(e2);
				}

            //Otherwise insert the new one after the current selection,
            //and select new one.
            } else {
                listModel.insertElementAt(nameField.getText(), index+1);
                list.setSelectedIndex(index+1);
            }
        }

    }

    /** 
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */
    private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("File Searcher");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Create and set up the content pane.
        JComponent newContentPane = new MySearchFrame();
        newContentPane.setOpaque(true); //content panes must be opaque
        frame.setContentPane(newContentPane);
        
        //Don't let the content pane get too small.
        //(Works if the Java look and feel provides
        //the window decorations.)
        newContentPane.setMinimumSize(
                new Dimension(
                        newContentPane.getPreferredSize().width,
                        100));

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }

	@Override
	public void valueChanged(ListSelectionEvent e) {
	}
}

class FileSearcher{
	AtomicInteger count= new AtomicInteger();
	JobExecutor job = null;
	List<Path> paths = Collections.synchronizedList(new ArrayList<>());
	
	public FileSearcher(JobExecutor job) throws IOException, URISyntaxException {
		this.job = job;
	}
	public int  searchDirectory(Path initialPath) throws IOException, URISyntaxException{
  		try(Stream<Path> s = Files.list(initialPath);){
  			s.forEach((i)-> {
  				if (Files.isDirectory(i, LinkOption.NOFOLLOW_LINKS)){
  					try {
						job.submitJob(i);
					} catch (Exception e) {
						e.printStackTrace();
					}
  				}	
  				else
  					if(i.getFileName().toString().matches("(?i:.*txt)")){
  						count.incrementAndGet();
  						paths.add(i);
  					}
  				
  				});
  		}
  		return 0 ;
  		
	}
	public int getCount(){
		return count.get();
	}
	public List<Path> getPaths(){
		return paths;
	}
}

class JobExecutor{
	 ThreadFactory tf = new MyThreadFactory();
	 ExecutorService executor = Executors.newFixedThreadPool(3,tf);
	 FileSearcher searcher;
	 public JobExecutor(){
		 try {
			searcher = new FileSearcher(this);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
	 }

	public Future<Integer> submitJob(final Path i) {
		return executor.submit(new Callable<Integer>() {
			@Override
			public Integer call() throws Exception {
				return searcher.searchDirectory(i);
			}
		});
		
	
//		executor.execute(new Runnable() {
//			
//			@Override
//			public void run() {
//				try {
//					FileSearcher.searchDirectory(i.toUri().toString(), "");
//				} catch (Exception e) {
//					System.err.println(e);
//				}
//				
//			}
//		});
		
	}
	public int getCount(){
		return searcher.getCount();
	}
	public List<Path> getPaths(){
		return searcher.getPaths();
	}
	public void  join(){
		try {
			ThreadPoolExecutor tpe = (ThreadPoolExecutor)executor;
			while(tpe.getCompletedTaskCount()!= tpe.getTaskCount()){
				System.err.println("count="+tpe.getTaskCount()+","+tpe.getCompletedTaskCount());
				Thread.sleep(500);
			}
			System.err.println("count="+tpe.getTaskCount()+","+tpe.getCompletedTaskCount());
			executor.shutdown();
			executor.awaitTermination(20, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}

