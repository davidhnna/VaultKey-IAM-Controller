import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

// User class to store user information
class User {
    String username;
    String role;
    
    User(String username, String role) {
        this.username = username;
        this.role = role;
    }
}

public class IAMapp extends JFrame {

    private JTextField usernameField, roleField;
    private JTextArea auditLogArea;
    private ArrayList<User> userDatabase;
    private String currentUser = null; // Tracks who is logged in

    public IAMapp() {
        userDatabase = new ArrayList<>();
        // Pre-load a default Admin
        userDatabase.add(new User("admin", "admin"));

        setTitle("CyberVault IAM - Security Controller");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        getContentPane().setBackground(new Color(30, 30, 30));

        // --- HEADER ---
        JPanel header = new JPanel(new FlowLayout(FlowLayout.LEFT));
        header.setBackground(new Color(40, 40, 40));
        JLabel titleLabel = new JLabel("SECURE IDENTITY MANAGER");
        titleLabel.setForeground(new Color(0, 255, 127)); // Spring Green
        titleLabel.setFont(new Font("Monospaced", Font.BOLD, 22));
        header.add(titleLabel);
        add(header, BorderLayout.NORTH);

        // --- INPUT PANEL ---
        JPanel inputPanel = new JPanel(new GridBagLayout());
        inputPanel.setBackground(new Color(45, 45, 45));
        inputPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        usernameField = new JTextField(15);
        roleField = new JTextField(15);

        addLabelAndField(inputPanel, "USERNAME:", usernameField, 0, gbc);
        addLabelAndField(inputPanel, "ROLE (Admin/User):", roleField, 1, gbc);

        // --- BUTTONS ---
        JPanel btnPanel = new JPanel(new GridLayout(1, 3, 10, 0));
        btnPanel.setOpaque(false);

        JButton loginBtn = new JButton("LOGIN");
        JButton addBtn = new JButton("PROVISION USER");
        JButton accessBtn = new JButton("ACCESS RESOURCE");

        styleButton(loginBtn, new Color(100, 149, 237));
        styleButton(addBtn, new Color(46, 204, 113));
        styleButton(accessBtn, new Color(231, 76, 60));

        btnPanel.add(loginBtn);
        btnPanel.add(addBtn);
        btnPanel.add(accessBtn);

        gbc.gridx = 0; gbc.gridy = 2; gbc.gridwidth = 2;
        inputPanel.add(btnPanel, gbc);
        add(inputPanel, BorderLayout.CENTER);

        // --- AUDIT LOG (The Security Feature) ---
        auditLogArea = new JTextArea();
        auditLogArea.setEditable(false);
        auditLogArea.setBackground(Color.BLACK);
        auditLogArea.setForeground(new Color(0, 255, 0));
        auditLogArea.setFont(new Font("Consolas", Font.PLAIN, 12));

        JScrollPane scroll = new JScrollPane(auditLogArea);
        scroll.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.GRAY), "SECURITY AUDIT LOG", 0, 0, null, Color.WHITE));
        scroll.setPreferredSize(new Dimension(800, 200));
        add(scroll, BorderLayout.SOUTH);

        // --- ACTION LISTENERS ---

        loginBtn.addActionListener(e -> {
            String user = usernameField.getText().trim();
            User foundUser = findUser(user);
            if (foundUser != null) {
                currentUser = user;
                logAction("SUCCESS: User '" + user + "' authenticated and session started.");
                JOptionPane.showMessageDialog(this, "Logged in as: " + user);
            } else {
                logAction("FAILURE: Authentication attempt failed for user '" + user + "'.");
                JOptionPane.showMessageDialog(this, "Access Denied: Invalid User.");
            }
        });

        addBtn.addActionListener(e -> {
            if (!checkAdminPrivilege()) return;

            String newUser = usernameField.getText().trim();
            String newRole = roleField.getText().trim().toLowerCase();
            if (!newUser.isEmpty()) {
                // Check if user already exists
                if (findUser(newUser) != null) {
                    logAction("WARNING: Attempted to create duplicate user '" + newUser + "'.");
                    JOptionPane.showMessageDialog(this, "User already exists.");
                    return;
                }
                userDatabase.add(new User(newUser, newRole));
                logAction("PROVISION: Admin created new identity '" + newUser + "' with role '" + newRole + "'.");
                JOptionPane.showMessageDialog(this, "User '" + newUser + "' created successfully.");
            }
        });

        accessBtn.addActionListener(e -> {
            if (currentUser == null) {
                logAction("WARNING: Unauthenticated resource access attempt.");
                JOptionPane.showMessageDialog(this, "Please login first.");
                return;
            }
            User user = findUser(currentUser);
            if (user != null && "admin".equals(user.role)) {
                logAction("ACCESS GRANTED: User '" + currentUser + "' accessed Root Directory.");
                JOptionPane.showMessageDialog(this, "Access Granted: Root System Files.");
            } else {
                logAction("ACCESS DENIED: User '" + currentUser + "' attempted to access Root Directory.");
                JOptionPane.showMessageDialog(this, "Forbidden: Standard Users cannot access Root.");
            }
        });

        setVisible(true);
    }

    // Helper method to find a user in the ArrayList
    private User findUser(String username) {
        for (User u : userDatabase) {
            if (u.username.equals(username)) {
                return u;
            }
        }
        return null;
    }

    private boolean checkAdminPrivilege() {
        if (currentUser == null) {
            logAction("SECURITY ALERT: Privilege Escalation attempt by Anonymous user.");
            JOptionPane.showMessageDialog(this, "Unauthorized: Admin privileges required.");
            return false;
        }
        
        User user = findUser(currentUser);
        if (user == null || !"admin".equals(user.role)) {
            logAction("SECURITY ALERT: Privilege Escalation attempt by '" + currentUser + "'.");
            JOptionPane.showMessageDialog(this, "Unauthorized: Admin privileges required.");
            return false;
        }
        return true;
    }

    private void logAction(String message) {
        String time = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        auditLogArea.append("[" + time + "] " + message + "\n");
    }

    private void addLabelAndField(JPanel p, String label, JTextField tf, int y, GridBagConstraints gbc) {
        JLabel l = new JLabel(label);
        l.setForeground(Color.WHITE);
        gbc.gridx = 0; gbc.gridy = y; gbc.gridwidth = 1;
        p.add(l, gbc);
        gbc.gridx = 1; p.add(tf, gbc);
    }

    private void styleButton(JButton b, Color c) {
        b.setBackground(c);
        b.setForeground(Color.WHITE);
        b.setFocusPainted(false);
        b.setFont(new Font("SansSerif", Font.BOLD, 12));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(IAMapp::new);
    }
}
