
**Project Name:** MovieZoomApp

**Description:**
The MovieZoomApp is a Java-based desktop application designed to manage a personal movie collection. This project leverages the Swing framework for the graphical user interface, enabling users to add, edit, delete, and filter movies in their collection.

**Technical Details:**

1. **GUI Implementation:**
   - **Swing Framework:** Utilized to create the user interface, providing various components such as `JFrame`, `JList`, `JTextField`, `JTextArea`, `JComboBox`, and `JButton`.
   - **Panels and Layouts:** Used `JPanel`, `BoxLayout`, and `BorderLayout` to structure the interface into left and right panels for input fields and movie list display, respectively.

2. **Functionalities:**
   - **Add Movie:** Allows users to add a new movie by entering details like title, director, year, category, and comments.
   - **Edit Movie:** Enables users to update the details of a selected movie from the list.
   - **Delete Movie:** Removes the selected movie from the collection.
   - **Filter Movies:** Provides filtering options by category and year using combo boxes.
   - **Display Movies:** Shows all movies or filtered movies in a scrollable list.

3. **Data Management:**
   - **Serialization:** Utilized Java serialization to save and load the movie collection from a file (`movieCollection.ser`), ensuring persistence of data between sessions.
   - **Model Classes:** Defined a `Movie` class with properties (title, director, year, category, comment) and methods to get and set these properties. Implemented the `Serializable` interface for file operations.

4. **Event Handling:**
   - Implemented action listeners for buttons (add, edit, delete, submit, filter) to handle user interactions and perform corresponding operations on the movie collection.
   - **Message Display:** Used `JOptionPane` to show messages to the user for operations like adding, updating, or deleting a movie.

5. **User Interaction:**
   - **Input Fields:** Provided text fields for movie details and a text area for comments, enhancing user input experience.
   - **Combo Boxes:** Included for category selection and filtering options, improving usability for categorizing and filtering movies.

**Frontend:**
- **Swing-based GUI:** Developed a user-friendly interface that facilitates easy interaction with the movie collection management functionalities.

This project effectively combines a robust GUI with comprehensive movie management features, offering a seamless experience for users to manage and interact with their personal movie collections.
