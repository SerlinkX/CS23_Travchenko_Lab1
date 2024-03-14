import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.*;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class ClassInfoViewer extends JFrame {
    private JTextArea textArea;

    public ClassInfoViewer() {
        setTitle("Class Info Viewer");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        textArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(textArea);
        add(scrollPane, BorderLayout.CENTER);

        JButton getInfoButton = new JButton("Get Class Info");
        getInfoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showClassInfo();
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(getInfoButton);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void showClassInfo() {
        String className = "java.lang.String"; // Replace with the desired class name
        try {
            Class<?> clazz = Class.forName(className);
            String classInfo = getClassInfo(clazz);
            textArea.setText(classInfo);
        } catch (ClassNotFoundException e) {
            textArea.setText("Class not found: " + className);
        }
    }

    private String getClassInfo(Class<?> clazz) {
        StringBuilder info = new StringBuilder();
        info.append("Package: ").append(clazz.getPackage()).append("\n");
        info.append("Modifiers: ").append(Modifier.toString(clazz.getModifiers())).append("\n");
        info.append("Class Name: ").append(clazz.getSimpleName()).append("\n");
        info.append("Superclass: ").append(clazz.getSuperclass()).append("\n");

        Class<?>[] interfaces = clazz.getInterfaces();
        if (interfaces.length > 0) {
            info.append("Implemented Interfaces: ");
            for (Class<?> iface : interfaces) {
                info.append(iface.getName()).append(", ");
            }
            info.delete(info.length() - 2, info.length()); // Remove the trailing comma and space
            info.append("\n");
        }

        info.append("\nFields:\n");
        for (Field field : clazz.getDeclaredFields()) {
            info.append(Modifier.toString(field.getModifiers())).append(" ")
                    .append(field.getType().getSimpleName()).append(" ")
                    .append(field.getName()).append("\n");
        }

        info.append("\nConstructors:\n");
        for (Constructor<?> constructor : clazz.getDeclaredConstructors()) {
            info.append(Modifier.toString(constructor.getModifiers())).append(" ")
                    .append(constructor.getName()).append("\n");
        }

        info.append("\nMethods:\n");
        for (Method method : clazz.getDeclaredMethods()) {
            info.append(Modifier.toString(method.getModifiers())).append(" ")
                    .append(method.getReturnType().getSimpleName()).append(" ")
                    .append(method.getName()).append("\n");
        }

        return info.toString();
    }

    public static void main(String[] args) {
        ClassInfoViewer viewer = new ClassInfoViewer();
        viewer.setVisible(true);
    }
}