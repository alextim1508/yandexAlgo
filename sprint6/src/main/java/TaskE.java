import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class TaskE {

    public static class InputData {
        public List<List<Integer>> adjList;

        public InputData(List<List<Integer>> adjList) {
            this.adjList = adjList;
        }
    }

    public static void main(String[] args) throws IOException {
        InputData in = read();

        int[] colors = new int[in.adjList.size()];
        Arrays.fill(colors, -1);

        int componentCount = 0;
        for (int i = 0; i < in.adjList.size(); i++) {
            if(colors[i] == -1) {
                depthFirstSearch(in.adjList, colors, i, componentCount);
                componentCount++;
            }
        }

        write(componentCount, colors);
    }

    private static void depthFirstSearch(List<List<Integer>> adjList, int[] colors, int vertex, int componentCount) {
        colors[vertex] = 1;
        adjList.get(vertex).forEach(i -> {
            if (colors[i] == -1)
                depthFirstSearch(adjList, colors, i, componentCount );
        });

        colors[vertex] = componentCount;
    }

    private static InputData read() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());

        int vertex = Integer.parseInt(tokenizer.nextToken());

        List<List<Integer>> adjList = new ArrayList<>(vertex);
        for (int i = 0; i < vertex; i++) {
            adjList.add(new ArrayList<>());
        }

        int edge = Integer.parseInt(tokenizer.nextToken());

        for (int i = 0; i < edge; i++) {
            tokenizer = new StringTokenizer(reader.readLine());
            int vertex1 = Integer.parseInt(tokenizer.nextToken())-1;
            int vertex2 = Integer.parseInt(tokenizer.nextToken())-1;
            adjList.get(vertex1).add(vertex2);
            adjList.get(vertex2).add(vertex1);
        }

        return new InputData(adjList);
    }

    private static void write(int componentCount, int[] colors) {
        StringBuilder out = new StringBuilder();

        out.append(componentCount).append("\n");

        for (int i = 0; i < componentCount; i++) {
            for (int j = 0; j < colors.length; j++) {
                if(colors[j] == i) {
                    out.append(j+1).append(" ");
                }
            }
            out.append(System.lineSeparator());
        }

        System.out.println(out);
    }
}
