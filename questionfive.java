import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class questionfive extends JFrame {
    private JPanel canvas;
    private java.util.List<Node> nodes;
    private java.util.List<Edge> edges;
    private Node selectedNode;
    private JLabel costLabel, latencyLabel;

    public questionfive() {
        setTitle("Network Optimizer");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        nodes = new ArrayList<>();
        edges = new ArrayList<>();

        // Canvas for drawing the graph
        canvas = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                // Draw nodes
                for (Node node : nodes) {
                    g2d.setColor(Color.BLUE);
                    g2d.fillOval(node.x - 10, node.y - 10, 20, 20);
                    g2d.setColor(Color.BLACK);
                    g2d.drawString(node.id, node.x - 10, node.y - 15);
                }
                // Draw edges
                for (Edge edge : edges) {
                    g2d.setColor(Color.BLACK);
                    g2d.drawLine(edge.source.x, edge.source.y, edge.target.x, edge.target.y);
                    g2d.drawString("C: " + edge.cost + ", BW: " + edge.bandwidth,
                            (edge.source.x + edge.target.x) / 2,
                            (edge.source.y + edge.target.y) / 2);
                }
            }
        };
        canvas.setBackground(Color.WHITE);
        

        canvas.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                for (Node node : nodes) {
                    if (Math.abs(e.getX() - node.x) < 10 && Math.abs(e.getY() - node.y) < 10) {
                        if (selectedNode == null) {
                            // Select the node
                            selectedNode = node;
                            System.out.println("Node selected: " + selectedNode.id);
                        } else {
                            // Add edge between selectedNode and the clicked node
                            String cost = JOptionPane.showInputDialog("Enter cost:");
                            String bandwidth = JOptionPane.showInputDialog("Enter bandwidth:");
                            if (cost != null && bandwidth != null) {
                                edges.add(new Edge(selectedNode, node, Integer.parseInt(cost), Integer.parseInt(bandwidth)));
                                System.out.println("Edge added: " + selectedNode.id + " -> " + node.id +
                                        " (Cost: " + cost + ", BW: " + bandwidth + ")");
                                updateMetrics();
                                selectedNode = null; // Reset selectedNode after adding an edge
                            }
                        }
                        canvas.repaint();
                        return;
                    }
                }
                // If no node is clicked, add a new node
                Node newNode = new Node(e.getX(), e.getY(), "Node" + (nodes.size() + 1));
                nodes.add(newNode);
                System.out.println("Node added: " + newNode.id + " at (" + newNode.x + ", " + newNode.y + ")");
                canvas.repaint();
            }
        });
        // Button for optimization
        JButton optimizeButton = new JButton("Optimize");
        optimizeButton.addActionListener(e -> {
            java.util.List<Edge> mst = kruskalAlgorithm();
            edges = mst;
            canvas.repaint();
            updateMetrics();
        });

        // Labels for real-time metrics
        costLabel = new JLabel("Total Cost: 0");
        latencyLabel = new JLabel("Total Latency: 0");

        // Panel for buttons and labels
        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new FlowLayout());
        controlPanel.add(optimizeButton);
        controlPanel.add(costLabel);
        controlPanel.add(latencyLabel);

        // Add components to the frame
        add(canvas, BorderLayout.CENTER);
        add(controlPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    private void updateMetrics() {
        int totalCost = 0;
        int totalLatency = 0;
        for (Edge edge : edges) {
            totalCost += edge.cost;
            totalLatency += 1; // Assuming latency is proportional to the number of edges
        }
        costLabel.setText("Total Cost: " + totalCost);
        latencyLabel.setText("Total Latency: " + totalLatency);
    }

    private java.util.List<Edge> kruskalAlgorithm() {
        // Placeholder for Kruskal's Algorithm
        // For now, return the current edges
        return new ArrayList<>(edges);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new questionfive().setVisible(true);
        });
    }

    static class Node {
        int x, y;
        String id;

        Node(int x, int y, String id) {
            this.x = x;
            this.y = y;
            this.id = id;
        }
    }

    static class Edge {
        Node source, target;
        int cost, bandwidth;

        Edge(Node source, Node target, int cost, int bandwidth) {
            this.source = source;
            this.target = target;
            this.cost = cost;
            this.bandwidth = bandwidth;
        }
    }
}