import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class TaskH {

    public static class InputData {
        public List<Set<Integer>> adjList;

        public InputData(List<Set<Integer>> adjList) {
            this.adjList = adjList;
        }
    }

    public static void main(String[] args) throws IOException {
        InputData in = read();

        int[] colors = new int[in.adjList.size()];
        int[] entry = new int[in.adjList.size()];
        int[] leave = new int[in.adjList.size()];

        depthFirstSearch(in.adjList, 0, colors, entry, leave);

        write(entry, leave);
    }

    private static int time = 0;
    private static void depthFirstSearch(List<Set<Integer>> adjList, int vertex, int[] colors, int[] entry, int[] leave) {
        entry[vertex] = ++time;
        colors[vertex] = 1;

        adjList.get(vertex).forEach(i -> {
            if(colors[i] == 0)
                depthFirstSearch(adjList, i, colors, entry, leave);
        });
        colors[vertex] = 2;
        leave[vertex] = ++time;
    }

    private static InputData read() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());

        int vertex = Integer.parseInt(tokenizer.nextToken());

        List<Set<Integer>> adjList = new ArrayList<>(vertex);
        for (int i = 0; i < vertex; i++) {
            adjList.add(new TreeSet<>(Integer::compare));
        }

        int edge = Integer.parseInt(tokenizer.nextToken());

        for (int i = 0; i < edge; i++) {
            tokenizer = new StringTokenizer(reader.readLine());
            int vertex1 = Integer.parseInt(tokenizer.nextToken()) - 1;
            int vertex2 = Integer.parseInt(tokenizer.nextToken()) - 1;
            adjList.get(vertex1).add(vertex2);
        }

        return new InputData(adjList);
    }

    private static void write(int[] entry, int[] leave) {
        StringBuilder out = new StringBuilder();
        for (int i = 0; i < leave.length; i++)
            out.append(entry[i]-1).append(" ").append(leave[i]-1).append("\n");

        System.out.println(out);
    }
}
