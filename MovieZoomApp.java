package Project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class MovieZoomApp {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MovieCollectionManager manager = new MovieCollectionManager();
            manager.createAndShowGUI();
        });
    }
}



class MovieCollectionManager {
    private List<Movie> movieCollection;
    private JFrame mainFrame;
    private JList<Movie> movieList;
    private DefaultListModel<Movie> listModel;
    private JTextField titleField;
    private JTextField directorField;
    private JTextField yearField;
    private JTextArea commentArea;
    private JLabel messageLabel;
    private JButton submitButton;
    private JButton filterButton;
    private JComboBox<String> categoryFilter;
    private JComboBox<Integer> yearFilter;

   
    
    
    public void createAndShowGUI() {
       
    	movieCollection = new ArrayList<>();
        
    	mainFrame = new JFrame("MovieZoom - Movie Collection Manager");
        
        listModel = new DefaultListModel<>();
       
        movieList = new JList<>(listModel);

        
        
        JButton addButton = new JButton("Add Movie");
        JButton editButton = new JButton("Edit Movie");
        JButton deleteButton = new JButton("Delete Movie");
       
        JComboBox<String> categoryComboBox = new JComboBox<>(new String[]{"Action", "Comedy", "Family"});
       
        titleField = new JTextField(15);
        directorField = new JTextField(15);
        yearField = new JTextField(6);
        commentArea = new JTextArea(5, 15);
        submitButton = new JButton("Submit");
        filterButton = new JButton("Filter Movies");
        messageLabel = new JLabel();
        categoryFilter = new JComboBox<>(new String[]{"All", "Action", "Comedy", "Family"});
        yearFilter = new JComboBox<>();

       
        
        
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addMovie(titleField.getText(), directorField.getText(), Integer.parseInt(yearField.getText()),
                        categoryComboBox.getSelectedItem().toString(), commentArea.getText());
                showMessage("Movie added.");
                clearInputFields();
                populateYearFilter();
                displayMovies();
            }
        });

       
        editButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = movieList.getSelectedIndex();
                if (selectedIndex != -1) {
                    Movie selectedMovie = listModel.getElementAt(selectedIndex);
                    updateInputFields(selectedMovie);
                }
            }
        });

       
        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                deleteSelectedMovie();
                showMessage("Movie deleted.");
                clearInputFields();
                populateYearFilter();
                displayMovies();
            }
        });

        
        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                updateMovie();
                showMessage("Movie updated.");
                clearInputFields();
                populateYearFilter();
                displayMovies();
            }
        });

        
        filterButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                filterMovies();
            }
        });

        
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        leftPanel.add(new JLabel("Title:"));
        leftPanel.add(titleField);
        leftPanel.add(new JLabel("Director:"));
        leftPanel.add(directorField);
        leftPanel.add(new JLabel("Year:"));
        leftPanel.add(yearField);
        leftPanel.add(new JLabel("Category:"));
        leftPanel.add(categoryComboBox);
        leftPanel.add(new JLabel("Comment:"));
        leftPanel.add(commentArea);
        leftPanel.add(addButton);
        leftPanel.add(editButton);
        leftPanel.add(deleteButton);
        leftPanel.add(submitButton);
        leftPanel.add(filterButton);

       
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BorderLayout());
        JLabel movieInfoLabel = new JLabel("Movie Information");
        rightPanel.add(movieInfoLabel, BorderLayout.NORTH);
        rightPanel.add(new JScrollPane(movieList), BorderLayout.CENTER);
        rightPanel.add(categoryFilter, BorderLayout.SOUTH);

        
        mainFrame.setLayout(new GridLayout(1, 2));
        mainFrame.add(leftPanel);
        mainFrame.add(rightPanel);

       
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(800, 400);
        mainFrame.setVisible(true);

        
        loadMovieCollection();
        populateYearFilter();
        displayMovies();
    }

   
    private void showMessage(String message) {
        JOptionPane.showMessageDialog(mainFrame, message, "Movie Collection", JOptionPane.INFORMATION_MESSAGE);
    }

   
    private void addMovie(String title, String director, int year, String category, String comment) {
        Movie newMovie = new Movie(title, director, year, category, comment);
        movieCollection.add(newMovie);
        listModel.addElement(newMovie);
        saveMovieCollection();
    }

   
    private void deleteSelectedMovie() {
        int selectedIndex = movieList.getSelectedIndex();
        if (selectedIndex != -1) {
            listModel.remove(selectedIndex);
            movieCollection.remove(selectedIndex);
            saveMovieCollection();
        }
    }

   
    private void updateInputFields(Movie selectedMovie) {
        titleField.setText(selectedMovie.getTitle());
        directorField.setText(selectedMovie.getDirector());
        yearField.setText(Integer.toString(selectedMovie.getYear()));
        commentArea.setText(selectedMovie.getComment());
        submitButton.setEnabled(true);
    }

   
    private void updateMovie() {
        int selectedIndex = movieList.getSelectedIndex();
        if (selectedIndex != -1) {
            Movie selectedMovie = listModel.getElementAt(selectedIndex);
            selectedMovie.setTitle(titleField.getText());
            selectedMovie.setDirector(directorField.getText());
            selectedMovie.setYear(Integer.parseInt(yearField.getText()));
            selectedMovie.setComment(commentArea.getText());
            listModel.setElementAt(selectedMovie, selectedIndex);
            saveMovieCollection();
            submitButton.setEnabled(false);
        }
    }

    private void loadMovieCollection() {
        try {
            FileInputStream fileIn = new FileInputStream("movieCollection.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            movieCollection = (List<Movie>) in.readObject();
            in.close();
            fileIn.close();
            listModel.clear();
            for (Movie movie : movieCollection) {
                listModel.addElement(movie);
            }
        } catch (IOException | ClassNotFoundException e) {
            // Handle exceptions (e.g., file not found)
        }
    }

   
    private void saveMovieCollection() {
        try {
            FileOutputStream fileOut = new FileOutputStream("movieCollection.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(movieCollection);
            out.close();
            fileOut.close();
        } catch (IOException e) {
            // Handle exceptions (e.g., file write error)
        }
    }

   
    private void populateYearFilter() {
        yearFilter.removeAllItems();
        yearFilter.addItem(0); // Use 0 to represent "All" years

        for (Movie movie : movieCollection) {
            int year = movie.getYear();
            if (!yearFilter.getItemAt(0).equals(year)) {
                yearFilter.addItem(year);
            }
        }
    }

   
    private void filterMovies() {
        String selectedCategory = categoryFilter.getSelectedItem().toString();
        Integer selectedYear = (Integer) yearFilter.getSelectedItem();
        
        listModel.clear();
        
        for (Movie movie : movieCollection) {
            if ((selectedCategory.equals("All") || movie.getCategory().equals(selectedCategory)) &&
                (selectedYear == 0 || movie.getYear() == selectedYear)) {
                listModel.addElement(movie);
            }
        }
    }

   
    private void displayMovies() {
        listModel.clear();
        for (Movie movie : movieCollection) {
            listModel.addElement(movie);
        }
    }

   
    private void clearInputFields() {
        titleField.setText("");
        directorField.setText("");
        yearField.setText("");
        commentArea.setText("");
    }
    
    
  
    
  
}
