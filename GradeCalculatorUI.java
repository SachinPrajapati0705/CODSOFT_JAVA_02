package CODSOFT;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GradeCalculatorUI {
    public static void main(String[] args) {
        // Create UI components
        JFrame frame = new JFrame("Grade Calculator");
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        JLabel title = new JLabel("Subject-wise Marks (out of 100)");
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panel.add(title, gbc);

        JLabel[] subjectLabels = new JLabel[5];
        JTextField[] subjectFields = new JTextField[5];
        for (int i = 0; i < 5; i++) {
            subjectLabels[i] = new JLabel("Subject " + (i + 1) + ":");
            gbc.gridx = 0;
            gbc.gridy = i + 1;
            gbc.gridwidth = 1;
            panel.add(subjectLabels[i], gbc);

            subjectFields[i] = new JTextField(5);
            gbc.gridx = 1;
            gbc.gridy = i + 1;
            panel.add(subjectFields[i], gbc);
        }

        JLabel totalMarksLabel = new JLabel("Total Marks:");
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 1;
        panel.add(totalMarksLabel, gbc);

        JTextField totalMarksField = new JTextField(10);
        totalMarksField.setEditable(false);
        gbc.gridx = 1;
        gbc.gridy = 6;
        panel.add(totalMarksField, gbc);

        JLabel avgPercentageLabel = new JLabel("Average Percentage:");
        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.gridwidth = 1;
        panel.add(avgPercentageLabel, gbc);

        JTextField avgPercentageField = new JTextField(10);
        avgPercentageField.setEditable(false);
        gbc.gridx = 1;
        gbc.gridy = 7;
        panel.add(avgPercentageField, gbc);

        JLabel gradeLabel = new JLabel("Grade:");
        gbc.gridx = 0;
        gbc.gridy = 8;
        gbc.gridwidth = 1;
        panel.add(gradeLabel, gbc);

        JTextField gradeField = new JTextField(5);
        gradeField.setEditable(false);
        gbc.gridx = 1;
        gbc.gridy = 8;
        panel.add(gradeField, gbc);

        JButton calculateButton = new JButton("Calculate");
        gbc.gridx = 0;
        gbc.gridy = 9;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(calculateButton, gbc);

        // Add panel to frame
        frame.add(panel);

        // Set frame properties
        frame.pack();
        frame.setLocationRelativeTo(null); // Center the frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        // Add action listener to calculate button
        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int totalMarks = 0;
                int numSubjects = 0;

                // Calculate total marks and count number of subjects
                for (JTextField field : subjectFields) {
                    try {
                        int marks = Integer.parseInt(field.getText());
                        if (marks < 0 || marks > 100) {
                            JOptionPane.showMessageDialog(frame, "Marks should be between 0 and 100", "Error", JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                        totalMarks += marks;
                        numSubjects++;
                    } catch (NumberFormatException ex) {
                        // Ignore invalid input
                    }
                }

                if (numSubjects == 0) {
                    JOptionPane.showMessageDialog(frame, "Please enter marks for at least one subject", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Calculate average percentage
                double avgPercentage = (double) totalMarks / (numSubjects * 100) * 100;

                // Determine grade
                String grade;
                if (avgPercentage >= 90) {
                    grade = "A";
                } else if (avgPercentage >= 80) {
                    grade = "B";
                } else if (avgPercentage >= 70) {
                    grade = "C";
                } else if (avgPercentage >= 60) {
                    grade = "D";
                } else {
                    grade = "F";
                }

                // Display results
                totalMarksField.setText(String.valueOf(totalMarks));
                avgPercentageField.setText(String.format("%.2f", avgPercentage));
                gradeField.setText(grade);
            }
        });
    }
}
