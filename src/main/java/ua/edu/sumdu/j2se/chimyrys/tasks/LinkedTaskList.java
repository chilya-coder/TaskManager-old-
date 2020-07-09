package ua.edu.sumdu.j2se.chimyrys.tasks;

public class LinkedTaskList {
    private Node firstNode;
    private Node lastNode;
    private class Node {
        private Task task;
        private Node nextNode;
        public Node(Task task) {
            this.task = task;
        }
    }
    public void add(Task task) {
        if (firstNode == null) {
            firstNode = new Node(task);
            lastNode = firstNode;
            return;
        }
        Node nextNode = new Node(task);
        lastNode.nextNode = nextNode;
        lastNode = nextNode;
    }
    public int size() {
        if (firstNode == null) {
            return 0;
        }
        if (firstNode == lastNode) {
            return 1;
        }
        int listSize = 1;
        Node currentNode = firstNode;
        while (currentNode.nextNode != null) {
            listSize++;
            currentNode = currentNode.nextNode;
        }
        return listSize;
    }
    public Task getTask(int index) {
        if (index >= size()) {
            throw new IndexOutOfBoundsException();
        }
        Node current = firstNode;
        for (int i = 0; i < size(); ++i) {
            if (i == index) {
                return current.task;
            }
            current = current.nextNode;
        }
        return null;
    }
    public boolean remove(Task task) {
        Node current = firstNode;
        if (current.task.equals(task)) {
            firstNode = current.nextNode;
            return true;
        }
        for (int i = 0; i < size(); ++i) {
            if (current.nextNode.task.equals(task)) {
                if (current.nextNode == lastNode) {
                    lastNode = current;
                    lastNode.nextNode = null;
                    return true;
                }
                current.nextNode = current.nextNode.nextNode;
                return true;
            }
            current = current.nextNode;
        }
        return false;
    }
    public LinkedTaskList incoming(int from, int to) {
        LinkedTaskList list = new LinkedTaskList();
        for (int i = 0; i < size(); ++i) {
            int taskNextTime = getTask(i).nextTimeAfter(from);
            if (taskNextTime <= to && taskNextTime != -1) {
                list.add(getTask(i));
            }
        }
        return list;
    }
}